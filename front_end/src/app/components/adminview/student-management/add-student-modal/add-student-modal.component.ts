
import { Component, EventEmitter, Output , ChangeDetectorRef} from '@angular/core';
import { UserDTO } from '../../../../models/user.model';
import { UserService } from '../../../../services/user.service';
import { ApiResponse } from '../../../../models/api-response.model';

@Component({
  selector: 'app-add-student-modal',
  standalone: false,
  
  templateUrl: './add-student-modal.component.html',
  styleUrl: './add-student-modal.component.css'
})
export class AddStudentModalComponent {
  @Output() studentAdded = new EventEmitter<void>();
  @Output() studentError = new EventEmitter<string>();
  @Output() studentSuccess = new EventEmitter<string>();
  showModal: boolean = false;
  newStudent: UserDTO = { 
    name: '', 
    email: '', 
    password: null, 
    userCode: null, 
    status: null, 
    role: 'STUDENT', 
    createAdmin: null 
  };

  constructor(private userService: UserService,private cdr: ChangeDetectorRef) { }

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

  addStudent(): void {
    this.userService.createUser(this.newStudent).subscribe(
      (response: ApiResponse<UserDTO>) => {
        if (response.status === 200) {
          this.studentAdded.emit();
          this.studentSuccess.emit(response.message);
          this.closeModal();
          this.resetForm();
        } else {
          this.studentError.emit(response.message);
          this.closeModal();
        this.resetForm();
        }
      },
      (error) => {
        console.error('Failed to add student', error);
        if (error.error && error.error.message) {
          this.studentError.emit(error.error.message);
        } else {
          this.studentError.emit('An unexpected error occurred.');
        }
        this.closeModal();
        this.resetForm();
      }
    );
  }
  resetForm(): void {
    this.newStudent = {
      name: '',
      email: '',
      password: null,
      userCode: null,
      status: null,
      role: 'STUDENT',
      createAdmin: null,
    };
  }
}