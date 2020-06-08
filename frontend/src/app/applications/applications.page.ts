/**
 * @description
 *    This page holds the apllications to be finalized and sent to HR
 */
import { Component, OnInit } from '@angular/core';
import { NavController } from '@ionic/angular';

import { FormsData } from '../model/forms-data.model';
import { StorageHandlerService } from '../services/storage-handler.service';

@Component({
  selector: 'app-applications',
  templateUrl: './applications.page.html',
  styleUrls: ['./applications.page.scss'],
})
export class ApplicationsPage implements OnInit {
  countFinalized = 0;
  countNotFInalized = 0;
  // tslint:disable-next-line
  notFinishedEntry: any[] = [];

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
  fetchApplications(val: Array<FormsData>): void {
    for (const a of val) {
      if (a.isRated === 1) {
        this.countFinalized += 1;
      } else {
        this.countNotFInalized += 1;
        this.notFinishedEntry.push({
          firstName: a.basicInfo.firstName,
          lastName: a.basicInfo.lastName,
          submittedTime: a.submittedTime,
          id: a.id,
        });
      }
    }
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
    alert('to be implemented');
  }
}
