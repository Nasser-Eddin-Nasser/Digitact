import { Component, OnInit, OnDestroy } from '@angular/core';
import 'rxjs';
import { ActivatedRoute } from '@angular/router';
import { NavController } from '@ionic/angular';

@Component({
  selector: 'app-forms',
  templateUrl: './forms.page.html',
  styleUrls: ['./forms.page.scss'],
})
export class FormsPage implements OnInit, OnDestroy {

  private stepsComponentMap = new Map();


  totalSteps = 10;

  completedStep = 0;

  progressPercentage = this.completedStep / this.totalSteps;


  constructor(private activeRoute: ActivatedRoute, private navigationController: NavController) {
    let stepsComponentMap = new Map();
    stepsComponentMap.set("1", "form-basic-info");
    stepsComponentMap.set("2", "babbab");
    this.stepsComponentMap = stepsComponentMap;
  }

  ngOnInit() {
    this.activeRoute.queryParams.subscribe((params) => {
      console.log(this.stepsComponentMap.get(params.step));
      this.completedStep = params.step;
      this.progressPercentage = this.completedStep / this.totalSteps;
    })
  }

  ngOnDestroy() { }

  onClose(): void {

    console.log("XSSX");
    this.navigationController.navigateForward([
      '/home'
    ]);

  }

}
