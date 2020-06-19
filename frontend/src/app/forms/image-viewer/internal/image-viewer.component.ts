import { Component, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { IonSlides, NavController, ViewDidEnter } from '@ionic/angular';

import { AlertController } from '../../../common/ion-wrappers/alert-controller';
import { ToastController } from '../../../common/ion-wrappers/toast-controller';
import { ImageViewerService } from '../image-viewer.service';
import { ImageViewerSettings } from '../model/image-viewer-settings.model';

/**
 * Important:
 * This Component is tightly coupled to the Image Viewer Service
 * since it gets all of its data from it and must always inform the Service when it gets destroyed.
 *
 * This is probably not the most elegant approach, but at least it works for our current use case.
 */
@Component({
  templateUrl: './image-viewer.component.html',
  styleUrls: ['./image-viewer.component.scss'],
})
export class ImageViewerComponent implements OnInit, OnDestroy, ViewDidEnter {
  @ViewChild('slider')
  private slider: IonSlides;

  // It seems like Ionic does not export a type for the IonSlides Options.
  sliderOptions = {};

  /**
   * The settings we got from our Service.
   */
  providedSettings: ImageViewerSettings;

  /**
   * The index of the currently displayed image.
   */
  currentSlideIndex: number;

  /**
   * Has the view been fully loaded (so has the ionViewDidEnter lifecycle hook fired)?
   * Important: We only set this property once (when the view is loaded for the very first time).
   */
  viewDidEnter = false;

  constructor(
    private alertController: AlertController,
    private imageViewerService: ImageViewerService,
    private navController: NavController,
    private toastController: ToastController
  ) {}

  ionViewDidEnter(): void {
    this.viewDidEnter = true;
  }

  ngOnInit(): void {
    window.setTimeout(() => {
      this.slider.update();
    }, 10000);
    this.providedSettings = this.imageViewerService.getProvidedSettings();

    // Initialize the Slider Options.
    this.sliderOptions = {
      initialSlide: this.providedSettings.initialImage,
      spaceBetween: 10,
      zoom: {
        maxRatio: 3,
        minRatio: 1,
        toggle: true,
      },
    };

    this.currentSlideIndex = this.providedSettings.initialImage;
  }

  ngOnDestroy(): void {
    this.imageViewerService.onImageViewerDestroy();
  }

  closeImageViewer(): void {
    this.navController.pop();
  }

  /**
   * Update the index of the currently displayed image.
   *
   * This method should also be called when an image was deleted.
   */
  async updateCurrentSlideIndex(): Promise<void> {
    const imageIndex = await this.slider.getActiveIndex();
    this.currentSlideIndex = imageIndex;
  }

  /**
   * Show an alert popup asking if the user really want to delete the currently visible image.
   * If he confirms this, delete the image.
   */
  async deleteCurrentImage(): Promise<void> {
    const imageIndex = await this.slider.getActiveIndex();

    const alert = await this.alertController.create({
      header: 'Delete',
      message: 'Do you really want to delete this image?',
      cssClass: 'custom-alert-button-colors',
      buttons: [
        {
          text: 'Cancel',
          role: 'cancel',
        },
        {
          text: 'Delete',
          cssClass: 'color-secondary',
          handler: () => {
            this.deleteImage(imageIndex);
          },
        },
      ],
    });

    alert.present();
  }

  /**
   * Delete the image at the given index.
   */
  private async deleteImage(imageIndex: number): Promise<void> {
    this.providedSettings.imagesBase64.removeAt(imageIndex);

    if (this.providedSettings.imagesBase64.length < 1) {
      this.closeImageViewer();
    } else {
      /*
        TODO: Not sure if this is the correct way to wait for the slider to update.
        If the slide numbers don't reliably get updated, we should look for a different solution.
      */
      await this.slider.update();
      this.updateCurrentSlideIndex();
    }

    // Display a success message.
    const toast = await this.toastController.create({
      message: 'The image has been deleted',
      duration: 3000,
    });
    toast.present();
  }
}
