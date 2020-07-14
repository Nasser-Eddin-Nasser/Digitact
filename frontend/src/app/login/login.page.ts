/**
 * @description
 *    This page handles the login action
 */

import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { NavController } from '@ionic/angular';
import { TranslateService } from '@ngx-translate/core';

import { ToastController } from '../common/ion-wrappers/toast-controller';
import { StorageHandlerService } from '../services/storage-handler.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.page.html',
  styleUrls: ['./login.page.scss'],
})
export class LoginPage {
  /**
   * String that  holds the user name
   */
  userName: string;

  /**
   * String that  holds the password
   */
  password: string;

  /**
   * Holds host URL
   */
  apiHostUrl = 'http://localhost:9090';

  constructor(
    private navController: NavController,
    private httpClient: HttpClient,
    private storage: StorageHandlerService,
    private toastController: ToastController,
    private translate: TranslateService
  ) {}

  /**
   * In this method skip login action is handled
   */
  skip(): void {
    this.navController.navigateForward(['/home']);
  }

  /**
   * In this method register API is sent to server
   */
  login(): void {
    this.httpClient
      .post(
        this.apiHostUrl + '/api/controller/register',
        { userName: this.userName, password: this.password },
        {
          responseType: 'text',
          observe: 'response',
        }
      )
      .subscribe(
        (res) => {
          if (res.status === 201) {
            this.storage.addItem(
              this.storage.commonPropertiesDb,
              'deviceToken',
              res.headers.get('deviceauthorization')
            );
            this.storage.addItem(
              this.storage.commonPropertiesDb,
              'userToken',
              res.headers.get('userauthorization')
            );
            this.navController.navigateForward(['/home']);
          }
        },
        async (error) => {
          let toastMessage;
          if (error.status === 401) {
            toastMessage = this.translate.instant(
              'loginPage.credentialsDoesnotMatch'
            );
          } else {
            toastMessage = this.translate.instant(
              'loginPage.generalErrorMessage'
            );
          }
          const toast = await this.toastController.create({
            message: toastMessage,
            color: 'danger',
            position: 'bottom',
            duration: 4000,
          });
          toast.present();
        }
      );
  }
}
