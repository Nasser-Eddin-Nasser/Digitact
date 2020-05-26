/**
 * @description
 *  This component renders the contact information step view and it's actions.
 */
import {
  Component,
  EventEmitter,
  Input,
  OnDestroy,
  OnInit,
  Output,
} from '@angular/core';
import { Subscription } from 'rxjs';

import { FormGroup } from './../../../common/forms/forms';
import { ContactInfo } from './../../../model/forms-data.model';

@Component({
  selector: 'form-contact-info',
  templateUrl: './contact-info.component.html',
  styleUrls: ['./contact-info.component.scss'],
})
export class ContactInfoComponent implements OnInit, OnDestroy {
  /**
   * It holds the current menu object from parent
   */
  @Input() menuObject: {
    id: number;
    displayName: string;
    isCompleted: boolean;
    isActive: boolean;
    selecteor: string;
  };

  /**
   * It holds typesafe form group property fields.
   */
  @Input() contactInfoObject: FormGroup<ContactInfo>;

  /**
   * It holds the array objects of drop down menu.
   */
  @Output() updatedMenuCompletionStatus = new EventEmitter();

  /**
   * Holds all the subscription which will be useful for un subscribing on destroy.
   */
  private subscriptions: Subscription[] = [];

  ngOnInit(): void {
    const subscription = this.contactInfoObject.valueChanges.subscribe(
      (changedValue) => {
        this.menuObject.isCompleted =
          changedValue.phoneNumber.length &&
          changedValue.eMail.length &&
          changedValue.linkedIn.length &&
          changedValue.xing.length
            ? true
            : false;
        this.updatedMenuCompletionStatus.emit();
      }
    );
    this.subscriptions.push(subscription);
  }

  /**
   * In this method un subscribe event is handled.
   */
  ngOnDestroy(): void {
    for (const subscription of this.subscriptions) {
      subscription.unsubscribe();
    }
  }
}
