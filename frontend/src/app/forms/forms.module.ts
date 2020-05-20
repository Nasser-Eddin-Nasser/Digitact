/*
@Author
Bharathwaj Ravi

Add modifiers under @Modifiers
@Modifiers

@Purpose
  - This module serves as a common place where the form related pages, componets are handled.
*/
import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { IonicModule } from '@ionic/angular';

import { FormsPageRoutingModule } from './forms-routing.module';
import { FormsPage } from './forms.page';
import { SideMenuComponent } from './side-menu/side-menu.component';
import { BasicInfoComponent } from './steps/basic-info/basic-info.component';
import { ContactInfoComponent } from './steps/contact-info/contact-info.component';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    FormsPageRoutingModule,
    IonicModule,
    ReactiveFormsModule,
  ],
  declarations: [
    FormsPage,
    BasicInfoComponent,
    ContactInfoComponent,
    SideMenuComponent,
  ],
})
export class FormsPageModule {}
