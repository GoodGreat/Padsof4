package ejercicio;
import java.util.List;

import sistema.Alumno;

/**
 * Clase Multirrespuesta, que implementa los metodos para operar con
 * respuestas que exigiran diferentes opciones seleccionadas. Todas estas tienen
 * que coincidir con las opciones elegidas por el profesor para ser evaluada
 * correctamente
 * 
 * @author Alejandro Martin Climent y Alvaro Martinez de Navascues
 *
 */
public class Multirrespuesta extends Respuesta {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor de la clase Multirrespuesta
	 * 
	 * @param alumno, el alumno que desarrolla la respuesta
	 */
	public Multirrespuesta(Alumno alumno){
		super(alumno);
	}
	

	/**
	 *  Getter de las opciones
	 *  
	 *  @return Opciones, las opciones de la respuesta.
	 */
	public List<Opcion> getOpciones() {
		return super.getOpciones();
	}
	
	/**
	 *  Getter del alumno que ha desarrollado esta respuesta
	 *  
	 *  @return Alumno, el alumno que ha hecho la respuesta
	 */
	public Alumno getAlumno(){
		return super.getAlumno();
	}
	
	/**
	 *  Funcion que aniade una opcion a una respuesta de tipo multirrespuesta
	 *  
	 *  @param opcion, opcion que se quiere aniadir
	 *  @return booleano a true si se ha podido aniadir o false en caso contrario 
	 */
	public boolean resAniadirOpcion(Opcion opcion){
			return super.resAniadirOpcion(opcion);
	}
	
}
