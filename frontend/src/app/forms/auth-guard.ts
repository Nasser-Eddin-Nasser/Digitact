/**
 * @description
 *   This page handles the basic operation of the Guard.
 */

import { Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  CanDeactivate,
  RouterStateSnapshot,
} from '@angular/router';
import { Observable } from 'rxjs';

import { FormsPage } from './forms.page';
import { FULL_IMAGE_VIEWER_PATH } from './router-constants';

@Injectable({
  providedIn: 'root',
})
export class AuthGuard implements CanDeactivate<FormsPage> {
  /**
   * CanDeactivate method of the Guard to prevent going back to the previous page accidentally without a warning
   */
  canDeactivate(
    component: FormsPage,
    _currentRoute: ActivatedRouteSnapshot,
    _currentState: RouterStateSnapshot,
    nextState?: RouterStateSnapshot
  ): Observable<boolean> | boolean {
    if (nextState?.url === FULL_IMAGE_VIEWER_PATH) {
      return true;
    }
    return component.mayLeaveView();
  }
}
