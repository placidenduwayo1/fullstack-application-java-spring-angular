import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../auth-service/auth.service';

@Component({
  selector: 'app-login-component',
  templateUrl: './login-component.component.html',
  styleUrls: ['./login-component.component.scss']
})
export class LoginComponentComponent implements OnInit {

  loginForm!: FormGroup;

  constructor(private fb: FormBuilder,
    private authService: AuthService,
    private router: Router) { }

  ngOnInit(): void {
    this.loginForm = this.fb.group({
      login: ['', Validators.required],
      pwd: ['', Validators.required]
    })
  }

  onLogin() {
    const login: string = this.loginForm.value.login;
    const pwd: string = this.loginForm.value.pwd;
    
    const loginP: string ='placide';
    const pwdP='Placide';

    console.log('login = ' + login + ' pwd= ' + pwd);
    if (login !== loginP || pwd !== pwdP) {
      alert(
      `erreur d\'authentification:
      login et pwd doivent être ${loginP} et ${pwdP}`
      );
      this.loginForm.reset();
      this.router.navigateByUrl('');
    }
    else {
      this.authService.login();
      this.router.navigateByUrl("menu");
    }

  }

}
