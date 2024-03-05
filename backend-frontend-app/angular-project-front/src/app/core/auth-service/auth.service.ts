import { Injectable } from "@angular/core";
import { Router } from "@angular/router";
import { Observable, of } from "rxjs";

@Injectable({ providedIn: 'root' })
export class AuthService {

    constructor(private router: Router){}

    setToken(token: string) {
        localStorage.setItem('token', token)
    }
    
    login(email : string, pwd: string) : Observable<any> {
        const mm='placide.nd@gmail.com';
        const pp = 'placide';
        if (email === mm && pwd === pp) {
            this.setToken('abcdefghijklmnopqrstuvwxyz');
            this.router.navigate(['menu']);
            return of({nom:'Placide ND',email:mm})
        }
        else {
            alert(`login fail: 
            login is ${mm}, pwd si ${pp}`);
            this.router.navigate(['login']);
            return of (null);
        }
    }

    getToken(): string | null{
        return localStorage.getItem('token');
    }

    isLoggedIn(): boolean {
        const token = this.getToken();
        if (token) {
            return true;
        }
        return false;
    }

    logout() {
        localStorage.removeItem('token');
        this.router.navigateByUrl('/login');
    }
}

