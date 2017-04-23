package ejercicio;
import java.util.List;

import sistema.Alumno;

/**
 * Clase Respuesta Libre, que implementa los metodos para operar con
 * respuestas que el alumno tiene que introducir por teclado
 * 
 * @author Alejandro Martin Climent y Alvaro Martinez de Navascues
 *
 */
public class RespuestaLibre extends Respuesta{
	
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor de la clase RespuestaLibre
	 * 
	 * @param alumno, el alumno que desarrolla la respuesta
	 */
	public RespuestaLibre(Alumno alumno){
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
	 *  Funcion que aniade una opcion a una respuesta Libre 
	 *  
	 *  @param opcion, opcion que se quiere aniadir
	 *  @return booleano a true si se ha podido aniadir o false en caso contrario 
	 */
	public boolean resAniadirOpcion(Opcion opcion){
		if(super.getOpciones().size() == 0){
			return super.resAniadirOpcion(opcion);
		}
	return false;
	}
	
}
