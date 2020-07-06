/**
 * @description
 * This page send the applications to the server
 */
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NavController } from '@ionic/angular';
import { TranslateService } from '@ngx-translate/core';

import { FormValue } from '../../common/forms/forms';
import { ToastController } from '../../common/ion-wrappers/toast-controller';
import { FormsData, KeyCompetenciesEntry } from '../../model/forms-data.model';
import { RatingForm } from '../../rating/model/rating-form.model';
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
   * The application count that is being uploaded.
   */
  uploadingCount = 1;
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
   * refers to the application that is being processed
   */
  index = 0;
  /**
   * To store all the application from the local database
   */
  applicantDetailsList: FormValue<FormsData>[];

  /**
   * In this method locally stored data are fetched and post request function is called for one application at time.
   */
  ngOnInit(): void {
    this.storage
      .getAllItems<FormValue<FormsData>>(this.storage.applicantDetailsDb)
      .then((data) => {
        this.applicantDetailsList = data.filter((x) => x.isRated === 1);
        this.totalSize = this.applicantDetailsList.length;
        this.sendPostRequest();
      });
  }

  /**
   * In this method a post request is made to the server.
   */
  private sendPostRequest(): void {
    const applicantData = this.applicantDetailsList[this.index];
    /**
     * In this method locally stored data HR rating data is fetched and set according to JSON format
     */
    this.storage
      .getItem<FormValue<RatingForm>>(
        this.storage.applicantRatingsDb,
        applicantData.id
      )
      .then((val) => {
        const applicantHRScore = {
          rhetoric: val.applicantScore.rhetoric,
          motivation: val.applicantScore.motivation,
          selfAssurance: val.applicantScore.selfAssurance,
          personalImpression: val.applicantScore.personalImpression,
          impression: val.impressionInfo.impression,
        };

        const keyCompetence: Array<{
          category: string;
          name: string;
          rating: number;
        }> = [];

        const images: Array<{ content: string; type: string }> = [];
        /**
         * key competencies are mapped according json format required by server.
         */
        if (applicantData.keyCompetencies) {
          Object.entries(applicantData.keyCompetencies).map(([k, v]) => {
            v.forEach((x: KeyCompetenciesEntry) => {
              keyCompetence.push({
                category: k,
                name: x.name,
                rating: x.rating,
              });
            });
          });
        }
        /**
         * picture of applicant and documents is mapped according json format required by server.
         */
        if (
          applicantData.profilePicture.pictureBase64 !== '' &&
          applicantData.profilePicture.pictureBase64 !== undefined &&
          applicantData.profilePicture.pictureBase64 !== null
        ) {
          images.push({
            content: applicantData.profilePicture.pictureBase64,
            type: 'profilePic',
          });
        }
        applicantData.documents?.documentsBase64?.forEach((x) => {
          images.push({ content: x, type: 'CV' });
        });
        /**
         * complete json structure is formed here to send to server.
         */
        const formsData = {
          firstName: applicantData.basicInfo.firstName,
          lastName: applicantData.basicInfo.lastName,
          phone: applicantData.contactInfo.phoneNumber,
          email: applicantData.contactInfo.eMail,
          title: applicantData.basicInfo.salutation,
          linkedIn: applicantData.contactInfo.linkedIn,
          xing: applicantData.contactInfo.xing,
          imageList: images,
          workExperiences: applicantData.workExperienceInfo.workExperienceForm,
          educations: applicantData.educationInfo?.educationInfoForm,
          industries: applicantData.fieldDesignationInfo?.field,
          positions: applicantData.fieldDesignationInfo?.designation,
          keyCompetencies: keyCompetence,
          additionalInfo: applicantData.additionalInfo?.additionalInfo,
          hrRating: applicantHRScore,
        };

        // The server expects all keys to exist. We need to explicitly set the value to null so that it is actually submitted.
        for (const [key, value] of Object.entries(formsData)) {
          if (value === undefined || value === null) {
            // tslint:disable-next-line: no-null-keyword no-any
            (formsData as any)[key] = null;
          }
        }

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
                this.storage.deleteItem(
                  this.storage.applicantDetailsDb,
                  applicantData.id
                );
                this.storage.deleteItem(
                  this.storage.applicantRatingsDb,
                  applicantData.id
                );
                this.index++;
                if (this.uploadingCount === this.totalSize) {
                  this.isSuccess = true;
                  this.completionAlert();
                } else {
                  this.uploadingCount++;
                }
                if (this.index <= this.totalSize - 1) {
                  this.sendPostRequest();
                }
              }
            },
            (error) => {
              this.index++;
              this.isError = true;
              if (error.status === 500) {
                this.errorMessage = this.translate.instant(
                  'applicantsUploadPage.nApplicantsFailedToUploadMessage',
                  {
                    failedCount: this.totalSize - this.uploadingCount + 1,
                    errorMessage: error.error,
                  }
                );
              } else {
                this.errorMessage = this.translate.instant(
                  'applicantsUploadPage.serverConnectionErrorMessage'
                );
              }
              if (this.index <= this.totalSize - 1) {
                this.sendPostRequest();
              }
            }
          );
      });
  }
  /**
   * In this method completion notification is displayed
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
