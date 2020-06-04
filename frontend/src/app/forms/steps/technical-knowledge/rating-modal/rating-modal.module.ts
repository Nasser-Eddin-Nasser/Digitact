import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { IonicModule } from '@ionic/angular';

import { ItemPopoverComponent } from './item-popover/item-popover.component';
import { RatingModalComponent } from './rating-modal.component';
import { RangeToPipe } from './stars/range-to.pipe';
import { StarsComponent } from './stars/stars.component';

@NgModule({
  imports: [CommonModule, FormsModule, IonicModule, ReactiveFormsModule],
  declarations: [
    RatingModalComponent,
    ItemPopoverComponent,
    RangeToPipe,
    StarsComponent,
  ],
  exports: [RatingModalComponent],
})
export class RatingModalModule {}
