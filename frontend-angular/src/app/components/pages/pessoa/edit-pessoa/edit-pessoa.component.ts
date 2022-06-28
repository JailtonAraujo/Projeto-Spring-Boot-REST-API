import { Component, OnInit } from '@angular/core';
import { Pessoa } from 'src/interfaces/Pessoa';
import { PessoaService } from 'src/app/services/pessoa.service';
import { MessageService } from 'src/app/services/message.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-edit-pessoa',
  templateUrl: './edit-pessoa.component.html',
  styleUrls: ['./edit-pessoa.component.css']
})
export class EditPessoaComponent implements OnInit {

  btnText="Salvar"
  person!:Pessoa;

  constructor(private activateRoute:ActivatedRoute,
    private pessoaSerice:PessoaService,
    private messageService:MessageService) { }

  ngOnInit(): void {
    const id = Number(this.activateRoute.snapshot.paramMap.get("id"));

    this.pessoaSerice.get(id).subscribe((iten) =>{
      this.person = iten;
    });
  }

  async edit(pessoa:Pessoa){

    await this.pessoaSerice.edit(pessoa).subscribe();

    this.messageService.add("Editado com sucesso");

  }

}
