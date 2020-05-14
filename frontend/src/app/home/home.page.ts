import { Component } from '@angular/core';
import { NavController } from '@ionic/angular';

import { FormControl, FormGroup } from '../common/forms/forms';

@Component({
  selector: 'app-home',
  templateUrl: 'home.page.html',
  styleUrls: ['home.page.scss'],
})
export class HomePage {
  checkOutForm = new FormGroup<CheckOutFormModel>({
    firstName: new FormControl(''),
    lastName: new FormControl(''),
  });

  constructor(private navController: NavController) {}

  myS(): void {
    console.log('firstName', this.checkOutForm.controls.firstName.value);

    this.navController.navigateForward([
      '/home',
      'done',
      this.checkOutForm.controls.firstName.value,
    ]);
  }
}

interface CheckOutFormModel {
  firstName: string;
  lastName: string;
}
