import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-confirmation',
  templateUrl: './confirmation.page.html',
  styleUrls: ['./confirmation.page.scss'],
})
export class ConfirmationPage {
  constructor(private router: Router) {}
  // ngOnInit() {}
  navigateToNextPage(): void {
    alert('HR form yet to be implemented');
  }
  quitForm(): void {
    this.router.navigate(['/home']);
  }
}
