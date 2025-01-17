
import { Component, EventEmitter, Output , ChangeDetectorRef} from '@angular/core';
import { UserDTO } from '../../../../models/user.model';
import { UserService } from '../../../../services/user.service';

@Component({
  selector: 'app-add-student-modal',
  standalone: false,
  
  templateUrl: './add-student-modal.component.html',
  styleUrl: './add-student-modal.component.css'
})
export class AddStudentModalComponent {
  @Output() studentAdded = new EventEmitter<void>();
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
    this.resetForm(); // Clear form fields when modal is closed
    const modalElement = document.querySelector('.modal') as HTMLElement;
    if (modalElement) {
      modalElement.classList.remove('show');
      modalElement.style.display = 'none';
    }
  }

  addStudent(): void {
    this.userService.createUser(this.newStudent).subscribe(
      (response) => {
        this.studentAdded.emit(); // Notify parent component
        this.closeModal();
      this.resetForm();
      },
      (error) => {
        console.error('Failed to add student', error);
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