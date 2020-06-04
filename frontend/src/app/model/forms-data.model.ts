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
  technicalKnowledge: TechnicalKnowledge;
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

export interface TechnicalKnowledge {
  professionalSoftware: TechnicalKnowledgeEntry[];
  databases: TechnicalKnowledgeEntry[];
  /**
   * Not only programming languages, but also frameworks are permitted here.
   */
  programmingLanguagesAndFrameworks: TechnicalKnowledgeEntry[];
}
export interface TechnicalKnowledgeEntry {
  name: string;
  rating: number;
}
