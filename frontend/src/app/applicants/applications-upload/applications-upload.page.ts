/**
 * @description
 * This page send the applications to the server
 */
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NavController } from '@ionic/angular';
import { TranslateService } from '@ngx-translate/core';

import { ToastController } from '../../common/ion-wrappers/toast-controller';
import { FormsData, KeyCompetenciesEntry } from '../../model/forms-data.model';
import { StorageHandlerService } from '../../services/storage-handler.service';

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
    private toastController: ToastController,
    private translate: TranslateService
  ) {}
  /**
   * The total application size.
   */
  totalSize = 0;
  /**
   * The application size that is being uploaded.
   */
  uploadSize = 1;
  /**
   * To check whether all the applications are uploaded.
   */
  isSuccess = false;
  /**
   * To check whether there is error during upload.
   */
  isError = false;
  /**
   * To display the error message dynamically.
   */
  errorMessage: string;
  /**
   * URL of the server host.
   */
  url = 'http://localhost:9090';

  /**
   * In this method locally stored data are fetched and post request function is called for one application at time.
   */
  ngOnInit(): void {
    this.storage
      .getAllItems<FormsData>(this.storage.applicantDetailsDb)
      .then(async (data) => {
        const applicantDetailsList = data.filter((x) => x.isRated === 1);
        this.totalSize = applicantDetailsList.length;
        for (const applicantData of applicantDetailsList) {
          if (!this.isError) {
            await this.sendPostRequest(applicantData);
          }
        }
      });
  }

  /**
   * In this method timeout of 1 second is speified to have a better readability of the application upload.
   */
  sleep(ms: number): Promise<void> {
    return new Promise((resolve) => setTimeout(resolve, ms));
  }
  /**
   * In this method a post request is made to the server.
   */
  sendPostRequest(inp: FormsData): Promise<void> {
    return this.sleep(1000).then(() => {
      const keyCompetence: Array<{
        category: string;
        name: string;
        rating: number;
      }> = [];

      const images: Array<{ content: string; type: string }> = [];
      /**
       * key competencies are mapped according json format required by server.
       */
      Object.entries(inp.keyCompetencies).map(([k, v]) => {
        v.forEach((x: KeyCompetenciesEntry) => {
          keyCompetence.push({ category: k, name: x.name, rating: x.rating });
        });
      });
      /**
       * picture of applicant and documents is mapped according json format required by server.
       */
      images.push({
        content: inp.profilePicture.pictureBase64,
        type: 'profilePic',
      });
      inp.documents.documentsBase64.forEach((x) => {
        images.push({ content: x, type: 'CV' });
      });
      /**
       * complete json structure is formed here to send to server.
       */
      const formsData = {
        firstName: inp.basicInfo.firstName,
        lastName: inp.basicInfo.lastName,
        phone: inp.contactInfo.phoneNumber,
        email: inp.contactInfo.eMail,
        title: inp.basicInfo.salutation,
        linkedIn: inp.contactInfo.linkedIn,
        xing: inp.contactInfo.xing,
        imageList: images,
        educations: inp.educationInfo.educationInfoForm,
        industries: inp.fieldDesignationInfo.field,
        positions: inp.fieldDesignationInfo.designation,
        keyCompetencies: keyCompetence,
        additionalInfo: inp.additionalInfo.additionalInfo,
      };

      const headers = new HttpHeaders();
      headers.append('Content-Type', 'application/json');
      /**
       * post request is made here and based on response locally stored data is deleted
       * error and success messages are shown wherever necessary
       */
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
                this.isSuccess = true;
                this.completionAlert();
              } else {
                this.uploadSize++;
              }
            }
          },
          (error) => {
            this.isError = true;
            if (error.status === 500) {
              this.errorMessage = this.translate.instant(
                'applicantsUploadPage.nApplicantsFailedToUploadMessage',
                {
                  failedCount: this.totalSize - this.uploadSize + 1,
                  errorMessage: error.error,
                }
              );
            } else {
              this.errorMessage = this.translate.instant(
                'applicantsUploadPage.serverConnectionErrorMessage'
              );
            }
          }
        );
    });
  }

  /**
   * In this method confirmation alert is displayed to notify the applications uplooad to server
   * TimeOut for navigation is set to display the toast on current page and then navigate
   */
  async completionAlert(): Promise<void> {
    const toast = await this.toastController.create({
      message: this.translate.instant(
        'applicantsUploadPage.applicantUploadedSUccessMessage'
      ),
      color: 'success',
      position: 'bottom',
      duration: 2000,
    });
    toast.present();
    setTimeout(() => {
      this.goBack();
    }, 2000);
  }

  /**
   * In this method navigation to home is handled.
   */
  goBack(): void {
    this.navController.navigateBack(['/applicants']);
  }
}
