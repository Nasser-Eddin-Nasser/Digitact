/**
 * @description
 * - This component renders the confirmation view and it's actions.
 */
import { Component } from '@angular/core';
import { NavController } from '@ionic/angular';

@Component({
  selector: 'app-confirmation',
  templateUrl: './confirmation.page.html',
  styleUrls: ['./confirmation.page.scss'],
})
export class ConfirmationPage {
  constructor(private navController: NavController) {}

  /**
   * In this method navigation to HR form is handled.
   */
  navigateToNextPage(): void {
    // Clear the whole navigation stack, but still show a "forward" animation.
    this.navController.navigateRoot(['/rating'], {
      animationDirection: 'forward',
    });
  }

  /**
   * In this method navigation to home page is handled.
   */
  quitPage(): void {
    this.navController.navigateBack(['/home']);
  }
}
