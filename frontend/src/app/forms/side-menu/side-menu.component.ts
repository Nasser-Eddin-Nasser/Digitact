/**
 * @description
 *  This component renders the hamburger side menu and its actions.
 */

import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-side-menu',
  templateUrl: './side-menu.component.html',
  styleUrls: ['./side-menu.component.scss'],
})
export class SideMenuComponent {
  /**
   * This array holds the list of different steps information.
   */
  @Input() sideMenuList: Array<{
    id: number;
    displayName: string;
    isCompleted: boolean;
    isActive: boolean;
    selector: string;
  }>;

  /**
   * This takes care of emitting event to parent.
   */
  @Output() private chosenStep = new EventEmitter();

  /**
   * This method emits the event for step navigation.
   * @param  menu - Contains the menu to navigate
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
