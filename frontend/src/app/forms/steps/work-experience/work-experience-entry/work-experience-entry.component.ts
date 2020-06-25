/**
 *  @description
 *  This component renders the work information form add and modify.
 */
import { Component, Input } from '@angular/core';
import { ModalController } from '@ionic/angular';
import { TranslateService } from '@ngx-translate/core';

import { FormGroup } from '../../../../common/forms/forms';
import { WorkExperienceEntry } from '../../../../model/forms-data.model';

@Component({
  templateUrl: './work-experience-entry.component.html',
  styleUrls: ['./work-experience-entry.component.scss'],
})
export class WorkExperienceEntryComponent {
  constructor(
    public modalController: ModalController,
    private translate: TranslateService
  ) {}

  employmentTypeArray = [
    {
      value: 'Full-Time',
      displayName: this.translate.instant('workExperience.fullTime'),
    },
    {
      value: 'Part-Time',
      displayName: this.translate.instant('workExperience.partTime'),
    },
    {
      value: 'Self-Employed',
      displayName: this.translate.instant('workExperience.selfEmployed'),
    },
    {
      value: 'Freelance',
      displayName: this.translate.instant('workExperience.freelance'),
    },
    {
      value: 'Contract',
      displayName: this.translate.instant('workExperience.contract'),
    },
    {
      value: 'Internship',
      displayName: this.translate.instant('workExperience.internship'),
    },
    {
      value: 'Apprenticeship',
      displayName: this.translate.instant('workExperience.apprenticeship'),
    },
    {
      value: 'Other',
      displayName: this.translate.instant('workExperience.other'),
    },
  ];

  @Input() work: FormGroup<WorkExperienceEntry>;

  /**
   *  save and close the work experience info form
   */
  save(): void {
    this.modalController.dismiss({
      canSubmitData: true,
    });
  }

  /**
   * not save and close the work experience info form
   */
  cancel(): void {
    this.modalController.dismiss({
      canSubmitData: false,
    });
  }
}
