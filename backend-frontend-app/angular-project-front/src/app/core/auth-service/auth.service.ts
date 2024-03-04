import { Injectable } from "@angular/core";

@Injectable({providedIn: 'root'})
export class AuthService {
    
    private token!: string;

    login(){
        this.token = "my-token to authorize all request of application";
    }
   
    getToken(): string{
        return this.token;
    }
    
    isLoged(): boolean {
        const token = this.getToken();
        if(token){
            return true;
        }
        return false;
    }
}

