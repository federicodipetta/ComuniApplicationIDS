import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RichiesteComponent } from './richieste.component';

describe('RichiesteComponent', () => {
  let component: RichiesteComponent;
  let fixture: ComponentFixture<RichiesteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RichiesteComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(RichiesteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
