import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { NavController } from '@ionic/angular';

import { FormsData } from '../model/forms-data.model';
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
    private storage: StorageHandlerService,
    private router: ActivatedRoute
  ) {}
  /**
   * In this method size of the applications is fetched
   */
  ngOnInit(): void {
    this.router.params.subscribe(() => {
      this.storage
        .getAllItems<FormsData>(this.storage.applicantDetailsDb)
        .then((data) => {
          this.applicationSize = data.length;
        });
    });
  }
  /**
   * In this method navigation to application form is handled.
   */
  startApplication(): void {
    this.navController.navigateForward(['/forms']);
  }
  /**
   * In this method navigation to operation on submitted and finalized applications is handled.
   */
  goToApplications(): void {
    this.navController.navigateRoot(['/applications']);
  }
}
