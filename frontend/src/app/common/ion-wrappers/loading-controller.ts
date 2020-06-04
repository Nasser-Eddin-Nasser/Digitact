import { Injectable } from '@angular/core';
// tslint:disable-next-line: import-blacklist
import { LoadingController as IonLoadingController } from '@ionic/angular';
// tslint:disable-next-line: import-blacklist
import { LoadingOptions as IonLoadingOptions } from '@ionic/core';

import { ContentSecurityService } from './services/content-security.service';

@Injectable({
  providedIn: 'root',
})
export class LoadingController extends IonLoadingController {
  constructor(private contentSecurityService: ContentSecurityService) {
    super();
  }

  /**
   * Create a new loading overlay.
   * Please note that the passed options object might get changed.
   */
  create(opts: LoadingOptions): Promise<HTMLIonLoadingElement> {
    const newOpts = { ...opts };

    newOpts.message = this.contentSecurityService.escapeSecurely(
      newOpts.message
    );

    return super.create(newOpts);
  }
}

export interface LoadingOptions extends IonLoadingOptions {
  /**
   * For now, we only allow simple Strings and not the Ionic Safe Strings.
   */
  message: string;
}
