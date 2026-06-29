import {
  Component,
  OnInit,
  ChangeDetectorRef
} from '@angular/core';

import {
  CommonModule
} from '@angular/common';

import {
  ReactiveFormsModule,
  FormBuilder,
  Validators,
  FormsModule
} from '@angular/forms';

import {
  AdminService
} from '../../services/admin';

@Component({
  selector: 'app-admin',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    FormsModule
  ],
  templateUrl: './admin.html',
  styleUrls: ['./admin.css']
})
export class Admin
implements OnInit {

  isLoggedIn = false;

  loginForm:any;

  dashboardStats:any = {};

  students:any[] = [];

  filteredStudents:any[] = [];

  searchText = '';

  constructor(

    private fb: FormBuilder,

    private adminService: AdminService,

    private cd: ChangeDetectorRef

  ) {

    this.loginForm = this.fb.group({

      username: [
        '',
        Validators.required
      ],

      password: [
        '',
        Validators.required
      ]
    });
  }

  ngOnInit(): void {

    const loginStatus =

    localStorage.getItem(
      'adminLogin'
    );

    if(loginStatus === 'true') {

      this.isLoggedIn = true;

      this.loadDashboardStats();

      this.loadStudents();
    }
  }

  // LOGIN

  login() {

    if(this.loginForm.invalid) {

      this.loginForm.markAllAsTouched();

      return;
    }

    const username =
    this.loginForm.value.username;

    const password =
    this.loginForm.value.password;

    if(

      username ===
      'Elite_Lab'

      &&

      password ===
      'elite@3005'

    ) {

      this.isLoggedIn = true;

      localStorage.setItem(
        'adminLogin',
        'true'
      );

      this.loadDashboardStats();

      this.loadStudents();

      this.cd.detectChanges();
    }

    else {

      alert(
        'Invalid Username Or Password'
      );
    }
  }

  // LOAD DASHBOARD

  loadDashboardStats() {

    this.adminService
      .getDashboardStats()
      .subscribe({

        next: (data:any) => {
          this.dashboardStats = {

    premiumBooked: Number(data.premiumBooked) || 0,

    premiumRemaining: Number(data.premiumRemaining) || 0,

    normalBooked: Number(data.normalBooked) || 0,

    normalRemaining: Number(data.normalRemaining) || 0,

    revenue: Number(data.revenue) || 0
};
         

          this.cd.detectChanges();
        },

        error: () => {

          alert(
            'Dashboard Stats Failed'
          );
        }
      });
  }

  // LOAD STUDENTS

  loadStudents() {

    this.adminService
      .getStudents()
      .subscribe({

        next: (data:any) => {

          this.students = [...data];

          this.filteredStudents = [...data];

          this.cd.detectChanges();
        },

        error: () => {

          alert(
            'Students Load Failed'
          );
        }
      });
  }

  // SEARCH

  searchStudent() {

    const text =

    this.searchText
    .toLowerCase();

    this.filteredStudents =

    this.students.filter(

      (s:any) =>

      s.fullName
      ?.toLowerCase()
      .includes(text)

      ||

      s.mobile
      ?.includes(text)

      ||

      s.studentCode
      ?.toLowerCase()
      .includes(text)

      ||

      s.seatNumber
      ?.toString()
      .includes(text)
    );

    this.cd.detectChanges();
  }

  // DELETE

  deleteStudent(id:number) {

    if(confirm(
      'Delete Student ?'
    )) {

      this.adminService
        .deleteStudent(id)
        .subscribe({

          next: () => {

            this.students =

            this.students.filter(

              student =>
              student.id !== id
            );

            this.filteredStudents =

            this.filteredStudents.filter(

              student =>
              student.id !== id
            );

            this.loadDashboardStats();

            this.cd.detectChanges();

            alert(
              'Deleted Successfully'
            );
          },

          error: () => {

            alert(
              'Delete Failed'
            );
          }
        });
    }
  }

  // PAYMENT UPDATE

  updatePayment(
    id:number,
    status:string
  ) {

    this.adminService
      .updatePaymentStatus(
        id,
        status
      )
      .subscribe({

        next: () => {

          const student =

          this.students.find(
            s => s.id === id
          );

          if(student) {

            student.paymentStatus =
            status;
          }

          const filtered =

          this.filteredStudents.find(
            s => s.id === id
          );

          if(filtered) {

            filtered.paymentStatus =
            status;
          }

          this.loadDashboardStats();

          this.cd.detectChanges();
        },

        error: () => {

          alert(
            'Payment Update Failed'
          );
        }
      });
  }

  // TRACK

  trackByStudent(
    index:number,
    item:any
  ) {

    return item.id;
  }

  // LOGOUT

  logout() {

    localStorage.removeItem(
      'adminLogin'
    );

    this.isLoggedIn = false;

    this.students = [];

    this.filteredStudents = [];

    this.dashboardStats = {};

    this.searchText = '';

    this.loginForm.reset();
  }
}
