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

    if(this.authService.isLoggedIn()){
      this.router.navigate(["menu"]); //s'il est déjà loggé, même si il revient on login, du moment qu'il n'a pas cliqué sur logout, il reste connecté
    }
  }
  onLogin() {
    const login = this.loginForm.get('login')?.value;
    const pwd = this.loginForm.get('pwd')?.value;
    this.authService.login(login, pwd).subscribe(
      (result)=>{
        console.log('-----------------',result);
        this.router.navigateByUrl('/menu');
      }
    );
  }
}


