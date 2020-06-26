/*
 * @description
 *   This module serves as a common place where appplication upload page components are handled.
 */
import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { IonicModule } from '@ionic/angular';

import { SharedModule } from '../../shared.module';

import { ApplicationsUploadPageRoutingModule } from './applications-upload-routing.module';
import { ApplicationsUploadPage } from './applications-upload.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    ApplicationsUploadPageRoutingModule,
    SharedModule,
  ],
  declarations: [ApplicationsUploadPage],
})
export class ApplicationsUploadPageModule {}
