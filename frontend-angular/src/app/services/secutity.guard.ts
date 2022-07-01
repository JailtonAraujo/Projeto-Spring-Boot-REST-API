import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { MessageService } from './message.service';

@Injectable({
  providedIn: 'root'
})

export class SecutityGuard implements CanActivate {

  constructor(private router:Router, private messageService:MessageService ){}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {

      console.log("Chamou");

      if(localStorage.getItem('token') == null || localStorage.getItem('token')?.trim() == ''){
        this.router.navigate(['']);
        this.messageService.add('Erro na autenticação ou token expirado. por favor, autentique-se!');
        return false;
      }else{
    return true;
      }
  }
  
}
