import {
  Component,
  OnInit
} from '@angular/core';

import {
  CommonModule
} from '@angular/common';

import {
  RouterLink
} from '@angular/router';

import {
  ReactiveFormsModule,
  FormBuilder,
  Validators
} from '@angular/forms';

import {
  StudentService
} from '../../services/student';

declare var Razorpay:any;

@Component({
  selector: 'app-seats',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterLink
  ],
  templateUrl: './seats.html',
  styleUrls: ['./seats.css']
})
export class Seats
implements OnInit {

  leftSeats = Array.from(
    { length: 40 },
    (_, i) => i + 1
  );

  rightSeats = Array.from(
    { length: 40 },
    (_, i) => i + 41
  );

  bookedSeats:number[] = [];

  showForm = false;

  selectedSeat:number = 0;

  bookingForm:any;

  constructor(

    private studentService:
    StudentService,

    private fb:
    FormBuilder

  ) {

    this.bookingForm =
    this.fb.group({

      fullName: [
        '',
        Validators.required
      ],

      mobile: [
        '',
        Validators.required
      ],

      email: [
        '',
        Validators.required
      ]
    });
  }

  ngOnInit(): void {

    this.loadBookedSeats();
  }

  loadBookedSeats() {

    this.studentService
      .getStudents()
      .subscribe({

        next: (students:any[]) => {

          const paidStudents =

          students.filter(

            (s:any) =>

            s.paymentStatus
            === 'PAID'
          );

          this.bookedSeats =

          paidStudents.map(

            (s:any) =>

            Number(
              s.seatNumber
            )
          );
        }
      });
  }

  isBooked(
    seat:number
  ): boolean {

    return this.bookedSeats
    .includes(seat);
  }

  selectSeat(
    seat:number
  ) {

    if(
      this.isBooked(seat)
    ) {

      alert(
        'Seat Already Booked'
      );

      return;
    }

    this.selectedSeat = seat;

    this.showForm = true;
  }

  confirmBooking() {

    if(
      this.bookingForm.invalid
    ) {

      this.bookingForm
      .markAllAsTouched();

      return;
    }

    const amount =

    this.selectedSeat <= 40

    ? 1500

    : 1200;


        const studentData = {

          fullName:
          this.bookingForm
          .value.fullName,

          mobile:
          this.bookingForm
          .value.mobile,

          email:
          this.bookingForm
          .value.email,

          seatNumber:
          this.selectedSeat
          .toString(),

          fees:
          amount,

          admissionDate:

          new Date()
          .toISOString()
          .split('T')[0],

          expiryDate:

          new Date(

            new Date()
            .setMonth(

              new Date()
              .getMonth() + 1
            )

          )
          .toISOString()
          .split('T')[0],

          paymentStatus:
          'PAID'
        };

        this.studentService
          .addStudent(
            studentData
          )
          .subscribe({

            next: () => {

              alert(
                'Seat Booked Successfully'
              );

              this.bookingForm.reset();

              this.showForm = false;

              this.selectedSeat = 0;

              this.loadBookedSeats();
            },
             error: (err) => {

             console.log(err);

             alert(
            'Failed to save student'
               );
          }
      });

} 
 closeForm() {

    this.showForm = false;

    this.bookingForm.reset();

    this.selectedSeat = 0;
  }
}
