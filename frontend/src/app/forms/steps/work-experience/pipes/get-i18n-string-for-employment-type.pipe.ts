/**
 * This pipe is used to get corresponding locale string for the given employment type.
 */

import { Pipe, PipeTransform } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';

@Pipe({
  name: 'getI18NStringForEmploymentType'
})
export class GetI18nStringForEmploymentTypePipe implements PipeTransform {

  constructor(private translate: TranslateService) {}

  transform(employmentType:string): string {
    let localizedString:string;
    switch(employmentType){
      case 'FullTime': {localizedString=this.translate.instant('workExperience.fullTime');break;}
      case 'PartTime': {localizedString=this.translate.instant('workExperience.partTime');break;}
      case 'SelfEmployed': {localizedString=this.translate.instant('workExperience.selfEmployed');break;}
      case 'Freelance': {localizedString=this.translate.instant('workExperience.freelance');break;}
      case 'Contract': {localizedString=this.translate.instant('workExperience.contract');break;}
      case 'Internship': {localizedString=this.translate.instant('workExperience.internship');break;}
      case 'Apprenticeship':{ localizedString=this.translate.instant('workExperience.apprenticeship');break;}
      case 'Other': {localizedString=this.translate.instant('workExperience.other');break;}
      }
    return localizedString;
  }

}