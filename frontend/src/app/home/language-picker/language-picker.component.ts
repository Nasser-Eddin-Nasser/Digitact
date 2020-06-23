/*
  @description
    This component renders the language chooser popover.
*/

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
  /**
   * Holds the supoorted languages code, display name and chosen flag.
   */
  supportedLanguages = [
    { localeCode: 'en', displayName: 'English', isChosen: false },
    { localeCode: 'de', displayName: 'Duetsch', isChosen: false },
  ];

  /**
   * This method  initiate translation
   * @param localeCode : String that holds locale code
   */
  switchLanguage(localeCode: string): void {
    this.translate.use(localeCode);
    this.setChosenLocale(localeCode);
    this.popoverController.dismiss();
  }

  /**
   * This method sets the locale in storage and update isChosen flag.
   * @param localeCode : String that holds locale code
   */
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
