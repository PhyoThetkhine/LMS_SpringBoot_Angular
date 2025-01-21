// add-teacher-modal.component.ts
import { Component, EventEmitter, Output, ChangeDetectorRef } from '@angular/core';
import { UserDTO } from '../../../../models/user.model';
import { UserService } from '../../../../services/user/user.service';
import { ApiResponse } from '../../../../models/api-response.model';

@Component({
  selector: 'app-add-teacher-modal',
  standalone: false,
  templateUrl: './add-teacher-modal.component.html',
  styleUrl: './add-teacher-modal.component.css'
})
export class AddTeacherModalComponent {
  @Output() teacherAdded = new EventEmitter<void>();
  @Output() teacherError = new EventEmitter<string>();
  @Output() teacherSuccess = new EventEmitter<string>();
  showModal: boolean = false;
  newTeacher: UserDTO = {
    name: '',
    email: '',
    password: null,
    userCode: null,
    status: null,
    role: 'TEACHER',
    createAdmin: null
  };

  constructor(private userService: UserService, private cdr: ChangeDetectorRef) {}

  openModal(): void {
    this.showModal = true;
    this.resetForm();
    this.cdr.detectChanges();
  }

  closeModal(): void {
    this.showModal = false;
    this.resetForm();
    const modalElement = document.querySelector('.modal') as HTMLElement;
    if (modalElement) {
      modalElement.classList.remove('show');
      modalElement.style.display = 'none';
    }
  }

  addTeacher(): void {
    this.userService.createUser(this.newTeacher).subscribe(
      (response: ApiResponse<UserDTO>) => {
        if (response.status === 200) {
          this.teacherAdded.emit();
          this.teacherSuccess.emit(response.message);
          this.closeModal();
          this.resetForm();
        } else {
          this.teacherError.emit(response.message);
          this.closeModal();
          this.resetForm();
        }
      },
      (error) => {
        console.error('Failed to add teacher', error);
        if (error.error && error.error.message) {
          this.teacherError.emit(error.error.message);
        } else {
          this.teacherError.emit('An unexpected error occurred.');
        }
        this.closeModal();
        this.resetForm();
      }
    );
  }

  resetForm(): void {
    this.newTeacher = {
      name: '',
      email: '',
      password: null,
      userCode: null,
      status: null,
      role: 'TEACHER',
      createAdmin: null
    };
  }
}