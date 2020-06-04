/*
  @description
    This component renders the technical knowledge step view and its actions.
*/
import { Component, Input } from '@angular/core';
import { IonRouterOutlet, ModalController } from '@ionic/angular';

import { FormGroup } from '../../../common/forms/forms';
import { FormsData } from '../../../model/forms-data.model';

import {
  RatingModalComponent,
  RatingModalProps,
} from './rating-modal/rating-modal.component';

@Component({
  selector: 'form-technical-knowledge',
  templateUrl: './technical-knowledge.component.html',
  styleUrls: ['./technical-knowledge.component.scss'],
})
export class TechnicalKnowledgeComponent {
  /**
   * Data of the entire form.
   */
  @Input()
  formsData: FormGroup<FormsData>;

  constructor(
    private ionRouterOutlet: IonRouterOutlet,
    private modalController: ModalController
  ) {
    // TODO: Just for development purposes.
    window.setTimeout(() => {
      this.showRatingModal();
    });
  }

  async showRatingModal(): Promise<void> {
    const ratingModalProps: RatingModalProps = {
      formArray: this.formsData.controls.technicalKnowledge.controls
        .professionalSoftware,
    };

    const modal = await this.modalController.create({
      component: RatingModalComponent,
      componentProps: ratingModalProps,

      swipeToClose: true,
      // By setting this property, the Modal will be displayed in "card style" on iPhone.
      presentingElement: this.ionRouterOutlet.nativeEl,
    });

    modal.present();
  }
}
