import { Routes } from '@angular/router';

import { Home } from './pages/home/home';
import { Student } from './pages/student/student';
import { Admin } from './pages/admin/admin';
import { Seats } from './pages/seats/seats';

export const routes: Routes = [

  {
    path: '',
    component: Home
  },

  {
    path: 'student',
    component: Student
  },

  {
    path: 'admin',
    component: Admin
  },

  {
    path: 'seats',
    component: Seats
  }

];