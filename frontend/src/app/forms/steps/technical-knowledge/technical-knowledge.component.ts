/*
  @description
    This component renders the technical knowledge step view and its actions.
*/
import { Component, Input, OnInit } from '@angular/core';
import { IonRouterOutlet, ModalController } from '@ionic/angular';

import { FormArray, FormGroup } from '../../../common/forms/forms';
import {
  FormsData,
  TechnicalKnowledgeEntry,
} from '../../../model/forms-data.model';

import {
  RatingModalComponent,
  RatingModalProps,
} from './rating-modal/rating-modal.component';

@Component({
  selector: 'form-technical-knowledge',
  templateUrl: './technical-knowledge.component.html',
  styleUrls: ['./technical-knowledge.component.scss'],
})
export class TechnicalKnowledgeComponent implements OnInit {
  /**
   * Data of the entire form.
   */
  @Input()
  formsData: FormGroup<FormsData>;

  /**
   * The items we will later render in the template.
   *
   * Important: Currently, we initialize it in ngOnInit and never change it afterwards.
   * So, if the reference(s) to the form items change in our input, this will not be reflected here.
   */
  listItems: ListItem[];

  constructor(
    private ionRouterOutlet: IonRouterOutlet,
    private modalController: ModalController
  ) {}

  ngOnInit(): void {
    // This might lead to bugs if the form controls (their references) get changed. But let's ignore that for now.

    this.listItems = [
      {
        displayName: 'Professional Software',
        formItem: this.formsData.controls.technicalKnowledge.controls
          .professionalSoftware,
      },
      {
        displayName: 'Databases',
        formItem: this.formsData.controls.technicalKnowledge.controls.databases,
      },
      {
        displayName: 'Programming Languages and Frameworks',
        formItem: this.formsData.controls.technicalKnowledge.controls
          .programmingLanguages,
      },
    ];
  }

  async showRatingModal(
    formItem: FormArray<TechnicalKnowledgeEntry>
  ): Promise<void> {
    const ratingModalProps: RatingModalProps = {
      formArray: formItem,
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

interface ListItem {
  formItem: FormArray<TechnicalKnowledgeEntry>;
  displayName: string;
}
