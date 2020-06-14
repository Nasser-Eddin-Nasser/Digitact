/**
 * @description
 * This page send the applications to the server
 */
import { HttpClient } from '@angular/common/http';
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
  constructor(
    private navController: NavController,
    private storage: StorageHandlerService,
    private httpClient: HttpClient
  ) {}
  totalSize = 0;
  uploadSize = 0;
  url = 'http://localhost:9090';
  jsonData = {
    firstName: 'Hey',
    lastName: 'Hey',
    phone: '0176808080',
    email: 'test@fau.de',
    imageList: [
      {
        content:
          'data:image/jpeg;base64,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',
        type: 'CV',
      },
    ],
    industries: [
      'automotive',
      'finance',
      'commerce',
      'pharma_Helthcare',
      'public_Sector',
    ],
    positions: [
      'consultant_Business_Consultant',
      'iT_Consultant_Informationsmanagement',
      'iT_Consultant_Java_JEE',
      'iT_Consultant_Data_Science',
      'iT_Consultant_Artificial_Intelligence',
      'internship_Working_Student',
      'consultant_SAP',
    ],
    educations: [
      {
        university: 'fau',
        subject: 'infasdfasdfor',
        degree: 'Master',
        grade: 3.7,
        graduation_date: '22',
      },
      {
        university: 'bamberg',
        subject: 'infor',
        degree: 'Master',
        grade: 5,
        graduation_date: '23',
      },
      {
        university: 'berlin',
        subject: 'infor',
        degree: 'Master',
        grade: 1.7,
        graduation_date: '24',
      },
    ],
    keyCompetencies: [
      {
        category: 'languages',
        name: 'string-name',
        rating: 123,
      },
      {
        category: 'databases',
        name: 'string-name',
        rating: 312,
      },
    ],
  };

  ngOnInit(): void {
    this.storage
      .getAllItems<FormsData>(this.storage.applicantDetailsDb)
      .then((data) => {
        const applicantDetailsList = data.filter((x) => x.isRated === 1);
        this.totalSize = applicantDetailsList.length;
        let isSuccess = true;
        applicantDetailsList.forEach((x) => {
          if (isSuccess) {
            isSuccess = this.sendPostRequest(x);
          }
        });
      });
  }
  sendPostRequest(inp: FormsData): boolean {
    console.log(inp);
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');

    this.httpClient
      .post(this.url + '/api/controller/createApplicant', this.jsonData, {
        responseType: 'text',
      })
      .subscribe(
        (data) => {
          ++this.uploadSize;
          console.log(data);
          return true;
        },
        (error) => {
          this.uploadSize++;
          console.log('hey here');
          console.log(error);
          return false;
        }
      );
    return true;
  }

  /**
   * In this method navigation to home is handled.
   */
  goBack(): void {
    this.navController.navigateBack(['/applicants']);
  }
}