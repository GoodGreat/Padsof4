package ejercicio;

import java.io.Serializable;

/**
 * Clase Opcion, cada opcion que se presenta a los alumnos a la hora de solucionar
 * una pregunta
 * 
 * @author Alejandro Martin Climent y Alvaro Martinez de Navascues
 *
 */
public class Opcion implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String opcion;
	
	/**
	 * Constructor de la clase Opcion
	 * 
	 * @param opcion, el texto de la opcion que se mostrara a los alumnos
	 */
	public Opcion(String opcion){
		this.opcion = opcion;
	}
	
	/**
	 *  Getter de la opcion
	 *  
	 *  @return Opcion, la cadena de caracteres que se mostrara
	 */
	public String getOpcion() {
		return this.opcion;
	}
	
}
