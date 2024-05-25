package com.expense.tracker.repository;

import com.expense.tracker.entity.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final JdbcTemplate template;

    public UserRepositoryImpl(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    @Override
    public Integer insert(User user) {
        String sql = "INSERT INTO EXPENSE_TRACKER.USERS (USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL) VALUES (?, ?, ?, ?, ?)";
        return template.update(sql, user.getUsername(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getEmail());
    }

    @Override
    public User select(Long id) {
        String sql = "SELECT * FROM EXPENSE_TRACKER.USERS WHERE ID = ?";
        return template.queryForObject(sql, userRowMapper(), id);
    }

    private RowMapper<User> userRowMapper() {
        return (rs, rowNum) -> new User(
                rs.getLong("ID"),
                rs.getString("USERNAME"),
                rs.getString("PASSWORD"),
                rs.getString("FIRSTNAME"),
                rs.getString("LASTNAME"),
                rs.getString("EMAIL")
        );
    }
}
