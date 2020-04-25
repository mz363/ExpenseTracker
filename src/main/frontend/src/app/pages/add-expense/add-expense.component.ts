import { ExpenseService } from './../../service/expense/expense.service';
import { Expense } from './../../model/expense';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-add-expense',
  templateUrl: './add-expense.component.html',
  styleUrls: ['./add-expense.component.css']
})
export class AddExpenseComponent implements OnInit {

  expense: Expense = new Expense("", new Date(), 0.0, "", "");

  message:any

  constructor(private service:ExpenseService) { }

  ngOnInit() {
  }

  public addExpense() {
    let addExpense = this.service.insertExpense(this.expense);
    addExpense.subscribe((data)=>this.message=data);
  }
}
