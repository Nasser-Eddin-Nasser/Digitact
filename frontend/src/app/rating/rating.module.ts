import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { IonicModule } from '@ionic/angular';

import { RatingPageRoutingModule } from './rating-routing.module';
import { RatingPage } from './rating.page';

@NgModule({
  imports: [CommonModule, FormsModule, IonicModule, RatingPageRoutingModule],
  declarations: [RatingPage],
})
export class RatingPageModule {}
