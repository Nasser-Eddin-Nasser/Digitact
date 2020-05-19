import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-side-menu',
  templateUrl: './side-menu.component.html',
  styleUrls: ['./side-menu.component.scss'],
})
export class SideMenuComponent {

  @Input() sideMenuList: Array<{ id: number, displayName: string, isCompleted: boolean, isActive: boolean, selector: string }>

  @Output() private pageProgressStatusCallBack = new EventEmitter();

  constructor() { }

  onMenuChange(menu: { id: number, displayName: string, isCompleted: boolean, isActive: boolean, selector: string }): void {
    this.pageProgressStatusCallBack.emit(menu);
  }

}
