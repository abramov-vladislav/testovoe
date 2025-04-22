package org.abramov.spring.testovoe.userservice.repository.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.abramov.spring.testovoe.userservice.entity.User;
import org.abramov.spring.testovoe.userservice.repository.UserRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.*;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final static ResultSetExtractor<List<User>> userExtractor = (rs) -> {
        final var users = new ArrayList<User>();
        while (rs.next()) {
            final var user = new User();
            user.setUserId(rs.getObject("user_id", UUID.class));
            user.setUsername(rs.getString("username"));
            user.setUserLastName(rs.getString("user_last_name"));
            user.setUserFirstName(rs.getString("user_first_name"));

            users.add(user);
        }
        return users;
    };


    private final JdbcTemplate jdbcTemplate;

    @Override
    public Optional<User> getUserByUsername(String username) {
        String sql = """
                SELECT *
                FROM user_service.users
                WHERE username = ?
                """;

        final var users = Objects.requireNonNull(jdbcTemplate.query(sql, userExtractor, username));

        return users.isEmpty() ? Optional.empty() : Optional.of(users.getFirst());
    }

    @Override
    public boolean existsUserByUsername(String username) {
        String sql = """
                SELECT 1
                FROM user_service.users
                WHERE username = ?
                LIMIT 1
                """;

        Boolean exists = jdbcTemplate.queryForObject(sql, Boolean.class, username);
        return Boolean.TRUE.equals(exists);
    }

    @Override
    public List<User> getAllUsers(Integer pageNumber, Integer pageSize) {
        int offset = (pageNumber - 1) * pageSize;
        int limit = pageSize;

        String sql = """
                SELECT *
                FROM user_service.users
                LIMIT ? OFFSET ?
                """;

        final var args = new Object[]{limit, offset};
        final var types = new int[]{Types.INTEGER, Types.INTEGER};

        return Objects.requireNonNull(jdbcTemplate.query(sql, args, types, userExtractor));
    }

    @Override
    public Optional<User> getUserByUserId(UUID userId) {
        String sql = """
                SELECT *
                FROM user_service.users
                WHERE user_id = ?
                """;

        final var users = Objects.requireNonNull(jdbcTemplate.query(sql, userExtractor, userId));

        return users.isEmpty() ? Optional.empty() : Optional.of(users.getFirst());
    }

    @Override
    public User createUser(User user) {
        final var sql = """
                INSERT INTO user_service.users
                (user_id, username, user_last_name, user_first_name)
                VALUES (?, ?, ?, ?)
                """;

        final var args = new Object[]{user.getUserId(), user.getUsername(), user.getUserLastName(), user.getUserFirstName()};
        final var types = new int[]{Types.OTHER, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR};

        jdbcTemplate.update(sql, args, types);

        return user;
    }

    @Override
    public void deleteById(UUID userId) {
        final var sql = """
                DELETE FROM user_service.users
                WHERE user_id = ?
                """;
        jdbcTemplate.update(sql, userId);
    }
}
