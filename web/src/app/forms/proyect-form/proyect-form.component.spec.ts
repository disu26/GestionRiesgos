import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProyectFormComponent } from './proyect-form.component';

describe('ProyectFormComponent', () => {
  let component: ProyectFormComponent;
  let fixture: ComponentFixture<ProyectFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProyectFormComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProyectFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
