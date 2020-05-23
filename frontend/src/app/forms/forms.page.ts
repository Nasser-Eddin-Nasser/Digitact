/**
 * @description
 *   This page handles the basic operation of tracking progress, navigation, close menu, continue button,
 *   event handlers for child to parent communication and parent to child data down.
 */

import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NavController } from '@ionic/angular';
import { Subscription } from 'rxjs';

import { BasicInfo } from '../interfaces/basic-info';
import { ContactInfo } from '../interfaces/contact-info';

import { FormControl, FormGroup } from './../common/forms/forms';

@Component({
  selector: 'app-forms',
  templateUrl: './forms.page.html',
  styleUrls: ['./forms.page.scss'],
})
export class FormsPage implements OnInit, OnDestroy {
  /**
   * This array holds the object with   information required for different views.
   */
  sideMenuList = [
    {
      id: 1,
      displayName: 'Personal info',
      selector: 'form-basic-info',
      isActive: false,
      isCompleted: false,
    },
    {
      id: 2,
      displayName: 'Contact info',
      selector: 'form-contact-info',
      isActive: false,
      isCompleted: false,
    },
  ];

  /**
   * This object holds current view information.
   */
  currentMenu: {
    id: number;
    displayName: string;
    isCompleted: boolean;
    isActive: boolean;
    selector: string;
  };

  /**
   * Holds total steps in the form.
   */
  totalSteps: number;

  /**
   * Holds progress value.
   */
  progressPercentage: number;

  /**
   * Holds all the subscription which will be useful for un subscribing on destroy.
   */
  private subscriptions: Subscription[] = [];

  constructor(
    private activeRoute: ActivatedRoute,
    private navigationController: NavController,
    private router: Router
  ) {}

  /**
   * This property holds the type safe form group fields for basic information view.
   */
  basicInfoObj = new FormGroup<BasicInfo>({
    firstName: new FormControl(''),
    lastName: new FormControl(''),
    salutation: new FormControl('mr'),
  });

  /*
  @Usage  This property holds the type safe form group fields for contact information view.
  */
  contactInfoObj = new FormGroup<ContactInfo>({
    phoneNumber: new FormControl(''),
    eMail: new FormControl(''),
    linkedIn: new FormControl(''),
    xing: new FormControl(''),
  });

  /*
  @Usage In this method route change is observed and handling is done.
  */
  ngOnInit(): void {
    this.totalSteps = this.sideMenuList.length;
    this.progressPercentage = 0;
    this.sideMenuList[0].isActive = true;
    this.currentMenu = this.sideMenuList[0];

    const subscription = this.activeRoute.queryParams.subscribe((params) => {
      const step = Number(params.step);
      if (step > 0 && step <= this.totalSteps) {
        this.sideMenuList.filter((obj) => (obj.isActive = false));
        this.currentMenu = this.sideMenuList.filter(
          (obj) => obj.id === Number(params.step)
        )[0];
        this.currentMenu.isActive = true;
      } else {
        console.warn('Invalid query param');
        this.sideMenuList[0].isActive = true;
        this.currentMenu = this.sideMenuList[0];
      }
    });
    this.subscriptions.push(subscription);
  }

  /**
   * In this method navigation to next step is handled.
   */
  navigateToNextStep(): void {
    if (this.currentMenu.id !== this.totalSteps) {
      const nextMenuIndex = this.sideMenuList.indexOf(this.currentMenu) + 1;
      this.navigateToStep(this.sideMenuList[nextMenuIndex]);
    } else {
      alert('Next is submit page which is yet to be implmented');
    }
  }

  /**
   * In this method un subscribe event and restore to default values are handled.
   */
  ngOnDestroy(): void {
    for (const subscription of this.subscriptions) {
      subscription.unsubscribe();
    }
    this.basicInfoObj = new FormGroup<BasicInfo>({
      firstName: new FormControl(''),
      lastName: new FormControl(''),
      salutation: new FormControl('mr'),
    });
  }

  /**
   * In this method navigation to home page is handled.
   */
  closeForm(): void {
    this.navigationController.navigateForward(['/home']);
  }

  /**
   * In this method navigation to respective step is handled.
   * @param  menu - Contains the menu to navigate
   */
  navigateToStep(menu: {
    id: number;
    displayName: string;
    isCompleted: boolean;
    isActive: boolean;
    selector: string;
  }): void {
    const param = menu.id;
    this.router.navigate([], {
      relativeTo: this.activeRoute,
      queryParams: {
        step: param,
      },
    });
  }

  /**
   * In this methos progress values are updated.
   */
  updateProgessStatus(): void {
    const completedStep = this.sideMenuList.filter((obj) => obj.isCompleted)
      .length;
    this.progressPercentage = completedStep / this.totalSteps;
  }
}
