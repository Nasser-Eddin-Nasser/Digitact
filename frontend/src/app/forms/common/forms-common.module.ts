import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { IonicModule } from '@ionic/angular';

import { Base64ImageComponent } from './base64-image/base64-image.component';

@NgModule({
  imports: [CommonModule, IonicModule],
  declarations: [Base64ImageComponent],
  exports: [Base64ImageComponent],
})
export class FormsCommonModule {}
