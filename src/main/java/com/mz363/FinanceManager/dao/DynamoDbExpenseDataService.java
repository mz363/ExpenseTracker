package com.mz363.FinanceManager.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.mz363.FinanceManager.model.Expense;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.slf4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository("dynamoDbExpenseDao")
public class DynamoDbExpenseDataService implements DynamoDbExpenseDao {

//    private static final Logger LOGGER = LoggerFactory.getLogger(DynamoDbExpenseDataService.class);
    private static int add_id = 0;
    @Autowired
    private DynamoDBMapper mapper;

    public void insertExpense(Expense expense) {
        add_id += 1;
        expense.setId(Integer.toString(add_id));
        mapper.save(expense);
    }

    public List<Expense> selectAllExpenses() {
        List<Expense> allExpenses = mapper.scan(Expense.class, new DynamoDBScanExpression());
        allExpenses = allExpenses.stream()
                .sorted(Comparator.comparing(Expense::getDate))
                .collect(Collectors.toList());
        return allExpenses;
    }

    public List<Expense> selectExpenseByCategory(String category) {
        List<Expense> DB_Expense = selectAllExpenses();
        List<Expense> DB_Expense_Category = new ArrayList<>();
        for (Expense expense : DB_Expense) {
            if (expense.getCategory().equals(category)) {
                DB_Expense_Category.add(expense);
            }
        }
        return DB_Expense_Category;
    }

    public Expense selectExpenseById(String id, String category) {
        return mapper.load(Expense.class, id, category);
    }


//    public void updateStudentDetails(Expense expense) {
//        try {
//            mapper.save(expense, buildDynamoDBSaveExpression(expense));
//        } catch (ConditionalCheckFailedException exception) {
//            LOGGER.error("invalid data - " + exception.getMessage());
//        }
//    }

    public List<Expense> deleteExpenseById(Expense expense) {
        mapper.delete(expense);
        return selectAllExpenses();
    }

//    public DynamoDBSaveExpression buildDynamoDBSaveExpression(Expense expense) {
//        DynamoDBSaveExpression saveExpression = new DynamoDBSaveExpression();
//        Map<String, ExpectedAttributeValue> expected = new HashMap<>();
//        expected.put("studentId", new ExpectedAttributeValue(new AttributeValue(expense.getId()))
//                .withComparisonOperator(ComparisonOperator.EQ));
//        saveExpression.setExpected(expected);
//        return saveExpression;
//    }
}
