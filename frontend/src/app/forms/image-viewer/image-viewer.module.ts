import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { IonicModule } from '@ionic/angular';

import { ImageViewerRoutingModule } from './image-viewer-routing.module';
import { ImageViewerComponent } from './internal/image-viewer.component';
import { SharedModule } from '../../shared.module';
@NgModule({
  imports: [CommonModule, IonicModule, ImageViewerRoutingModule, SharedModule],
  declarations: [ImageViewerComponent],
})
export class ImageViewerModule {}
