
import { Component, OnInit, ViewChild } from '@angular/core';
import { UserDTO } from '../../../models/user.model';
import { AddStudentModalComponent } from './add-student-modal/add-student-modal.component';
import { UserService } from '../../../services/user.service';
import { Page } from '../../../models/page.model';
import { ApiResponse } from '../../../models/api-response.model';
import { ChangeDetectorRef } from '@angular/core';

@Component({
  selector: 'app-student-management',
  standalone: false,
  
  templateUrl: './student-management.component.html',
  styleUrl: './student-management.component.css'
})
export class StudentManagementComponent implements OnInit {
  students: UserDTO[] = [];
  error: string | null = null;
  currentPage: number = 0;
  totalPages: number = 0;
  pageSize: number = 10;
  sortBy: string = 'id';
  totalElements: number = 0;

  @ViewChild('addStudentModal') addStudentModal!: AddStudentModalComponent;

  constructor(private userService: UserService, private cdr: ChangeDetectorRef) {}

  ngOnInit(): void {
    this.loadStudents();
  }

  loadStudents(): void {
    this.userService.getStudentPagination(this.currentPage, this.pageSize, this.sortBy).subscribe(
      (response) => {
        console.log('API Response:', response);  // Log the entire response for debugging
        const responseData = response?.data;
  
        if (responseData) {
          console.log('Pagination Data:', responseData);
          
          // Update the student list and pagination details
          this.students = responseData.content || [];
          this.totalPages = responseData.totalPages;
          this.totalElements = responseData.totalElements;
          console.log('TotalElements:', this.totalElements);
          console.log('Total Pages:', this.totalPages);
          
          // Trigger change detection
          this.cdr.markForCheck();  // Mark component for change detection
          this.cdr.detectChanges();  // Ensure change detection runs after data update
        } else {
          this.error = 'Unexpected response format';
        }
      },
      (error) => {
        this.error = 'Failed to load students';
        console.error('Error details:', error);
      }
    );
  }
  
  goToPage(page: number): void {
    if (page >= 0 && page < this.totalPages) {
      this.currentPage = page; // Update the current page
      console.log('Current Page:', this.currentPage);  // Log the current page for debugging
      this.loadStudents();  // Make sure to call loadStudents after updating the currentPage
    }
  }
  onStatusUpdated(): void {
    this.loadStudents(); // Reload data for the current page
  }
}