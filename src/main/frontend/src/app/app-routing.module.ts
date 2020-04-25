import { ViewExpenseComponent } from './pages/view-expense/view-expense.component';
import { AddExpenseComponent } from './pages/add-expense/add-expense.component';
import { RegistrationComponent } from './pages/registration/registration.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  //{path: "", redirectTo: "register", pathMatch: "full"},
  {path:"register", component: RegistrationComponent},
  {path:"add-expense", component: AddExpenseComponent},
  {path:"view-expense", component: ViewExpenseComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
