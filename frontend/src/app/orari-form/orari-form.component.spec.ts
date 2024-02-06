import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OrariFormComponent } from './orari-form.component';

describe('OrariFormComponent', () => {
  let component: OrariFormComponent;
  let fixture: ComponentFixture<OrariFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OrariFormComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(OrariFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
