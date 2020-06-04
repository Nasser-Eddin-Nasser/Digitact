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
    private modalController: ModalController,
    private popoverController: PopoverController
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
