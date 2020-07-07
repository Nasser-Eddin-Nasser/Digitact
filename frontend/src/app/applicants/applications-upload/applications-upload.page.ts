/**
 * @description
 * This page send the applications to the server
 */
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NavController } from '@ionic/angular';
import { TranslateService } from '@ngx-translate/core';
import { Subscription } from 'rxjs';

import { FormValue } from '../../common/forms/forms';
import { ToastController } from '../../common/ion-wrappers/toast-controller';
import {
  CreateApplicantData,
  HrRatingEntry,
  ImagesEntry,
  KeyCompetencesEntry,
} from '../../model/create-applicant-data.model';
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
  totalFinalizedApplicantsCount = 0;

  /**
   * URL of the server host.
   */
  apiHostUrl = 'http://localhost:9090';

  /**
   * refers to the application that is being processed
   */
  index = -1;

  /**
   * To store all the application from the local database
   */
  applicantDetailsList: FormValue<FormsData>[];

  /**
   * Holds to subscription object of request under process
   */

  currentRequest: Subscription;

  /**
   * Holds count of the API that is succeeded
   */
  succeededAPICount = 0;

  /**
   * Holds count of the API that is failed
   */
  failedAPICount = 0;

  /**
   * Boolean that holds whether APIs are sent or not
   */
  isAPIInProgress = true;

  /**
   * Holds the message to display  based on status
   */
  statusMessage: string;

  /**
   * Holds the icon name to dispaly based on status
   */
  statusIconName: string;

  /**
   * Holds the icon color to dispaly based on status
   */
  statusIconColor: string;

  /**
   * In this method locally stored data are fetched and post request function is called for one application at time.
   */
  ngOnInit(): void {
    this.storage
      .getAllItems<FormValue<FormsData>>(this.storage.applicantDetailsDb)
      .then((data) => {
        this.applicantDetailsList = data.filter((x) => x.isRated === 1);
        this.totalFinalizedApplicantsCount = this.applicantDetailsList.length;
        this.initiateRequests();
      });
  }

  /**
   * In this method requests are initiated and status properties are updated.
   */
  private initiateRequests(): void {
    this.index++;
    if (this.index <= this.totalFinalizedApplicantsCount - 1) {
      this.statusMessage = this.translate.instant(
        'applicantsUploadPage.uploadingProgressMessage',
        {
          uploadSize: this.index + 1,
          totalSize: this.totalFinalizedApplicantsCount,
        }
      );
      this.sendPostRequest(this.applicantDetailsList[this.index]);
    } else {
      if (this.succeededAPICount === this.totalFinalizedApplicantsCount) {
        this.statusMessage = this.translate.instant(
          'applicantsUploadPage.allApplicantsSavedSuccessMessage'
        );
        this.statusIconName = 'checkmark-circle-outline';
        this.statusIconColor = 'success';
      } else if (this.failedAPICount === this.totalFinalizedApplicantsCount) {
        this.statusMessage = this.translate.instant(
          'applicantsUploadPage.allApplicantsFailedMessage'
        );
        this.statusIconName = 'close-circle-outline';
        this.statusIconColor = 'danger';
      } else {
        this.statusMessage = this.translate.instant(
          'applicantsUploadPage.partialySentAndFailedMessage',
          {
            succeededCount: this.succeededAPICount,
            failedCount: this.failedAPICount,
          }
        );
        this.statusIconName = 'alert-circle-outline';
        this.statusIconColor = 'warning';
      }

      this.isAPIInProgress = false;
      this.goBack();
    }
  }

  private constructPayLoad(
    applicantData: FormValue<FormsData>,
    hrRatingData: FormValue<RatingForm>
  ): CreateApplicantData {
    const applicantHRScore: HrRatingEntry = {
      rhetoric: hrRatingData.applicantScore.rhetoric,
      motivation: hrRatingData.applicantScore.motivation,
      selfAssurance: hrRatingData.applicantScore.selfAssurance,
      personalImpression: hrRatingData.applicantScore.personalImpression,
      impression: hrRatingData.impressionInfo.impression,
    };

    const keyCompetence: KeyCompetencesEntry[] = [];

    const images: ImagesEntry[] = [];
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
    images.push({
      content: applicantData.profilePicture.pictureBase64,
      type: 'profilePic',
    });
    applicantData.documents?.documentsBase64?.forEach((x) => {
      images.push({ content: x, type: 'CV' });
    });
    /**
     * complete json structure is formed here to send to server.
     */
    const formsData: CreateApplicantData = {
      firstName: applicantData.basicInfo.firstName,
      lastName: applicantData.basicInfo.lastName,
      phone: applicantData.contactInfo.phoneNumber,
      email: applicantData.contactInfo.eMail,
      title: applicantData.basicInfo.salutation,
      linkedIn: applicantData.contactInfo.linkedIn,
      xing: applicantData.contactInfo.xing,
      imageList: images,
      workExperiences: applicantData.workExperienceInfo?.workExperienceForm,
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
    return formsData;
  }
  /**
   * In this method a post request is made to the server.
   */
  private sendPostRequest(applicantData: FormValue<FormsData>): void {
    /**
     * In this method locally stored data HR rating data is fetched and set according to JSON format
     */

    let formsData: CreateApplicantData;
    this.storage
      .getItem<FormValue<RatingForm>>(
        this.storage.applicantRatingsDb,
        applicantData.id
      )
      .then((hrRatingData) => {
        formsData = this.constructPayLoad(applicantData, hrRatingData);

        /**
         * post request is made here and based on response locally stored data is deleted
         * error and success messages are shown wherever necessary
         */
        this.currentRequest = this.httpClient
          .post(
            this.apiHostUrl + '/api/controller/createApplicant',
            formsData,
            {
              responseType: 'text',
              observe: 'response',
            }
          )
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
                this.succeededAPICount++;
                this.initiateRequests();
              }
            },
            () => {
              this.failedAPICount++;
              this.initiateRequests();
            }
          );
      });
  }

  /**
   * In this method completion notification is displayed
   */
  async completionAlert(): Promise<void> {
    const toastMessage = this.isAPIInProgress
      ? this.succeededAPICount
        ? this.translate.instant(
            'applicantsUploadPage.nSucceededRestCancelledMessage',
            { succeededCount: this.succeededAPICount }
          )
        : this.translate.instant(
            'applicantsUploadPage.allRequestCancelledMessage'
          )
      : this.statusMessage;

    const toastColor = this.isAPIInProgress ? 'warning' : this.statusIconColor;
    const toast = await this.toastController.create({
      message: toastMessage,
      color: toastColor,
      position: 'bottom',
      duration: 4000,
    });
    toast.present();
  }

  /**
   * In this method navigation to home is handled.
   */
  goBack(): void {
    if (this.isAPIInProgress && this.currentRequest) {
      this.currentRequest.unsubscribe();
    }
    setTimeout(() => {
      this.navController.navigateBack(['/applicants']);
      this.completionAlert();
    }, 1000);
  }
}
