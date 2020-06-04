import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { IonicModule } from '@ionic/angular';

import { ItemPopoverComponent } from './item-popover/item-popover.component';
import { RatingModalComponent } from './rating-modal.component';

@NgModule({
  imports: [CommonModule, FormsModule, IonicModule, ReactiveFormsModule],
  declarations: [RatingModalComponent, ItemPopoverComponent],
  exports: [RatingModalComponent],
})
export class RatingModalModule {}
