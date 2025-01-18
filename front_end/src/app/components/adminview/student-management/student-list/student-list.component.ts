import { Component, EventEmitter, Input, Output } from '@angular/core';
import { UserDTO } from '../../../../models/user.model';
@Component({
  selector: 'app-student-list',
  standalone: false,
  templateUrl: './student-list.component.html',
  styleUrl: './student-list.component.css'
})
  export class StudentListComponent {
    @Input() students: UserDTO[] = [];
    @Output() statusUpdated = new EventEmitter<void>();
}
