import { Injectable } from '@angular/core';
// tslint:disable-next-line: import-blacklist
import { ToastController as IonToastController } from '@ionic/angular';
// tslint:disable-next-line: import-blacklist
import { ToastOptions as IonToastOptions } from '@ionic/core';

import { ContentSecurityService } from './services/content-security.service';

@Injectable({
  providedIn: 'root',
})
export class ToastController extends IonToastController {
  constructor(private contentSecurityService: ContentSecurityService) {
    super();
  }

  /**
   * Create a new toast.
   * Please note that the passed options object might get changed.
   */
  create(opts: ToastOptions): Promise<HTMLIonToastElement> {
    const newOpts = { ...opts };

    newOpts.message = this.contentSecurityService.escapeSecurely(
      newOpts.message
    );

    return super.create(newOpts);
  }
}

export interface ToastOptions extends IonToastOptions {
  /**
   * For now, we only allow simple Strings and not the Ionic Safe Strings.
   */
  message: string;
}
