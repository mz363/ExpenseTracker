import { Observable } from 'rxjs';
import { Expense } from './../../model/expense';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ExpenseService {

  constructor(private http: HttpClient) { }

  public insertExpense(expense: any) {
    return this.http.post("http://localhost:8080/api/v1/expense", expense, {responseType:'text' as 'json'});
  }

  public selectAllExpenses(){
    return this.http.get("http://localhost:8080/api/v1/expense")
  }

  public selectExpensesByCategory(category){
    return this.http.get("http://localhost:8080/api/v1/expense/category/"+category)
  }

  public deleteExpense(id, category){
    return this.http.delete("http://localhost:8080/api/v1/expense/"+id+"/"+category);
  }
}
