/**
 * This pipe is used to get corresponding locale string for the given degree type.
 */

import { Pipe, PipeTransform } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';

@Pipe({
  name: 'getI18NStringForDegree',
})
export class GetI18nStringForDegreePipe implements PipeTransform {
  constructor(private translate: TranslateService) {}

  transform(degree: string): string {
    let localizedString: string;
    switch (degree) {
      case 'Master': {
        localizedString = this.translate.instant('educationInfo.master');
        break;
      }
      case 'Bachelor': {
        localizedString = this.translate.instant('educationInfo.bachelor');
        break;
      }
      case 'School': {
        localizedString = this.translate.instant('educationInfo.school');
        break;
      }
    }
    return localizedString;
  }
}
