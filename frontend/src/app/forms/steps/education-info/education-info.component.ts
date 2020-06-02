/**
 *  @description
 *   This component renders the education information step view and its actions.
 */
import { Component, Input } from '@angular/core';
import { Validators } from '@angular/forms';
import { ModalController } from '@ionic/angular';

import { FormControl, FormGroup } from '../../../common/forms/forms';
import { EducationInfoEntry, FormsData } from '../../../model/forms-data.model';
import { EducationInfoEntryComponent } from '../education-info-entry/education-info-entry.component';

@Component({
  selector: 'form-education-info',
  templateUrl: './education-info.component.html',
  styleUrls: ['./education-info.component.scss'],
})
export class EducationInfoComponent {
  @Input()
  formsData: FormGroup<FormsData>;

  /**
   * Constructor
   */
  constructor(public modalController: ModalController) {}

  /**
   * add education info entry
   */
  async addEducationInfo(): Promise<void> {
    const educationInfoAdd = new FormGroup<EducationInfoEntry>({
      university: new FormControl('', Validators.required),
      subject: new FormControl('', Validators.required),
      degree: new FormControl('', Validators.required),
      grade: new FormControl('', Validators.required),
      graduationYear: new FormControl('', Validators.required),
    });
    const modal = await this.modalController.create({
      component: EducationInfoEntryComponent,
      componentProps: {
        education: educationInfoAdd,
      },
      swipeToClose: true,
      presentingElement: await this.modalController.getTop(),
    });
    /**
     * save and cancel of education info form
     */
    modal.onDidDismiss().then((val) => {
      if (val.data && val.data.canSubmitData) {
        const year = educationInfoAdd.controls.graduationYear.value.substr(
          0,
          4
        );
        educationInfoAdd.controls.graduationYear.setValue(year);
        this.formsData.controls.educationInfo.controls.educationInfoForm.push(
          educationInfoAdd
        );
      }
    });
    return await modal.present();
  }
  /**
   * modify education info entry
   */
  async modifyEducationInfo(
    educationData: FormGroup<EducationInfoEntry>,
    index: number
  ): Promise<void> {
    const educationInfoMod = new FormGroup<EducationInfoEntry>({
      university: new FormControl(
        educationData.controls.university.value,
        Validators.required
      ),
      subject: new FormControl(
        educationData.controls.subject.value,
        Validators.required
      ),
      degree: new FormControl(
        educationData.controls.degree.value,
        Validators.required
      ),
      grade: new FormControl(
        educationData.controls.grade.value,
        Validators.required
      ),
      graduationYear: new FormControl(
        educationData.controls.graduationYear.value,
        Validators.required
      ),
    });

    const modal = await this.modalController.create({
      component: EducationInfoEntryComponent,
      componentProps: {
        education: educationInfoMod,
      },
      swipeToClose: true,
      presentingElement: await this.modalController.getTop(),
    });
    /**
     * save and cancel of education info form
     */
    modal.onDidDismiss().then((val) => {
      if (val.data && val.data.canSubmitData) {
        const year = educationInfoMod.controls.graduationYear.value.substr(
          0,
          4
        );
        educationInfoMod.controls.graduationYear.setValue(year);
        this.formsData.controls.educationInfo.controls.educationInfoForm
          .at(index)
          .patchValue(educationInfoMod.value);
      }
    });
    return await modal.present();
  }
  /**
   * delete education info entry
   */
  deleteEducationInfo(index: number): void {
    this.formsData.controls.educationInfo.controls.educationInfoForm.removeAt(
      index
    );
  }
}
