import { Component, Input } from '@angular/core';
import { Validators } from '@angular/forms';
import { ModalController } from '@ionic/angular';

import { FormControl, FormGroup } from '../../../common/forms/forms';
import {
  FormsData,
  WorkExperienceEntry,
} from '../../../model/forms-data.model';

import { WorkExperienceEntryComponent } from './work-experience-entry/work-experience-entry.component';

@Component({
  selector: 'form-work-experience',
  templateUrl: './work-experience.component.html',
  styleUrls: ['./work-experience.component.scss'],
})
export class WorkExperienceComponent {
  @Input()
  formsData: FormGroup<FormsData>;
  constructor(public modalController: ModalController) {}

  /**
   * add education info entry
   */
  async addWorkExperience(): Promise<void> {
    const addWorkExp = new FormGroup<WorkExperienceEntry>({
      jobTitle: new FormControl('', Validators.required),
      company: new FormControl('', Validators.required),
      employmentType: new FormControl('', Validators.required),
      startDate: new FormControl('', Validators.required),
      endDate: new FormControl(''),
      description: new FormControl(''),
    });
    const modal = await this.modalController.create({
      component: WorkExperienceEntryComponent,
      componentProps: {
        work: addWorkExp,
      },
      swipeToClose: true,
      presentingElement: await this.modalController.getTop(),
    });
    /**
     * save and cancel of education info form
     */
    modal.onDidDismiss().then((val) => {
      if (val.data && val.data.canSubmitData) {
        this.formsData.controls.workExperienceInfo.controls.workExperienceForm.push(
          addWorkExp
        );
      }
    });
    return await modal.present();
  }

  modifyWorkExperience(): void {}

  deleteWorkExperience(index: number): void {
    this.formsData.controls.educationInfo.controls.educationInfoForm.removeAt(
      index
    );
  }
}
