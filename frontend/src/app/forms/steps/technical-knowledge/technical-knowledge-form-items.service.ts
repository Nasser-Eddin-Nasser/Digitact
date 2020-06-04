import { Injectable } from '@angular/core';
import { ValidationErrors } from '@angular/forms';

import { FormArray, FormControl, FormGroup } from '../../../common/forms/forms';
import {
  TechnicalKnowledge,
  TechnicalKnowledgeEntry,
} from '../../../model/forms-data.model';

/**
 * This Service can/should be used to generate and reset form items for the Technical Knowledge step.
 */
@Injectable()
export class TechnicalKnowledgeFormItemsService {
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
   * A Validator that checks if there is at least one entry in the "professional Software" slot.
   *
   * This Validator is needed because we disable all form elements by default.
   * And this would lead to the parent being seen as "valid" in some cases.
   */
  private validateRootFormGroup(
    formGroup: FormGroup<TechnicalKnowledge>
  ): ValidationErrors | undefined {
    /*
       We use the FormArray children in the following way: If they are disabled, then we also don't want them to be part of the result.
       If 0 items are enabled within the FormArray, then the parent also becomes disabled.
       So: If the FormArray is disabled, then we know that 0 items have been selected.

       (To be really safe about this, we also check for the length of the value here.)
    */
    if (
      formGroup.controls.professionalSoftware.disabled ||
      formGroup.controls.professionalSoftware.value.length < 1
    ) {
      return {
        required: true,
      };
    }

    return undefined;
  }

  generateAllFormItems(): FormGroup<TechnicalKnowledge> {
    const result = new FormGroup<TechnicalKnowledge>(
      {
        professionalSoftware: new FormArray<TechnicalKnowledgeEntry>(
          this.generateItemsFromArray(this.PROFESSIONAL_SOFTWARE_ITEMS)
        ),
        databases: new FormArray<TechnicalKnowledgeEntry>(
          this.generateItemsFromArray(this.DATABASE_ITEMS)
        ),
        programmingLanguagesAndFrameworks: new FormArray<
          TechnicalKnowledgeEntry
        >(
          this.generateItemsFromArray(
            this.PROGRAMMING_LANGUAGE_AND_FRAMEWORKS_ITEMS
          )
        ),
      },
      this.validateRootFormGroup
    );

    return result;
  }

  /**
   * A helper method to generate all items for a FormArray, based on an array of strings that will be used for the names.
   */
  private generateItemsFromArray(
    arr: string[]
  ): FormGroup<TechnicalKnowledgeEntry>[] {
    const result: FormGroup<TechnicalKnowledgeEntry>[] = [];

    for (const name of arr) {
      const control = this.generateFormItem(name);
      result.push(control);
    }

    return result;
  }

  /**
   * Generate a single form item that can later be used in the FormArray.
   */
  generateFormItem(name: string): FormGroup<TechnicalKnowledgeEntry> {
    const result = new FormGroup<TechnicalKnowledgeEntry>({
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
  resetFormItem(item: FormGroup<TechnicalKnowledgeEntry>): void {
    item.reset({
      name: item.controls.name.value,
      rating: 1,
    });
    item.disable();
  }
}
