import { Injectable } from '@angular/core';
import { Validators } from '@angular/forms';

import { FormArray, FormControl, FormGroup } from '../../../common/forms/forms';
import {
  TechnicalKnowledge,
  TechnicalKnowledgeEntry,
} from '../../../model/forms-data.model';

@Injectable()
export class TechnicalKnowledgeFormItemsService {
  private readonly PROFESSIONAL_SOFTWARE_ITEMS = [
    'Adobe Photoshop',
    'Adobe InDesign',
    'Microsoft Excel',
    'Microsoft Powerpoint',
    'Microsoft Word',
  ];

  private readonly DATABASE_ITEMS = ['MySQL', 'PostgreSQL'];

  private readonly PROGRAMMING_LANGUAGE_ITEMS = [
    'Java',
    'JavaScript',
    'Python',
  ];

  generateAllFormItems(): FormGroup<TechnicalKnowledge> {
    const result = new FormGroup<TechnicalKnowledge>({
      professionalSoftware: new FormArray<TechnicalKnowledgeEntry>(
        this.generateItemsFromArray(this.PROFESSIONAL_SOFTWARE_ITEMS),
        Validators.required
      ),
      databases: new FormArray<TechnicalKnowledgeEntry>(
        this.generateItemsFromArray(this.DATABASE_ITEMS)
      ),
      programmingLanguages: new FormArray<TechnicalKnowledgeEntry>(
        this.generateItemsFromArray(this.PROGRAMMING_LANGUAGE_ITEMS)
      ),
    });

    return result;
  }

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