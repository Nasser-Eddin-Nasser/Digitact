/*
@Author
Alex, Don

Add modifiers under @Modifiers
@Modifiers

@Purpose
  - This component renders the basic information step view and it's actions.
*/
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

import { FormGroup } from './../../../common/forms/forms';
import { ContactInfo } from './../../../interfaces/contact-info';

@Component({
  selector: 'form-contact-info',
  templateUrl: './contact-info.component.html',
  styleUrls: ['./contact-info.component.scss'],
})
export class ContactInfoComponent implements OnInit {
  @Input() menuObject: {
    id: number;
    displayName: string;
    isCompleted: boolean;
    isActive: boolean;
    selecteor: string;
  };
  @Input() contactInfoObject: FormGroup<ContactInfo>;

  @Output() pageProgressStatusCallBack = new EventEmitter();

  constructor() {}

  ngOnInit(): void {}

  onValChange(): void {
    if (
      this.contactInfoObject.controls.phoneNumber.value.length &&
      this.contactInfoObject.controls.eMail.value.length &&
      this.contactInfoObject.controls.linkedIn.value.length &&
      this.contactInfoObject.controls.xing.value.length
    ) {
      this.menuObject.isCompleted = true;
    } else {
      this.menuObject.isCompleted = false;
    }
    this.pageProgressStatusCallBack.emit(this.menuObject);
  }
}
