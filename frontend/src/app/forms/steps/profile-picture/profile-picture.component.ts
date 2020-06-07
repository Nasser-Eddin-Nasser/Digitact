import { Component, Input } from '@angular/core';
import {
  CameraDirection,
  CameraResultType,
  CameraSource,
  Plugins,
} from '@capacitor/core';

import { FormGroup } from '../../../common/forms/forms';
import { FormsData } from '../../../model/forms-data.model';

@Component({
  selector: 'form-profile-picture',
  templateUrl: './profile-picture.component.html',
  styleUrls: ['./profile-picture.component.scss'],
})
export class ProfilePictureComponent {
  constructor() {}
  @Input()
  formsData: FormGroup<FormsData>;

  async takePicture(): Promise<void> {
    const image = await Plugins.Camera.getPhoto({
      quality: 100,
      allowEditing: false,
      resultType: CameraResultType.Base64,
      source: CameraSource.Camera,
      direction: CameraDirection.Front,
    }).catch((err) => {
      console.log('catch cameraphoto');
      throw new Error(err);
    });

    this.formsData.controls.profilePicture.controls.pictureBase64.setValue(
      'data:image/jpeg;base64,' + image.base64String
    );
  }
  /**
   * delete picture entry and reset page so you can add a new picture.
   */
  deletePictureEntry(): void {
    this.formsData.controls.profilePicture.controls.pictureBase64.setValue('');
  }
}
