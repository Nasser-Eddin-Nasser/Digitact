import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-submit-page',
  templateUrl: './submit-page.component.html',
  styleUrls: ['./submit-page.component.scss'],
})
export class SubmitPageComponent {
  constructor(private router: Router) {}

  onStartApplication(): void {
    this.router.navigate(['/forms/steps/submit-page']);
  }
}
