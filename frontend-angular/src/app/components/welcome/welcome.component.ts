import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MessageService } from 'src/app/services/message.service';

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.css']
})
export class WelcomeComponent implements OnInit {

  constructor(private router:Router, private messageService:MessageService) { }

  ngOnInit(): void {
    /*
    if(localStorage.getItem('token') == null || localStorage.getItem('token')?.trim() == ''){
      this.router.navigate(['']);
      this.messageService.add('Erro na autenticação ou token expirado. por favor, autentique-se!');
    }
    */
  }

}
