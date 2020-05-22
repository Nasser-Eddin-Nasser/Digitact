/*
@author
Bharathwaj Ravi

@description
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
   * It holds the current menu object from parent
   */
  @Input() menuObject: {
    id: number;
    displayName: string;
    isCompleted: boolean;
    isActive: boolean;
    selecteor: string;
  };

  /*
   * It holds typesafe form group property fields.
   */
  @Input() basicInfoObject: FormGroup<BasicInfo>;

  /*
   * It emits the progress callback on this step.
   */
  @Output() updatedProgressStatus = new EventEmitter();

  /*
   * It holds the array objects of drop down menu.
   */
  salutationsArray = [
    { value: 'mr', displayName: 'Mr' },
    { value: 'mrs', displayName: 'Mrs' },
    { value: 'ms', displayName: 'Ms' },
  ];

  /*
   * Value changes are observed to update completion status and event is emitted.
   */
  ngOnInit(): void {
    this.basicInfoObject.valueChanges.subscribe((changedValue) => {
      this.menuObject.isCompleted =
        changedValue.salutation.length &&
        changedValue.firstName.length &&
        changedValue.lastName.length
          ? true
          : false;
      this.updatedProgressStatus.emit();
    });
  }
}
