import { HomeComponent } from "./home/home.component";
import { ProfileComponent } from "./profile/profile.component";
import { LoginComponent } from "./login/login.component";
import { RegisterComponent } from "./register/register.component";
import { AddRentACarComponent } from "./rent-a-car/add-rent-a-car/add-rent-a-car.component";
import { RentACarListComponent } from "./rent-a-car/rent-a-car-list/rent-a-car-list.component";
import { RentACarProfileComponent } from "./rent-a-car/rent-a-car-profile/rent-a-car-profile.component";
import { AddCarComponent } from "./car/add-car/add-car.component";


export const MainRoutes=[
    {path : '', component: HomeComponent},
    {path: 'profile', component : ProfileComponent},
    {path: 'login', component: LoginComponent},
    {path: 'register', component:RegisterComponent},
    {path: 'add-rent-a-car', component : AddRentACarComponent},
    {path: 'rent-a-car', component: RentACarListComponent},
    {path: 'rent-a-car/:rentACarId', component:RentACarProfileComponent}
    {path: 'rent-a-car/:rentACarId/add-car', component:AddCarComponent}
    
]