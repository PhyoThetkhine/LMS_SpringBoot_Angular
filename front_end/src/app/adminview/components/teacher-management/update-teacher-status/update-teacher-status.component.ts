import { Component, EventEmitter, Input, Output } from '@angular/core';
import { UserDTO } from '../../../../models/user.model';
import { UserService } from '../../../../services/user/user.service';

@Component({
  selector: 'app-update-teacher-status',
  standalone: false,
  
  templateUrl: './update-teacher-status.component.html',
  styleUrl: './update-teacher-status.component.css'
})
export class UpdateTeacherStatusComponent {
@Input() teacher!: UserDTO;
  @Output() statusUpdated = new EventEmitter<void>();
  constructor(private userService: UserService) { }
  updateStatus(newStatus: string): void {
    if (this.teacher.id !== undefined) {
      if (newStatus === 'ACTIVE') {
        this.userService.activateUser(this.teacher.id).subscribe(
          (response) => {
            console.log('User activated successfully:', response);
            this.statusUpdated.emit(); 
          },
          (error) => {
            console.error('Failed to activate user', error);
          }
        );
      } else if (newStatus === 'TERMINATE') {
        this.userService.terminateUser(this.teacher.id).subscribe(
          (response) => {
            console.log('User terminated successfully:', response);
            this.statusUpdated.emit(); 
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