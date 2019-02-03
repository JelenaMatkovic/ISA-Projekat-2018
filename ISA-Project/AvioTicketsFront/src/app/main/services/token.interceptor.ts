import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { AuthService } from "./auth.service";

@Injectable()
export class TokenInterceptor implements HttpInterceptor{

    constructor(public auth: AuthService) {}
    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const token:string = this.auth.getToken();
    if(token){
      request = request.clone({
        setHeaders: {
          Authorization: token
        }
      });
    }
    return next.handle(request);
  }
}