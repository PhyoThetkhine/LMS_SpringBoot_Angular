
import { Component, EventEmitter, Output } from '@angular/core';
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

  constructor(private userService: UserService) { }

  openModal(): void {
    this.showModal = true;
  }

  closeModal(): void {
    this.showModal = false;
  }

  addStudent(): void {
    this.userService.createUser(this.newStudent).subscribe(
      (response) => {
        this.studentAdded.emit(); // Notify parent component
        this.closeModal();
      },
      (error) => {
        console.error('Failed to add student', error);
      }
    );
  }
}