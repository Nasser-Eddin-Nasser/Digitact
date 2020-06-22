/*
  @description
    This component renders the hamburger side menu and its actions.
*/

import { Component, Input } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';

import { FormGroup } from '../../common/forms/forms';
import { FormsData } from '../../model/forms-data.model';
import { ApplicationStep, ApplicationStepsArr } from '../model/steps.model';
@Component({
  selector: 'app-side-menu',
  templateUrl: './side-menu.component.html',
  styleUrls: ['./side-menu.component.scss'],
})
export class SideMenuComponent {
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
  readonly APPLICATION_STEP = ApplicationStep;

  /**
   * Make the Steps Array available in the template.
   *
   * Only access this property in the template.
   * In the TS file, you can directly refer to the underlying element.
   */
  readonly APPLICATION_STEP_ARR = ApplicationStepsArr;

  /**
   * Data of the entire form.
   */
  @Input()
  formsData: FormGroup<FormsData>;

  /**
   * Which step is currently displayed?
   */
  @Input()
  currentStep: ApplicationStep;

  displayNames = {
    basicInfo: this.translate.instant('basicInfo.title'),
    contactInfo: this.translate.instant('contactInfo.title'),
    profilePicture: this.translate.instant('profilePicture.title'),
    documentsUpload: this.translate.instant('documentsUpload.title'),
    educationInfo: this.translate.instant('educationInfo.title'),
    industryAndPositionPreference: this.translate.instant(
      'industryAndPositionPreference.title'
    ),
    keyCompetencies: this.translate.instant('keyCompetencies.title'),
    submit: this.translate.instant('submitData.title'),
  };

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
