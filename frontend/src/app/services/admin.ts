import { Injectable }
from '@angular/core';

import { HttpClient }
from '@angular/common/http';

import { Observable }
from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  apiUrl =
       '/api/students';

  constructor(

    private http: HttpClient

  ) {}

  // DASHBOARD STATS

    getDashboardStats(): Observable<any> {
    return this.http.get('/api/dashboard');
}

  // GET STUDENTS

  getStudents()
  : Observable<any> {

    return this.http.get(

      this.apiUrl

    );
  }

  // DELETE STUDENT

  deleteStudent(id:number)
  : Observable<any> {

    return this.http.delete(

      `${this.apiUrl}/${id}`

    );
  }

  // UPDATE PAYMENT

  updatePaymentStatus(

    id:number,

    status:string

  )
  : Observable<any> {

    return this.http.put(

      `${this.apiUrl}/payment/${id}?status=${status}`,

      {}

    );
  }
}
