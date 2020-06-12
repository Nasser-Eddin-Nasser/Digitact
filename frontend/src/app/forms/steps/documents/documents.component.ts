/*
  @description
    This component renders the documents step view and its actions.
*/
import { Component, Input } from '@angular/core';
import {
  CameraOptions,
  CameraPhoto,
  CameraResultType,
  CameraSource,
  Plugins,
} from '@capacitor/core';

import { FormControl, FormGroup } from '../../../common/forms/forms';
import { FormsData } from '../../../model/forms-data.model';
import { ImageViewerService } from '../../image-viewer/image-viewer.service';

@Component({
  selector: 'form-documents',
  templateUrl: './documents.component.html',
  styleUrls: ['./documents.component.scss'],
})
export class DocumentsComponent {
  constructor(private imageViewerService: ImageViewerService) {}

  /**
   * Data of the entire form.
   */
  @Input()
  formsData: FormGroup<FormsData>;

  takePicture(): void {
    const cameraConfig: CameraOptions = {
      quality: 100,
      allowEditing: false,
      resultType: CameraResultType.Base64,
      source: CameraSource.Camera,
    };
    Plugins.Camera.getPhoto(cameraConfig)
      .then((result) => {
        this.handleTakenPicture(result);
      })
      .catch(() => {
        // It looks like closing the Camera overlay in the webview, rejects the promise
      });
  }

  private handleTakenPicture(picture: CameraPhoto): void {
    const base64Photo = 'data:image/jpeg;base64,' + picture.base64String;

    const newFormControl = new FormControl<string>(base64Photo);

    this.formsData.controls.documents.controls.documentsBase64.push(
      newFormControl
    );
  }

  /**
   * Open the Image Viewer and pre-select an image.
   *
   * @param initialImage The index of the image that shall be pre-selected.
   */
  openImageViewer(initialImage: number): void {
    this.imageViewerService.showImageViewer({
      imagesBase64: this.formsData.controls.documents.controls.documentsBase64,
      initialImage,
    });
  }
}
