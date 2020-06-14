import { NgModule } from '@angular/core';
import { PreloadAllModules, RouterModule, Routes } from '@angular/router';

import { AuthGuardService } from './services/auth-guard.service';

const routes: Routes = [
  {
    path: 'home',
    loadChildren: () =>
      import('./home/home.module').then((m) => m.HomePageModule),
  },
  {
    path: '',
    redirectTo: 'home',
    pathMatch: 'full',
  },
  {
    path: 'forms',
    loadChildren: () =>
      import('./forms/forms.module').then((m) => m.FormsPageModule),
    canActivate: [AuthGuardService],
  },
  {
    path: 'rating',
    loadChildren: () =>
      import('./rating/rating.module').then((m) => m.RatingPageModule),
  },
  {
    path: 'applicants',
    loadChildren: () =>
      import('./applicants/applicants.module').then(
        (m) => m.ApplicantsPageModule
      ),
  },
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, { preloadingStrategy: PreloadAllModules }),
  ],
  exports: [RouterModule],
})
export class AppRoutingModule {}
