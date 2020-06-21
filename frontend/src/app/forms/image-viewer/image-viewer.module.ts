import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { IonicModule } from '@ionic/angular';

import { FormsCommonModule } from '../common/forms-common.module';

import { ImageViewerRoutingModule } from './image-viewer-routing.module';
import { ImageViewerComponent } from './internal/image-viewer.component';

@NgModule({
  imports: [
    CommonModule,
    IonicModule,
    ImageViewerRoutingModule,
    FormsCommonModule,
  ],
  declarations: [ImageViewerComponent],
})
export class ImageViewerModule {}
