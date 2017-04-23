package ejercicio;
import java.io.Serializable;
import java.util.*;
import sistema.Alumno;


/**
 * Clase abstracta Respuesta, que implementa los metodos para operar con
 * los diferentes tipos de respuesta
 * 
 * @author Alejandro Martin Climent y Alvaro Martinez de Navascues
 *
 */
public abstract class Respuesta implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private List<Opcion> opciones;
	private Alumno alumno;
	
	/**
	 * Constructor de la clase Respuesta
	 * @param alumno, el alumno que desarrolla la respuesta
	 */
	public Respuesta(Alumno alumno){
		this.alumno = alumno;
		this.opciones = new ArrayList<Opcion>();
	}
	

	/**
	 *  Getter de las opciones
	 *  
	 *  @return Opciones, las opciones de la respuesta.
	 */
	public List<Opcion> getOpciones() {
		return this.opciones;
	}
	
	/**
	 *  Funcion que aniade una opcion a una respuesta
	 *  
	 *  @param opcion, opcion que se quiere aniadir
	 *  @return booleano a true si se ha podido aniadir o false en caso contrario, 
	 */
	public boolean resAniadirOpcion(Opcion opcion){
		return opciones.add(opcion);
	}
	
	/**
	 *  Getter del alumno que ha desarrollado esta respuesta
	 *  
	 *  @return Alumno, el alumno que ha hecho la respuesta
	 */
	public Alumno getAlumno(){
		return this.alumno;
	}
	
}
