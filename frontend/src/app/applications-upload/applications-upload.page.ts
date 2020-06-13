/**
 * @description
 * This page send the applications to the server
 */
import { Component, OnInit } from '@angular/core';
import { NavController } from '@ionic/angular';

import { FormsData } from '../model/forms-data.model';
import { StorageHandlerService } from '../services/storage-handler.service';

@Component({
  selector: 'app-applications-upload',
  templateUrl: './applications-upload.page.html',
  styleUrls: ['./applications-upload.page.scss'],
})
export class ApplicationsUploadPage implements OnInit {
  size = 0;

  constructor(
    private navController: NavController,
    private storage: StorageHandlerService
  ) {}

  ngOnInit(): void {
    this.storage
      .getAllItems<FormsData>(this.storage.applicantDetailsDb)
      .then((data) => {
        this.size = data.length;
      });
  }
  /**
   * In this method navigation to home is handled.
   */
  goBack(): void {
    this.navController.navigateBack(['/applicants']);
  }
}
