import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-success-alert',
  standalone: false,
  
  templateUrl: './success-alert.component.html',
  styleUrl: './success-alert.component.css'
})
export class SuccessAlertComponent {
  @Input() success: string | null = null;
}
