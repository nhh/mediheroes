
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

  constructor(private authService : AuthService, private router : Router){

  }

  private handleHttpError(error){
    if (error instanceof HttpErrorResponse) {
      if (error.status === 401 || error.status === 0) {
        this.authService.logout().subscribe(
          (success) => {

          }, (error) => {
            console.log(error);
          },
          () => this.router.navigate(['/login'])
        );
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
