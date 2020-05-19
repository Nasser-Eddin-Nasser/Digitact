import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NavController } from '@ionic/angular';
import { Subscription } from 'rxjs';
import { FormControl, FormGroup } from './../common/forms/forms';

@Component({
  selector: 'app-forms',
  templateUrl: './forms.page.html',
  styleUrls: ['./forms.page.scss'],
})
export class FormsPage implements OnInit, OnDestroy {

  sideMenuList = [{ id: 1, displayName: 'Personal info', selector: 'form-basic-info', isActive: false, isCompleted: false }];

  currentMenu: { id: number, displayName: string, isCompleted: boolean, isActive: boolean, selector: string };

  totalSteps: number;

  progressPercentage: number;

  private subscriptions: Subscription[] = [];

  constructor(private activeRoute: ActivatedRoute, private navigationController: NavController, private router: Router) {
  }

  basicInfoObj = new FormGroup<BasicInfo>({
    firstName: new FormControl(''),
    lastName: new FormControl(''),
    salutation: new FormControl('mr')
  });

  ngOnInit(): void {
    this.totalSteps = this.sideMenuList.length;
    this.progressPercentage = 0;
    this.sideMenuList[0].isActive = true;
    this.currentMenu = this.sideMenuList[0];

    const subscription = this.activeRoute.queryParams.subscribe((params) => {
      const step = Number(params.step);
      if (step > 0 && step <= this.totalSteps) {
        this.sideMenuList.filter(obj => obj.isActive = false);
        this.currentMenu = this.sideMenuList.filter(obj => obj.id === Number(params.step))[0];
        this.currentMenu.isActive = true;
      } else {
        console.warn('Invalid query param');
        this.sideMenuList[0].isActive = true;
        this.currentMenu = this.sideMenuList[0];
      }
    })
    this.subscriptions.push(subscription);
  }

  navigateToNextForm(): void {
    if (this.currentMenu.id !== this.totalSteps) {
      const nextMenuIndex = this.sideMenuList.indexOf(this.currentMenu) + 1;
      this.onFormStepsNavigation(this.sideMenuList[nextMenuIndex]);
    } else {
      alert('Next is submit page which is yet to be implmented');
    }
  }

  ngOnDestroy(): void {
    for (const subscription of this.subscriptions) {
      subscription.unsubscribe();
    }
    this.basicInfoObj = new FormGroup<BasicInfo>({
      firstName: new FormControl(''),
      lastName: new FormControl(''),
      salutation: new FormControl('mr')
    });
  }

  onClose(): void {
    this.navigationController.navigateForward([
      '/home'
    ]);
  }

  onFormStepsNavigation(event: { id: number, displayName: string, isCompleted: boolean, isActive: boolean, selector: string }): void {
    const param = event.id;
    this.router.navigate([], {
      relativeTo: this.activeRoute,
      queryParams: {
        step: param
      },
    });
  }
  pageProgressStatusCallBack(event: { id: number, displayName: string, isCompleted: boolean, isActive: boolean, selector: string }): void {
    console.log(event);
    const completedStep = this.sideMenuList.filter(obj => obj.isCompleted).length;
    this.progressPercentage = completedStep / this.totalSteps;
  }

}

interface BasicInfo {
  firstName: string,
  lastName: string,
  salutation: string
}
