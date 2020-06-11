import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { IonicModule } from '@ionic/angular';

import { KeyCompetenciesFormItemsService } from './key-competencies-form-items.service';
import { KeyCompetenciesComponent } from './key-competencies.component';
import { ItemPopoverComponent } from './rating-modal/item-popover/item-popover.component';
import { TrimPipe } from './rating-modal/pipes/trim.pipe';
import { RatingModalComponent } from './rating-modal/rating-modal.component';
import { RangeToPipe } from './rating-modal/stars/pipes/range-to.pipe';
import { StarsComponent } from './rating-modal/stars/stars.component';

@NgModule({
  imports: [CommonModule, FormsModule, IonicModule, ReactiveFormsModule],
  declarations: [
    ItemPopoverComponent,
    KeyCompetenciesComponent,
    RangeToPipe,
    RatingModalComponent,
    StarsComponent,
    TrimPipe,
  ],
  providers: [KeyCompetenciesFormItemsService],
  exports: [KeyCompetenciesComponent],
})
export class KeyCompetenciesModule {}
