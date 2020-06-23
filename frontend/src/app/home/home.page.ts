import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { NavController, PopoverController } from '@ionic/angular';

import { FormsData } from '../model/forms-data.model';
import { StorageHandlerService } from '../services/storage-handler.service';

import { LanguagePickerComponent } from './language-picker/language-picker.component';

@Component({
  selector: 'app-home',
  templateUrl: 'home.page.html',
  styleUrls: ['home.page.scss'],
})
export class HomePage implements OnInit {
  applicationSize: number;
  constructor(
    private navController: NavController,
    private storage: StorageHandlerService,
    private router: ActivatedRoute,
    private popoverController: PopoverController
  ) {}

  /**
   * In this method size of the applications is fetched
   */
  ngOnInit(): void {
    this.router.params.subscribe(() => {
      this.storage
        .getAllItems<FormsData>(this.storage.applicantDetailsDb)
        .then((data) => {
          this.applicationSize = data.length;
        });
    });
  }
  /**
   * In this method navigation to application form is handled.
   */
  startApplication(): void {
    this.navController.navigateForward(['/privacy']);
  }
  /**
   * In this method navigation to operation on submitted and finalized applications is handled.
   */
  goToApplications(): void {
    this.navController.navigateRoot(['/applicants']);
  }

  async openLanguagePicker(ev: MouseEvent): Promise<void> {
    const popover = await this.popoverController.create({
      component: LanguagePickerComponent,
      event: ev,
      componentProps: { page: 'Login' },
      cssClass: 'popover_class',
    });
    return await popover.present();
  }
}
