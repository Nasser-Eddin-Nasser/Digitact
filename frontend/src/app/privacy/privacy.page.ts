import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NavController } from '@ionic/angular';

@Component({
  selector: 'app-privacy',
  templateUrl: './privacy.page.html',
  styleUrls: ['./privacy.page.scss'],
})
export class PrivacyPage implements OnInit {
  constructor(
    private navigationController: NavController,
    private router: Router
  ) {}

  isChecked = false;

  ngOnInit() {}

  navigateToHomePage() {
    this.navigationController.navigateBack(['/home']);
  }

  openPolicydetails() {
    this.router.navigate(['/privacy/details']);
  }

  startApplication(): void {
    this.navigationController.navigateForward(['/forms']);
  }

  togglePrivacyPolicyAcceptance() {
    this.isChecked = !this.isChecked;
  }
}
