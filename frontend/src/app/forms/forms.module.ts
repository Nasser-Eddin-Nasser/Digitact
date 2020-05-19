<<<<<<< HEAD
=======
/*
@Author
Bharathwaj Ravi

Add modifiers under @Modifiers
@Modifiers

@Purpose
  - This module serves as a common place where the form related pages, componets are handled.
*/

>>>>>>> a40cdd678f8684ffbf0ed13799cb2e793f90f684
import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { IonicModule } from '@ionic/angular';

import { FormsPageRoutingModule } from './forms-routing.module';
import { FormsPage } from './forms.page';
import { SideMenuComponent } from './side-menu/side-menu.component';
import { BasicInfoComponent } from './steps/basic-info/basic-info.component';
<<<<<<< HEAD
import { ContactInfoComponent } from './steps/contact-info/contact-info.component';
=======
>>>>>>> a40cdd678f8684ffbf0ed13799cb2e793f90f684

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    FormsPageRoutingModule,
    IonicModule,
    ReactiveFormsModule,
  ],
<<<<<<< HEAD
  declarations: [
    FormsPage,
    BasicInfoComponent,
    ContactInfoComponent,
    SideMenuComponent,
  ],
=======
  declarations: [FormsPage, BasicInfoComponent, SideMenuComponent],
>>>>>>> a40cdd678f8684ffbf0ed13799cb2e793f90f684
})
export class FormsPageModule {}
