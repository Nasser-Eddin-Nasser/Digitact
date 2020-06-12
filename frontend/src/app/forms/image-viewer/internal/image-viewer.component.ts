import { Component, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { IonSlides, NavController } from '@ionic/angular';

import { AlertController } from '../../../common/ion-wrappers/alert-controller';
import { ToastController } from '../../../common/ion-wrappers/toast-controller';
import { ImageViewerService } from '../image-viewer.service';
import { ImageViewerSettings } from '../model/image-viewer-settings.model';

@Component({
  templateUrl: './image-viewer.component.html',
  styleUrls: ['./image-viewer.component.scss'],
})
export class ImageViewerComponent implements OnInit, OnDestroy {
  @ViewChild('slider')
  private slider: IonSlides;

  // It seems like Ionic does not export a type for the IonSlides Options.
  sliderOptions = {};

  /**
   * The settings we got from our Service.
   */
  providedSettings: ImageViewerSettings;

  constructor(
    private alertController: AlertController,
    private imageViewerService: ImageViewerService,
    private navController: NavController,
    private toastController: ToastController
  ) {}

  ngOnInit(): void {
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
  }

  ngOnDestroy(): void {
    this.imageViewerService.onImageViewerDestroy();
  }

  closeImageViewer(): void {
    this.navController.pop();
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
    this.slider.update();

    // Display a success message.
    const toast = await this.toastController.create({
      message: 'The image has been deleted',
      duration: 3000,
    });
    toast.present();

    if (this.providedSettings.imagesBase64.length < 1) {
      this.closeImageViewer();
    }
  }
}
