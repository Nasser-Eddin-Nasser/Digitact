import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { IonicModule } from '@ionic/angular';

import { DonePageRoutingModule } from './done-routing.module';
import { DonePage } from './done.page';

@NgModule({
  imports: [CommonModule, FormsModule, IonicModule, DonePageRoutingModule],
  declarations: [DonePage],
})
export class DonePageModule {}
