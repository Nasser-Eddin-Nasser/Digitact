import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';

import { ApplicationStep, ApplicationStepsArr } from './model/steps.model';

@Component({
  selector: 'app-application-form',
  templateUrl: './application-form.page.html',
  styleUrls: ['./application-form.page.scss'],
})
export class ApplicationFormPage implements OnInit, OnDestroy {
  // Make the Steps available in the template.
  readonly APPLICATION_STEP = ApplicationStep;

  currentStep: ApplicationStep;

  private readonly subscriptions: Subscription[] = [];

  constructor(private activatedRoute: ActivatedRoute, private router: Router) {}

  ngOnInit(): void {
    const subscription = this.activatedRoute.queryParams.subscribe((params) => {
      /*
        This Observable will also fire when the page is loaded.
        So, it allows us not only to react to changes of the query parameter,
        but also to set the initial query parameter.
      */
      this.handleStep(params.step);
    });

    this.subscriptions.push(subscription);
  }

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
      this.currentStep = step as ApplicationStep;
      return;
    }

    if (step) {
      console.warn('The following Step is unknown:', step);
    }

    // A fallback: If a step was requested that we don't know, we simply show the Basic Information page.

    this.navigateToStep(ApplicationStep.BasicInformation);
  }

  /**
   * Update the "step" query paremeter.
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
}
