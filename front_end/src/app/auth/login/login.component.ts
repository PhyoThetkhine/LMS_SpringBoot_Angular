import { Component } from '@angular/core';
import { FormBuilder, Validators, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth/auth.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  standalone: true, // Mark as standalone
  imports: [ReactiveFormsModule, CommonModule] // Import ReactiveFormsModule here
})
export class LoginComponent {
  loginForm: FormGroup;

  errorMessage: string | null = null;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {
    // Initialize loginForm inside the constructor
    this.loginForm = this.fb.group({
      userCode: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  onSubmit(): void {
    if (this.loginForm.valid) {
      const credentials = {
        userCode: this.loginForm.value.userCode!,
        password: this.loginForm.value.password!
      };

      this.authService.login(credentials).subscribe({
        next: (response: any) => {
          const token = response.data.split('token : ')[1];
          localStorage.setItem('jwt_token', token);
          this.router.navigate(['/admin/student-list']);
        },
        error: (error) => {
          this.handleLoginError(error);
        }
      });
    }
  }

  private handleLoginError(error: any): void {
    if (error.status === 401) {
      this.errorMessage = 'Invalid credentials';
    } else if (error.status === 404) {
      this.errorMessage = 'User not found';
    } else {
      this.errorMessage = 'An unexpected error occurred';
    }
  }
}