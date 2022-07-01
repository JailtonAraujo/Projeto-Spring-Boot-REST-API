import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import { LoginComponent } from './components/login/login.component';
import { MessageComponent } from './components/message/message.component';
import { HomeComponent } from './components/home/home.component';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { FormPessoaComponent } from './components/pages/pessoa/form-pessoa/form-pessoa.component';
import { AboutComponent } from './components/about/about.component';
import { WelcomeComponent } from './components/welcome/welcome.component';
import { HttpInterceptorModule } from './services/header-interceptor.service';
import { ListPessoaComponent } from './components/pages/pessoa/list-pessoa/list-pessoa.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { NewPessoaComponent } from './components/pages/pessoa/new-pessoa/new-pessoa.component';
import { EditPessoaComponent } from './components/pages/pessoa/edit-pessoa/edit-pessoa.component';
import { NgxPaginationModule } from 'ngx-pagination';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    MessageComponent,
    HomeComponent,
    HeaderComponent,
    FooterComponent,
    FormPessoaComponent,
    AboutComponent,
    WelcomeComponent,
    ListPessoaComponent,
    NewPessoaComponent,
    EditPessoaComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule, 
    ReactiveFormsModule,
    HttpClientModule,
    HttpInterceptorModule,
    FontAwesomeModule,
    NgxPaginationModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
