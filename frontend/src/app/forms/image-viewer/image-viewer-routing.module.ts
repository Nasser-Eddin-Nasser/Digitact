import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ImageViewerComponent } from './internal/image-viewer.component';

// Important: Do not route to the Image Viewer on your own. Instead, please use the provided Service for that.
const routes: Routes = [
  {
    path: '',
    component: ImageViewerComponent,
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ImageViewerRoutingModule {}
