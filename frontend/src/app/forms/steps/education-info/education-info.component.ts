/*
  @description
    This component renders the education information step view and its actions.
*/
import { Component, Input } from '@angular/core';
import { ModalController } from '@ionic/angular';

import { FormGroup } from '../../../common/forms/forms';
import { FormsData } from '../../../model/forms-data.model';
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
  constructor(public modalController: ModalController) {
    console.log('This is EducationInfoComponent');
  }

  /**
   * Open education info entry
   */
  // tslint:disable-next-line: typedef
  async presentEduInfo() {
    const modal = await this.modalController.create({
      component: EducationInfoEntryComponent,
      swipeToClose: true,
      presentingElement: await this.modalController.getTop(),
    });
    return await modal.present();
  }
}
