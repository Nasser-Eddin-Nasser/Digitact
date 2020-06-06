/*
  @description
    This model holds the all the interfaces related to forms data.
 */

import { UseControl } from '../common/forms/forms';

export interface FormsData {
  basicInfo: BasicInfo;
  contactInfo: ContactInfo;
  educationInfo: EducationInfo;
  fieldDesignationInfo: FieldDesignationInfo;
  keyCompetencies: KeyCompetencies;
}

export interface BasicInfo {
  firstName: string;
  lastName: string;
  salutation: string;
}

export interface ContactInfo {
  phoneNumber: string;
  eMail: string;
  linkedIn: string;
  xing: string;
}

export interface EducationInfo {
  educationInfoForm: EducationInfoEntry[];
}

export interface EducationInfoEntry {
  university: string;
  subject: string;
  degree: string;
  grade: string;
  graduationYear: string;
}
export interface FieldDesignationInfo {
  field: UseControl<string[]>;
  designation: UseControl<string[]>;
}

export interface KeyCompetencies {
  languages: UseControl<KeyCompetenciesEntry[]>;
  professionalSoftware: UseControl<KeyCompetenciesEntry[]>;
  databases: UseControl<KeyCompetenciesEntry[]>;
  programmingLanguagesAndFrameworks: UseControl<KeyCompetenciesEntry[]>;
}
export interface KeyCompetenciesEntry {
  name: string;
  rating: number;
}
