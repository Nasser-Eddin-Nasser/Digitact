import { Injectable } from '@angular/core';
// tslint:disable-next-line: import-blacklist
import { AlertController as IonAlertController } from '@ionic/angular';
// tslint:disable-next-line: import-blacklist
import { AlertOptions as IonAlertOptions } from '@ionic/core';

import { ContentSecurityService } from './services/content-security.service';

@Injectable({
  providedIn: 'root',
})
export class AlertController extends IonAlertController {
  constructor(private contentSecurityService: ContentSecurityService) {
    super();
  }

  /**
   * Create a new alert.
   * Please note that the passed options object might get changed.
   */
  create(opts: AlertOptions): Promise<HTMLIonAlertElement> {
    const newOpts = { ...opts };

    newOpts.message = this.contentSecurityService.escapeSecurely(
      newOpts.message
    );

    return super.create(newOpts);
  }
}

export interface AlertOptions extends IonAlertOptions {
  /**
   * For now, we only allow simple Strings and not the Ionic Safe Strings.
   */
  message: string;
}
