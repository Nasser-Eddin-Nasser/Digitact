import { Component } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { NavController } from '@ionic/angular';

@Component({
  selector: 'app-home',
  templateUrl: 'home.page.html',
  styleUrls: ['home.page.scss'],
})
export class HomePage {
  checkOutForm = this.formBuilder.group({ firstName: [''], lastName: [''] });

  constructor(
    private formBuilder: FormBuilder,
    private navController: NavController
  ) {}
  myS(): void {
    console.log('firstName', this.checkOutForm.value);
    this.navController.navigateForward([
      '/home',
      'done',
      this.checkOutForm.get('firstName').value,
    ]);
  }
}
