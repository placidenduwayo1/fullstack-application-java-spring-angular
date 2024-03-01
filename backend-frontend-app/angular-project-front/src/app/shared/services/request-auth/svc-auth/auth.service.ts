import { Injectable } from "@angular/core";

@Injectable({providedIn: 'root'})
export class AuthService {
    private token: string = "my-token to authorize all request of application";
   
    getToken(): string{
        return this.token;
    }
}