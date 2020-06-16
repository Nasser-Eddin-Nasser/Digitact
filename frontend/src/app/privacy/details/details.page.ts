/*
  @description
    This page lists the policy details
*/
import { Component } from '@angular/core';
import { NavController } from '@ionic/angular';

@Component({
  selector: 'app-details',
  templateUrl: './details.page.html',
  styleUrls: ['./details.page.scss'],
})
export class DetailsPage {
  constructor(private navigationController: NavController) {}
  /**
   * In this method navigation to privacy page is handled
   */
  navigateToPolicyPage(): void {
    this.navigationController.navigateBack(['/privacy']);
  }
}
