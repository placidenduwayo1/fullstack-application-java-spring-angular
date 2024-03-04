import { inject } from "@angular/core";
import { CanActivateChildFn, Router } from "@angular/router";
import { AuthService } from "../auth-service/auth.service";

export const authGuardService : CanActivateChildFn = () => {
  
  const router: Router = inject(Router);
  
  const authService: AuthService = inject(AuthService);
  
  const token = authService.getToken();
  
  if(token){
    return true;
  }
  else{
    alert("access denied because not authenticated");
    router.navigateByUrl('')
    return false;
  }
}

