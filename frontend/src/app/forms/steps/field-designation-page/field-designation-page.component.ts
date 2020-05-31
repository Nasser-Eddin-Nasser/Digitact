import { Component, Input } from '@angular/core';

import { FormGroup } from '../../../common/forms/forms';
import { FormsData } from '../../../model/forms-data.model';

@Component({
  selector: 'form-field-designation-page',
  templateUrl: './field-designation-page.component.html',
  styleUrls: ['./field-designation-page.component.scss'],
})
export class FieldDesignationPageComponent {
  @Input()
  formsData: FormGroup<FormsData>;

  industryArray = [
    { value: 'Automotive', displayName: 'Automotive' },
    { value: 'Finance', displayName: 'Finance' },
    { value: 'Commerce', displayName: 'Commerce' },
    { value: 'PharmaHealthcare', displayName: 'Pharma/Healthcare' },
    { value: 'PublicSector', displayName: 'Public Sector' },
  ];

  positionsArray = [
    {
      value: 'Consultant',
      displayName: 'Consultant/Business Consultant',
    },
    {
      value: 'ITConsultantInformationsmanagement',
      displayName: 'IT-Consultant - Informationsmanagement',
    },
    {
      value: 'ITConsultantJavaJEE',
      displayName: 'IT-Consultant - Java/JEE',
    },
    {
      value: 'ITConsultantDataScienceArtificialIntelligence',
      displayName: 'IT-Consultant - Data Science / Artificial Intelligence',
    },
    {
      value: 'ConsultantSAP',
      displayName: 'Consultant SAP',
    },
    {
      value: 'InternshipWorkingStudent',
      displayName: 'Internship/Working Student',
    },
  ];
}
