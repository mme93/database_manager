import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-database-settings',
  templateUrl: './database-settings.component.html',
  styleUrl: './database-settings.component.scss'
})
export class DatabaseSettingsComponent implements OnInit {
  paramValue: string | null = '';

  constructor(private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.paramValue=localStorage.getItem('database-settings-name');
  }

}
