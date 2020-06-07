/**
 * @description
 *    This page holds the apllications to be finalized and sent to HR
 */
import { Component, Input } from '@angular/core';
import { NavController } from '@ionic/angular';

import { FormGroup } from '../common/forms/forms';
import { FormsData } from '../model/forms-data.model';

@Component({
  selector: 'app-applications',
  templateUrl: './applications.page.html',
  styleUrls: ['./applications.page.scss'],
})
export class ApplicationsPage {
  @Input()
  formsData: FormGroup<FormsData>;
  constructor(private navController: NavController) {}

  goToRating(): void {
    this.navController.navigateForward(['/rating']);
  }

  goBack(): void {
    this.navController.navigateBack(['/home']);
  }

  sendToHrMonitor(): void {
    console.log('to be implemented');
  }
}
