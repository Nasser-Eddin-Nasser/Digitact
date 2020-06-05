import { Component, Input } from '@angular/core';
import { /*DomSanitizer,*/ SafeResourceUrl } from '@angular/platform-browser';
import { CameraResultType, CameraSource, Plugins } from '@capacitor/core';

import { FormGroup } from '../../../common/forms/forms';
import { FormsData } from '../../../model/forms-data.model';

@Component({
  selector: 'form-pictures-entry',
  templateUrl: './pictures-entry.component.html',
  styleUrls: ['./pictures-entry.component.scss'],
})

/*
  To save Files as Data.url just change restultType and uncomment everything in this file
*/
export class PicturesEntryComponent {
  constructor(/*private sanitizer: DomSanitizer*/) {}
  @Input()
  formsData: FormGroup<FormsData>;

  photo: SafeResourceUrl;
  condition = true;

  async takePicture(): Promise<void> {
    const image = await Plugins.Camera.getPhoto({
      quality: 100,
      allowEditing: false,
      resultType: CameraResultType.Base64,
      source: CameraSource.Camera,
    });

    /*this.photo = this.sanitizer.bypassSecurityTrustResourceUrl(
      image && image.dataUrl
    );
    */
    this.photo = 'data:image/jpeg;base64,' + image.base64String;
    this.condition = false;
    this.formsData.controls.pictureEntry.controls.condition.setValue('s');
  }
  /**
   * delete picture entry and reset page so you can add a new picture.
   */
  deletePictureEntry(): void {
    this.condition = true;
    this.formsData.controls.pictureEntry.controls.condition.setValue('');
  }
}
