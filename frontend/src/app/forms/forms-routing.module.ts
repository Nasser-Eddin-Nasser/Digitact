/**
 * @description
 *   This page has the route path configuration in forms module.
 */
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AuthGuard } from './auth-guard';
import { FormsPage } from './forms.page';

const routes: Routes = [
  {
    path: '',
    component: FormsPage,
    canDeactivate: [AuthGuard],
  },
  {
    path: 'confirmation',
    loadChildren: () =>
      import('./confirmation/confirmation.module').then(
        (m) => m.ConfirmationPageModule
      ),
  },
  // Important: Do not route to the Image Viewer on your own. Instead, please use the provided Service for that.
  {
    path: 'image-viewer',
    loadChildren: () =>
      import('./image-viewer/image-viewer.module').then(
        (m) => m.ImageViewerModule
      ),
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class FormsPageRoutingModule {}
