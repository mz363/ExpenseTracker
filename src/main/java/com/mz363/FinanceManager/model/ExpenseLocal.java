package com.mz363.FinanceManager.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class ExpenseLocal {
    private final String category;
    private final Date date;
    private final double cost;
    private final String description;
    private final String tag;
    private final String id;

    public ExpenseLocal(@JsonProperty("category") String category,
                   @JsonProperty("date") Date date,
                   @JsonProperty("cost") double cost,
                   @JsonProperty("desc") String description,
                   @JsonProperty("tag") String tag,
                   @JsonProperty("id") String id){
        this.category = category;
        this.date = date;
        this.cost = cost;
        this.description = description;
        this.tag = tag;
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public Date getDate() {
        return date;
    }

    public double getCost() {
        return cost;
    }

    public String getDescription() {
        return description;
    }

    public String getTag() {
        return tag;
    }

    public String getId() {
        return id;
    }
}
