/*
  @description
    This page handles the acceptance of privacy policy and navigation to forms
*/
import { Component } from '@angular/core';
import { NavController } from '@ionic/angular';

import { FormControl } from '../common/forms/forms';

@Component({
  selector: 'app-privacy',
  templateUrl: './privacy.page.html',
  styleUrls: ['./privacy.page.scss'],
})
export class PrivacyPage {
  constructor(private navigationController: NavController) {}

  /**
   * The FormControl that is attached to our Toggle element.
   */
  policyToggle = new FormControl<boolean>(false);

  /**
   * Go (back) to the home page.
   */
  navigateToHomePage(): void {
    this.navigationController.navigateBack(['/home']);
  }

  /**
   * Go to the page that displays the Privacy Policy.
   */
  openPolicyDetails(): void {
    this.navigationController.navigateForward(['/privacy', 'details']);
  }

  /**
   * Go to the forms page.
   */
  startApplication(): void {
    this.navigationController.navigateRoot(['/forms'], {
      animationDirection: 'forward',
    });
  }
}
