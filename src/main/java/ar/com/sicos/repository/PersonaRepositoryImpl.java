package ar.com.sicos.repository;

import ar.com.sicos.model.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@Repository
public class PersonaRepositoryImpl implements PersonaRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Persona> getAll() {
        Supplier<List<Persona>> supPersona = () ->jdbcTemplate.query("SELECT * FROM personas;", (rs, rowNum) -> Persona.builder()
                .id(rs.getLong("id"))
                .dni(rs.getInt("dni"))
                .nombre(rs.getString("nombre"))
                .apellido(rs.getString("apellido"))
                .build());

        return supPersona.get();
    }

    @Override
    public void save(Persona persona) {
        jdbcTemplate.update(con -> Optional.of(persona.getId())
                                            .filter(id -> id > 0)
                                            .map(id -> {
                                                try {
                                                    PreparedStatement statement = con.prepareStatement("UPDATE personas SET nombre=?, apellido=?, dni=? WHERE id=?");

                                                    statement.setString(1, persona.getNombre());
                                                    statement.setString(2, persona.getApellido());
                                                    statement.setInt(3, persona.getDni());
                                                    statement.setLong(4, id);

                                                    return statement;
                                                } catch (SQLException exc) {
                                                    throw new RuntimeException(exc);
                                                }
                                            })
                                            .orElseGet(() -> {
                                                try {
                                                    PreparedStatement statement = con.prepareStatement("INSERT INTO personas (nombre, apellido, dni) VALUES(?,?,?)");

                                                    statement.setString(1, persona.getNombre());
                                                    statement.setString(2, persona.getApellido());
                                                    statement.setInt(3, persona.getDni());

                                                    return statement;
                                                } catch (SQLException exc) {
                                                    throw new RuntimeException(exc);
                                                }
                                            }));
    }

    @Override
    public Persona getById(long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM personas WHERE id =?",
                new Object[] {id}, new int[] {Types.BIGINT},
                (rs, rowNum) -> Persona.builder()
                        .id(rs.getLong("id"))
                        .dni(rs.getInt("dni"))
                        .nombre(rs.getString("nombre"))
                        .apellido(rs.getString("apellido"))
                        .build());
    }

    @Override
    public void delete(long id) {
        jdbcTemplate.update(con -> {
            PreparedStatement statement = con.prepareStatement("DELETE FROM personas WHERE id=?");

            statement.setLong(1, id);

            return statement;

        });

    }
}
