/*
  @description
    This page handles the basic operation of tracking progress, navigation, close menu, continue button,
    event handlers for child to parent communication and parent to child data down.
*/

import { Component, OnDestroy, OnInit } from '@angular/core';
import { Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { NavController } from '@ionic/angular';
import { Subscription } from 'rxjs';

import { FormArray, FormControl, FormGroup } from '../common/forms/forms';
import {
  BasicInfo,
  ContactInfo,
  EducationInfo,
  FieldDesignationInfo,
  FormsData,
} from '../model/forms-data.model';
import { StorageHandlerService } from '../services/storage-handler.service';

import { ApplicationStep, ApplicationStepsArr } from './model/steps.model';

@Component({
  selector: 'app-forms',
  templateUrl: './forms.page.html',
  styleUrls: ['./forms.page.scss'],
})
export class FormsPage implements OnInit, OnDestroy {
  constructor(
    private activatedRoute: ActivatedRoute,
    private navigationController: NavController,
    private router: Router,
    private storage: StorageHandlerService
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
    educationInfo: new FormGroup<EducationInfo>({
      educationInfoForm: new FormArray([], Validators.required),
    }),
    fieldDesignationInfo: new FormGroup<FieldDesignationInfo>({
      field: new FormControl<string[]>([], Validators.required),
      designation: new FormControl<string[]>([], Validators.required),
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
   * In this method route change is observed and handling is done.
   */
  ngOnInit(): void {
    this.formsData.controls.id.disable();
    this.formsData.controls.isRated.disable();
    this.formsData.controls.submittedTime.disable();

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

      this.storage.addItem<FormsData>(
        this.storage.applicantDetailsDb,
        key,
        this.formsData.getRawValue()
      );
      this.router.navigate(['/forms', 'confirmation'], { state: { id: key } });
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
    // Don't count the submit page.
    const totalNumberOfSteps = ApplicationStepsArr.length - 1;

    let validSteps = 0;
    for (const control of Object.values(this.formsData.controls)) {
      if (control.valid) {
        validSteps++;
      }
    }
    this.progressPercentage = validSteps / totalNumberOfSteps;
  }
}
