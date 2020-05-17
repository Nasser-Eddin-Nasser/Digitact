import { Component, OnInit, Output, EventEmitter } from '@angular/core';
//import { Subscription } from 'rxjs';
import { BehaviorSubject } from 'rxjs';

@Component({
  selector: 'form-basic-info',
  templateUrl: './basic-info.component.html',
  styleUrls: ['./basic-info.component.scss'],
})
export class BasicInfoComponent implements OnInit {

  @Output() pageProgressStatusCallBack = new EventEmitter();

  salutationsArray = [{ value: "mr", displayName: "Mr" }, { value: "mrs", displayName: "Mrs" }, { value: "ms", displayName: "Ms" }]
  chosenSalutation = this.salutationsArray[0];
  basicInfoObject = { salutation: this.chosenSalutation.value, firstName: '', lastName: '' };
  fn = '';
  private behave = new BehaviorSubject<string>(this.fn);
  constructor() { }

  ngOnInit() {
    this.behave.subscribe((val) => {
      console.log("change", val);
    });
  }

  onValChange(ev: any) {
    console.log(ev)
  }

}
