import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-sidebar',
  standalone: false,
  
  templateUrl: './sidebar.component.html',
  styleUrl: './sidebar.component.css'
})
export class SidebarComponent {

  constructor(private router: Router) { }

  // Logout function
  logout() {
    // Perform logout logic (e.g., clear session, remove tokens)
    console.log('Logging out...');
    this.router.navigate(['/login']); // Redirect to login page
  }
}