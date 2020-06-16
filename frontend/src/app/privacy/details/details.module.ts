/**
 * @description
 *  This module lists privacy policy points in deatil.
 */
import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { IonicModule } from '@ionic/angular';

import { DetailsPageRoutingModule } from './details-routing.module';
import { DetailsPage } from './details.page';

@NgModule({
  imports: [CommonModule, FormsModule, IonicModule, DetailsPageRoutingModule],
  declarations: [DetailsPage],
})
export class DetailsPageModule {}
