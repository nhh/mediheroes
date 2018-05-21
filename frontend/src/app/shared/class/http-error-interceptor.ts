
import {
  HttpErrorResponse,
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {throwError, Observable} from 'rxjs';
import {catchError, mergeMap} from 'rxjs/operators';
import {AuthService} from '../service/auth/auth.service';
import {Router} from '@angular/router';

@Injectable()
export class HttpErrorInterceptor implements HttpInterceptor {

  constructor(
    private authService : AuthService
  ){}

  private handleHttpError(error){
    if (error instanceof HttpErrorResponse) {
      if(error.status === 502 || error.status === 503){

      }

      if (error.status === 401 || error.status === 0) {
        this.authService.logout();
      }
    }
    return throwError(error);
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const request = req.clone();
    return next.handle(request).pipe(
      catchError((err: HttpErrorResponse) => this.handleHttpError(err))
    );
  }

}
