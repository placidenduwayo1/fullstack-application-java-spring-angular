import { HTTP_INTERCEPTORS } from "@angular/common/http";
import { RequestAuthInterceptor } from "./request-auth-interceptor.service";

export const httpInterceptorProvider = [
    { provide: HTTP_INTERCEPTORS, useClass: RequestAuthInterceptor, multi: true }
]