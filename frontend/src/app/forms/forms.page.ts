<<<<<<< HEAD
import { Component, OnDestroy, OnInit } from '@angular/core';
import { Validators } from '@angular/forms';
import { FormBuilder } from '@angular/forms';
=======
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
>>>>>>> a40cdd678f8684ffbf0ed13799cb2e793f90f684
import { ActivatedRoute, Router } from '@angular/router';
import { NavController } from '@ionic/angular';
import { Subscription } from 'rxjs';

<<<<<<< HEAD
=======
import { BasicInfo } from '../interfaces/basic-info';

>>>>>>> a40cdd678f8684ffbf0ed13799cb2e793f90f684
import { FormControl, FormGroup } from './../common/forms/forms';

@Component({
  selector: 'app-forms',
  templateUrl: './forms.page.html',
  styleUrls: ['./forms.page.scss'],
})
export class FormsPage implements OnInit, OnDestroy {
<<<<<<< HEAD
=======
  /*
  @Usage this array holds the object with   information required for different views.
  */
>>>>>>> a40cdd678f8684ffbf0ed13799cb2e793f90f684
  sideMenuList = [
    {
      id: 1,
      displayName: 'Personal info',
      selector: 'form-basic-info',
      isActive: false,
      isCompleted: false,
    },
<<<<<<< HEAD
    {
      id: 2,
      displayName: 'Contact info',
      selector: 'form-contact-info',
      isActive: false,
      isCompleted: false,
    },
  ];

=======
  ];

  /*
  @Usage this  object holds current view information.
  */
>>>>>>> a40cdd678f8684ffbf0ed13799cb2e793f90f684
  currentMenu: {
    id: number;
    displayName: string;
    isCompleted: boolean;
    isActive: boolean;
    selector: string;
  };

<<<<<<< HEAD
  formBuilder: FormBuilder;

  totalSteps: number;

  progressPercentage: number;

=======
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
>>>>>>> a40cdd678f8684ffbf0ed13799cb2e793f90f684
  private subscriptions: Subscription[] = [];

  constructor(
    private activeRoute: ActivatedRoute,
    private navigationController: NavController,
    private router: Router
  ) {}

<<<<<<< HEAD
=======
  /*
  @Usage  This property holds the type safe form group fields for basic information view.
  */
>>>>>>> a40cdd678f8684ffbf0ed13799cb2e793f90f684
  basicInfoObj = new FormGroup<BasicInfo>({
    firstName: new FormControl(''),
    lastName: new FormControl(''),
    salutation: new FormControl('mr'),
  });

<<<<<<< HEAD
  contactInfoObj = new FormGroup<ContactInfo>({
    phoneNumber: new FormControl(''),
    eMail: new FormControl(''),
    linkedIn: new FormControl(''),
    xing: new FormControl(''),
  });

  /*
  contactInfoObj = this.formBuilder.group({
    phoneNumber: new FormControl(
      '',
      Validators.compose([Validators.required, Validators.pattern('^[0-9]+$')])
    ),
    eMail: new FormControl(
      '',
      Validators.compose([
        Validators.required,
        Validators.pattern('^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$'),
      ])
    ),
    linkedIn: new FormControl(''),
    xing: new FormControl(''),
  });
  */

=======
  /*
  @Usage In this method route change is observed and handling is done.
  */
>>>>>>> a40cdd678f8684ffbf0ed13799cb2e793f90f684
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

<<<<<<< HEAD
=======
  /*
  @Usage In this method navigation to next step is handled.
  */
>>>>>>> a40cdd678f8684ffbf0ed13799cb2e793f90f684
  navigateToNextForm(): void {
    if (this.currentMenu.id !== this.totalSteps) {
      const nextMenuIndex = this.sideMenuList.indexOf(this.currentMenu) + 1;
      this.onFormStepsNavigation(this.sideMenuList[nextMenuIndex]);
    } else {
      alert('Next is submit page which is yet to be implmented');
    }
  }

<<<<<<< HEAD
=======
  /*
  @Usage In this method un subscribe event and restore to default values are handled.
  */
>>>>>>> a40cdd678f8684ffbf0ed13799cb2e793f90f684
  ngOnDestroy(): void {
    for (const subscription of this.subscriptions) {
      subscription.unsubscribe();
    }
    this.basicInfoObj = new FormGroup<BasicInfo>({
      firstName: new FormControl(''),
      lastName: new FormControl(''),
      salutation: new FormControl('mr'),
    });
<<<<<<< HEAD
    this.contactInfoObj = new FormGroup<ContactInfo>({
      phoneNumber: new FormControl('', Validators.maxLength(12)),
      eMail: new FormControl('', Validators.email),
      linkedIn: new FormControl(''),
      xing: new FormControl(''),
    });
  }

=======
  }

  /*
  @Usage In this method navigation to home page is handled.
  */
>>>>>>> a40cdd678f8684ffbf0ed13799cb2e793f90f684
  onClose(): void {
    this.navigationController.navigateForward(['/home']);
  }

<<<<<<< HEAD
=======
  /*
  @Usage In this method navigation to respective step is handled.
  */
>>>>>>> a40cdd678f8684ffbf0ed13799cb2e793f90f684
  onFormStepsNavigation(event: {
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
<<<<<<< HEAD
=======

  /*
  @Usage In this methos progress values are updated.
  */
>>>>>>> a40cdd678f8684ffbf0ed13799cb2e793f90f684
  pageProgressStatusCallBack(event: {
    id: number;
    displayName: string;
    isCompleted: boolean;
    isActive: boolean;
    selector: string;
  }): void {
    console.log(event);
    const completedStep = this.sideMenuList.filter((obj) => obj.isCompleted)
      .length;
    this.progressPercentage = completedStep / this.totalSteps;
  }
}
<<<<<<< HEAD

interface BasicInfo {
  firstName: string;
  lastName: string;
  salutation: string;
}

interface ContactInfo {
  phoneNumber: string;
  eMail: string;
  linkedIn: string;
  xing: string;
}
=======
>>>>>>> a40cdd678f8684ffbf0ed13799cb2e793f90f684
