import { Component, Input, Output, EventEmitter } from '@angular/core';
import { UserDTO } from '../../../../models/user.model';
import { UserService } from '../../../../services/user.service';

@Component({
  selector: 'app-update-student-status',
  standalone: false,
  
  templateUrl: './update-student-status.component.html',
  styleUrl: './update-student-status.component.css'
})
export class UpdateStudentStatusComponent {
  @Input() student!: UserDTO;
  @Output() statusUpdated = new EventEmitter<void>();

  constructor(private userService: UserService) { }

  updateStatus(newStatus: string): void {
    this.userService.updateUser({ ...this.student, status: newStatus }).subscribe(
      (response) => {
        this.statusUpdated.emit(); // Notify parent component
      },
      (error) => {
        console.error('Failed to update status', error);
      }
    );
  }
}