import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MenuItem } from 'primeng/api';


@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss']
})
export class MenuComponent implements OnInit {

  items!: MenuItem[];
  activeItem!: MenuItem;

  constructor(private router: Router) { }

  ngOnInit(): void {
    this.items = [
      {
        command: ()=>this.router.navigateByUrl('')
      },
      {
        label: 'Home', icon: 'pi pi-fw pi-home',
        command: () => this.router.navigateByUrl('/accueil')
      },
      {
        label: 'Addresses',
        command: ()=>this.router.navigateByUrl('/addresses-management')
      },
      {
        label: 'Companies',
        command : ()=>this.router.navigateByUrl('/companies-management')
      },
      {
        label: 'Employees',
        command: ()=>this.router.navigateByUrl('/employees-management')
      },
      {
        label: 'Projects',
        command: ()=>this.router.navigateByUrl('/projects-management')
      }
    ];

    this.activeItem = this.items[0];
  }

}
