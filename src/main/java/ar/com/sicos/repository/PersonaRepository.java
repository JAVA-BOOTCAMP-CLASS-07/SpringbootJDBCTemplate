package ar.com.sicos.repository;

import ar.com.sicos.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface PersonaRepository {
    List<Persona> getAll();
    void save(Persona persona);
    Persona getById(long id);
    void delete(long id);
}
