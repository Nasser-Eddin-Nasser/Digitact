import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import { NavController } from '@ionic/angular';
import { FormControl, FormGroup } from '../common/forms/forms';


@Component({
  selector: 'app-forms',
  templateUrl: './forms.page.html',
  styleUrls: ['./forms.page.scss'],
})
export class FormsPage implements OnInit, OnDestroy {

  sideMenuList = [{ id: 1, displayName: "Personal info", selector: "form-basic-info", isActive: false, isCompleted: false }];

  currentMenu: { id: number, displayName: string, isCompleted: boolean, isActive: boolean, selector: string };

  completedStep = 0;

  totalSteps = this.sideMenuList.length;

  progressPercentage = this.completedStep / this.totalSteps;

  private subscriptions: Subscription[] = [];

  basicInfoObject = { salutation: 'mr', firstName: '', lastName: '' };

  constructor(private activeRoute: ActivatedRoute, private navigationController: NavController, private router: Router) {
    this.sideMenuList[0].isActive = true;
    this.currentMenu = this.sideMenuList[0];
  }

  basicInfoObj = new FormGroup<basicInfo>({
    firstName: new FormControl(''),
    lastName: new FormControl(''),
    salutation: new FormControl('mr')
  });
  ngOnInit() {
    const subscription = this.activeRoute.queryParams.subscribe((params) => {
      let step = Number(params.step);
      if (step > 0 && step <= this.totalSteps) {
        this.sideMenuList.filter(obj => obj.isActive = false);
        this.currentMenu = this.sideMenuList.filter(obj => obj.id == Number(params.step))[0];
        this.currentMenu.isActive = true;
      } else {
        console.warn("Invalid query param");
        this.sideMenuList[0].isActive = true;
        this.currentMenu = this.sideMenuList[0];

      }
    })
    this.subscriptions.push(subscription);
  }

  navigateToNextForm() {
    if (this.currentMenu.id != this.totalSteps) {
      let nextMenuIndex = this.sideMenuList.indexOf(this.currentMenu) + 1;
      this.onFormStepsNavigation(this.sideMenuList[nextMenuIndex]);
    } else {
      alert("Next is submit page which is yet to be implmented");
    }
  }

  ngOnDestroy() {
    for (const subscription of this.subscriptions)
      subscription.unsubscribe();
  }

  onClose(): void {
    this.navigationController.navigateForward([
      '/home'
    ]);
  }

  onFormStepsNavigation(ev: { id: number, displayName: string, isCompleted: boolean, isActive: boolean, selector: string }) {

    let param = ev.id;
    this.router.navigate([], {
      relativeTo: this.activeRoute,
      queryParams: {
        step: param
      },
    });
  }
  pageProgressStatusCallBack(ev: { id: number, displayName: string, isCompleted: boolean, isActive: boolean, selector: string }) {
    console.log(ev);
    this.completedStep = this.sideMenuList.filter(obj => obj.isCompleted == true).length;
    this.progressPercentage = this.completedStep / this.totalSteps;


  }

}

interface basicInfo {
  firstName: string,
  lastName: string,
  salutation: string
}
