import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateStudentStatusComponent } from './update-student-status.component';

describe('UpdateStudentStatusComponent', () => {
  let component: UpdateStudentStatusComponent;
  let fixture: ComponentFixture<UpdateStudentStatusComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [UpdateStudentStatusComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UpdateStudentStatusComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
