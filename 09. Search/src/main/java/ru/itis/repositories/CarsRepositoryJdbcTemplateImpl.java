package ru.itis.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.itis.models.Car;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CarsRepositoryJdbcTemplateImpl implements CarsRepository {

    //language=SQL
    private static final String SQL_INSERT = "insert into car (model, color) values (:model, :color);";

    //language=SQL
    private static final String SQL_SELECT = "select * from car";

    private static final RowMapper<Car> carRowMapper = (row, rowNumber) -> Car.builder()
            .id(row.getInt("id"))
            .model(row.getString("model"))
            .color(row.getString("color"))
            .build();

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public CarsRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<Car> findAll() {
        return jdbcTemplate.query(SQL_SELECT, carRowMapper);
    }

    @Override
    public void save(Car car) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        Map<String, String> params = new HashMap<>();

        params.put("model", car.getModel());
        params.put("color", car.getColor());

        SqlParameterSource parameterSource = new MapSqlParameterSource(params);

        jdbcTemplate.update(SQL_INSERT, parameterSource, keyHolder, new String[]{"id"});

        car.setId(keyHolder.getKeyAs(Integer.class));
    }
}
