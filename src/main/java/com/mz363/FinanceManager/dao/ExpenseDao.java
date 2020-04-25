package com.mz363.FinanceManager.dao;

import com.mz363.FinanceManager.model.Expense;
import com.mz363.FinanceManager.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ExpenseDao {

    int insertExpense(Expense expense);

//    default int insertExpense(Expense expense) {
//        String tag = "";
//        return insertExpense(expense, tag);
//    }

    List<Expense> selectAllExpenses();

    List<Expense> selectExpenseByCategory(String category);

    Optional<Expense> selectExpenseById(String id);

    List<Expense> deleteExpenseById(String id);

}
