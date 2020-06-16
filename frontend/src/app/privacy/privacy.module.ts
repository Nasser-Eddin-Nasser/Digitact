/**
 * @description
 *  This module handles privacy policy and its details.
 */
import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { IonicModule } from '@ionic/angular';

import { PrivacyPageRoutingModule } from './privacy-routing.module';
import { PrivacyPage } from './privacy.page';

@NgModule({
  imports: [CommonModule, FormsModule, IonicModule, PrivacyPageRoutingModule],
  declarations: [PrivacyPage],
})
export class PrivacyPageModule {}
