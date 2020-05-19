import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: 'home.page.html',
  styleUrls: ['home.page.scss'],
})
export class HomePage {
<<<<<<< HEAD

  constructor(private router: Router) { }

  onStartApplication(): void {
    this.router.navigate(['/forms'], { queryParams: { step: 1 } });

=======
  constructor(private router: Router) {}

  onStartApplication(): void {
    this.router.navigate(['/forms'], { queryParams: { step: 1 } });
>>>>>>> a40cdd678f8684ffbf0ed13799cb2e793f90f684
  }
}
