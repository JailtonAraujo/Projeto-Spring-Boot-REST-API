import { Component, OnInit } from '@angular/core';
import { Pessoa } from 'src/interfaces/Pessoa';
import { PessoaService } from 'src/app/services/pessoa.service';
import { MessageService } from 'src/app/services/message.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-home-pessoa',
  templateUrl: './home-pessoa.component.html',
  styleUrls: ['./home-pessoa.component.css']
})
export class HomePessoaComponent implements OnInit {



  constructor(private pessoaService:PessoaService,
  private messageService:MessageService, private router:Router,
  private activateRoute:ActivatedRoute) { }

  ngOnInit(): void {
    const id = Number(this.activateRoute.snapshot.paramMap.get('id'));
    if(id > 0){
      this.pessoaService.get(id).subscribe((item)=>{
        this.pessoa.id = item.id;

      });
    }
  }

  async createHandler(pessoa:Pessoa){

    await this.pessoaService.savePessoa(pessoa).subscribe();
    this.messageService.add("Salvo com sucesso!");
  }
  

}
