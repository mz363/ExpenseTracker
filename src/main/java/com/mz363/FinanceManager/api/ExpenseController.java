package com.mz363.FinanceManager.api;

import com.mz363.FinanceManager.dao.DynamoDbExpenseDataService;
import com.mz363.FinanceManager.model.Expense;
import com.mz363.FinanceManager.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RequestMapping("api/v1/expense")
@RestController
@CrossOrigin("*")
public class ExpenseController {

    private final ExpenseService expenseService;

    @Autowired
    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping
    public String addExpense(@RequestBody Expense expense) {
        expenseService.addExpense(expense);
        return "Successfully inserted into DynamoDB table";
    }

    @GetMapping
    public List<Expense> getAllExpenses() {
        return expenseService.getAllExpenses();
    }

    @GetMapping(path = "/category/{category}")
    public List<Expense> getExpenseByCategory(@PathVariable("category") String category) {
        return expenseService.getExpenseByCategory(category);
    }

    @GetMapping(path = "/{id}/{category}")
    public ResponseEntity<Expense> getExpenseById(@PathVariable("id") String id, @PathVariable("category") String category) {
         Expense expense = expenseService.getExpenseById(id, category);
        return new ResponseEntity<Expense>(expense, HttpStatus.OK);
    }

//    @PutMapping
//    public void updateStudentDetails(@RequestBody Student student) {
//        repository.updateStudentDetails(student);
//    }

    @DeleteMapping(path = "/{id}/{category}")
    public List<Expense> deleteExpenseById(@PathVariable("id") String id, @PathVariable("category") String category) {
        Expense expense = new Expense();
        expense.setId(id);
        expense.setCategory(category);
        return expenseService.deleteExpenseById(expense);
    }
}
