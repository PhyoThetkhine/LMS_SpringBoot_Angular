import { NgModule } from '@angular/core';

import { HttpClientModule } from '@angular/common/http'; // Add this if you're using HttpClient
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SidebarComponent } from './adminview/components/sidebar/sidebar.component';
import { StudentManagementComponent } from './adminview/components/student-management/student-management.component';
import { StudentListComponent } from './adminview/components/student-management/student-list/student-list.component';
import { AddStudentModalComponent } from './adminview/components/student-management/add-student-modal/add-student-modal.component';
import { UpdateStudentStatusComponent } from './adminview/components/student-management/update-student-status/update-student-status.component';
import { ErrorAlertComponent } from './adminview/components/error-alert/error-alert.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SuccessAlertComponent } from './adminview/components/success-alert/success-alert.component';
import { TeacherManagementComponent } from './adminview/components/teacher-management/teacher-management.component';
import { TeacherListComponent } from './adminview/components/teacher-management/teacher-list/teacher-list.component';
import { AddTeacherModalComponent } from './adminview/components/teacher-management/add-teacher-modal/add-teacher-modal.component';
import { UpdateTeacherStatusComponent } from './adminview/components/teacher-management/update-teacher-status/update-teacher-status.component';
import { LoginComponent } from './auth/login/login.component';
import { ChangePasswordComponent } from './auth/change-password/change-password.component';
import { BrowserModule } from '@angular/platform-browser';



@NgModule({
  declarations: [
    AppComponent,
    SidebarComponent,
    StudentManagementComponent,
    StudentListComponent,
    AddStudentModalComponent,
    UpdateStudentStatusComponent,
    UpdateTeacherStatusComponent,
    ErrorAlertComponent,
    SuccessAlertComponent,
    TeacherManagementComponent,
    TeacherListComponent,
    AddTeacherModalComponent,

   
  ],
  imports: [
    BrowserModule, // Only once
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule, // Only once
    LoginComponent, // Import standalone LoginComponent
    ChangePasswordComponent // Import standalone ChangePasswordComponent
  ],

  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
