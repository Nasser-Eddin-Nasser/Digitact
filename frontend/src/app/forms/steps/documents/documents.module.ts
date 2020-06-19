import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { IonicModule } from '@ionic/angular';

import { FormsCommonModule } from '../../common/forms-common.module';

import { DocumentsComponent } from './documents.component';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    ReactiveFormsModule,
    FormsCommonModule,
  ],
  declarations: [DocumentsComponent],
  exports: [DocumentsComponent],
})
export class DocumentsModule {}
