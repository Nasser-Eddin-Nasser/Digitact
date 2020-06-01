import { Component, Input, OnInit } from '@angular/core';
import { ModalController } from '@ionic/angular';

import { FormGroup } from '../../../common/forms/forms';
import { EducationInfoEntry } from '../../../model/forms-data.model';

@Component({
  selector: 'forms-education-info-entry',
  templateUrl: './education-info-entry.component.html',
  styleUrls: ['./education-info-entry.component.scss'],
})
export class EducationInfoEntryComponent implements OnInit {
  constructor(public modalController: ModalController) {}

  degreeTypeArray = [
    { value: 'master', displayName: 'Master' },
    { value: 'bachelor', displayName: 'Bachelor' },
    { value: 'school', displayName: 'School' },
  ];

  @Input() edu: FormGroup<EducationInfoEntry>;

  save(): void {
    this.modalController.dismiss({
      canSubmitData: true,
    });
  }

  cancel(): void {
    this.modalController.dismiss({
      canSubmitData: false,
    });
  }

  ngOnInit(): void {
    console.log(this.edu);
  }
}
