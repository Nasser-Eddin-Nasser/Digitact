/*
  @description
    This Component displays star icons next to each other - some of them filled, some not filled.
*/

import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-stars',
  templateUrl: './stars.component.html',
  styleUrls: ['./stars.component.scss'],
})
export class StarsComponent {
  readonly TOTAL_STARS = 5;

  @Input()
  filledStars: number;
}
