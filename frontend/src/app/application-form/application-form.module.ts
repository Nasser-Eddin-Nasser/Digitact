import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { IonicModule } from '@ionic/angular';

import { ApplicationFormPageRoutingModule } from './application-form-routing.module';
import { ApplicationFormPage } from './application-form.page';
import { BasicInformationComponent } from './form-steps/basic-information/basic-information.component';
import { ContactInformationComponent } from './form-steps/contact-information/contact-information.component';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    ApplicationFormPageRoutingModule,
  ],
  declarations: [
    ApplicationFormPage,
    BasicInformationComponent,
    ContactInformationComponent,
  ],
})
export class ApplicationFormPageModule {}
