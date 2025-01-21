import { Component } from '@angular/core';
import { FormBuilder, Validators, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { AuthService } from '../../services/auth/auth.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css'],
  standalone: true, // Mark as standalone
  imports: [ReactiveFormsModule, CommonModule] // Import ReactiveFormsModule here
})
export class ChangePasswordComponent {
  passwordForm: FormGroup; // Use passwordForm, not loginForm

  successMessage: string | null = null;
  errorMessage: string | null = null;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {
    // Initialize passwordForm inside the constructor
    this.passwordForm = this.fb.group({
      currentPassword: ['', Validators.required],
      newPassword: ['', [Validators.required, Validators.minLength(6)]]
    });
  }

  onSubmit(): void {
    if (this.passwordForm.valid) {
      const passwords = {
        currentPassword: this.passwordForm.value.currentPassword!,
        newPassword: this.passwordForm.value.newPassword!
      };

      this.authService.changePassword(passwords).subscribe({
        next: () => {
          this.successMessage = 'Password changed successfully!';
          this.passwordForm.reset();
          setTimeout(() => this.successMessage = null, 3000);
        },
        error: (error) => {
          this.handlePasswordError(error);
        }
      });
    }
  }

  private handlePasswordError(error: any): void {
    if (error.status === 400) {
      this.errorMessage = error.error.message;
    } else if (error.status === 401) {
      this.authService.logout();
      this.router.navigate(['/login']);
    } else {
      this.errorMessage = 'An error occurred while changing password';
    }
    setTimeout(() => this.errorMessage = null, 5000);
  }
}