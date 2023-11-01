package ar.com.sicos.service;

import ar.com.sicos.model.Persona;
import ar.com.sicos.repository.PersonaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PersonaServiceImpl implements PersonaService {

	private final String KEY = this.getClass().getSimpleName();

	@Autowired
	private PersonaRepository personaRepository;

	@Override
	public List<Persona> getAll() {
		log.info("{} - Consultando listado de Personas", KEY);

		log.debug("{} - Informacion detallada de la consulta .....", KEY);

		return personaRepository.getAll();
	}

	@Override
	public void save(Persona persona) {
		this.personaRepository.save(persona);
	}

	@Override
	public Persona getById(long id) {
		return this.personaRepository.getById(id);
	}

	@Override
	public void delete(long id) {
		this.personaRepository.delete(id);
	}

}
