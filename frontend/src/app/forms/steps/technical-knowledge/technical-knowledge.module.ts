import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { IonicModule } from '@ionic/angular';

import { ItemPopoverComponent } from './rating-modal/item-popover/item-popover.component';
import { RatingModalComponent } from './rating-modal/rating-modal.component';
import { RangeToPipe } from './rating-modal/stars/range-to.pipe';
import { StarsComponent } from './rating-modal/stars/stars.component';
import { TechnicalKnowledgeFormItemsService } from './technical-knowledge-form-items.service';
import { TechnicalKnowledgeComponent } from './technical-knowledge.component';

@NgModule({
  imports: [CommonModule, FormsModule, IonicModule, ReactiveFormsModule],
  declarations: [
    RatingModalComponent,
    ItemPopoverComponent,
    RangeToPipe,
    StarsComponent,
    TechnicalKnowledgeComponent,
  ],
  providers: [TechnicalKnowledgeFormItemsService],
  exports: [TechnicalKnowledgeComponent],
})
export class TechnicalKnowledgeModule {}
