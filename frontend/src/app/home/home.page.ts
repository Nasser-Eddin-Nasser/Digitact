import { Component, OnInit } from '@angular/core';
import { NavController } from '@ionic/angular';

import { StorageHandlerService } from '../services/storage-handler.service';

@Component({
  selector: 'app-home',
  templateUrl: 'home.page.html',
  styleUrls: ['home.page.scss'],
})
export class HomePage implements OnInit {
  applicationSize: number;
  constructor(
    private navController: NavController,
    private storage: StorageHandlerService
  ) {}

  startApplication(): void {
    this.navController.navigateForward(['/forms']);
  }
  goToApplications(): void {
    this.navController.navigateRoot(['/applications']);
  }
  ngOnInit(): void {
    this.storage.getAllItems(this.storage.applicantDetailsDb).then((data) => {
      this.applicationSize = data.length;
    });
  }
}
