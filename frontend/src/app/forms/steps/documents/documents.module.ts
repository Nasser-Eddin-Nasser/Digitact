import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { IonicModule } from '@ionic/angular';

import { DocumentsComponent } from './documents.component';
import { SharedModule } from '../../../shared.module';
@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    ReactiveFormsModule,
    SharedModule,
  ],
  declarations: [DocumentsComponent],
  exports: [DocumentsComponent],
})
export class DocumentsModule {}
