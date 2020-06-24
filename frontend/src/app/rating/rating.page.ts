/**
 * @description
 *   This page handles the basic operation of navigation, close menu and validating of the rating pages.
 *
 */

import { Component, OnDestroy, OnInit } from '@angular/core';
import { Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { NavController } from '@ionic/angular';
import { TranslateService } from '@ngx-translate/core';
import { Subscription } from 'rxjs';

import { FormControl, FormGroup, FormValue } from '../common/forms/forms';
import { AlertController } from '../common/ion-wrappers/alert-controller';
import { ToastController } from '../common/ion-wrappers/toast-controller';
import { FormsData } from '../model/forms-data.model';
import { StorageHandlerService } from '../services/storage-handler.service';

import {
  ApplicantScore,
  ImpressionInfo,
  RatingForm,
} from './model/rating-form.model';
import { hrRatingStep, hrRatingStepArr } from './model/steps.model';

@Component({
  selector: 'app-rating',
  templateUrl: './rating.page.html',
  styleUrls: ['./rating.page.scss'],
})
export class RatingPage implements OnDestroy, OnInit {
  constructor(
    private navController: NavController,
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private alertController: AlertController,
    private toastController: ToastController,
    private storage: StorageHandlerService,
    private translate: TranslateService
  ) {}

  /**
   * Make the Steps available in the template.
   *
   * This property is really only used to make the Steps available in the template.
   * In the TS file, you can directly refer to the underlying element.
   */
  readonly HR_RATING_STEP = hrRatingStep;

  /**
   * Make the Steps Array available in the template.
   *
   * This property is really only used to make the Steps available in the template.
   * In the TS file, you can directly refer to the underlying element.
   */
  readonly HR_RATING_STEP_ARR = hrRatingStepArr;

  /**
   * This property holds the type safe form group fields for applicant-score view.
   */
  ratingForm = new FormGroup<RatingForm>({
    id: new FormControl(this.router.getCurrentNavigation().extras.state.id),
    applicantScore: new FormGroup<ApplicantScore>({
      rhetoric: new FormControl(undefined, Validators.required),
      motivation: new FormControl(undefined, Validators.required),
      selfAssurance: new FormControl(undefined, Validators.required),
      personalImpression: new FormControl(undefined, Validators.required),
    }),
    impressionInfo: new FormGroup<ImpressionInfo>({
      impression: new FormControl(undefined, Validators.required),
    }),
  });

  /**
   * Which step is currently displayed?
   *
   * **Important! Do not modify this value directly! Use `setCurrentStep()` instead!**
   */
  currentStep: hrRatingStep;
  /**
   * Which step is currently displayed?
   * This is the index in our array of steps.
   *
   * **Important! Do not modify this value directly! Use `setCurrentStep()` instead!**
   */
  currentStepIndex: number;

  /**
   * The current progress (between 0 and 1).
   */
  progressPercentage = 0;

  /**
   * Holds all the subscription which will be useful for un subscribing on destroy.
   */
  private subscriptions: Subscription[] = [];

  /**
   * In this method route change is observed and handling is done.
   */
  ngOnInit(): void {
    this.ratingForm.controls.id.disable();
    this.storage
      .getItem<RatingForm>(
        this.storage.applicantRatingsDb,
        this.ratingForm.controls.id.value
      )
      .then((applicantData) => {
        if (applicantData) {
          /*
            Usually, you can just use patchValue in order to set the value of the form.
            However, the ion-range element has issues with null values (they become NaN).
            Because of this, we need to "repair" the values first.
          */
          const filtered = this.removeNullValuesRec(applicantData);
          this.ratingForm.patchValue(filtered);
        }
      });
    const routerSubscription = this.activatedRoute.queryParams.subscribe(
      (params) => {
        /*
          This Observable will also fire when the page is loaded.
          So, it allows us not only to react to changes of the query parameter,
          but also to set the initial query parameter.
       */
        this.handleStep(params.step);
      }
    );
    this.subscriptions.push(routerSubscription);

    const formStatusChangesSubscriptions = this.ratingForm.statusChanges.subscribe(
      () => {
        /*
          This Observable fires every time the validity status recalculates.
          So, it will basically fire whenever someone enters some data somewhere.
          (To be more precise: It might fire even more often than that.)
          So, we can use it to update our progress bar.
          Of course, there is still room for performance improvement (since we will call the following method really often).
          But for now, it should be fine.
        */
        this.storage.updateItem<FormValue<RatingForm>>(
          this.storage.applicantRatingsDb,
          this.ratingForm.controls.id.value,
          this.ratingForm.value
        );
        this.updateProgessStatus();
      }
    );
    this.subscriptions.push(formStatusChangesSubscriptions);
  }

  /**
   * Unsubscribe from all of our Subscriptions.
   */
  ngOnDestroy(): void {
    for (const subscription of this.subscriptions) {
      subscription.unsubscribe();
    }
  }

  /**
   * This helper function removes null/undefined entries from a given object or array.
   * This happens recursively.
   */
  // tslint:disable-next-line: no-any
  private removeNullValuesRec(input: any): any {
    // This is equal to `input === undefined || input === null`.
    if (input == undefined) {
      return undefined;
    }

    if (Array.isArray(input)) {
      const newArray = [];
      for (const item of input) {
        const filtered = this.removeNullValuesRec(item);
        if (filtered == undefined) {
          continue;
        }
        newArray.push(filtered);
      }

      return newArray;
    }

    if (typeof input === 'object') {
      // tslint:disable-next-line: no-any
      const newObject: any = {};
      for (const [key, value] of Object.entries(input)) {
        const filtered = this.removeNullValuesRec(value);
        if (filtered == undefined) {
          continue;
        }
        newObject[key] = filtered;
      }

      return newObject;
    }

    return input;
  }

  /**
   * Handle a Step as provided by the Router.
   *
   * The string provided by the Router could contain anything.
   * So: If the Step is not known, we simply show the Applicant rating step.
   *
   * (When navigating to the URL without providing a query parameter, we will also redirect to the Applicant rating step).
   *
   * @param step The "step" query parameter we got from the Router.
   */
  private handleStep(step: unknown): void {
    // tslint:disable-next-line: no-any
    if (hrRatingStepArr.includes(step as any)) {
      this.setCurrentStep(step as hrRatingStep);
      return;
    }

    if (step) {
      console.warn('The following Step is unknown:', step);
    }

    // A fallback: If a step was requested that we don't know, we simply show the Applicant rating page.
    this.navigateToStep(hrRatingStep.ApplicantRating);
  }

  /**
   * Set the currentStep and currentStepIndex property.
   *
   * Usually, if you want to change the Step, you should use the Router.
   */
  private setCurrentStep(step: hrRatingStep): void {
    this.currentStep = step;
    this.currentStepIndex = hrRatingStepArr.indexOf(step);
  }

  /**
   * Update the "step" query parameter.
   * You can use this to navigate between the different form steps!
   */
  navigateToStep(step: hrRatingStep): void {
    this.router.navigate([], {
      relativeTo: this.activatedRoute,
      queryParams: {
        step,
      },
    });
  }

  /**
   * In this method navigation to next step is handled.
   */
  navigateToNextStep(): void {
    if (this.currentStepIndex < hrRatingStepArr.length - 1) {
      const step = hrRatingStepArr[this.currentStepIndex + 1];
      this.navigateToStep(step);

      return;
    }

    console.warn('There is no next step.');
  }
  /**
   * In this method navigation to home page is handled.
   */
  goToHomePage(): void {
    this.navController.navigateBack('/home');
  }
  /**
   * In this method popup is displayed to confirm finalize the applicant information
   */
  async finalize(): Promise<void> {
    const alert = await this.alertController.create({
      header: this.translate.instant('commonLables.finalize'),
      message: this.translate.instant('ratingPage.finalizeConfirmationMessage'),
      cssClass: 'custom-alert-button-colors',
      buttons: [
        {
          text: this.translate.instant('commonLables.cancel'),
          cssClass: 'color-primary',
        },

        {
          text: this.translate.instant('commonLables.finalize'),
          cssClass: 'color-secondary',
          handler: () => this.finalizeApplicant(),
        },
      ],
    });

    await alert.present();
  }
  /**
   * In this method information is stored in persistent storage
   */
  finalizeApplicant(): void {
    this.storage
      .getItem<FormsData>(
        this.storage.applicantDetailsDb,
        this.ratingForm.controls.id.value
      )
      .then((applicantData) => {
        applicantData.isRated = 1;
        this.storage.updateItem<FormsData>(
          this.storage.applicantDetailsDb,
          this.ratingForm.controls.id.value,
          applicantData
        );
      });

    this.completionAlert();
  }
  /**
   * In this method confirmation alert is displayed to notify the application completion
   */
  async completionAlert(): Promise<void> {
    const toast = await this.toastController.create({
      message: this.translate.instant(
        'ratingPage.applicationFinalizedNotification'
      ),
      color: 'success',
      position: 'bottom',
      duration: 2000,
    });
    toast.present();
    this.navController.navigateBack(['/home']);
  }
  /**
   * Update the value of our progress counter.
   */
  updateProgessStatus(): void {
    // finalize page is skipped
    const totalNumberOfSteps = hrRatingStepArr.length - 1;
    let validSteps = 0;
    for (const control of Object.values(this.ratingForm.controls)) {
      if (control.valid) {
        validSteps++;
      }
    }
    this.progressPercentage = validSteps / totalNumberOfSteps;
  }
}
