import { Injectable } from '@angular/core';

import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class StudentService {

  apiUrl =
         '/api/students';

  constructor(
    private http: HttpClient
  ) {}

  getStudents()
  : Observable<any[]> {

    return this.http.get<any[]>(
      this.apiUrl
    );
  }

  addStudent(
    student:any
  )
  : Observable<any> {

    return this.http.post(
      this.apiUrl,
      student
    );
  }
}
