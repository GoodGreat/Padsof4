package sistema;

import asignatura.*;
import java.io.*;

/**
 * La clase Expulsion gestiona el manejo de las expulsiones de alumnos. Cuando un alumno es expulsado de una asignatura
 * queda registrado en esta clase. Por lo tanto, al igual que solicitud, una expulsion relaciona un alumno con una
 * asignatura
 * 
 * @author Álvaro Martinez de Navascues y Alejandro Martin Climent
 */
public class Expulsion implements Serializable{
	private static final long serialVersionUID = 1L; //Necesario para la implementacion de la interfaz Serializable
	private Asignatura asignatura;
	private Alumno alumno;
	
	/**
	 * Constructor de la clase Expulsion
	 * 
	 * @author Alvaro Martinez de Navascues
	 * @param asignatura. Hace referencia a la asignatura de la cual se expulsa a un alumno
	 * @param alumno. Hace referencia al alumno expulsado.
	 */
	public Expulsion(Alumno alumno, Asignatura asignatura){
		this.asignatura = asignatura;
		this.alumno = alumno;
	}


	/**
	 *  Getter de Asignatura
	 *  
	 *  @author Álvaro Martinez de Navascues
	 *  @return La Asignatura de la que un alumno ha sido expulsado
	 */
	public Asignatura getAsignatura(){
		return this.asignatura;
	}
	
	/**
	 *  Getter de Alumno
	 *  
	 *  @author Alejandro Martin Climent
	 *  @return El alumno que ha sido expulsado
	 */
	public Alumno getAlumno(){
		return this.alumno;
	}
	
	/**
	 * Este metodo elimina una expulsion, aniadiendo de nuevo el alumno a la asignatura, y la asignatura al alumno
	 * 
	 * @author Alejandro Martin Climent
	 * @return booleano. En caso de que se aniadan con exito el alumno y la asignatura respectivamente, devolvera 'true'. 'false' en caso contrario
	 */
	public boolean revocarExpulsion(){
		return (this.asignatura.aniadirAlumno(this.alumno) && this.alumno.aniadirAsignatura(this.asignatura));	
	}
}
