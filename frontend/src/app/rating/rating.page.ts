import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-rating',
  templateUrl: './rating.page.html',
  styleUrls: ['./rating.page.scss'],
})
export class RatingPage {
  constructor(private router: Router) {}

  rhetoricScale = 1;
  motivationScale = 1;
  selfAssuranceScale = 1;
  personalImpressionScale = 1;

  startApplication(): void {
    this.router.navigate(['/rating'], { queryParams: { step: 1 } });
  }
}
