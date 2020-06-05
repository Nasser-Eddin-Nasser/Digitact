/*
  @description
    This component renders the technical knowledge step view and its actions.

  Important! Internally, we use FormArrays instead of FormControls.
  For the detailed explanation, please have a look at the form items service.
*/
import { Component, Input, OnDestroy, OnInit } from '@angular/core';
import { IonRouterOutlet, ModalController } from '@ionic/angular';
import { Subscription } from 'rxjs';

import { FormArray, FormGroup, FormValue } from '../../../common/forms/forms';
import {
  FormsData,
  TechnicalKnowledge,
  TechnicalKnowledgeEntry,
} from '../../../model/forms-data.model';

import {
  RatingModalComponent,
  RatingModalProps,
} from './rating-modal/rating-modal.component';
import {
  TechnicalKnowledgeFormItemsService,
  TechnicalKnowledgeInternal,
} from './technical-knowledge-form-items.service';

@Component({
  selector: 'form-technical-knowledge',
  templateUrl: './technical-knowledge.component.html',
  styleUrls: ['./technical-knowledge.component.scss'],
})
export class TechnicalKnowledgeComponent implements OnInit, OnDestroy {
  /**
   * Data of the entire form.
   *
   * **Important**
   * We use our "internalFormsData" to actually deal with our data.
   * Because of this, in most cases, you don't want to access the formsData object directly.
   */
  @Input()
  formsData: FormGroup<FormsData>;

  /**
   * "Internally", we use FormArrays instead of FormControls.
   * So: This object holds everything we need to deal with our data.
   */
  internalFormsData: FormGroup<TechnicalKnowledgeInternal>;

  /**
   * The items we will later render in the template.
   *
   * Important: Currently, we initialize it in ngOnInit and never change it afterwards.
   * So, if the reference(s) to the form items change in our input, this will not be reflected here.
   */
  listItems: ListItem[];

  /**
   * Subscriptions we need to unsubscribe from when the component gets destroyed.
   */
  private subscriptions: Subscription[] = [];

  constructor(
    private ionRouterOutlet: IonRouterOutlet,
    private modalController: ModalController,
    private technicalKnowledgeFormItemsService: TechnicalKnowledgeFormItemsService
  ) {}

  ngOnInit(): void {
    // All of the following might lead to bugs if the form controls (their references) get changed. But let's ignore that for now.

    this.internalFormsData = this.technicalKnowledgeFormItemsService.generateAllFormItems(
      this.formsData.controls.technicalKnowledge
    );

    this.listItems = [
      {
        displayName: 'Professional Software',
        formItem: this.internalFormsData.controls.professionalSoftware,
      },
      {
        displayName: 'Databases',
        formItem: this.internalFormsData.controls.databases,
      },
      {
        displayName: 'Programming Languages and Frameworks',
        formItem: this.internalFormsData.controls
          .programmingLanguagesAndFrameworks,
      },
    ];

    // Make sure our "internal" changes are also reflected in the overall form.

    const internalFormsDataSubscription = this.internalFormsData.valueChanges.subscribe(
      () => {
        // First, clear all controls.
        for (const control of Object.values(
          this.formsData.controls.technicalKnowledge.controls
        )) {
          control.setValue([]);
        }

        // Is the whole "internal" form disabled? Then there is nothing we can/should add to the "external" form.
        if (this.internalFormsData.disabled) {
          return;
        }

        // The "internal" form has pretty much the same structure as the "external" one.
        this.formsData.controls.technicalKnowledge.patchValue(
          this.internalFormsData.value as FormValue<TechnicalKnowledge>
        );
      }
    );
    this.subscriptions.push(internalFormsDataSubscription);
  }

  ngOnDestroy(): void {
    for (const subscription of this.subscriptions) {
      subscription.unsubscribe();
    }
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
