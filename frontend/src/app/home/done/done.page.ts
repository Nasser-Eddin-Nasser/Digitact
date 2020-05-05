import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-done',
  templateUrl: './done.page.html',
  styleUrls: ['./done.page.scss'],
})
export class DonePage implements OnInit {
  name: string;

  constructor(private activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.name = this.activatedRoute.snapshot.paramMap.get('name');
  }
}
