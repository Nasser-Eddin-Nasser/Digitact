/*
 * @description
 *  This module serves as a common place where confirmation page components are handled.
 */
import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { IonicModule } from '@ionic/angular';

import { ConfirmationPageRoutingModule } from './confirmation-routing.module';
import { ConfirmationPage } from './confirmation.page';

@NgModule({
  imports: [CommonModule, IonicModule, ConfirmationPageRoutingModule],
  declarations: [ConfirmationPage],
})
export class ConfirmationPageModule {}
