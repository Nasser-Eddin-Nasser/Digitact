import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-side-menu',
  templateUrl: './side-menu.component.html',
  styleUrls: ['./side-menu.component.scss'],
})
export class SideMenuComponent implements OnInit {

  @Input() sideMenuList: Array<{ id: number, displayName: string, isCompleted: boolean, isActive: boolean, selector: string }>

  @Output() private pageProgressStatusCallBack = new EventEmitter();

  constructor() { }

  ngOnInit() { }

  onMenuChange(menu: { id: number, displayName: string, isCompleted: boolean, isActive: boolean, selector: string }) {
    this.pageProgressStatusCallBack.emit(menu);
  }

}
