package ar.com.sicos.model;

import lombok.*;

@Getter
@Setter
@Data
@Builder
@ToString
public class Persona {
	private long id;
	
	private String nombre;
	private String apellido;
	private int dni;
}
