package ar.com.sicos.controller;

import ar.com.sicos.model.Persona;
import ar.com.sicos.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class PersonaController {

	@Autowired
	private PersonaService personaService;
	
	@GetMapping("/")
	public String viewHomePage(Model model) {
		List<Persona> listPersonas = personaService.getAll();
		model.addAttribute("listPersonas", listPersonas);
		return "index";
	}
	
	@GetMapping("/mostrarInfoInsert")
	public String mostrarInfoInsert(Model model) {
		model.addAttribute("persona", Persona.builder().build());
		return "nuevaPersona";
	}
	
	@PostMapping("/savePersona")
	public String savePersona(@ModelAttribute("persona") Persona persona) {
		personaService.save(persona);
		return "redirect:/";
	}
	
	@GetMapping("/mostrarInfoUpdate/{id}")
	public String mostrarInfoUpdate(@PathVariable ( value = "id") long id, Model model) {
		
		Persona persona = personaService.getById(id);
		
		model.addAttribute("persona", persona);
		return "updatePersona";
	}
	
	@GetMapping("/deletePersona/{id}")
	public String deletePersona(@PathVariable (value = "id") long id) {
		this.personaService.delete(id);
		return "redirect:/";
	}

}
