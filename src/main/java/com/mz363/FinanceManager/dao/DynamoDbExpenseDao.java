package com.mz363.FinanceManager.dao;

import com.mz363.FinanceManager.model.Expense;

import java.util.Date;
import java.util.List;

public interface DynamoDbExpenseDao {

    void insertExpense(Expense expense);

    List<Expense> selectAllExpenses();

    List<Expense> selectExpenseByCategory(String category);

    Expense selectExpenseById(String id, String category);

    List<Expense> deleteExpenseById(Expense expense);
}
