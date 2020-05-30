/*
  @description
    This model holds the all the interfaces related to forms data.
 */

export interface FormsData {
  basicInfo: BasicInfo;
  contactInfo: ContactInfo;
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
