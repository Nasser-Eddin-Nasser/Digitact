/*
  @description
    This page handles the basic operation of tracking progress, navigation, close menu, continue button,
    event handlers for child to parent communication and parent to child data down.
*/

import { Component, OnDestroy, OnInit } from '@angular/core';
import { Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import {
  IonRouterOutlet,
  NavController,
  Platform,
  ViewDidEnter,
  ViewWillLeave,
} from '@ionic/angular';
import { TranslateService } from '@ngx-translate/core';
import { Observable, Subscription } from 'rxjs';

import {
  FormArray,
  FormControl,
  FormGroup,
  FormValue,
} from '../common/forms/forms';
import { AlertController } from '../common/ion-wrappers/alert-controller';
import {
  AdditionalInfo,
  BasicInfo,
  ContactInfo,
  Documents,
  EducationInfo,
  FieldDesignationInfo,
  FormsData,
  KeyCompetencies,
  ProfilePicture,
} from '../model/forms-data.model';
import { StorageHandlerService } from '../services/storage-handler.service';

import {
  ApplicationStep,
  ApplicationStepsArr,
  ApplicationStepsConfig,
} from './model/steps.model';

@Component({
  selector: 'app-forms',
  templateUrl: './forms.page.html',
  styleUrls: ['./forms.page.scss'],
})
export class FormsPage
  implements OnInit, OnDestroy, ViewDidEnter, ViewWillLeave {
  constructor(
    private activatedRoute: ActivatedRoute,
    private alertController: AlertController,
    private ionRouterOutlet: IonRouterOutlet,
    private navigationController: NavController,
    private platform: Platform,
    private router: Router,
    private storage: StorageHandlerService,
    private translate: TranslateService
  ) {}
  /**
   * Make the Steps available in the template.
   *
   * This property is really only used to make the Steps available in the template.
   * In the TS file, you can directly refer to the underlying element.
   */
  readonly APPLICATION_STEP = ApplicationStep;

  /**
   * Make the Steps Array available in the template.
   *
   * This property is really only used to make the Steps available in the template.
   * In the TS file, you can directly refer to the underlying element.
   */
  readonly APPLICATION_STEP_ARR = ApplicationStepsArr;

  readonly APPLICATION_STEPS_CONFIG = ApplicationStepsConfig;

  /**
   * Data of the entire form.
   */
  formsData = new FormGroup<FormsData>({
    id: new FormControl(''),
    isRated: new FormControl(0),
    submittedTime: new FormControl(''),

    basicInfo: new FormGroup<BasicInfo>({
      firstName: new FormControl('', Validators.required),
      lastName: new FormControl('', Validators.required),
      salutation: new FormControl(undefined, Validators.required),
    }),
    contactInfo: new FormGroup<ContactInfo>({
      phoneNumber: new FormControl('', Validators.required),
      eMail: new FormControl('', Validators.required),
      linkedIn: new FormControl(''),
      xing: new FormControl(''),
    }),
    profilePicture: new FormGroup<ProfilePicture>({
      pictureBase64: new FormControl('', Validators.required),
    }),
    documents: new FormGroup<Documents>({
      documentsBase64: new FormArray([]),
    }),
    educationInfo: new FormGroup<EducationInfo>({
      educationInfoForm: new FormArray([], Validators.required),
    }),
    fieldDesignationInfo: new FormGroup<FieldDesignationInfo>({
      field: new FormControl<string[]>([], Validators.required),
      designation: new FormControl<string[]>([], Validators.required),
    }),
    keyCompetencies: new FormGroup<KeyCompetencies>({
      languages: new FormControl([], Validators.required),
      businessSkills: new FormControl([]),
      professionalSoftware: new FormControl([]),
      databases: new FormControl([]),
      programmingLanguagesAndFrameworks: new FormControl([]),
    }),
    additionalInfo: new FormGroup<AdditionalInfo>({
      additionalInfo: new FormControl(''),
    }),
  });

  /**
   * Which step is currently displayed?
   *
   * **Important! Do not modify this value directly! Use `setCurrentStep()` instead!**
   */
  currentStep: ApplicationStep;
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
   * Boolean used to check, if the applicant wants to leave the forms page by submitting,
   * if applicant wants to submit, mayLeaveView() returns true, so Guard will allow leaving the page.
   */
  private hasSubmittedForm = false;

  /**
   * Allows skipping a step.
   * Please note that not all pages can be skipped.
   * But currently, it is the easiest typesafe way to just create FormControls for every page.
   *
   * So: In the future, you could attempt to refactor this.
   */
  readonly isSkipped: {
    [key in ApplicationStep]: FormControl<boolean>;
  } = {
    [ApplicationStep.BasicInformation]: new FormControl<boolean>(false),
    [ApplicationStep.ContactInformation]: new FormControl<boolean>(false),
    [ApplicationStep.ProfilePicture]: new FormControl<boolean>(false),
    [ApplicationStep.Documents]: new FormControl<boolean>(false),
    [ApplicationStep.EducationInformation]: new FormControl<boolean>(false),
    [ApplicationStep.FieldDesignationPreference]: new FormControl<boolean>(
      false
    ),
    [ApplicationStep.KeyCompetencies]: new FormControl<boolean>(false),
    [ApplicationStep.AdditionalInformation]: new FormControl<boolean>(false),
    [ApplicationStep.Submit]: new FormControl<boolean>(false),
  };

  /**
   * In this method route change is observed and handling is done.
   */
  ngOnInit(): void {
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

    const formStatusChangesSubscriptions = this.formsData.statusChanges.subscribe(
      () => {
        /*
          This Observable fires every time the validity status recalculates.
          So, it will basically fire whenever someone enters some data somewhere.
          (To be more precise: It might fire even more often than that.)
          So, we can use it to update our progress bar.
          Of course, there is still room for performance improvement (since we will call the following method really often).
          But for now, it should be fine.
        */
        this.updateProgessStatus();
      }
    );
    this.subscriptions.push(formStatusChangesSubscriptions);

    this.setUpSkipListeners();
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
   * Disabling swipe gesture in iOS
   */
  ionViewDidEnter(): void {
    if (this.platform.is('ios')) {
      // Disable the router swipe gesture on iOS, because this gesture cannot be properly handled with our Guard.
      this.ionRouterOutlet.swipeGesture = false;
    }
  }

  /**
   * Activating swipe gesture in iOS
   */
  ionViewWillLeave(): void {
    if (this.platform.is('ios')) {
      this.ionRouterOutlet.swipeGesture = true;
    }
  }

  /**
   * Create listeners for every single FormControl in `isSkipped`.
   * When the value changes to `true`, we disable the corresponding form element and show an alert message.
   * When it changes to `false`, we re-enable the corresponding form element.
   */
  private setUpSkipListeners(): void {
    for (const step of ApplicationStepsArr) {
      const formControl = this.isSkipped[step];

      const subscription = formControl.valueChanges.subscribe((isChecked) => {
        const stepConfig = ApplicationStepsConfig[step];
        if (!stepConfig.maySkipStep) {
          console.warn(
            'Tried to change a skip toggle on a page that should not be skipped.'
          );
          return;
        }

        if (isChecked) {
          this.showSkipStepAlert(formControl);
        }

        if (isChecked) {
          this.formsData.controls[stepConfig.formItemName].disable();
        } else {
          this.formsData.controls[stepConfig.formItemName].enable();
        }
      });

      this.subscriptions.push(subscription);
    }
  }

  /**
   * Show an alert message asking if the user really wanted to skip this step.
   * (This is actually shown after the toggle has toggled, but that should be alright for now.)
   */
  async showSkipStepAlert(
    theToggleFormControl: FormControl<boolean>
  ): Promise<void> {
    const alert = await this.alertController.create({
      header: 'Skip',
      message:
        'Do you really want to skip this step? Please only do this if the data asked for in this step are part of the documents you took a photo of.',
      buttons: [
        {
          text: 'Cancel',
          role: 'cancel',
          handler: () => {
            theToggleFormControl.setValue(false);
          },
        },
        {
          text: 'Skip',
          handler: () => {
            // Nothing to do here, since the toggle is automatically toggled.
          },
        },
      ],
    });

    alert.present();
  }

  /**
   * Handle a Step as provided by the Router.
   *
   * The string provided by the Router could contain anything.
   * So: If the Step is not known, we simply show the Basic Information step.
   *
   * (When navigating to the URL without providing a query parameter, we will also redirect to the Basic Information step).
   *
   * @param step The "step" query parameter we got from the Router.
   */
  private handleStep(step: unknown): void {
    // tslint:disable-next-line: no-any
    if (ApplicationStepsArr.includes(step as any)) {
      this.setCurrentStep(step as ApplicationStep);
      return;
    }

    if (step) {
      console.warn('The following Step is unknown:', step);
    }

    // A fallback: If a step was requested that we don't know, we simply show the Basic Information page.
    this.navigateToStep(ApplicationStep.BasicInformation);
  }

  /**
   * Set the currentStep and currentStepIndex property.
   *
   * Usually, if you want to change the Step, you should use the Router.
   */
  private setCurrentStep(step: ApplicationStep): void {
    this.currentStep = step;
    this.currentStepIndex = ApplicationStepsArr.indexOf(step);
  }

  /**
   * Update the "step" query parameter.
   * You can use this to navigate between the different form steps!
   */
  navigateToStep(step: ApplicationStep): void {
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
    if (this.currentStepIndex < ApplicationStepsArr.length - 1) {
      const step = ApplicationStepsArr[this.currentStepIndex + 1];
      this.navigateToStep(step);

      return;
    }

    console.warn('There is no next step.');
  }

  /**
   * "Submit" the form: Store the value in our persistent storage and navigate to the confirmation page.
   */
  submit(): void {
    const time = new Date().toLocaleTimeString('en-US', {
      hour12: false,
      hour: 'numeric',
      minute: 'numeric',
    });
    this.formsData.controls.submittedTime.setValue(time);

    this.storage.getNextId().then((key) => {
      this.formsData.controls.id.setValue(key);

      this.storage.addItem<FormValue<FormsData>>(
        this.storage.applicantDetailsDb,
        key,
        this.formsData.value
      );

      this.hasSubmittedForm = true;
      this.navigationController.navigateRoot(['/forms', 'confirmation'], {
        animationDirection: 'forward',
        state: { id: key },
      });
    });
  }

  /**
   * Go to the Home page.
   */
  closeForm(): void {
    this.navigationController.navigateBack(['/home']);
  }

  /**
   * Update the value of our progress counter.
   */
  updateProgessStatus(): void {
    let finishedSteps = 0;
    let totalNumberOfRequiredSteps = 0;

    for (const item of Object.values(ApplicationStepsConfig)) {
      if (!item.useForProgressCalculation) {
        continue;
      }

      totalNumberOfRequiredSteps++;

      const control = this.formsData.controls[item.formItemName];
      if (control.valid || control.disabled) {
        finishedSteps++;
      }
    }
    this.progressPercentage = finishedSteps / totalNumberOfRequiredSteps;
  }

  /**
   * Showing an alert message, when leaving a form page.
   *
   * @returns A Promise. This Promise will emit true if the user wants to leave the page. Otherwise, false is emitted.
   */
  private async showClosingAlert(): Promise<boolean> {
    const result = new Promise<boolean>(async (resolve) => {
      const alert = await this.alertController.create({
        header: this.translate.instant('commonLables.close'),
        message: this.translate.instant('formsPage.pageExitWarningMessage'),
        cssClass: 'custom-alert-button-colors',
        buttons: [
          {
            text: this.translate.instant('commonLables.no'),
            role: 'cancel',
            handler: () => {
              resolve(false);
            },
          },
          {
            text: this.translate.instant('commonLables.yes'),
            cssClass: 'color-secondary',
            handler: () => {
              resolve(true);
            },
          },
        ],
      });

      alert.present();
    });
    return result;
  }

  /**
   * Deciding Function to handle leaving forms page, needed for the Guard
   *
   * @returns true (either directly or emitted in the Observable) if the view may be left. Otherwise, false is returned (or emitted).
   */
  mayLeaveView(): Observable<boolean> | boolean {
    if (this.hasSubmittedForm) {
      return true;
    }

    const result = new Observable<boolean>((observer) => {
      this.showClosingAlert().then((mayLeave) => {
        observer.next(mayLeave);
        observer.complete();
      });
    });
    return result;
  }
}
