import { ExpenseService } from './service/expense/expense.service';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { RegistrationComponent } from './pages/registration/registration.component';
import { UserRegistrationService } from './service/user-registration/user-registration.service';
import { AddExpenseComponent } from './pages/add-expense/add-expense.component';
import { ViewExpenseComponent } from './pages/view-expense/view-expense.component';

@NgModule({
  declarations: [
    AppComponent,
    RegistrationComponent,
    AddExpenseComponent,
    ViewExpenseComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [UserRegistrationService, ExpenseService],
  bootstrap: [AppComponent]
})
export class AppModule { }
