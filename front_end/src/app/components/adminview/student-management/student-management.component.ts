
import { Component, OnInit, ViewChild } from '@angular/core';
import { UserDTO } from '../../../models/user.model';
import { AddStudentModalComponent } from './add-student-modal/add-student-modal.component';
import { UserService } from '../../../services/user.service';
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
  success : string | null = null;
  currentPage: number = 0;
  totalPages: number = 0;
  pageSize: number = 10;
  sortBy: string = 'id';
  totalElements: number = 0;

  @ViewChild('addStudentModal') addStudentModal!: AddStudentModalComponent;
  constructor(private userService: UserService, private cdr: ChangeDetectorRef) {}
  handleError(errorMessage: string): void {
    console.log('Handling error:', errorMessage);
    this.error = errorMessage;
    setTimeout(() => {
      this.error = null;
    }, 5000);
  }
  
  handleSuccess(successMessage: string): void {
    console.log('Handling success:', successMessage);
    this.success = successMessage;
    setTimeout(() => {
      this.success = null;
    }, 5000);
  }
  ngOnInit(): void {
    this.loadStudents();
  }

  loadStudents(): void {
    this.userService.getStudentPagination(this.currentPage, this.pageSize, this.sortBy).subscribe(
      (response) => {
        console.log('API Response:', response);  
        const responseData = response?.data;
        if (responseData) {
          console.log('Pagination Data:', responseData);
          this.students = responseData.content || [];
          this.totalPages = responseData.totalPages;
          this.totalElements = responseData.totalElements;
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
      this.loadStudents();  // Make sure to call loadStudents after updating the currentPage
    }
  }
  onStatusUpdated(): void {
    this.loadStudents(); // Reload data for the current page
  }
}