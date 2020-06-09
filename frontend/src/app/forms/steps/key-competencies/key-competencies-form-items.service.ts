import { Injectable } from '@angular/core';

import { FormArray, FormControl, FormGroup } from '../../../common/forms/forms';
import {
  KeyCompetencies,
  KeyCompetenciesEntry,
} from '../../../model/forms-data.model';

/**
 * This Service can/should be used to generate and reset form items for the Key Competencies step.
 * It should only be used within this module.
 *
 * ## Important!
 * Outside of this module, we use FormControls in order to store the arrays of already added entries.
 * Within this module, we use FormArrays instead.
 *
 * The reason: The FormArrays provide better flexibility.
 * But: We disable the child controls by default, so that they aren't part of the aggregated value.
 * Due to the fact that all entries are disabled by default, the parents also become disabled.
 * And this leads to all kinds of funny bugs, like Validators being ignored, etc.
 * Because of this, we use the FormControl on the outside,
 * so that people can use our form without having to think about the details of this module.
 */
@Injectable()
export class KeyCompetenciesFormItemsService {
  private readonly LANGUAGE_ITEMS = [
    'Arabic',
    'Chinese',
    'Czech',
    'English',
    'Finnish',
    'French',
    'German',
    'Greek',
    'Hungarian',
    'Italian',
    'Japanese',
    'Korean',
    'Norwegian',
    'Polish',
    'Portuguese',
    'Russian',
    'Spanish',
    'Swedish',
    'Turkish',
  ];

  private readonly PROFESSIONAL_SOFTWARE_ITEMS = [
    'Adobe Photoshop',
    'Adobe InDesign',
    'Microsoft Excel',
    'Microsoft Powerpoint',
    'Microsoft Word',
  ];

  private readonly DATABASE_ITEMS = [
    'MariaDB',
    'MongoDB',
    'MySQL',
    'PostgreSQL',
  ];

  private readonly PROGRAMMING_LANGUAGE_AND_FRAMEWORKS_ITEMS = [
    // Programming languages
    'C',
    'C++',
    'C#',
    'Go',
    'Java',
    'JavaScript',
    'Kotlin',
    'Objective-C',
    'PHP',
    'Python',
    'Swift',
    'TypeScript',
    'R',

    // Frameworks
    'Angular',
    'Cordova/PhoneGap',
    'Ionic',
    'NativeScript',
    'React',
    'React Native',
    'Vue',
  ];

  /**
   * Generate all form items (so mainly the FormArrays and their content),
   * based on the "basis" provided as parameter.
   */
  generateAllFormItems(
    basis: FormGroup<KeyCompetencies>
  ): FormGroup<KeyCompetenciesInternal> {
    // No need to set any Validators here. This needs to be done on the "external" form.

    const result = new FormGroup<KeyCompetenciesInternal>({
      languages: new FormArray<KeyCompetenciesEntry>(
        this.generateItems(this.LANGUAGE_ITEMS, basis.controls.languages)
      ),
      professionalSoftware: new FormArray<KeyCompetenciesEntry>(
        this.generateItems(
          this.PROFESSIONAL_SOFTWARE_ITEMS,
          basis.controls.professionalSoftware
        )
      ),
      databases: new FormArray<KeyCompetenciesEntry>(
        this.generateItems(this.DATABASE_ITEMS, basis.controls.databases)
      ),
      programmingLanguagesAndFrameworks: new FormArray<KeyCompetenciesEntry>(
        this.generateItems(
          this.PROGRAMMING_LANGUAGE_AND_FRAMEWORKS_ITEMS,
          basis.controls.programmingLanguagesAndFrameworks
        )
      ),
    });

    return result;
  }

  /**
   * A helper method to generate all items for a FormArray, based on an array of strings that will be used for the names.
   */
  private generateItems(
    defaultEntries: string[],
    alreadyKnownItems: FormControl<KeyCompetenciesEntry[]>
  ): FormGroup<KeyCompetenciesEntry>[] {
    const result: FormGroup<KeyCompetenciesEntry>[] = [];

    const alreadyAddedNames: { [key: string]: true } = {};
    for (const item of alreadyKnownItems.value) {
      const control = this.generateEnabledFormItem(item.name, item.rating);
      result.push(control);

      alreadyAddedNames[item.name] = true;
    }

    for (const name of defaultEntries) {
      if (alreadyAddedNames[name]) {
        continue;
      }

      // We don't modfiy the "alreadyAddedNames" object here, since we simply expect the base array not to include any duplicates.

      const control = this.generateDefaultFormItem(name);
      result.push(control);
    }

    return result;
  }

  /**
   * Generate a form item that can later be used in the FormArray, without disabling it by default.
   */
  private generateEnabledFormItem(
    name: string,
    rating: number
  ): FormGroup<KeyCompetenciesEntry> {
    const result = new FormGroup<KeyCompetenciesEntry>({
      name: new FormControl(name),
      rating: new FormControl(rating),
    });

    return result;
  }

  /**
   * Generate a single form item that can later be used in the FormArray.
   */
  generateDefaultFormItem(name: string): FormGroup<KeyCompetenciesEntry> {
    const result = new FormGroup<KeyCompetenciesEntry>({
      name: new FormControl(name),
      rating: new FormControl(1),
    });

    result.disable();

    return result;
  }

  /**
   * Reset a single item of our FormArray to its initial state.
   * (The items are disabled by default, so this method will also disable the item.)
   */
  resetFormItem(item: FormGroup<KeyCompetenciesEntry>): void {
    item.reset({
      name: item.controls.name.value,
      rating: 1,
    });
    item.disable();
  }
}

/**
 * "Externally" (outside this module), we use a FormControl that holds the values.
 * "Internally" (within this module), we use a FormArray.
 */
export type KeyCompetenciesInternal = {
  [key in keyof KeyCompetencies]: KeyCompetenciesEntry[];
};
