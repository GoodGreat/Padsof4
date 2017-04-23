package ejercicio;
import java.util.List;

import sistema.Alumno;

/**
 * Clase RespuestaBooleana, que implementa los metodos para operar con
 * respuestas de dos opciones posibles, entre las que se debe escoger una
 * 
 * @author Alejandro Martin Climent y Alvaro Martinez de Navascues
 *
 */
public class RespuestaBooleana extends Respuesta{

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor de la clase RespuestaBooleana
	 * 
	 * @param alumno, el alumno que desarrolla la respuesta
	 */
	public RespuestaBooleana(Alumno alumno){
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
	 *  Funcion que aniade una opcion a una respuesta booleana 
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
