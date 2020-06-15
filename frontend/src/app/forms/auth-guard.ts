import { Injectable } from '@angular/core';
import { CanDeactivate } from '@angular/router';
import { Observable } from 'rxjs';

import { FormsPage } from './forms.page';

@Injectable({
  providedIn: 'root',
})
export class AuthGuard implements CanDeactivate<FormsPage> {
  canDeactivate(component: FormsPage): Observable<boolean> | boolean {
    return component.mayLeaveView();
  }
}
