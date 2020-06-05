/*
  @description
    This Component renders the content of a modal where the user may rate his skills.
*/
import { Component, Input, OnDestroy, OnInit } from '@angular/core';
import { ModalController, PopoverController } from '@ionic/angular';
import { Subscription } from 'rxjs';

import {
  FormArray,
  FormControl,
  FormGroup,
} from '../../../../common/forms/forms';
import { AlertController } from '../../../../common/ion-wrappers/alert-controller';
import { ToastController } from '../../../../common/ion-wrappers/toast-controller';
import { TechnicalKnowledgeEntry } from '../../../../model/forms-data.model';
import { TechnicalKnowledgeFormItemsService } from '../technical-knowledge-form-items.service';

import {
  ItemPopoverComponent,
  ItemPopoverProps,
} from './item-popover/item-popover.component';

@Component({
  templateUrl: './rating-modal.component.html',
  styleUrls: ['./rating-modal.component.scss'],
})
export class RatingModalComponent
  implements RatingModalProps, OnInit, OnDestroy {
  @Input()
  formArray: FormArray<TechnicalKnowledgeEntry>;

  searchInput = new FormControl<string>('');

  /**
   * Make the possible Segment values available in the template.
   *
   * This property is really only used to make the Segment values available in the template.
   * In the TS file, you can directly refer to the enum.
   */
  readonly SEGMENT_FILTER_VALUES = SegmentFilterValue;

  segmentFilter = new FormControl<SegmentFilterValue>(SegmentFilterValue.All);

  /**
   * The form items (children of the FormArray) we want to display at the moment.
   * This list gets modified, for instance, when a search term is entered.
   */
  filteredFormItems: FormGroup<TechnicalKnowledgeEntry>[];

  /**
   * Subscriptions we need to unsubscribe from when this Component is getting destroyed.
   */
  private readonly subscriptions: Subscription[] = [];

  constructor(
    private alertController: AlertController,
    private modalController: ModalController,
    private popoverController: PopoverController,
    private technicalKnowledgeFormItemsService: TechnicalKnowledgeFormItemsService,
    private toastController: ToastController
  ) {}

  ngOnInit(): void {
    // Initialize the list.
    this.buildItemsList();

    const searchSubscription = this.searchInput.valueChanges.subscribe(() => {
      this.buildItemsList();
    });
    this.subscriptions.push(searchSubscription);

    const segmentSubscription = this.segmentFilter.valueChanges.subscribe(
      () => {
        this.buildItemsList();
      }
    );
    this.subscriptions.push(segmentSubscription);
  }

  ngOnDestroy(): void {
    for (const subscription of this.subscriptions) {
      subscription.unsubscribe();
    }
  }

  /**
   * Close this modal.
   */
  closeModal(): void {
    this.modalController.dismiss();
  }

  /**
   * Build the list of form items we want to display now.
   * The list will:
   * - be filtered based on whether the user wants to see all or only the selected values
   * - be filtered based on the current search value
   * - get ordered alphabetically.
   *
   * You can also call this method in order to initialize the list.
   */
  buildItemsList(): void {
    let result = [...this.formArray.controls];

    // Does the user want to see only the selected items?
    if (this.segmentFilter.value === SegmentFilterValue.Selected) {
      result = result.filter((item) => {
        if (item.enabled) {
          return true;
        }
        return false;
      });
    }

    // Handle the search value.
    if (this.searchInput.value !== '') {
      const searchedFor = this.searchInput.value.toLowerCase();

      result = result.filter((item) => {
        const name = item.controls.name.value.toLowerCase();
        if (name.includes(searchedFor)) {
          return true;
        }
        return false;
      });
    }

    // Sort the items alphabetically.
    result.sort((a, b) => {
      const aName = a.controls.name.value.toLowerCase();
      const bName = b.controls.name.value.toLowerCase();

      return aName.localeCompare(bName);
    });

    this.filteredFormItems = result;
  }

  /**
   * Show the popover for an item the user has clicked on.
   *
   * @param formItem The item from the form array corresponding to the element the user has clicked on.
   * @param Event The (click) event that triggered this method.
   */
  async showItemPopover(
    formItem: FormGroup<TechnicalKnowledgeEntry>,
    event: Event
  ): Promise<void> {
    const popoverProps: ItemPopoverProps = {
      formItem,
    };

    const popover = await this.popoverController.create({
      component: ItemPopoverComponent,
      componentProps: popoverProps,
      event,
      cssClass: ['popover-width-350'],
    });

    await popover.present();

    // We need to wait until the Popover has been presented.
    // Otherwise, the Popover might get positioned poorly since calling "enable" changes our view.
    formItem.enable();

    /*
      In the popover, the current item may be deleted.
      If we are currently displaying the "selected" items, the item would still be shown.
      So: Update the list.

      (In the future, we might think about how we can make this process more generalized. But it should be fine like this for now.)
    */
    await popover.onWillDismiss();
    this.buildItemsList();
  }

  /**
   * Show an alert message asking if the user really wants to add the current search term to the list.
   * If so, the term will be added.
   */
  async addSearchTermToList(): Promise<void> {
    const searchTerm = this.searchInput.value.trim();

    const mayAdd = this.mayAddItemToList(searchTerm);

    if (!mayAdd) {
      const toast = await this.toastController.create({
        message: `"${searchTerm}" cannot be added because it is already part of the list or it is not a valid name.`,
        color: 'danger',
        duration: 4000,
      });
      toast.present();
      return;
    }

    const alert = await this.alertController.create({
      header: 'Add to list',
      message: `Do you really want to add "${searchTerm}" to the list?`,
      buttons: [
        {
          text: 'Cancel',
          role: 'cancel',
        },
        {
          text: 'Add',
          handler: () => {
            this._addSearchTermToList(searchTerm);
          },
        },
      ],
    });

    alert.present();
  }

  /**
   * May an item with the provided name be added to the list?
   *
   * This method checks whether:
   * - The name contains at least one alphabetical character.
   * - The name is not already part of the list.
   */
  private mayAddItemToList(name: string): boolean {
    const regex = new RegExp('[a-z]', 'i');
    if (!regex.test(name)) {
      return false;
    }

    // Make sure no other item with the same name exists.
    const preparedName = name.toLowerCase();
    for (const item of this.formArray.controls) {
      if (preparedName === item.controls.name.value.toLowerCase()) {
        return false;
      }
    }

    return true;
  }

  /**
   * If the user wants to add the search term to the list (he has confirmed the alert), then this method will be invoked.
   * Here, the actual logic for adding the item can be found.
   *
   * Once the item has been added, a toast message will be displayed, informing that the item has been added.
   */
  private async _addSearchTermToList(searchTerm: string): Promise<void> {
    const newFormItem = this.technicalKnowledgeFormItemsService.generateDefaultFormItem(
      searchTerm
    );
    this.formArray.push(newFormItem);

    // Re-build the list so that the new element is shown.
    this.buildItemsList();

    // Change the filter to "All" so that the new item can actually be seen.
    this.segmentFilter.setValue(SegmentFilterValue.All);

    // Display a success message.
    const toast = await this.toastController.create({
      message: `"${searchTerm}" has been added to the list.`,
      duration: 3000,
    });
    toast.present();
  }
}

/**
 * Data to be sent to the Rating Modal when it gets initialized.
 */
export interface RatingModalProps {
  formArray: FormArray<TechnicalKnowledgeEntry>;
}

enum SegmentFilterValue {
  All = 'all',
  Selected = 'selected',
}
