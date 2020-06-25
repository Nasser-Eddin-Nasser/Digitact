/**
 *  @description
 *  This component renders the work information form add and modify.
 */
import { Component, Input } from '@angular/core';
import { ModalController } from '@ionic/angular';

import { FormGroup } from '../../../../common/forms/forms';
import { WorkExperienceEntry } from '../../../../model/forms-data.model';

@Component({
  selector: 'app-work-experience-entry',
  templateUrl: './work-experience-entry.component.html',
  styleUrls: ['./work-experience-entry.component.scss'],
})
export class WorkExperienceEntryComponent {
  constructor(public modalController: ModalController) {}

  employmentTypeArray = [
    { value: 'Full-Time', displayName: 'Full-Time' },
    { value: 'Part-Time', displayName: 'Part-Time' },
    { value: 'Self-Employed', displayName: 'Self-employed' },
    { value: 'Freelance', displayName: 'Freelance' },
    { value: 'Contract', displayName: 'Contract' },
    { value: 'Internship', displayName: 'Internship' },
    { value: 'Apprenticeship', displayName: 'Apprenticeship' },
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
