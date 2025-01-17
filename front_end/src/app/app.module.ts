import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http'; // Add this if you're using HttpClient
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SidebarComponent } from './components/adminview/student-management/sidebar/sidebar.component';
import { StudentManagementComponent } from './components/adminview/student-management/student-management.component';
import { StudentListComponent } from './components/adminview/student-management/student-list/student-list.component';
import { AddStudentModalComponent } from './components/adminview/student-management/add-student-modal/add-student-modal.component';
import { UpdateStudentStatusComponent } from './components/adminview/student-management/update-student-status/update-student-status.component';
import { ErrorAlertComponent } from './components/adminview/student-management/error-alert/error-alert.component';
import { FormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    AppComponent,
    SidebarComponent,
    StudentManagementComponent,
    StudentListComponent,
    AddStudentModalComponent,
    UpdateStudentStatusComponent,
    ErrorAlertComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
  ],

  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
