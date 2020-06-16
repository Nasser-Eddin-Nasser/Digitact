/*
  @description
    This page handles the acceptance of privacy policy and navigation to forms
*/
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { NavController } from '@ionic/angular';

@Component({
  selector: 'app-privacy',
  templateUrl: './privacy.page.html',
  styleUrls: ['./privacy.page.scss'],
})
export class PrivacyPage {
  constructor(
    private navigationController: NavController,
    private router: Router
  ) {}

  /**
   * Used to toggle continue button on acceptance of policy changes.
   */
  isPolicyAccepted = false;

  /**
   * In this method navigation to home page is handled
   */
  navigateToHomePage(): void {
    this.navigationController.navigateBack(['/home']);
  }

  /**
   * In this method navigation to policy details page is handled
   */
  openPolicydetails(): void {
    this.router.navigate(['/privacy/details']);
  }

  /**
   * In this method navigation to forms page is handled
   */
  startApplication(): void {
    this.navigationController.navigateForward(['/forms']);
  }

  /**
   * In this method isPolicyAccepted property is toggled
   */
  togglePrivacyPolicyAcceptance(): void {
    this.isPolicyAccepted = !this.isPolicyAccepted;
  }
}
