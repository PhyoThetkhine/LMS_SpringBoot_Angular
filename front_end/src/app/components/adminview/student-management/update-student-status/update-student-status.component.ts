import { Component, Input, Output, EventEmitter } from '@angular/core';
import { UserDTO } from '../../../../models/user.model';
import { UserService } from '../../../../services/user.service';

@Component({
  selector: 'app-update-student-status',
  standalone: false,
  
  templateUrl: './update-student-status.component.html',
  styleUrls: ['./update-student-status.component.css'] // Fix the path to the styles
})
export class UpdateStudentStatusComponent {
  @Input() student!: UserDTO;
  @Output() statusUpdated = new EventEmitter<void>();

  constructor(private userService: UserService) { }

  updateStatus(newStatus: string): void {
    if (this.student.id !== undefined) {
      if (newStatus === 'ACTIVE') {
        this.userService.activateUser(this.student.id).subscribe(
          (response) => {
            console.log('User activated successfully:', response);
            this.statusUpdated.emit(); // Notify parent component
          },
          (error) => {
            console.error('Failed to activate user', error);
          }
        );
      } else if (newStatus === 'TERMINATE') {
        this.userService.terminateUser(this.student.id).subscribe(
          (response) => {
            console.log('User terminated successfully:', response);
            this.statusUpdated.emit(); // Notify parent component
          },
          (error) => {
            console.error('Failed to terminate user', error);
          }
        );
      }
    } else {
      console.error('User ID is undefined');
    }
  }  
}  