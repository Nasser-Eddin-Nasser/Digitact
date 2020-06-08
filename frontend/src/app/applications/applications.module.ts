/*
 * @description
 *   This module serves as a common place where appplication page components are handled.
 */
import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { IonicModule } from '@ionic/angular';

import { ApplicationsPageRoutingModule } from './applications-routing.module';
import { ApplicationsPage } from './applications.page';

@NgModule({
  imports: [CommonModule, IonicModule, ApplicationsPageRoutingModule],
  declarations: [ApplicationsPage],
})
export class ApplicationsPageModule {}
