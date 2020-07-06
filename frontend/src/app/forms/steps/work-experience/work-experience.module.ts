import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { IonicModule } from '@ionic/angular';

import { SharedModule } from '../../../shared.module';

import { GetI18nStringForEmploymentTypePipe } from './pipes/get-i18n-string-for-employment-type.pipe';
import { WorkExperienceEntryComponent } from './work-experience-entry/work-experience-entry.component';
import { WorkExperienceComponent } from './work-experience.component';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    ReactiveFormsModule,
    SharedModule,
  ],
  declarations: [
    WorkExperienceComponent,
    WorkExperienceEntryComponent,
    GetI18nStringForEmploymentTypePipe,
  ],
  exports: [WorkExperienceComponent],
})
export class WorkExperienceModule {}
