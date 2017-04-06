package sistema;

import asignatura.*;
import java.io.*;

/**
 * La clase Solicitud gestiona el manejo de solicitudes que han realizado los alumnos para matricularse en asignaturas
 * Por lo tanto, una solicitud relaciona un alumno con una asignatura
 * 
 * @author Álvaro Martinez de Navascues y Alejandro Martin Climent
 */
public class Solicitud implements Serializable{
	private static final long serialVersionUID = 1L; //Necesario para que implemente la interfaz Serializable
	private Alumno alumno;
	private Asignatura asignatura;
	
	/**
	 * Constructor de la clase Solicitud
	 * 
	 * @author Alejandro Martin Climent
	 * @param alumno. Alumno que crea la solicitud
	 * @param asignatura. Asignatura a la cual pide matricularse
	 */
	public Solicitud(Alumno alumno, Asignatura asignatura){
		this.alumno = alumno;
		this.asignatura =  asignatura;
	}
	
	/**
	 * Este metodo se encarga de añadir respectivamente el alumno a los matriculados de una asignatura, 
	 * y la asignatura a las matriculaciones de un alumno  
	 * @author Alvaro Martinez de Navascues
	 */
	public void aceptarSolicitud(){
		this.asignatura.aniadirAlumno(this.alumno);
		this.alumno.aniadirAsignatura(this.asignatura);
		this.alumno.getSolicitudes().remove(this);
	}
	
	/**
	 * Este metodo elimina la solicitud del alumno
	 * 
	 * @author Alejandro Martin Climent
	 */
	public void denegarSolicitud(){
		this.alumno.getSolicitudes().remove(this);
	}

	/**
	 * Getter del atributo alumno
	 * 
	 * @author Alejandro Martin Climent
	 * @return alumno. Devuelve el alumno asociado a la solicitud
	 */
	public Alumno getAlumno() {
		return alumno;
	}

	/**
	 * Getter del atributo asignatura
	 * 
	 * @author Alvaro Martinez de Navascues
	 * @return asignatura. Devuelve la asignatura asociada a la solicitud
	 */
	public Asignatura getAsignatura() {
		return asignatura;
	}
	
	
}
