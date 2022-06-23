import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { Usuario } from 'src/interfaces/Usuario';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Response } from 'src/interfaces/Response';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  baseApiUrl = environment.baseApiUrl;
  ApiUriLogar = `${this.baseApiUrl}login`;

  constructor(private http:HttpClient,
    private router:Router) { }

  logar(usuario:Usuario):Observable<Response<Usuario>>{
    return this.http.post<Response<Usuario>>(this.ApiUriLogar, JSON.stringify(usuario))
  }

  logout(){
    localStorage.setItem('token','');
    this.router.navigate(['']);
  }
}
