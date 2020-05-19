<<<<<<< HEAD
=======
/*
@Author
Bharathwaj Ravi

Add modifiers under @Modifiers
@Modifiers

@Purpose
  - This component renders the hamburger side menu and its actions.
*/

>>>>>>> a40cdd678f8684ffbf0ed13799cb2e793f90f684
import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-side-menu',
  templateUrl: './side-menu.component.html',
  styleUrls: ['./side-menu.component.scss'],
})
export class SideMenuComponent {
<<<<<<< HEAD

  @Input() sideMenuList: Array<{ id: number, displayName: string, isCompleted: boolean, isActive: boolean, selector: string }>

  @Output() private pageProgressStatusCallBack = new EventEmitter();

  constructor() { }

  onMenuChange(menu: { id: number, displayName: string, isCompleted: boolean, isActive: boolean, selector: string }): void {
    this.pageProgressStatusCallBack.emit(menu);
  }

=======
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
  @Output() private pageProgressStatusCallBack = new EventEmitter();

  constructor() {}

  /*
  @Usage this method emits the event for progrss call back.
  */
  onMenuChange(menu: {
    id: number;
    displayName: string;
    isCompleted: boolean;
    isActive: boolean;
    selector: string;
  }): void {
    this.pageProgressStatusCallBack.emit(menu);
  }
>>>>>>> a40cdd678f8684ffbf0ed13799cb2e793f90f684
}
