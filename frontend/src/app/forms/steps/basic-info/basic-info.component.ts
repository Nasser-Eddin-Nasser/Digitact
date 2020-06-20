/*
  @description
    This component renders the basic information step view and its actions.
*/
import { Component, Input } from '@angular/core';

import { FormGroup } from '../../../common/forms/forms';
import { FormsData } from '../../../model/forms-data.model';
import { TranslateService } from '@ngx-translate/core';
@Component({
  selector: 'form-basic-info',
  templateUrl: './basic-info.component.html',
  styleUrls: ['./basic-info.component.scss'],
})
export class BasicInfoComponent {
  constructor(private translate: TranslateService) {}
  /**
   * Data of the entire form.
   */
  @Input()
  formsData: FormGroup<FormsData>;

  /**
   * The possible salutations, displayed in the dropdown menu.
   */
  salutationsArray = [
    { value: 'Mr', displayName: this.translate.instant('basicInfo.mr') },
    { value: 'Mrs', displayName: this.translate.instant('basicInfo.mrs') },
  ];
}
