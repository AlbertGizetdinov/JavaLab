package ru.itis.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.itis.models.NewFile;

import javax.sql.DataSource;
import java.util.*;

@Repository
public class FileRepositoryImpl implements FileRepository {

    //language=SQL
    private final static String SQL_SELECT_ALL = "select * from file order by id";

    //language=SQL
    private static final String SQL_INSERT = "insert into file(size, original_name, uuid_name, mime_type) " +
            "values (:size, :originalName, :uuidName, :mimeType)";

    //language=SQL
    private final static String SQL_SELECT_BY_UUID_NAME = "select * from file where file.uuid_name = :uuidName";

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final RowMapper<NewFile> fileRowMapper = (row, rowNumber) -> NewFile.builder()
            .id(row.getInt("id"))
            .size(row.getLong("size"))
            .originalName(row.getString("original_name"))
            .uuidName(row.getString("uuid_name"))
            .mimeType(row.getString("mime_type"))
            .build();

    @Autowired
    public FileRepositoryImpl(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<NewFile> findAll() {
        return namedParameterJdbcTemplate.query(SQL_SELECT_ALL, fileRowMapper);
    }

    @Override
    public void save(NewFile file) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        Map<String, Object> values = new HashMap<>();
        values.put("size", file.getSize());
        values.put("originalName", file.getOriginalName());
        values.put("uuidName", file.getUuidName());
        values.put("mimeType", file.getMimeType());

        SqlParameterSource parameterSource = new MapSqlParameterSource(values);

        namedParameterJdbcTemplate.update(SQL_INSERT, parameterSource, keyHolder, new String[]{"id"});

        file.setId(keyHolder.getKeyAs(Integer.class));
    }

    @Override
    public Optional<NewFile> findByUuidName(String uuidName) {
        try {
            return Optional.of(namedParameterJdbcTemplate.queryForObject(SQL_SELECT_BY_UUID_NAME,
                    Collections.singletonMap("uuidName", uuidName), fileRowMapper));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}
