import {
  AfterViewInit,
  ChangeDetectorRef,
  Component,
  EventEmitter,
  Input,
  OnDestroy,
  Output,
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
   * The image as a base64 string. Please note that this Component expects the input not to change.
   */
  @Input()
  base64String: string;

  /**
   * If necessary, downsize the image so that it does not exceed this width (in pixels).
   * If this property (and `maxImageHeight`) is not set, the image will be rendered in full size.
   */
  @Input()
  maxImageWidth: number;
  /**
   * If necessary, downsize the image so that it does not exceed this height (in pixels).
   * If this property (and `maxImageWidth`) is not set, the image will be rendered in full size.
   */
  @Input()
  maxImageHeight: number;

  /**
   * Is this image used within an ion-slides element where zooming is possible?
   */
  @Input()
  isZoomableSlideImage = false;

  /**
   * Should the image be rendered/processed now?
   *
   * If you have a list with many high-resolution images,
   * but not all of them are visible now (e.g. because you are displaying them in a slider),
   * then it is recommeded to set this property to `false` at the beginning
   *  and only to enable it once the image might come into viewport soon.
   */
  @Input()
  set enableImageProcessing(value: boolean) {
    this._enableImageProcessing = value;

    this.processImageIfNeeded();
  }
  get enableImageProcessing(): boolean {
    return this._enableImageProcessing;
  }
  private _enableImageProcessing = true;

  /**
   * The event is emitted when the image has been fully loaded.
   */
  @Output()
  imageHasLoaded = new EventEmitter<void>();

  isCurrentlyProcessingTheImage = false;

  processedImage: ProcessedImage;

  constructor(
    private changeDetectorRef: ChangeDetectorRef,
    private domSanitizer: DomSanitizer
  ) {}

  ngAfterViewInit(): void {
    // Just give it a long timeout so that the browser can focus on other things meanwhile.
    window.setTimeout(() => {
      this.processImageIfNeeded();
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

  private processImageIfNeeded(): void {
    if (!this.enableImageProcessing) {
      return;
    }
    if (this.isCurrentlyProcessingTheImage) {
      return;
    }
    if (this.processedImage) {
      return;
    }

    this.processImage();
  }

  /**
   * Convert the base64 image to an Object URL.
   */
  private processImage(): void {
    this.isCurrentlyProcessingTheImage = true;

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
        this.isCurrentlyProcessingTheImage = false;

        this.changeDetectorRef.detectChanges();
      });
    };
  }

  /**
   * A helper method for `processImage()` that draws the image to the Canvas.
   */
  private drawToCanvas(canvas: HTMLCanvasElement, img: HTMLImageElement): void {
    const context = canvas.getContext('2d');

    let shouldResize = false;
    if (this.maxImageWidth && img.width > this.maxImageWidth) {
      shouldResize = true;
    }
    if (this.maxImageHeight && img.height > this.maxImageHeight) {
      shouldResize = true;
    }

    if (!shouldResize) {
      canvas.width = img.width;
      canvas.height = img.height;

      context.drawImage(img, 0, 0);
      return;
    }

    let widthResizeRatio = 1;
    let heightResizeRatio = 1;
    if (this.maxImageWidth) {
      widthResizeRatio = img.width / this.maxImageWidth;
    }
    if (this.maxImageHeight) {
      heightResizeRatio = img.height / this.maxImageHeight;
    }

    const resizeRatio = Math.max(widthResizeRatio, heightResizeRatio);

    const newWidth = img.width / resizeRatio;
    const newHeight = img.height / resizeRatio;

    canvas.width = newWidth;
    canvas.height = newHeight;

    context.drawImage(img, 0, 0, newWidth, newHeight);
  }

  /**
   * Called when the img element fires its load event.
   */
  onImageLoad(): void {
    this.imageHasLoaded.emit();
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
