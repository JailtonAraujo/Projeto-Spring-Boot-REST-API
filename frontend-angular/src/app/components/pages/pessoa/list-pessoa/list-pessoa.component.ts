import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { faSearch, faTimes, faPencil } from '@fortawesome/free-solid-svg-icons';
import { Pessoa } from 'src/interfaces/Pessoa';
import { PessoaService } from 'src/app/services/pessoa.service';

@Component({
  selector: 'app-list-pessoa',
  templateUrl: './list-pessoa.component.html',
  styleUrls: ['./list-pessoa.component.css']
})
export class ListPessoaComponent implements OnInit {

  faSearch=faSearch;
  faTimes=faTimes;
  faPencil=faPencil;
  
  @Output() deletar = new EventEmitter<Number>();

  @Input() allpessoas:Pessoa[] = [];
  constructor(private pessoaService:PessoaService) { }

  ngOnInit(): void {
    
  }

  Listpessoas(){

  }

  remove(id:Number){
    this.deletar.emit(id);
  }

}
