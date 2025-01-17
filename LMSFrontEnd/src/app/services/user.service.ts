import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UserDTO } from '../models/user.model';
import { ApiResponse } from '../models/api-response.model';
import { Page } from '../models/page.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiUrl = 'http://localhost:8080/api/user';

  constructor(private http: HttpClient) { }

  createUser(user: UserDTO): Observable<ApiResponse<UserDTO>> {
    return this.http.post<ApiResponse<UserDTO>>(`${this.apiUrl}/save`, user);
  }

  getUserById(id: number): Observable<ApiResponse<UserDTO>> {
    return this.http.get<ApiResponse<UserDTO>>(`${this.apiUrl}/${id}`);
  }

  getUsersPagination(page: number, size: number, sortBy: string): Observable<ApiResponse<Page<UserDTO>>> {
    return this.http.get<ApiResponse<Page<UserDTO>>>(`${this.apiUrl}/users`, {
      params: {
        page: page.toString(),
        size: size.toString(),
        sortBy
      }
    });
  }
  getAllStudents(): Observable<UserDTO[]> {
    return this.http.get<UserDTO[]>(`${this.apiUrl}`);
  }
  getStudentPagination(page: number, size: number, sortBy: string): Observable<ApiResponse<Page<UserDTO>>> {
    return this.http.get<ApiResponse<Page<UserDTO>>>(`${this.apiUrl}/students`, {
      params: {
        page: page.toString(),
        size: size.toString(),
        sortBy
      }
    });
  }  
  updateUser(user: UserDTO): Observable<ApiResponse<UserDTO>> {
    return this.http.put<ApiResponse<UserDTO>>(`${this.apiUrl}/update`, user);
  }

  deleteUser(id: number): Observable<ApiResponse<void>> {
    return this.http.delete<ApiResponse<void>>(`${this.apiUrl}/delete/${id}`);
  }

  activateUser(id: number): Observable<ApiResponse<void>> {
    return this.http.post<ApiResponse<void>>(`${this.apiUrl}/activate/${id}`, {});
  }

  getStudentsNotEnrolled(courseId: number): Observable<ApiResponse<UserDTO[]>> {
    return this.http.get<ApiResponse<UserDTO[]>>(`${this.apiUrl}/students-not-enroll`, {
      params: {
        courseId: courseId.toString()
      }
    });
  }

  getTeachersNotAssigned(courseId: number): Observable<ApiResponse<UserDTO[]>> {
    return this.http.get<ApiResponse<UserDTO[]>>(`${this.apiUrl}/teachers-not-assigned`, {
      params: {
        courseId: courseId.toString()
      }
    });
  }
}