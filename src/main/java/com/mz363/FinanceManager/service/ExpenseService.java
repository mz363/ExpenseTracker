package com.mz363.FinanceManager.service;

import com.mz363.FinanceManager.dao.DynamoDbExpenseDao;
import com.mz363.FinanceManager.dao.ExpenseDao;
import com.mz363.FinanceManager.model.Expense;
import com.mz363.FinanceManager.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ExpenseService {

    private final DynamoDbExpenseDao expenseDao;

    @Autowired
    public ExpenseService(@Qualifier("dynamoDbExpenseDao") DynamoDbExpenseDao dynamoDbExpenseDao) {
        this.expenseDao = dynamoDbExpenseDao;
    }

    public void addExpense(Expense expense) {
        expenseDao.insertExpense(expense);
    }

    public List<Expense> getAllExpenses() {
        return expenseDao.selectAllExpenses();
    }

    public List<Expense> getExpenseByCategory(String category) {
        return expenseDao.selectExpenseByCategory(category);
    }

    public Expense getExpenseById(String id, String category) {
        return expenseDao.selectExpenseById(id, category);
    }

    public List<Expense> deleteExpenseById(Expense expense) {
        return expenseDao.deleteExpenseById(expense);
    }

// Local expense service
//    private final ExpenseDao expenseDao;
//
//    @Autowired
//    public ExpenseService(@Qualifier("dynamoDbExpenseDao") ExpenseDao expenseDao) {
//        this.expenseDao = expenseDao;
//    }
//
//    public int addExpense(Expense expense) {
//        return expenseDao.insertExpense(expense);
//    }
//
//    public List<Expense> getAllExpenses() {
//        return expenseDao.selectAllExpenses();
//    }
//
//    public List<Expense> getExpenseByCategory(String category) {
//        return expenseDao.selectExpenseByCategory(category);
//    }
//
//    public Optional<Expense> getExpenseById(String id) {
//        return expenseDao.selectExpenseById(id);
//    }
//
//    public List<Expense> deleteExpenseById(String id) {
//        return expenseDao.deleteExpenseById(id);
//    }

    }
