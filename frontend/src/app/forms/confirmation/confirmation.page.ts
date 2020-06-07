/**
 * @description
 * - This component renders the confirmation view and it's actions.
 */
import { Component } from '@angular/core';
import { NavController } from '@ionic/angular';
import { Router } from '@angular/router';

@Component({
  selector: 'app-confirmation',
  templateUrl: './confirmation.page.html',
  styleUrls: ['./confirmation.page.scss'],
})
export class ConfirmationPage {
  userId: string;

  constructor(private navController: NavController, private router: Router) {
    this.userId = this.router.getCurrentNavigation().extras.state.id;
  }

  /**
   * In this method navigation to HR form is handled.
   */
  navigateToNextPage(): void {
    let id = this.userId;
    // Clear the whole navigation stack, but still show a "forward" animation.
    this.navController.navigateRoot(['/rating'], {
      animationDirection: 'forward',
      state: { id: id },
    });
  }

  /**
   * In this method navigation to home page is handled.
   */
  quitPage(): void {
    this.navController.navigateBack(['/home']);
  }
}
