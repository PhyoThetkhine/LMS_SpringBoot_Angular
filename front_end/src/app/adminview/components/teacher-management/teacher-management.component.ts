// teacher-management.component.ts
import { Component, OnInit, ViewChild } from '@angular/core';
import { UserDTO } from '../../../models/user.model';
import { AddTeacherModalComponent } from './add-teacher-modal/add-teacher-modal.component';
import { UserService } from '../../../services/user/user.service';
import { ChangeDetectorRef } from '@angular/core';

@Component({
  selector: 'app-teacher-management',
  standalone: false,
  templateUrl: './teacher-management.component.html',
  styleUrl: './teacher-management.component.css'
})
export class TeacherManagementComponent implements OnInit {
  teachers: UserDTO[] = [];
  error: string | null = null;
  success: string | null = null;
  currentPage: number = 0;
  totalPages: number = 0;
  pageSize: number = 10;
  sortBy: string = 'id';
  totalElements: number = 0;

  @ViewChild('addTeacherModal') addTeacherModal!: AddTeacherModalComponent;

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
    this.loadTeachers();
  }

  loadTeachers(): void {
    this.userService.getTeacherPagination(this.currentPage, this.pageSize, this.sortBy).subscribe(
      (response) => {
        const responseData = response?.data;
        if (responseData) {
          this.teachers = responseData.content || [];
          this.totalPages = responseData.totalPages;
          this.totalElements = responseData.totalElements;
          this.cdr.markForCheck();
          this.cdr.detectChanges();
        } else {
          this.error = 'Unexpected response format';
        }
      },
      (error) => {
        this.error = 'Failed to load teachers';
        console.error('Error details:', error);
      }
    );
  }

  goToPage(page: number): void {
    if (page >= 0 && page < this.totalPages) {
      this.currentPage = page;
      this.loadTeachers();
    }
  }

  onStatusUpdated(): void {
    this.loadTeachers();
  }
}