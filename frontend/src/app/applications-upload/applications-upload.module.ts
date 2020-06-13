import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { ApplicationsUploadPageRoutingModule } from './applications-upload-routing.module';

import { ApplicationsUploadPage } from './applications-upload.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    ApplicationsUploadPageRoutingModule
  ],
  declarations: [ApplicationsUploadPage]
})
export class ApplicationsUploadPageModule {}
