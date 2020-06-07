/**
 * @description
 *    This model hold the applicant score view related fields.
 */

export interface RatingForm {
  id: string;
  isRated: number;
  applicantScore: ApplicantScore;
}

export interface ApplicantScore {
  rhetoric: number;
  motivation: number;
  selfAssurance: number;
  personalImpression: number;
}
