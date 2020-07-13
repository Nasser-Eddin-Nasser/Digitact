import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { StorageHandlerService } from '../services/storage-handler.service';
import { NavController } from '@ionic/angular';

@Component({
  selector: 'app-login',
  templateUrl: './login.page.html',
  styleUrls: ['./login.page.scss'],
})
export class LoginPage {
  userName: string;
  password: string;
  apiHostUrl = 'http://localhost:9090';

  constructor(
    private navController: NavController,
    private httpClient: HttpClient,
    private storage: StorageHandlerService
  ) {}

  skip(): void {
    this.navController.navigateForward(['/home']);
  }

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
        () => {
          alert('Not autheticated');
        }
      );
  }
}
