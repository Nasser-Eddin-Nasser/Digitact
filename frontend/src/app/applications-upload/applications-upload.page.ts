/**
 * @description
 * This page send the applications to the server
 */
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NavController } from '@ionic/angular';

import { ToastController } from '../common/ion-wrappers/toast-controller';
import {
  EducationInfoEntry,
  FormsData,
  KeyCompetenciesEntry,
} from '../model/forms-data.model';
import { StorageHandlerService } from '../services/storage-handler.service';

@Component({
  selector: 'app-applications-upload',
  templateUrl: './applications-upload.page.html',
  styleUrls: ['./applications-upload.page.scss'],
})
export class ApplicationsUploadPage implements OnInit {
  constructor(
    private navController: NavController,
    private storage: StorageHandlerService,
    private httpClient: HttpClient,
    private toastController: ToastController
  ) {}
  totalSize = 0;
  uploadSize = 1;
  url = 'http://localhost:9090';

  ngOnInit(): void {
    this.storage
      .getAllItems<FormsData>(this.storage.applicantDetailsDb)
      .then((data) => {
        const applicantDetailsList = data.filter((x) => x.isRated === 1);
        this.totalSize = applicantDetailsList.length;
        applicantDetailsList.forEach(async (x) => {
          this.sendPostRequest(x);
        });
      });
  }

  sendPostRequest(inp: FormsData): void {
    const keyCompetence: Array<{
      category: string;
      name: string;
      rating: number;
    }> = [];
    const education: Array<EducationInfoEntry> = [];
    const images: Array<{ content: string; type: string }> = [];

    Object.entries(inp.keyCompetencies).map(([k, v]) => {
      v.forEach((x: KeyCompetenciesEntry) => {
        keyCompetence.push({ category: k, name: x.name, rating: x.rating });
      });
    });

    Object.values(inp.educationInfo.educationInfoForm).map((v) => {
      education.push(v);
    });

    images.push({
      content: inp.profilePicture.pictureBase64,
      type: 'profilePic',
    });

    const formsData = {
      firstName: inp.basicInfo.firstName,
      lastName: inp.basicInfo.lastName,
      phone: inp.contactInfo.phoneNumber,
      email: inp.contactInfo.eMail,
      title: inp.basicInfo.salutation,
      imageList: images,
      educations: education,
      industries: inp.fieldDesignationInfo.field,
      positions: inp.fieldDesignationInfo.designation,
      keyCompetencies: keyCompetence,
    };

    const headers = new HttpHeaders();
    headers.append('Content-Type', 'application/json');

    this.httpClient
      .post(this.url + '/api/controller/createApplicant', formsData, {
        responseType: 'text',
        observe: 'response',
      })
      .subscribe(
        (response) => {
          if (response.status === 201) {
            this.storage.deleteItem(this.storage.applicantDetailsDb, inp.id);
            this.storage.deleteItem(this.storage.applicantRatingsDb, inp.id);
            if (this.uploadSize === this.totalSize) {
              this.completionAlert();
            } else {
              this.uploadSize++;
            }
          }
        },
        (error) => {
          console.log(error);
        }
      );
  }

  /**
   * In this method confirmation alert is displayed to notify the applications uplooad to server
   */
  async completionAlert(): Promise<void> {
    const toast = await this.toastController.create({
      message: 'Applicantions are successfully uploaded',
      color: 'success',
      position: 'bottom',
      duration: 2000,
    });
    toast.present();
    this.goBack();
  }

  /**
   * In this method navigation to home is handled.
   */
  goBack(): void {
    this.navController.navigateBack(['/applicants']);
  }
}
