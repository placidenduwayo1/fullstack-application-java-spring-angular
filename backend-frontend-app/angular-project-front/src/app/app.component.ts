import { Component } from '@angular/core';
import { AuthService } from './core/auth-service/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'angular training project front';

  constructor(private authService: AuthService){}

  show(){
    return this.authService.isLoged();
  }
}
