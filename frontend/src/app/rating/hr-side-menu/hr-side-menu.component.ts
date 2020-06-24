/**
 * @description
 *  This component renders the hamburger hr-side menu and its actions.
 */

import { Component, Input } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';

import { FormGroup } from '../../common/forms/forms';
import { RatingForm } from '../model/rating-form.model';
import { hrRatingStep, hrRatingStepArr } from '../model/steps.model';

@Component({
  selector: 'app-hr-side-menu',
  templateUrl: './hr-side-menu.component.html',
  styleUrls: ['./hr-side-menu.component.scss'],
})
export class HrSideMenuComponent {
  constructor(
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private translate: TranslateService
  ) {}

  /**
   * Make the Steps available in the template.
   *
   * Only access this property in the template.
   * In the TS file, you can directly refer to the underlying element.
   */
  readonly HR_RATING_STEP = hrRatingStep;

  /**
   * Make the Steps Array available in the template.
   *
   * Only access this property in the template.
   * In the TS file, you can directly refer to the underlying element.
   */
  readonly HR_RATING_STEP_ARR = hrRatingStepArr;

  /**
   * Data of the entire form.
   */
  @Input()
  ratingForm: FormGroup<RatingForm>;

  /**
   * Which step is currently displayed?
   */
  @Input()
  currentStep: hrRatingStep;

  displayMessages = {
    applicantRating: this.translate.instant('hrSideMenu.applicantRating'),
    impression: this.translate.instant('hrSideMenu.impression'),
    finalize: this.translate.instant('commonLables.finalize'),
  };
  /**
   * Update the "step" query paremeter.
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
}
