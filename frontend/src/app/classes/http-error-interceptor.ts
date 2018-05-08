import {
  HttpErrorResponse,
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {Observable} from 'rxjs/Rx';
import {TokenService} from "../services/auth/token.service";


@Injectable()
export class HttpErrorInterceptor implements HttpInterceptor {

  constructor(private tokenService : TokenService){

  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const request = req.clone();
    return next.handle(request).catch((err: any) => {
      if (err instanceof HttpErrorResponse) {
        if (err.status === 401 || err.status === 0) {
          this.tokenService.logout();
        }
      }
      return Observable.throw(err);
    });
  }

}
