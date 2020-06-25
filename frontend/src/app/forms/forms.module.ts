/**
 * @description
 *  This module serves as a common place where the form related pages, componets are handled.
 */

import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { IonicModule } from '@ionic/angular';

import { SharedModule } from '../shared.module';

import { FormsPageRoutingModule } from './forms-routing.module';
import { FormsPage } from './forms.page';
import { SideMenuComponent } from './side-menu/side-menu.component';
import { AdditionalInfoComponent } from './steps/additional-info/additional-info.component';
import { BasicInfoComponent } from './steps/basic-info/basic-info.component';
import { ContactInfoComponent } from './steps/contact-info/contact-info.component';
import { DocumentsModule } from './steps/documents/documents.module';
import { EducationInfoModule } from './steps/education-info/education-info.module';
import { FieldDesignationPageComponent } from './steps/field-designation-page/field-designation-page.component';
import { KeyCompetenciesModule } from './steps/key-competencies/key-competencies.module';
import { ProfilePictureComponent } from './steps/profile-picture/profile-picture.component';
import { SubmitPageComponent } from './steps/submit-page/submit-page.component';
import { WorkExperienceEntryComponent } from './steps/work-experience/work-experience-entry/work-experience-entry.component';
import { WorkExperienceComponent } from './steps/work-experience/work-experience.component';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    FormsPageRoutingModule,
    IonicModule,
    ReactiveFormsModule,
    DocumentsModule,
    EducationInfoModule,
    KeyCompetenciesModule,
    SharedModule,
  ],
  declarations: [
    FormsPage,
    BasicInfoComponent,
    SideMenuComponent,
    SubmitPageComponent,
    ContactInfoComponent,
    FieldDesignationPageComponent,
    ProfilePictureComponent,
    AdditionalInfoComponent,
    WorkExperienceComponent,
    WorkExperienceEntryComponent,
  ],
})
export class FormsPageModule {}
