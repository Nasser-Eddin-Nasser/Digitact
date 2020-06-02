/*
  @description
    This model holds the all the interfaces related to forms data.
 */

import { UseControl } from '../common/forms/forms';

export interface FormsData {
  basicInfo: BasicInfo;
  contactInfo: ContactInfo;
  fieldDesignationInfo: FieldDesignationInfo;
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

export interface FieldDesignationInfo {
  field: UseControl<string[]>;
  designation: UseControl<string[]>;
}
