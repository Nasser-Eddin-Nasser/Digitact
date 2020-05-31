import { Component } from '@angular/core';

@Component({
  selector: 'forms-education-info-entry',
  templateUrl: './education-info-entry.component.html',
  styleUrls: ['./education-info-entry.component.scss'],
})
export class EducationInfoEntryComponent {
  degreeTypeArray = [
    { value: 'master', displayName: 'Master' },
    { value: 'bachelor', displayName: 'Bachelor' },
    { value: 'school', displayName: 'School' },
  ];
}
