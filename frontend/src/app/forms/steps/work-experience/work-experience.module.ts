import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { IonicModule } from '@ionic/angular';

import { SharedModule } from '../../../shared.module';

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
  declarations: [WorkExperienceComponent, WorkExperienceEntryComponent],
  exports: [WorkExperienceComponent],
})
export class WorkExperienceModule {}
