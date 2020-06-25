import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { IonicModule } from '@ionic/angular';

import { SharedModule } from '../../../shared.module';

import { EducationInfoEntryModalComponent } from './education-info-entry/education-info-entry.component';
import { EducationInfoComponent } from './education-info.component';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    ReactiveFormsModule,
    SharedModule,
  ],
  declarations: [EducationInfoComponent, EducationInfoEntryModalComponent],
  exports: [EducationInfoComponent],
})
export class EducationInfoModule {}
