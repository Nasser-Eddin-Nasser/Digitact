import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';
//import { Observable } from 'rxjs/Observable';

@Component({
  selector: 'form-basic-info',
  templateUrl: './basic-info.component.html',
  styleUrls: ['./basic-info.component.scss'],
})
export class BasicInfoComponent implements OnInit {

  @Input() menuObject: { id: number, displayName: string, isCompleted: boolean, isActive: boolean, selecteor: string };
  @Input() basicInfoObject: any;

  @Output() pageProgressStatusCallBack = new EventEmitter();

  salutationsArray = [{ value: "mr", displayName: "Mr" }, { value: "mrs", displayName: "Mrs" }, { value: "ms", displayName: "Ms" }]

  constructor(/*private Observable: Observable*/) { }

  ngOnInit() {
    // this.Observable.combineLatest(
    //   this.basicInfoObject.controls.firstName.valueChanges,
    //   this.basicInfoObject.controls.lastName.valueChanges,
    // ).subscribe(() => {
    //   console.log("In");
    //   if (this.basicInfoObject.controls.salutation.value.length && this.basicInfoObject.controls.firstName.value.length && this.basicInfoObject.controls.lastName.value.length)
    //     this.menuObject.isCompleted = true;
    //   else
    //     this.menuObject.isCompleted = false;
    //   this.basicInfoObject.controls.firstName.valueChanges.subscribe();
    // });
  }

  onValChange() {
    if (this.basicInfoObject.controls.salutation.value.length && this.basicInfoObject.controls.firstName.value.length && this.basicInfoObject.controls.lastName.value.length)
      this.menuObject.isCompleted = true;
    else
      this.menuObject.isCompleted = false;
    this.pageProgressStatusCallBack.emit(this.menuObject);
  }

}
