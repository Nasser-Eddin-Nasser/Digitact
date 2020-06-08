/**
 * Names of the individual "pages" (form steps) shown in the application form.
 *
 * When routing to a page (or referencing a page in another way),
 * you should always use this enum and not hard-code the values as they might change later.
 */
export enum ApplicationStep {
  BasicInformation = 'basic-information',
  ContactInformation = 'contact-information',
  EducationInformation = 'education-information',
  FieldDesignationPreference = 'field-designation-preference',
  KeyCompetencies = 'key-competencies',
  Submit = 'submit',
}

export enum hrRatingStep {
  ApplicantRating = 'applicant-rating',
  ImpressionInformation = 'impression-information',
  Finalize = 'finalize',
}

/**
 * All of the possible steps as an array.
 */
export const ApplicationStepsArr = Object.values(ApplicationStep);

export const hrRatingStepArr = Object.values(hrRatingStep);
