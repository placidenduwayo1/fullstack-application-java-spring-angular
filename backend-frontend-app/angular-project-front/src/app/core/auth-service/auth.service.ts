import { Injectable } from "@angular/core";
import { Observable, of } from "rxjs";

@Injectable({ providedIn: 'root' })
export class AuthService {

    private setToken(token: string) {
        localStorage.setItem('token', token)
    }
    
    public login(email : string, pwd: string) : Observable<any> {
        const mm='placide.nd@gmail.com';
        const pp = 'placide';
        if (email === mm && pwd === pp) {
            this.setToken('abcdefghijklmnopqrstuvwxyz');
            return of({nom:'Placide ND',email:mm})
        }
        else {
            return of (null);
        }
    }

    public  getToken(): string | null{
        return localStorage.getItem('token');
    }

    public isLoggedIn(): boolean {
        const token = this.getToken();
        if (token) {
            return true;
        }
        return false;
    }

    public logout() {
        localStorage.removeItem('token');
    }
}

