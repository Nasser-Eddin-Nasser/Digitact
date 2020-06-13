/**
 * @description
 *    This page holds the apllications to be finalized and sent to HR
 */
import { Component, OnInit } from '@angular/core';
import { NavController } from '@ionic/angular';

import { FormsData } from '../model/forms-data.model';
import { StorageHandlerService } from '../services/storage-handler.service';

@Component({
  selector: 'app-applicants',
  templateUrl: './applicants.page.html',
  styleUrls: ['./applicants.page.scss'],
})
export class ApplicantsPage implements OnInit {
  countFinalized = 0;
  countNotFinalized = 0;
  notFinishedEntry: FormsData[];

  constructor(
    private navController: NavController,
    private storage: StorageHandlerService
  ) {}

  /**
   * In this method all the applications stored in the local DB are fetched
   */
  ngOnInit(): void {
    this.storage
      .getAllItems<FormsData>(this.storage.applicantDetailsDb)
      .then((data) => {
        this.fetchApplications(data);
      });
  }

  /**
   * In this method finalized and not finalized application are rendered seperately
   */
  fetchApplications(inp: Array<FormsData>): void {
    this.countFinalized = inp.filter((val) => {
      return val.isRated === 1;
    }).length;

    this.notFinishedEntry = inp.filter((x) => x.isRated === 0);

    this.countNotFinalized = this.notFinishedEntry.length;
  }
  /**
   * In this method navigation to HR rating is handled.
   */
  goToRating(id: string): void {
    this.navController.navigateForward(['/rating'], { state: { id } });
  }
  /**
   * In this method navigation to home is handled.
   */
  goBack(): void {
    this.navController.navigateBack(['/home']);
  }
  /**
   * In this method passing of data to the server is done
   */
  sendToHrMonitor(): void {
    this.navController.navigateForward(['/applications-upload']);
  }
}
