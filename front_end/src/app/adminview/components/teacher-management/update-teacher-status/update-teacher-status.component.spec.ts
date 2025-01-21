import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateTeacherStatusComponent } from './update-teacher-status.component';

describe('UpdateTeacherStatusComponent', () => {
  let component: UpdateTeacherStatusComponent;
  let fixture: ComponentFixture<UpdateTeacherStatusComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [UpdateTeacherStatusComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UpdateTeacherStatusComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
