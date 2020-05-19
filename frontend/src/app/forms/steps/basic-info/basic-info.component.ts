<<<<<<< HEAD
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

=======
/*
@Author
Bharathwaj Ravi

Add modifiers under @Modifiers
@Modifiers

@Purpose
  - This component renders the basic information step view and it's actions.
*/
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

import { FormGroup } from './../../../common/forms/forms';
import { BasicInfo } from './../../../interfaces/basic-info';

>>>>>>> a40cdd678f8684ffbf0ed13799cb2e793f90f684
@Component({
  selector: 'form-basic-info',
  templateUrl: './basic-info.component.html',
  styleUrls: ['./basic-info.component.scss'],
})
export class BasicInfoComponent implements OnInit {
<<<<<<< HEAD

  @Input() menuObject: { id: number, displayName: string, isCompleted: boolean, isActive: boolean, selecteor: string };
  @Input() basicInfoObject: any;

  @Output() pageProgressStatusCallBack = new EventEmitter();

  salutationsArray = [{ value: 'mr', displayName: 'Mr' }, { value: 'mrs', displayName: 'Mrs' }, { value: 'ms', displayName: 'Ms' }]

  constructor(/*private Observable: Observable*/) { }

  ngOnInit(): void { }

  onValChange(): void {
    if (this.basicInfoObject.controls.salutation.value.length &&
      this.basicInfoObject.controls.firstName.value.length &&
      this.basicInfoObject.controls.lastName.value.length) {
=======
  /*
  @Usage It holds the current menu object from parent
  */
  @Input() menuObject: {
    id: number;
    displayName: string;
    isCompleted: boolean;
    isActive: boolean;
    selecteor: string;
  };

  /*
  @Usage It holds typesafe form group property fields.
  */
  @Input() basicInfoObject: FormGroup<BasicInfo>;

  /*
  @Usage It emits the progress callback on this step.
  */
  @Output() pageProgressStatusCallBack = new EventEmitter();

  /*
  @Usage It holds the array objects of drop down menu.
  */
  salutationsArray = [
    { value: 'mr', displayName: 'Mr' },
    { value: 'mrs', displayName: 'Mrs' },
    { value: 'ms', displayName: 'Ms' },
  ];

  constructor() {}

  ngOnInit(): void {}

  /*
  @Usage It handles the on change event for the fields and send emit call back event to parent.
  */
  onValChange(): void {
    if (
      this.basicInfoObject.controls.salutation.value.length &&
      this.basicInfoObject.controls.firstName.value.length &&
      this.basicInfoObject.controls.lastName.value.length
    ) {
>>>>>>> a40cdd678f8684ffbf0ed13799cb2e793f90f684
      this.menuObject.isCompleted = true;
    } else {
      this.menuObject.isCompleted = false;
    }
    this.pageProgressStatusCallBack.emit(this.menuObject);
<<<<<<< HEAD

  }

=======
  }
>>>>>>> a40cdd678f8684ffbf0ed13799cb2e793f90f684
}
