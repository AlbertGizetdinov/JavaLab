package ru.itis.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.itis.models.User;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final static String SQL_SELECT_ALL = "select * from users order by id;";
    private final static String SQL_SELECT_BY_NAME = "select * from users where name = ?;";

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<User> userRowMapper = (row, rowNumber) -> {
        long id = row.getLong("id");
        String name = row.getString("name");
        String password = row.getString("password");

        return new User(id, name, password);
    };

    @Autowired
    public UserRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, userRowMapper);
    }

    @Override
    public Optional<User> findByName(String name) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_SELECT_BY_NAME, userRowMapper, name));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}