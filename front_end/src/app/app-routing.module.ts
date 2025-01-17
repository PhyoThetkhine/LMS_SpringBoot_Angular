import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { StudentManagementComponent } from './components/adminview/student-management/student-management.component';
const routes: Routes = [
  { path: 'admin/student-list', component: StudentManagementComponent },
  // Add other routes here
  { path: '', redirectTo: '', pathMatch: 'full' } // Default route
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
