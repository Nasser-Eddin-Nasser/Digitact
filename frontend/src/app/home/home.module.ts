import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { IonicModule } from '@ionic/angular';

import { SharedModule } from '../shared.module';

import { HomePageRoutingModule } from './home-routing.module';
import { HomePage } from './home.page';
import { LanguagePickerComponent } from './language-picker/language-picker.component';

@NgModule({
  imports: [CommonModule, IonicModule, HomePageRoutingModule, SharedModule],
  declarations: [HomePage, LanguagePickerComponent],
  exports: [],
})
export class HomePageModule {}
