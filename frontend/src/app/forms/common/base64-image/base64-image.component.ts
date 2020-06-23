import {
  AfterViewInit,
  ChangeDetectorRef,
  Component,
  Input,
  OnDestroy,
} from '@angular/core';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';

/**
 * This Component is useful when many and large base64 images shall be displayed on a page:
 * Instead of directly rendering the base64 string in the DOM (which could slow down the DOM dramatically),
 * it creates an Object URL for it.
 *
 * Processing the image may not happen instantly.
 * If you want to show, for, for instance, a loading indicator, you can do it like so:
 *
 * ``` html
 * <app-base64-image>
 *  <span class="my-fancy-loading-indicator">Loading</span>
 * </app-base64-image>
 * ```
 */
@Component({
  selector: 'app-base64-image',
  templateUrl: './base64-image.component.html',
  styleUrls: ['./base64-image.component.scss'],
})
export class Base64ImageComponent implements AfterViewInit, OnDestroy {
  /**
   * Currently, when resizing the image, we just resize it to this fixed width.
   */
  private readonly THUBMNAIL_WIDTH = 300;

  /**
   * The image as a base64 string. Please note that this Component expects the input not to change.
   */
  @Input()
  base64String: string;

  /**
   * Should the image get resized, so that it can be better used as a thumbnail?
   * This is especially useful if you have a large image, but only want to display a small preview of it:
   */
  @Input()
  makeThumbnail = false;

  /**
   * Is this image used within an ion-slides element where zooming is possible?
   */
  @Input()
  isZoomableSlideImage = false;

  processedImage: ProcessedImage;

  constructor(
    private changeDetectorRef: ChangeDetectorRef,
    private domSanitizer: DomSanitizer
  ) {}

  ngAfterViewInit(): void {
    // Just give it a long timeout so that the browser can focus on other things meanwhile.
    window.setTimeout(() => {
      this.processImage();
    }, 200);
  }

  ngOnDestroy(): void {
    if (!this.processedImage) {
      console.warn(
        'The image could not be revoked because it has not yet been processed.'
      );
      return;
    }

    // While processing the image, we create an Object URL. We need to revoke it in order to prevent memory leaks.
    URL.revokeObjectURL(this.processedImage.rawObjectUrl);
  }

  /**
   * Convert the base64 image to an Object URL.
   */
  private processImage(): void {
    const canvas = document.createElement('canvas');

    const img = new Image();
    img.src = this.base64String;
    img.onload = () => {
      this.drawToCanvas(canvas, img);

      canvas.toBlob((result) => {
        const rawObjectUrl = URL.createObjectURL(result);
        const templateObjectUrl = this.domSanitizer.bypassSecurityTrustUrl(
          rawObjectUrl
        );

        this.processedImage = {
          rawObjectUrl,
          templateObjectUrl,
        };
        this.changeDetectorRef.detectChanges();
      });
    };
  }

  /**
   * A helper method for `processImage()` that draws the image to the Canvas.
   */
  private drawToCanvas(canvas: HTMLCanvasElement, img: HTMLImageElement): void {
    const context = canvas.getContext('2d');

    if (!this.makeThumbnail || img.width <= this.THUBMNAIL_WIDTH) {
      canvas.width = img.width;
      canvas.height = img.height;

      context.drawImage(img, 0, 0);
      return;
    }

    const resizeRatio = img.width / this.THUBMNAIL_WIDTH;

    const newWidth = img.width / resizeRatio;
    const newHeight = img.height / resizeRatio;

    canvas.width = newWidth;
    canvas.height = newHeight;

    context.drawImage(img, 0, 0, newWidth, newHeight);
  }
}

interface ProcessedImage {
  /**
   * The actual Object URL we go from `URL.createObjectURL()`.
   */
  rawObjectUrl: string;

  /**
   * The Object URL we can actually use in the template.
   * (This is the one that is retrieved from the DOM Sanitizer.)
   */
  templateObjectUrl: SafeUrl;
}
