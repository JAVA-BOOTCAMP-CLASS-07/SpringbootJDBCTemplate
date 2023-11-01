package ar.com.sicos.service;

import java.util.List;

import ar.com.sicos.model.Persona;
import org.springframework.data.domain.Page;

public interface PersonaService {
	List<Persona> getAll();
	void save(Persona persona);
	Persona getById(long id);
	void delete(long id);
}
