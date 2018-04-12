import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ActuComponentComponent } from './actu-component.component';

describe('ActuComponentComponent', () => {
  let component: ActuComponentComponent;
  let fixture: ComponentFixture<ActuComponentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ActuComponentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ActuComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
