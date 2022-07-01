import { HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HTTP_INTERCEPTORS } from '@angular/common/http';
import { Injectable, NgModule } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';

@Injectable(
)
export class HeaderInterceptorService implements HttpInterceptor{

  constructor() { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    if(localStorage.getItem('token') !== null){
      const token = `${localStorage.getItem('token')}`;

      const tokenRequest = req.clone({
        headers : req.headers.set('Authorization', token)
      });
      return next.handle(tokenRequest).pipe(catchError(this.processError));
    }else{
      return next.handle(req);
    }

  }

  processError(error:HttpErrorResponse){
    let errorMessage = "Erro desconhecido!";
    if(error.error instanceof ErrorEvent){
      console.log(error.error);
      errorMessage =  "Error: " + error.error.error;
    }else{
      errorMessage = "Codigo:  " + error.error.code + "\nMessage: " + error.error.error;
    }
    alert(errorMessage);
    return throwError(errorMessage);

  }

}

@NgModule({
 providers:[{
  provide : HTTP_INTERCEPTORS,
  useClass:HeaderInterceptorService,
  multi : true
 },
],
})

export class HttpInterceptorModule{

}
