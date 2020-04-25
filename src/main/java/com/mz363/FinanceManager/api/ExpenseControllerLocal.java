//package com.mz363.FinanceManager.api;
//
//import com.mz363.FinanceManager.model.Expense;
//import com.mz363.FinanceManager.service.ExpenseService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RequestMapping("api/v1/expenselocal")
//@RestController
//@CrossOrigin("*")
//public class ExpenseControllerLocal {
//    private final ExpenseService expenseService;
//
//    @Autowired
//    public ExpenseControllerLocal(ExpenseService expenseService) {
//        this.expenseService = expenseService;
//    }
//
//    @PostMapping
//    public void addExpense(@RequestBody Expense expense) {
//        expenseService.addExpense(expense);
//    }
//
//    @GetMapping
//    public List<Expense> getAllExpenses() {
//        return expenseService.getAllExpenses();
//    }
//
////    @GetMapping(path = "/category/{category}")
////    public List<Expense> getExpenseByCategory(@PathVariable("category") String category) {
////        return expenseService.getExpenseByCategory(category);
////    }
////
////    @GetMapping(path = "/{id}")
////    public Expense getExpenseById(@PathVariable("id") String id) {
////        return expenseService.getExpenseById(id)
////                .orElse(null);
////    }
//
////    @DeleteMapping(path = "{id}")
////    public List<Expense> deleteExpenseById(@PathVariable("id") String id) {
////        return expenseService.deleteExpenseById(id);
////    }
//}
