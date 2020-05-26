/**
 * @description
 *    This model hold the applicant score view related fields.
 */

export interface RatingForm {
  applicantScore: ApplicantScore;
}

export interface ApplicantScore {
  rhetoric: number;
  motivation: number;
  selfAssurance: number;
  personalImpression: number;
}
