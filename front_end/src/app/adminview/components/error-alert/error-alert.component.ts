import { Component,Input } from '@angular/core';

@Component({
  selector: 'app-error-alert',
  standalone: false,
  
  templateUrl: './error-alert.component.html',
  styleUrl: './error-alert.component.css'
})
export class ErrorAlertComponent {
  @Input() error: string | null = null;
}

