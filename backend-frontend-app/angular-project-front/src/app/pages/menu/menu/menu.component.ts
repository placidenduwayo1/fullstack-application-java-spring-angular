import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {MenuItem} from 'primeng/api';
import {AuthService} from 'src/app/core/auth-service/auth.service';


@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss']
})
export class MenuComponent implements OnInit {

  items!: MenuItem[];
  activeItem!: MenuItem;

  constructor(private router: Router, private auth: AuthService) {
  }

  ngOnInit(): void {
    this.items = [
      {
        label: 'Home', icon: 'pi pi-fw pi-home',
        command: () => this.router.navigateByUrl('session/accueil')
      },
      {
        label: 'Addresses',
        command: () => this.router.navigateByUrl('session/addresses-management')
      },
      {
        label: 'Companies',
        command: () => this.router.navigateByUrl('session/companies-management')
      },
      {
        label: 'Employees',
        command: () => this.router.navigateByUrl('session/employees-management')
      },
      {
        label: 'Projects',
        command: () => this.router.navigateByUrl('session/projects-management')
      },
      {
        label: 'logout', icon: 'my-margin-left pi pi-fw pi-sign-out',
        command: () => this.logout()
      }
    ];

    this.activeItem = this.items[0];
  }

  logout() {
    this.auth.logout();
    this.router.navigateByUrl("login")
  }

}
