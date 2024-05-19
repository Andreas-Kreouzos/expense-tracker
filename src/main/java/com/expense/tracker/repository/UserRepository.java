package com.expense.tracker.repository;

import com.expense.tracker.entity.User;

/**
 * Defines persistence set of operations for {@link User} objects
 */
public interface UserRepository {

    /**
     * Inserts the provided {@link User} object
     *
     * @param user the user object provided
     */
    Integer insert(User user);
}
