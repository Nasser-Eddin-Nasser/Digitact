/*
@Author
Bharathwaj Ravi

Add modifiers under @Modifiers
@Modifiers

@Purpose
  - This component renders the hamburger side menu and its actions.
*/

import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-side-menu',
  templateUrl: './side-menu.component.html',
  styleUrls: ['./side-menu.component.scss'],
})
export class SideMenuComponent {
  /*
  @Usage this array holds the list of different steps information.
  */
  @Input() sideMenuList: Array<{
    id: number;
    displayName: string;
    isCompleted: boolean;
    isActive: boolean;
    selector: string;
  }>;

  /*
  @Usage this takes care of emitting event to parent.
  */
  @Output() private chosenStep = new EventEmitter();

  /*
  @Usage this method emits the event for progrss call back.
  */
  changeStep(menu: {
    id: number;
    displayName: string;
    isCompleted: boolean;
    isActive: boolean;
    selector: string;
  }): void {
    this.chosenStep.emit(menu);
  }
}
