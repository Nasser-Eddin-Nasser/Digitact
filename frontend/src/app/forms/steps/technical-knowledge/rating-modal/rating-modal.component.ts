import { Component, Input, OnDestroy, OnInit } from '@angular/core';
import { ModalController, PopoverController } from '@ionic/angular';
import { Subscription } from 'rxjs';

import {
  FormArray,
  FormControl,
  FormGroup,
} from '../../../../common/forms/forms';
import { TechnicalKnowledgeEntry } from '../../../../model/forms-data.model';

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
   * The form items (children of the FormArray) we want to display at the moment.
   * This list gets modified, for instance, when a search term is entered.
   */
  filteredFormItems: FormGroup<TechnicalKnowledgeEntry>[];

  /**
   * Subscriptions we need to unsubscribe from when this Component is getting destroyed.
   */
  private readonly subscriptions: Subscription[] = [];

  constructor(
    private modalController: ModalController,
    private popoverController: PopoverController
  ) {}

  ngOnInit(): void {
    // Initialize the list.
    this.filterFormItems();

    const subscription = this.searchInput.valueChanges.subscribe(() => {
      this.filterFormItems();
    });
    this.subscriptions.push(subscription);
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
   * Filter the items in our list, based on the current search value.
   *
   * You can also call this method in order to initialize the list.
   */
  filterFormItems(): void {
    let result = [...this.formArray.controls];

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
  }
}

export interface RatingModalProps {
  formArray: FormArray<TechnicalKnowledgeEntry>;
}
