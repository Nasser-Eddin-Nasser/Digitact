/*
@Author 
Bharathwaj Ravi

Add modifiers under @Modifie
@Modifiers

@Purpose
  - This component renders the basic information step view and it's actions.
*/
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

import { FormGroup } from './../../../common/forms/forms';
import { BasicInfo } from './../../../interfaces/basic-info';

@Component({
  selector: 'form-basic-info',
  templateUrl: './basic-info.component.html',
  styleUrls: ['./basic-info.component.scss'],
})
export class BasicInfoComponent implements OnInit {

  /*
  @Usage It holds the current menu object from parent
  */
  @Input() menuObject: { id: number, displayName: string, isCompleted: boolean, isActive: boolean, selecteor: string };

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
  salutationsArray = [{ value: 'mr', displayName: 'Mr' }, { value: 'mrs', displayName: 'Mrs' }, { value: 'ms', displayName: 'Ms' }]

  constructor() { }

  ngOnInit(): void { }

  /*
  @Usage It handles the on change event for the fields and send emit call back event to parent.
  */
  onValChange(): void {
    if (this.basicInfoObject.controls.salutation.value.length &&
      this.basicInfoObject.controls.firstName.value.length &&
      this.basicInfoObject.controls.lastName.value.length) {
      this.menuObject.isCompleted = true;
    } else {
      this.menuObject.isCompleted = false;
    }
    this.pageProgressStatusCallBack.emit(this.menuObject);

  }

}
