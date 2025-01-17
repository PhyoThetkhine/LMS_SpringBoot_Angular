import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  standalone: false,
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'LMSFrontEnd';
  adminRoutes: string[] = [
    '/admin/dashboard',
    '/admin/teacher-list',
    '/admin/student-list',
    '/admin/student-enrollment',
    '/admin/teacher-enrollment'
  ];

  constructor(public router: Router) {}
  isAdminRoute(): boolean {
    return this.adminRoutes.includes(this.router.url);
  }
}
