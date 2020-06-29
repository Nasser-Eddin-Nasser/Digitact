import { Component, Input } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';

import { FormGroup } from '../../../common/forms/forms';
import { FormsData } from '../../../model/forms-data.model';
@Component({
  selector: 'form-field-designation-page',
  templateUrl: './field-designation-page.component.html',
  styleUrls: ['./field-designation-page.component.scss'],
})
export class FieldDesignationPageComponent {
  constructor(private translate: TranslateService) {}
  @Input()
  formsData: FormGroup<FormsData>;

  industryArray = [
    {
      value: 'Automotive',
      displayName: this.translate.instant(
        'industryAndPositionPreference.automotive'
      ),
    },
    {
      value: 'Finance',
      displayName: this.translate.instant(
        'industryAndPositionPreference.finance'
      ),
    },
    {
      value: 'Commerce',
      displayName: this.translate.instant(
        'industryAndPositionPreference.commerce'
      ),
    },
    {
      value: 'PharmaHealthcare',
      displayName: this.translate.instant(
        'industryAndPositionPreference.healthCare'
      ),
    },
    {
      value: 'PublicSector',
      displayName: this.translate.instant(
        'industryAndPositionPreference.publicSector'
      ),
    },
  ];

  positionsArray = [
    {
      value: 'Consultant',
      displayName: this.translate.instant(
        'industryAndPositionPreference.businessConsultant'
      ),
    },
    {
      value: 'ITConsultantInformationsmanagement',
      displayName: this.translate.instant(
        'industryAndPositionPreference.itConsultantManagement'
      ),
    },
    {
      value: 'ITConsultantJavaJEE',
      displayName: this.translate.instant(
        'industryAndPositionPreference.itConsultantJava'
      ),
    },
    {
      value: 'ITConsultantDataScienceArtificialIntelligence',
      displayName: this.translate.instant(
        'industryAndPositionPreference.itConsultantDataScience'
      ),
    },
    {
      value: 'ConsultantSAP',
      displayName: this.translate.instant(
        'industryAndPositionPreference.consultantSAP'
      ),
    },
    {
      value: 'InternshipWorkingStudent',
      displayName: this.translate.instant(
        'industryAndPositionPreference.internship'
      ),
    },
  ];
}
