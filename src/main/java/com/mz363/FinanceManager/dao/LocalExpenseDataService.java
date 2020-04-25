//package com.mz363.FinanceManager.dao;
//
//import com.mz363.FinanceManager.model.Expense;
//import org.springframework.stereotype.Repository;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import java.util.*;
//import java.util.stream.Collectors;
//
//@Repository("localExpenseDao")
//public class LocalExpenseDataService implements ExpenseDao {
//
//    private static List<Expense> DB_Expense = new ArrayList<>();
//    private static List<Expense> DB_Expense_Category = new ArrayList<>();
//    private static int add_id = 0;
//    @Override
//    public int insertExpense(Expense expense) {
//        add_id = add_id + 1;
//        DB_Expense.add(
//                new Expense(
//                        expense.getCategory(),
//                        expense.getDate(),
//                        expense.getCost(),
//                        expense.getDescription(),
//                        expense.getTag(),
//                        Integer.toString(add_id)
//                )
//        );
//        // sort the array with the new inputs by date
//        DB_Expense = DB_Expense.stream()
//                .sorted(Comparator.comparing(Expense::getDate))
//                .collect(Collectors.toList());
//        return 1;
//    }
//
//    @Override
//    public List<Expense> selectAllExpenses() {
//        return DB_Expense;
//    }
//
//    @Override
//    public List<Expense> selectExpenseByCategory(String category) {
//        DB_Expense_Category.clear();
//        for (Expense expense : DB_Expense) {
//            if (expense.getCategory().equals(category)) {
//                DB_Expense_Category.add(expense);
//            }
//        }
//        return DB_Expense_Category;
//    }
//
//    @Override
//    public Optional<Expense> selectExpenseById(String id) {
//        return DB_Expense.stream()
//                .filter(exp -> exp.getId().equals(id))
//                .findFirst();
//    }
//
//    @Override
//    public List<Expense> deleteExpenseById(String id) {
//        Optional<Expense> expense = selectExpenseById(id);
//        if(expense.isPresent()) {
//            DB_Expense.remove(expense.get());
//        }
//        return DB_Expense;
//    }
//}
