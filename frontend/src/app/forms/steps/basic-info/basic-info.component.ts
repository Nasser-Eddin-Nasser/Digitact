import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

@Component({
  selector: 'form-basic-info',
  templateUrl: './basic-info.component.html',
  styleUrls: ['./basic-info.component.scss'],
})
export class BasicInfoComponent implements OnInit {

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
      this.menuObject.isCompleted = true;
    } else {
      this.menuObject.isCompleted = false;
    }
    this.pageProgressStatusCallBack.emit(this.menuObject);

  }

}
