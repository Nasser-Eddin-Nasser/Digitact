/*
  @description
    This component renders the education information step view and its actions.
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

  //eduInfoArr: DeepPartial<EducationInfoEntry>[];

  /**
   * Constructor
   */
  constructor(public modalController: ModalController) {}

  /**
   * Open education info entry
   */
  // tslint:disable-next-line: typedef
  async presentEduInfo(): Promise<void> {
    let eduInfo = new FormGroup<EducationInfoEntry>({
      university: new FormControl('', Validators.required),
      degree: new FormControl('', Validators.required),
      typeOfDegree: new FormControl('', Validators.required),
      grade: new FormControl('', Validators.required),
      gradDate: new FormControl('', Validators.required),
    });
    const modal = await this.modalController.create({
      component: EducationInfoEntryComponent,
      componentProps: {
        edu: eduInfo,
      },
      swipeToClose: true,
      presentingElement: await this.modalController.getTop(),
    });
    modal.onDidDismiss().then((val) => {
      if (val.data.canSubmitData) {
        eduInfo.markAsPristine();
        this.formsData.controls.educationInfo.controls.eduInfo.push(eduInfo);
      }
    });
    return await modal.present();
  }
  async presentStoredInfo(
    eduStored: FormGroup<EducationInfoEntry>,
    index: number
  ): Promise<void> {
    let eduInfo = new FormGroup<EducationInfoEntry>({
      university: new FormControl(
        eduStored.controls.university.value,
        Validators.required
      ),
      degree: new FormControl(
        eduStored.controls.degree.value,
        Validators.required
      ),
      typeOfDegree: new FormControl(
        eduStored.controls.typeOfDegree.value,
        Validators.required
      ),
      grade: new FormControl(
        eduStored.controls.grade.value,
        Validators.required
      ),
      gradDate: new FormControl(
        eduStored.controls.gradDate.value,
        Validators.required
      ),
    });

    const modal = await this.modalController.create({
      component: EducationInfoEntryComponent,
      componentProps: {
        edu: eduInfo,
      },
      swipeToClose: true,
      presentingElement: await this.modalController.getTop(),
    });
    modal.onDidDismiss().then((val) => {
      if (val.data.canSubmitData) {
        this.formsData.controls.educationInfo.controls.eduInfo
          .at(index)
          .patchValue(eduInfo.value);
      }
    });
    return await modal.present();
  }

  deleteInfo(index: number): void {
    this.formsData.controls.educationInfo.controls.eduInfo.removeAt(index);
  }
}
