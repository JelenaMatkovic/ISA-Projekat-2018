import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MainComponent } from './main.component';
import { NavbarComponent } from './navbar/navbar.component';
import {MatToolbarModule, MatButtonModule, MatIconModule} from '@angular/material';
import {RouterModule} from '@angular/router';
import { HomeComponent } from './home/home.component';
import { MainRoutes } from './main.route';
import { ProfileComponent } from './profile/profile.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { SharedModule } from '../shared/shared.module';
import {FlexLayoutModule} from '@angular/flex-layout';
import { ReactiveFormsModule } from '@angular/forms';
import { AddRentACarComponent } from './rent-a-car/add-rent-a-car/add-rent-a-car.component';
import { AddCarComponent } from './car/add-car/add-car.component';
import { RentACarListComponent } from './rent-a-car/rent-a-car-list/rent-a-car-list.component';
import { RentACarProfileComponent } from './rent-a-car/rent-a-car-profile/rent-a-car-profile.component';
import { AddBranchComponent } from './branch/add-branch/add-branch.component';
import { CarListComponent } from './car/car-list/car-list.component';
import { BranchListComponent } from './branch/branch-list/branch-list.component';
import { EditRentACarComponent } from './rent-a-car/edit-rent-a-car/edit-rent-a-car.component';
import { EditCarComponent } from './car/edit-car/edit-car.component';
import { AddAvioCompanyComponent } from './avio-company/add-avio-company/add-avio-company.component';
import { EditBranchComponent } from './branch/edit-branch/edit-branch.component';
import { CarReservationComponent } from './car/car-reservation/car-reservation.component';
import { CarReservationDialogComponent } from './car/car-reservation-dialog/car-reservation-dialog.component';

@NgModule({
  declarations:[
    MainComponent, 
    NavbarComponent, 
    HomeComponent, 
    ProfileComponent, 
    LoginComponent, 
    RegisterComponent,
    AddRentACarComponent,
    AddCarComponent,
    RentACarListComponent,
    RentACarProfileComponent,
    AddBranchComponent,
    CarListComponent,
    BranchListComponent,
    EditRentACarComponent,
    EditCarComponent,
    EditBranchComponent,
    AddAvioCompanyComponent,
    CarReservationComponent,
    CarReservationDialogComponent,
  ],
  imports: [
    CommonModule,
    RouterModule.forRoot(MainRoutes),
    SharedModule,
    FlexLayoutModule,
    ReactiveFormsModule
  ],
  entryComponents:[CarReservationDialogComponent],
  exports: [MainComponent]
})
export class MainModule { }
