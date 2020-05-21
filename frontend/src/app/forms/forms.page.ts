/*
@Author
Bharathwaj Ravi

Add modifiers under @Modifiers
@Modifiers

@Purpose
  - This page handles the basic operation of tracking progress, navigation, close menu, continue button,
   event handlers for child to parent communication and parent to child data down.
*/

import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NavController } from '@ionic/angular';
import { Subscription } from 'rxjs';

import { BasicInfo } from '../interfaces/basic-info';

import { FormControl, FormGroup } from './../common/forms/forms';

@Component({
  selector: 'app-forms',
  templateUrl: './forms.page.html',
  styleUrls: ['./forms.page.scss'],
})
export class FormsPage implements OnInit, OnDestroy {
  /*
  @Usage this array holds the object with   information required for different views.
  */
  sideMenuList = [
    {
      id: 1,
      displayName: 'Personal info',
      selector: 'form-basic-info',
      isActive: false,
      isCompleted: false,
    },
  ];

  /*
  @Usage this  object holds current view information.
  */
  currentMenu: {
    id: number;
    displayName: string;
    isCompleted: boolean;
    isActive: boolean;
    selector: string;
  };

  /*
  @Usage  holds total steps in the form.
  */
  totalSteps: number;

  /*
  @Usage  holds progress value.
  */
  progressPercentage: number;

  /*
  @Usage  holds all the subscription which will be useful for un subscribing on destroy.
  */
  private subscriptions: Subscription[] = [];

  constructor(
    private activeRoute: ActivatedRoute,
    private navigationController: NavController,
    private router: Router
  ) {}

  /*
  @Usage  This property holds the type safe form group fields for basic information view.
  */
  basicInfoObj = new FormGroup<BasicInfo>({
    firstName: new FormControl(''),
    lastName: new FormControl(''),
    salutation: new FormControl('mr'),
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

  /*
  @Usage In this method navigation to next step is handled.
  */
  navigateToNextStep(): void {
    if (this.currentMenu.id !== this.totalSteps) {
      const nextMenuIndex = this.sideMenuList.indexOf(this.currentMenu) + 1;
      this.navigateToStep(this.sideMenuList[nextMenuIndex]);
    } else {
      alert('Next is submit page which is yet to be implmented');
    }
  }

  /*
  @Usage In this method un subscribe event and restore to default values are handled.
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

  /*
  @Usage In this method navigation to home page is handled.
  */
  closeForm(): void {
    this.navigationController.navigateForward(['/home']);
  }

  /*
  @Usage In this method navigation to respective step is handled.
  */
  navigateToStep(event: {
    id: number;
    displayName: string;
    isCompleted: boolean;
    isActive: boolean;
    selector: string;
  }): void {
    const param = event.id;
    this.router.navigate([], {
      relativeTo: this.activeRoute,
      queryParams: {
        step: param,
      },
    });
  }

  /*
  @Usage In this methos progress values are updated.
  */
  updateProgessStatus(): void {
    const completedStep = this.sideMenuList.filter((obj) => obj.isCompleted)
      .length;
    this.progressPercentage = completedStep / this.totalSteps;
  }
}
