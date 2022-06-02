package hr.tvz.smolcic.hardwareapp.repository;

import hr.tvz.smolcic.hardwareapp.enums.HardwareType;
import hr.tvz.smolcic.hardwareapp.interfaces.repositories.IHardwareRepository;
import hr.tvz.smolcic.hardwareapp.model.Hardware;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Primary
@Repository
public class JdbcMockHardwareRepository implements IHardwareRepository {

    private static final String SELECT_ALL =
            "SELECT hardware_id, name, code, price, hardware_type, stock_amount FROM hardware";
    private final JdbcTemplate jdbc;
    private final SimpleJdbcInsert jdbcInsert;

    public JdbcMockHardwareRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
        this.jdbcInsert = new SimpleJdbcInsert(jdbc).withTableName("hardware").usingGeneratedKeyColumns("hardware_id");
    }

    @Override
    public List<Hardware> findAll() {
        return List.copyOf(jdbc.query(SELECT_ALL, this::mapRowToHardware));
    }

    @Override
    public Optional<Hardware> findByCode(String code) {
        try {
            return Optional.ofNullable(
                    jdbc.queryForObject(SELECT_ALL + " WHERE code = ?", this::mapRowToHardware, code)
            );
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Hardware> save(Hardware hardware) {
        try {
            hardware.setId(saveHardwareDetails(hardware));
            return Optional.of(hardware);
        } catch (DuplicateKeyException e) {
            return Optional.empty();
        }
    }

    @Override
    public void delete(String code) {
        jdbc.update("DELETE FROM hardware WHERE code = ?", code);
    }

    private Hardware mapRowToHardware(ResultSet rs, int rowNum) throws SQLException {
        return new Hardware(
                rs.getInt("hardware_id"),
                rs.getString("name"),
                rs.getString("code"),
                rs.getDouble("price"),
                HardwareType.valueOf(rs.getString("hardware_type")),
                rs.getInt("stock_amount")
        );
    }

    private int saveHardwareDetails(Hardware hardware) {
        Map<String, Object> values = new HashMap<>();

        values.put("name", hardware.getName());
        values.put("code", hardware.getCode());
        values.put("price", hardware.getPrice());
        values.put("hardware_type", hardware.getType());
        values.put("stock_amount", hardware.getStock());

        return jdbcInsert.executeAndReturnKey(values).intValue();
    }
}
