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
   * The image as a base64 string. Please note that this Component expects the input not to change.
   */
  @Input()
  base64String: string;

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
    const context = canvas.getContext('2d');

    const img = new Image();
    img.src = this.base64String;
    img.onload = () => {
      canvas.height = img.height;
      canvas.width = img.width;

      context.drawImage(img, 0, 0);

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
