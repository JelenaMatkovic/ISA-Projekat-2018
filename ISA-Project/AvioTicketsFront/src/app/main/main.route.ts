import { HomeComponent } from "./home/home.component";
import { ProfileComponent } from "./profile/profile.component";
import { LoginComponent } from "./login/login.component";
import { RegisterComponent } from "./register/register.component";
import { AddRentACarComponent } from "./rent-a-car/add-rent-a-car/add-rent-a-car.component";
import { RentACarListComponent } from "./rent-a-car/rent-a-car-list/rent-a-car-list.component";
import { RentACarProfileComponent } from "./rent-a-car/rent-a-car-profile/rent-a-car-profile.component";
import { AddCarComponent } from "./car/add-car/add-car.component";
import { AddBranchComponent } from "./branch/add-branch/add-branch.component";
import { EditRentACarComponent } from "./rent-a-car/edit-rent-a-car/edit-rent-a-car.component";
import { EditCarComponent } from "./car/edit-car/edit-car.component";
import { EditBranchComponent } from "./branch/edit-branch/edit-branch.component";
import { AddAvioCompanyComponent } from "./avio-company/add-avio-company/add-avio-company.component";
import { CarReservationComponent } from './car/car-reservation/car-reservation.component';


export const MainRoutes=[
    {path : '', component: HomeComponent},
    {path: 'profile', component : ProfileComponent},
    {path: 'login', component: LoginComponent},
    {path: 'register', component:RegisterComponent},
    {path: 'add-rent-a-car', component : AddRentACarComponent},
    {path: 'edit-rent-a-car/:rentACarId', component : EditRentACarComponent},
    {path: 'rent-a-car', component: RentACarListComponent},
    {path: 'rent-a-car/:rentACarId', component:RentACarProfileComponent},
    {path: 'rent-a-car/:rentACarId/add-car', component:AddCarComponent},
    {path: 'rent-a-car/:rentACarId/edit-car/:carId', component:EditCarComponent},
    {path: 'rent-a-car/:rentACarId/add-branch', component:AddBranchComponent},
    {path: 'rent-a-car/:rentACarId/edit-branch/:branchId', component:EditBranchComponent},
    {path: 'rent-a-car/:rentACarId/reservation', component:CarReservationComponent},
    {path: 'avio-company/addAvioCompany', component:AddAvioCompanyComponent},
    
    
]
