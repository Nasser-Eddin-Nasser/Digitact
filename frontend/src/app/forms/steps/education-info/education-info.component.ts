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
        this.formsData.controls.educationInfo.controls.eduInfo.push(eduInfo);
      }
    });
    return await modal.present();
  }
  async presentStoredInfo(
    eduStored: FormGroup<EducationInfoEntry>,
    index: number
  ): Promise<void> {
    // const e = new FormGroup<EducationInfoEntry>({
    //   university: new FormControl(eduStored.university, Validators.required),
    //   degree: new FormControl(eduStored.degree, Validators.required),
    //   typeOfDegree: new FormControl(
    //     eduStored.typeOfDegree,
    //     Validators.required
    //   ),
    //   grade: new FormControl(eduStored.grade, Validators.required),
    //   gradDate: new FormControl(eduStored.gradDate, Validators.required),
    // });
    const modal = await this.modalController.create({
      component: EducationInfoEntryComponent,
      componentProps: {
        edu: eduStored,
      },
      swipeToClose: true,
      presentingElement: await this.modalController.getTop(),
    });
    // modal.onDidDismiss().then((val) => {
    //   if (val.data.canSubmitData) {
    //     //this.formsData.controls.educationInfo.controls.eduInfo[index] = e;
    //     //    this.eduInfoArr[index] = e.value;
    //   }
    //   // console.log(val.data.canSubmitData, val, e, this.eduInfoArr);
    // });
    return await modal.present();
  }

  deleteInfo(index: number): void {
    this.formsData.controls.educationInfo.controls.eduInfo.removeAt(index);
  }
}
