import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { StudentManagementComponent } from './adminview/components/student-management/student-management.component';
import { TeacherManagementComponent } from './adminview/components/teacher-management/teacher-management.component';
import { LoginComponent } from './auth/login/login.component';
import { AuthGuard } from './guards/auth.guard';
import { ChangePasswordComponent } from './auth/change-password/change-password.component';
const routes: Routes = [
  // { path: 'admin/student-list', component: StudentManagementComponent },
  { path: 'admin/teacher-list', component: TeacherManagementComponent },
  { path: 'login', component: LoginComponent },
  { path: 'change-password', component: ChangePasswordComponent },
  { 
    path: 'admin/student-list', 
    component: StudentManagementComponent,
    canActivate: [AuthGuard] // Protect this route
  },
  { 
    path: 'admin/teacher-list', 
    component: TeacherManagementComponent,
    canActivate: [AuthGuard] // Protect this route
  },
  { 
    path: 'change-password', 
    component: ChangePasswordComponent,
    canActivate: [AuthGuard] // Protect this route
  },
  // Add other routes here
  { path: '', redirectTo: '/login', pathMatch: 'full' }, // Default route
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
