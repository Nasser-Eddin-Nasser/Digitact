/**
 *  @description
 *  This component renders the work information step view and its actions.
 */
import { Component, Input } from '@angular/core';
import { Validators } from '@angular/forms';
import { IonRouterOutlet, ModalController } from '@ionic/angular';

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
  constructor(
    private ionRouterOutlet: IonRouterOutlet,
    private modalController: ModalController
  ) {}

  /**
   * add work info entry
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
      presentingElement: this.ionRouterOutlet.nativeEl,
    });
    /**
     * save and cancel of work info form
     */
    modal.onDidDismiss().then((val) => {
      if (val.data && val.data.canSubmitData) {
        const startDate = addWorkExp.controls.startDate.value.substr(0, 7);
        const endDate = addWorkExp.controls.endDate.value.substr(0, 7);
        addWorkExp.controls.startDate.setValue(startDate);
        addWorkExp.controls.endDate.setValue(endDate);
        this.formsData.controls.workExperienceInfo.controls.workExperienceForm.push(
          addWorkExp
        );
      }
    });
    return await modal.present();
  }

  /**
   * modify work info entry
   */
  async modifyWorkExperience(
    workdata: FormGroup<WorkExperienceEntry>,
    index: number
  ): Promise<void> {
    const modWorkExp = new FormGroup<WorkExperienceEntry>({
      jobTitle: new FormControl(
        workdata.controls.jobTitle.value,
        Validators.required
      ),
      company: new FormControl(
        workdata.controls.company.value,
        Validators.required
      ),
      employmentType: new FormControl(
        workdata.controls.employmentType.value,
        Validators.required
      ),
      startDate: new FormControl(
        workdata.controls.startDate.value,
        Validators.required
      ),
      endDate: new FormControl(workdata.controls.endDate.value),
      description: new FormControl(workdata.controls.description.value),
    });

    const modal = await this.modalController.create({
      component: WorkExperienceEntryComponent,
      componentProps: {
        work: modWorkExp,
      },
      swipeToClose: true,
      presentingElement: this.ionRouterOutlet.nativeEl,
    });
    /**
     * save and cancel of work info form
     */
    modal.onDidDismiss().then((val) => {
      if (val.data && val.data.canSubmitData) {
        const startDate = modWorkExp.controls.startDate.value.substr(0, 7);
        const endDate = modWorkExp.controls.endDate.value.substr(0, 7);
        modWorkExp.controls.startDate.setValue(startDate);
        modWorkExp.controls.endDate.setValue(endDate);
        this.formsData.controls.workExperienceInfo.controls.workExperienceForm
          .at(index)
          .patchValue(modWorkExp.value);
      }
    });
    return await modal.present();
  }
  /**
   * delete work info entry
   */
  deleteWorkExperience(index: number): void {
    this.formsData.controls.workExperienceInfo.controls.workExperienceForm.removeAt(
      index
    );
  }
}
