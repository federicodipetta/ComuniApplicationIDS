import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PuntiComponent } from './punti.component';

describe('PuntiComponent', () => {
  let component: PuntiComponent;
  let fixture: ComponentFixture<PuntiComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PuntiComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PuntiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
