import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { IonicModule } from '@ionic/angular';

import { SharedModule } from '../../shared.module';
import { FormsCommonModule } from '../common/forms-common.module';

import { ImageViewerRoutingModule } from './image-viewer-routing.module';
import { ImageViewerComponent } from './internal/image-viewer.component';
@NgModule({
  imports: [
    CommonModule,
    IonicModule,
    ImageViewerRoutingModule,
    FormsCommonModule,
    SharedModule,
  ],
  declarations: [ImageViewerComponent],
})
export class ImageViewerModule {}
