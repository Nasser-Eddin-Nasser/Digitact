/*
  @description
    This model holds the all the interfaces related to create applicants data.
 */

import { FormValue } from '../common/forms/forms';

import {EducationInfoEntry, WorkExperienceEntry } from './forms-data.model';

    export interface CreateApplicantData {
        firstName: string;
        lastName: string;
        phone: string;
        email: string;
        title: string;
        linkedIn: string;
        xing: string;
        imageList: ImagesEntry[];
        workExperiences: FormValue<WorkExperienceEntry>[];
        educations: FormValue<EducationInfoEntry>[];
        industries: string[];
        positions: string[];
        keyCompetencies: KeyCompetencesEntry[];
        additionalInfo: string;
        hrRating: HrRatingEntry;
      }

      export interface KeyCompetencesEntry {
        category: string;
        name: string;
        rating: number;
      }

      export interface ImagesEntry {
        content: string;
        type: string;
      }

      export interface HrRatingEntry {
        rhetoric: number;
        motivation: number;
        selfAssurance: number;
        personalImpression: number;
        impression: string;
      }
