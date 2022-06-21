import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class MessageService {

  message:string = "";

  add (msg:string ){
    this.message=msg;

    setTimeout(()=>{
      this.clear();
    },4000);
  }

  clear(){
    this.message = "";
  }

  constructor() { }
}
