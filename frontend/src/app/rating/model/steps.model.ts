/**
 * Names of the individual "pages" (form steps) shown in the application form.
 *
 * When routing to a page (or referencing a page in another way),
 * you should always use this enum and not hard-code the values as they might change later.
 */
export enum hrRatingStep {
  ApplicantRating = 'applicant-rating',
  ImpressionInformation = 'impression-information',
  Finalize = 'finalize',
}

export const hrRatingStepArr = Object.values(hrRatingStep);
