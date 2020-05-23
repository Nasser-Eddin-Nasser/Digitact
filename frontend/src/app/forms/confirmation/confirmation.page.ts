/*
@Author
Vishwas Anavatti

Add modifiers under @Modifiers
@Modifiers

@Purpose
  - This component renders the confirmation view and it's actions.
*/
import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-confirmation',
  templateUrl: './confirmation.page.html',
  styleUrls: ['./confirmation.page.scss'],
})
export class ConfirmationPage {
  constructor(private router: Router) {}
  /*
  @Usage In this method navigation to HR form is handled.
  */
  navigateToNextPage(): void {
    alert('HR form yet to be implemented');
  }
  /*
   * In this method navigation to home page is handled.
   */
  quitPage(): void {
    this.router.navigate(['/home']);
  }
}
