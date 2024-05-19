package com.expense.tracker.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Represents an expense for the application
 */
public class Expense {

    private Long id;
    private String description;
    private BigDecimal amount;
    private LocalDateTime date;
    private Long userId;

    /**
     * @return The id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the value of id
     *
     * @param id The new value to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of description
     *
     * @param description The new value to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return The amount
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Sets the value of amount
     *
     * @param amount The new value to set
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * @return The date
     */
    public LocalDateTime getDate() {
        return date;
    }

    /**
     * Sets the value of date
     *
     * @param date The new value to set
     */
    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    /**
     * @return The userId
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Sets the value of userId
     *
     * @param userId The new value to set
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
