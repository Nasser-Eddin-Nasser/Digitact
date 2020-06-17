import { Injectable } from '@angular/core';
import { NavController } from '@ionic/angular';

import { FULL_IMAGE_VIEWER_PATH } from '../router-constants';

import { ImageViewerSettings } from './model/image-viewer-settings.model';

/**
 * A Service that allows displaying an "image viewer" that is able to display multiple images.
 * It is possible for the user to swipe in order to switch between the provided images.
 * Also, a delete button is shown for every image.
 */
@Injectable({
  // Make this a Singleton. There might be better approaches, but for now, it works fine.
  providedIn: 'root',
})
export class ImageViewerService {
  private providedSettings: ImageViewerSettings | undefined;

  constructor(private navController: NavController) {}

  /**
   * Show the Image Viewer.
   * Internally, the Router is being used to go to the Image Viewer page.
   *
   * @param settings Settings for the Image Viewer.
   * Most importantly: This includes the array of images that will be displayed.
   * Please note that the Image Viewer may modify the content of these settings.
   */
  showImageViewer(settings: ImageViewerSettings): void {
    this.setSettings(settings);

    this.navController.navigateForward(FULL_IMAGE_VIEWER_PATH);
  }

  private setSettings(settings: ImageViewerSettings): void {
    if (this.providedSettings) {
      throw new Error(
        'Tried to override the settings. Have you maybe tried to open the Image Viewer twice (at once)?'
      );
    }

    this.providedSettings = settings;
  }

  /**
   * Internal API!
   *
   * Allows the Image Viewer Component to retrieve the settings that were passed to this Service.
   */
  getProvidedSettings(): ImageViewerSettings {
    if (!this.providedSettings) {
      throw new Error('Found no Settings');
    }

    return this.providedSettings;
  }

  /**
   * Internal API!
   *
   * This method must be called by the Image Viewer Component when it gets destroyed.
   */
  onImageViewerDestroy(): void {
    this.providedSettings = undefined;
  }
}
