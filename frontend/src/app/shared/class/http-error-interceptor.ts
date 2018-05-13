
import {
  HttpErrorResponse,
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {TokenService} from "../service/auth/token.service";
import {throwError, Observable} from 'rxjs';
import {catchError, mergeMap} from 'rxjs/operators'

@Injectable()
export class HttpErrorInterceptor implements HttpInterceptor {

  constructor(private tokenService : TokenService){

  }

  private handleHttpError(error){
    if (error instanceof HttpErrorResponse) {
      if (error.status === 401 || error.status === 0) {
        this.tokenService.logout();
      }
    }
    return throwError(error);
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const request = req.clone();
    return next.handle(request).pipe(
      catchError((err: HttpErrorResponse) => this.handleHttpError(err))
    )
  }

}
