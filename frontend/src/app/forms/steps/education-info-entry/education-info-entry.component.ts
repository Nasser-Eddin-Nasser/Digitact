/**
 *  @description
 *   This component renders the education information form add and modify.
 */
import { Component, Input } from '@angular/core';
import { ModalController } from '@ionic/angular';
import { TranslateService } from '@ngx-translate/core';

import { FormGroup } from '../../../common/forms/forms';
import { EducationInfoEntry } from '../../../model/forms-data.model';
@Component({
  selector: 'forms-education-info-entry',
  templateUrl: './education-info-entry.component.html',
  styleUrls: ['./education-info-entry.component.scss'],
})
export class EducationInfoEntryComponent {
  constructor(
    public modalController: ModalController,
    private translate: TranslateService
  ) {}

  degreeTypeArray = [
    {
      value: 'Master',
      displayName: this.translate.instant('educationInfo.master'),
    },
    {
      value: 'Bachelor',
      displayName: this.translate.instant('educationInfo.bachelor'),
    },
    {
      value: 'School',
      displayName: this.translate.instant('educationInfo.school'),
    },
  ];

  @Input() education: FormGroup<EducationInfoEntry>;

  /**
   *  save and close the education info form
   */
  save(): void {
    this.modalController.dismiss({
      canSubmitData: true,
    });
  }

  /**
   * not save and close the education info form
   */
  cancel(): void {
    this.modalController.dismiss({
      canSubmitData: false,
    });
  }
}
