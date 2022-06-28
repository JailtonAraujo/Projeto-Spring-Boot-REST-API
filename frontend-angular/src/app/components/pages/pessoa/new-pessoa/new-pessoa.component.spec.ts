import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewPessoaComponent } from './new-pessoa.component';

describe('NewPessoaComponent', () => {
  let component: NewPessoaComponent;
  let fixture: ComponentFixture<NewPessoaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NewPessoaComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NewPessoaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
