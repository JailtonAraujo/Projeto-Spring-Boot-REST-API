import { Component, Input, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Pessoa } from 'src/interfaces/Pessoa';

@Component({
  selector: 'app-form-pessoa',
  templateUrl: './form-pessoa.component.html',
  styleUrls: ['./form-pessoa.component.css']
})
export class FormPessoaComponent implements OnInit {

  formPerson!:FormGroup;
  @Input() person:Pessoa | null = null;


  constructor() { }

  ngOnInit(): void {
    this.formPerson = new FormGroup({
      id: new FormControl(this.person ? this.person.id : ""),
      name:new FormControl(this.person ? this.person.name : "",[Validators.required]),
      lastname: new FormControl(this.person ? this.person.lastname : "", [Validators.required]),
      birthdate: new FormControl(this.person ? this.person.birthdate : "", [Validators.required])
    })
  }

  submit(){

    if(this.formPerson.invalid){
      return;
    }

  }

  get name(){
    return this.formPerson.get('name')!;
  }

  get lastname(){
    return this.formPerson.get('lastname')!;
  }

  get birthdate(){
    return this.formPerson.get('birthdate')!;
  }

}
