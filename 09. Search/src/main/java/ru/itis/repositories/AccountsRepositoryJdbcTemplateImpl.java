package ru.itis.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.itis.dto.AccountDto;
import ru.itis.models.Account;

import javax.sql.DataSource;
import java.util.*;

import static ru.itis.dto.AccountDto.from;

@Repository
public class AccountsRepositoryJdbcTemplateImpl implements AccountsRepository {

    //language=SQL
    private static final String SQL_SEARCH_BY_EMAIL = "select * from account where email like :email";

    //language=SQL
    private static final String SQL_SELECT_ALL = "select * from account order by id";

    //language=SQL
    private static final String SQL_INSERT = "insert into account(first_name, last_name, email) " +
            "values (:firstName, :lastName, :email)";

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final RowMapper<Account> accountRowMapper = (row, rowNumber) -> Account.builder()
            .id(row.getInt("id"))
            .firstName(row.getString("first_name"))
            .lastName(row.getString("last_name"))
            .email(row.getString("email"))
            .build();

    @Autowired
    public AccountsRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<Account> findAll() {
        return namedParameterJdbcTemplate.query(SQL_SELECT_ALL, accountRowMapper);
    }

    @Override
    public void save(Account account) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        Map<String, Object> values = new HashMap<>();
        values.put("firstName", account.getFirstName());
        values.put("lastName", account.getLastName());
        values.put("email", account.getEmail());

        SqlParameterSource parameterSource = new MapSqlParameterSource(values);

        namedParameterJdbcTemplate.update(SQL_INSERT, parameterSource, keyHolder, new String[]{"id"});

        account.setId(keyHolder.getKeyAs(Integer.class));
    }

    @Override
    public List<AccountDto> searchByEmail(String email) {
        return from(namedParameterJdbcTemplate.query(SQL_SEARCH_BY_EMAIL,
                Collections.singletonMap("email", "%" + email.toLowerCase() + "%"), accountRowMapper));
    }
}
