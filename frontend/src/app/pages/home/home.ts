import {
  Component,
  OnInit,
  ChangeDetectorRef
} from '@angular/core';

import {
  CommonModule
} from '@angular/common';

import {
  RouterLink
} from '@angular/router';

import {
  StudentService
} from '../../services/student';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    CommonModule,
    RouterLink
  ],
  templateUrl: './home.html',
  styleUrls: ['./home.css']
})
export class Home
implements OnInit {

  // TOTAL

  totalSeats:number = 80;

  // LIVE

  bookedSeats:number = 0;

  availableSeats:number = 80;

  premiumSeats:number = 40;

  constructor(

    private studentService:
    StudentService,

    private cd:
    ChangeDetectorRef

  ) {}

  // PAGE LOAD

  ngOnInit(): void {

    this.loadSeatData();
  }

  // LOAD LIVE DATA

  loadSeatData() {

    this.studentService
      .getStudents()
      .subscribe({

        next: (students:any[]) => {

          console.log(
            students
          );

          // ONLY PAID

          const paidStudents =

          students.filter(

            (s:any) =>

            s.paymentStatus
            === 'PAID'
          );

          // BOOKED

          this.bookedSeats =

          paidStudents.length;

          // AVAILABLE

          this.availableSeats =

          this.totalSeats
          -
          this.bookedSeats;

          // REFRESH

          this.cd.detectChanges();
        },

        error: (err:any) => {

          console.log(err);

          alert(
            'Live Data Failed'
          );
        }
      });
  }
}