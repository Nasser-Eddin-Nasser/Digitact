/*
@Author
Alex, Don

Add modifiers under @Modifiers
@Modifiers

@Purpose
  - This component renders the basic information step view and it's actions.
*/
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormControl, Validators } from '@angular/forms';
import { FormGroup } from '@angular/forms';
// import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { computeStackId } from '@ionic/angular/directives/navigation/stack-utils';

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
  @Input() contactInfoObject: any;

  @Output() pageProgressStatusCallBack = new EventEmitter();

  formBuilder: FormBuilder;
  /*
  slideOneForm = this.formBuilder.group({
    fName: [
      '',
      Validators.compose([
        Validators.maxLength(12),
        Validators.pattern('^[0-9]+$'),
        Validators.required,
      ]),
    ],
  });
*/
  constructor() {
    this.contactInfoObject.controls.phoneNumber = new FormControl(
      '',
      Validators.required
    );
  }

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
