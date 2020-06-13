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

  ngOnInit(): void {
    this.storage
      .getAllItems<FormsData>(this.storage.applicantDetailsDb)
      .then((data) => {
        this.totalSize = data.length;
        this.uploadSize++;
        const applicantDetailsList = data.filter((x) => x.isRated === 1);
        applicantDetailsList.forEach((x) => {
          this.sendPostRequest(x);
        });
      });
  }
  sendPostRequest(inp: FormsData): void {
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');

    const jsonObject = JSON.parse(JSON.stringify(inp));
    // console.log(jsonObject);
    const jData = [];

    for (const [k, v] of Object.entries(inp.basicInfo)) {
      if (k !== 'salutation') {
        jData.push({ k: v });
      }
    }

    console.log(jsonObject);
    const jsonData = {
      firstName: 'hey',
      lastName: 'Anavatti',
      phone: '0176808080',
      email: 'test@fau.de',
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
          graduation_date: '22.06.1999',
        },
        {
          university: 'bamberg',
          subject: 'infor',
          degree: 'Master',
          grade: 5,
          graduation_date: '23.07.2010',
        },
        {
          university: 'berlin',
          subject: 'infor',
          degree: 'Master',
          grade: 1.7,
          graduation_date: '24.9.2019',
        },
      ],
      keyCompetencies: [
        {
          category: 'languages',
          name: 'string-name',
          rating: 1,
        },
        {
          category: 'databases',
          name: 'string-name',
          rating: 2,
        },
      ],
    };
    // console.log(jsonData);
    this.httpClient
      .post(this.url + '/api/controller/createApplicant', jsonData)
      .subscribe(
        (data) => {
          console.log(data);
        },
        (error) => {
          console.log(error);
        }
      );
  }
  /**
   * In this method navigation to home is handled.
   */
  goBack(): void {
    this.navController.navigateBack(['/applicants']);
  }
}
