import { FormArray } from '../../../common/forms/forms';

/**
 * The settings that need to be passed to the Image Viewer Service.
 */
export interface ImageViewerSettings {
  /**
   * A FormArray containing the images (base64 strings).
   * Please note that this FormArray may get modified by the Image viewer!
   */
  imagesBase64: FormArray<string>;

  /**
   * The index (within our FormArray) of the image that shall be pre-selected when loading the Image Viewer.
   */
  initialImage: number;
}
