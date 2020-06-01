/*
  @description
    This model holds the all the interfaces related to forms data.
 */

export interface FormsData {
  basicInfo: BasicInfo;
  contactInfo: ContactInfo;
  educationInfo: EducationInfo;
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
  eduInfo: EducationInfoEntry[];
}

export interface EducationInfoEntry {
  university: string;
  degree: string;
  typeOfDegree: string;
  grade: string;
  gradDate: string;
}
