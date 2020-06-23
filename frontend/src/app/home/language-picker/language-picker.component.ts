import { Component } from '@angular/core';
import { PopoverController } from '@ionic/angular';
import { TranslateService } from '@ngx-translate/core';

import { StorageHandlerService } from '../../services/storage-handler.service';

@Component({
  selector: 'app-language-picker',
  templateUrl: './language-picker.component.html',
  styleUrls: ['./language-picker.component.scss'],
})
export class LanguagePickerComponent {
  supportedLanguages = [
    { localeCode: 'en', displayName: 'English', isChosen: false },
    { localeCode: 'de', displayName: 'Duetsch', isChosen: false },
  ];
  switchLanguage(localeCode: string): void {
    this.translate.use(localeCode);
    this.setChosenLocale(localeCode);
    this.popoverController.dismiss();
  }

  setChosenLocale(localeCode: string): void {
    this.supportedLanguages.forEach((element) => {
      if (element.localeCode === localeCode) {
        element.isChosen = true;
      } else {
        element.isChosen = false;
      }
    });
    this.storage.updateItem(
      this.storage.commonPropertiesDb,
      'chosenLocale',
      localeCode
    );
  }
  constructor(
    private translate: TranslateService,
    private storage: StorageHandlerService,
    private popoverController: PopoverController
  ) {
    this.storage
      .getItem(this.storage.commonPropertiesDb, 'chosenLocale')
      .then((locale: string) => {
        this.setChosenLocale(locale);
      });
  }
}
