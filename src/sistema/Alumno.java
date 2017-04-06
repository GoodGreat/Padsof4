package sistema;

import java.util.*;
import asignatura.*;
import java.io.*;

/**
 * Esta clase se encarga de gestionar toda la informacion de un Alumno. De crear las solicitudes, y de que se le expulse de alguna asignatura 
 * entre otras funcionalidades
 * 
 * @author Álvaro Martinez de Navascues y Alejandro Martin Climent
 */
public class Alumno implements Serializable{
	private static final long serialVersionUID = 1L; //Necesario para la implementacion de la clase Serializable
	private String nombre;
	private String apellido;
	private String correo;
	private String numA;
	private String password;
	private List<Asignatura> asignaturas;
	private List<Solicitud> solicitudes;
	
	/**
	 * Constructor de la clase Alumno.
	 * 
	 * @author Álvaro Martinez de Navascues
	 * @param nombre. Nombre del alumno
	 * @param apellido. Apellido del alumno
	 * @param correo. Correo del alumno
	 * @param numA. Numero de identificacion del alumno
	 * @param password. Contraseña del alumno
	 */
	public Alumno(String nombre, String apellido, String correo, String numA, String password){
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;	
		this.numA = numA;
		this.password = password;
		
		/*Inicializacion de los arrays*/
		this.asignaturas = new ArrayList<Asignatura>();
		this.solicitudes = new ArrayList<Solicitud>();	
	}
	
	/**
	 * Este metodo es llamado desde la clase Sistema. Añade una solicitud al array del Alumno
	 * 
	 * @author Álvaro Martinez de Navascues
	 * @param solicitud. Solicitud que se quiere crear
	 * @return booleano. Retorna 'true' si la solicitud es aniadida correctamente. 'false' en caso contrario
	 */
	public boolean crearSolicitud(Solicitud solicitud){
		return this.solicitudes.add(solicitud);
	}
	
	/**
	 * Este metodo es llamado desde la clase Sistema. Se elimina a un alumno de una asignatura
	 * En concreto para esta clase, eliminaremos la asignatura de la cual ha sido expulsado el alumno
	 * 
	 * @author Alejandro Martin Climent
	 * @param asignatura. Asignatura de la que ha sido expulsado
	 * @return booleano. Retorna 'true' si la asignatura es eliminada correctamente. 'false' en caso contrario
	 */
	public boolean expulsarAlumno(Asignatura asignatura){
		return this.asignaturas.remove(asignatura);
	}

	/**
	 * Este metodo añade una asignatura al array de Asignaturas de un alumnos
	 * 
	 * @author Álvaro Martinez de Navascues
	 * @param asignatura. Asignatura que queremos añadir
	 * @return booleano. Retorna 'true' si la asignatura es añadida correctamente. 'false' en caso contrario
	 */
	public boolean aniadirAsignatura(Asignatura asignatura){
		return this.asignaturas.add(asignatura);
	}	
	
	/**
	 * Getter del atributo nombre
	 * 
	 * @author Alejandro Martin Climent
	 * @return String. Devuelve el nombre del apellido
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Setter del atributo nombre
	 * 
	 * @author Alvaro Martinez de Navascues
	 * @param nombre. Nombre del alumno
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Getter del atributo apellido
	 * 
	 * @author Alejandro Martin Climent
	 * @return String. Apellido del alumno
	 */
	public String getApellido() {
		return apellido;
	}

	/**
	 * Setter del atributo apellido
	 * 
	 * @author Álvaro Martinez de Navascues
	 * @param apellido. Apellido del alumno
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	/**
	 * Getter del atributo correo 
	 * 
	 * @author Alejandro Martin Climent
	 * @return String. Correo del alumno
	 */
	public String getCorreo() {
		return correo;
	}

	/**
	 * Setter del atributo correo
	 * 
	 * @author Álvaro Martinez de Navascues
	 * @param correo. Correo del alumno
	 */
	public void setCorreo(String correo) {
		this.correo = correo;
	}

	/**
	 *  Getter del atributo numA
	 *  
	 *  @author Alejandro Martin Climent
	 *  @return String con el numero del alumno
	 */
	public String getNumA() {
		return numA;
	}

	/**
	 * Setter del atributo numA
	 * 
	 * @author Álvaro Martinez de Navascues
	 * @param numA. Numero de identificacion del alumno
	 */
	public void setNumA(String numA) {
		this.numA = numA;
	}

	/**
	 * Getter del array de asignaturas del alumno
	 * 
	 * @author Alejandro Martin Climent
	 * @return List. Lista de asignaturas
	 */
	public List<Asignatura> getAsignaturas() {
		return this.asignaturas;
	}

	/**
	 * Setter del array de asignaturas del alumno
	 * 
	 * @author Álvaro Martinez de Navascues
	 * @param asignaturas. Lista de asignaturas
	 */
	public void setAsignaturas(ArrayList<Asignatura> asignaturas) {
		this.asignaturas = asignaturas;
	}

	/**
	 * Getter del array de solicitudes del alumno
	 * 
	 * @author Alejandro Martin Climent
	 * @return List. Lista de solicitudes
	 */
	public List<Solicitud> getSolicitudes() {
		return solicitudes;
	}

	/**
	 * Setter del array de solicitudes del alumno
	 * 
	 * @author Álvaro Martinez de Navascues
	 * @param solicitudes. Lista de solicitudes
	 */
	public void setSolicitudes(ArrayList<Solicitud> solicitudes) {
		this.solicitudes = solicitudes;
	}
	
	/**
	 *  Getter del atributo password
	 *  
	 *  @author Alejandro Martin Climent
	 *  @return String con la password del alumno
	 */
	public String getPassword(){
		return this.password;
	}

	/**
	 * Setter del atributo password
	 * 
	 * @author Álvaro Martinez de Navascues
	 * @param password. Contraseña del alumno
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
