import { Component, EventEmitter, Input, Output } from '@angular/core';
import { UserDTO } from '../../../../models/user.model';

@Component({
  selector: 'app-teacher-list',
  standalone: false,
  templateUrl: './teacher-list.component.html',
  styleUrl: './teacher-list.component.css'
})
export class TeacherListComponent {
  @Input() teachers: UserDTO[] = [];
  @Output() statusUpdated = new EventEmitter<void>();
}
