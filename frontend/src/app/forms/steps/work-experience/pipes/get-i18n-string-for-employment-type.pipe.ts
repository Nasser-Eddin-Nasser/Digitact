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
    let localizedString:string='';
    switch(employmentType){
      case 'FullTime': localizedString=this.translate.instant('workExperience.fullTime');
      case 'PartTime': localizedString=this.translate.instant('workExperience.partTime');
      case 'SelfEmployed': localizedString=this.translate.instant('workExperience.selfEmployed');
      case 'Freelance': localizedString=this.translate.instant('workExperience.freelance');
      case 'Contract': localizedString=this.translate.instant('workExperience.contract');
      case 'Internship': localizedString=this.translate.instant('workExperience.internship');
      case 'Apprenticeship': localizedString=this.translate.instant('workExperience.apprenticeship');
      case 'Other': localizedString=this.translate.instant('workExperience.other');
      }
    return localizedString;
  }

}