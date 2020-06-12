import { Component, OnDestroy, OnInit } from '@angular/core';
import { NavController } from '@ionic/angular';

import { ImageViewerService } from '../image-viewer.service';
import { ImageViewerSettings } from '../model/image-viewer-settings.model';

@Component({
  templateUrl: './image-viewer.component.html',
  styleUrls: ['./image-viewer.component.scss'],
})
export class ImageViewerComponent implements OnInit, OnDestroy {
  // It seems like Ionic does not export a type for the IonSlides Options.
  sliderOptions = {};

  /**
   * The settings we got from our Service.
   */
  providedSettings: ImageViewerSettings;

  constructor(
    private imageViewerService: ImageViewerService,
    private navController: NavController
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
}
