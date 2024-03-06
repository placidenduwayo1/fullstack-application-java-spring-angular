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
    });

    /*s'il est déjà loggé c'est à dire
      si le token est deja setté dans le storage:
      localStorage.setItem('token', token), 
      même s'il revient on login, du moment qu'il n'a pas cliqué sur logout,
      il reste connecté*/
    if (this.authService.isLoggedIn()) {
      this.router.navigate(["session/menu"]);
    }
  }
  onLogin() {
    const login = this.loginForm.get('login')?.value;
    const pwd = this.loginForm.get('pwd')?.value;
    this.authService.login(login, pwd).subscribe(
      data => {
        if (data) {
          console.log('auth informations ----- ', data);
          this.router.navigateByUrl('session/menu');
        }
        else {
          //console.log(err);
          alert(`login fail:
        login is placide.nd@gmail.com,
        pwd is placide`);
          this.router.navigateByUrl('/login')
        }
      });
  }
}




