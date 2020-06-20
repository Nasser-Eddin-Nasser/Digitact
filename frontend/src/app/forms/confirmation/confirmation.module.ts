/*
 * @description
 *  This module serves as a common place where confirmation page components are handled.
 */
import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { IonicModule } from '@ionic/angular';

import { ConfirmationPageRoutingModule } from './confirmation-routing.module';
import { ConfirmationPage } from './confirmation.page';
import { SharedModule } from '../../shared.module';
@NgModule({
  imports: [
    CommonModule,
    IonicModule,
    ConfirmationPageRoutingModule,
    SharedModule,
  ],
  declarations: [ConfirmationPage],
})
export class ConfirmationPageModule {}
