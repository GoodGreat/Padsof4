package asignatura;

import java.io.Serializable;
import java.util.*;

import ejercicio.*;
import  sistema.*;

/**
 * Clase Asignatura, contendrá los distintos temas en los que se clasifica la misma
 * Los alumnos realizaran solicitudes para matricularse en las asignaturas, que bien
 * pueden ser aprobadas o rechazadas.
 * 
 * @author Alejandro Martin Climent y Alvaro Martinez de Navascues
 *
 */
public class Asignatura implements Serializable{
	
	/**
	 * Constructor de la clase Asignatura
	 * 
	 * @author Alejandro Martin Climent
	 * 
	 * @param nombre, el nombre de la asignatura
	 * @param visible, indica si la asignatura es publica u oculta
	 * @param alumnos, los alumnos matriculados en la asignatura
	 * @param temas, los distintos temas de la asignatura
	 */
	private static final long serialVersionUID = 1L;
	private String nombre;
	private boolean visible;
	private List<Alumno> alumnos;
	private List<Tema> temas;
	
	/**
	 * Constructor de la clase Asignatura
	 * 
	 * @author Alejandro Martin Climent
	 * 
	 * @param nombre, el nombre de la asignatura
	 * @param visible, indica si los apuntes son publicos o privados
	 */
	public Asignatura(String nombre, boolean visible){
		this.nombre= nombre;
		this.visible = visible;
		this.alumnos = new ArrayList<Alumno>();
		this.temas = new ArrayList<Tema>();
	}

	/**
	 *  Getter del nombre
	 *  
	 *  @return nombre de la asignatura
	 */
	public String getNombre(){
		return this.nombre;
	}
	
	/**
	 *  Getter del atributo de visibilidad
	 *  
	 *  @return Estado de visibilidad: false si es privada y true si es publica
	 */
	public boolean getVisible(){
		return this.visible;
	}
	
	/**
	 *  Getter de los temas de una asignatura
	 *  
	 *  @return temas, todos los temas de una asignatura 
	 */
	public List<Tema> getTemas(){
		return this.temas;
	}
	

	/**
	 *  Getter de los alumnos matriculados de una asignatura
	 *  
	 *  @return lista de alumnos matriculados en esta asignatura 
	 */
	public List<Alumno> getAlumnos(){
		return this.alumnos;
	}
	
	/**
	 * Pone a true el atributo de visibilidad
	 */
	public void publicarAsignatura(){
		if (Sistema.getInstance().isProf() == true) {
			for(Tema temaAux: getTemas()){
				temaAux.publicarTema();
			}
			this.visible = true;
		}
	}
	
	/**
	 * Setter del atributo nombre
	 * 
	 * @author Alejandro Martin Climent
	 * @param nombre. Nombre de la asignatura
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * Oculta todo el contenido de una asignatura
	 */
	public void ocultarAsignatura(){
		boolean puedeOcultarse;
		
		if (Sistema.getInstance().isProf() == true) {
			puedeOcultarse = posibleCambiarAsignatura();
			
			if(puedeOcultarse == true){
				for(Tema temaAux: getTemas()){
					temaAux.ocultarTema();
				}
				this.visible = false;
			} else{
				System.out.println("No puede ocultarse esta asignatura");
			}
		}
	}
	
	/**
	 * Indica si puede cambiarse una asignatura, esto es, si no contiene ejercicios realizados
	 * 
	 * @return true si puede cambiarse (nRealizaciones de ejs = 0) o false si no
	 */
	public boolean posibleCambiarAsignatura(){
		
		for(Tema temaAux: getTemas()){
			if(posibleCambiarTema(temaAux) == false){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Aniade un tema a la asignatura
	 *  
	 *  @param tema , tema que se introducira a esta asigantura
	 *  @return true si se ha aniadido correctamente o false si no
	 */
	public boolean aniadirTema(Tema tema){
		if (Sistema.getInstance().isProf() == true) {
			tema.setAsignatura(this);
			return temas.add(tema);
		}
		return false;
	}
	
	/**
	 * Aniade un alumno a la asignatura
	 *  
	 *  @param alumno, el alumno que se matricula en la asignatura
	 *  @return true si se ha aniadido correctamente o false si no
	 */
	public boolean aniadirAlumno(Alumno alumno){
		if (Sistema.getInstance().isProf() == true) {
			return alumnos.add(alumno);
		}
		return false;
	}
	
	/**
	 * Elimina un tema de la asignatura, pero solo si no contiene ejs realizados
	 *  
	 *  @param tema, tema que se desea borrar
	 *  @return true si se ha eliminado correctamente, false en caso contrario
	 */
	public boolean eliminarTema(Tema tema){
		boolean puedeBorrarse;
		
		if (Sistema.getInstance().isProf() == true) {
			puedeBorrarse = posibleCambiarTema(tema);
			
			if(puedeBorrarse == false){
				return false;
			} else{
				return temas.remove(tema);
			}
		}
		return false;
	}
		
	
	/**
	 * Indica si un tema puede eliminarse u ocultarse
	 *  
	 *  @param tema. Tema que se quiere comprobar
	 *  @return true si puede cambiarse y false si no
	 */
	public boolean posibleCambiarTema(Tema tema){
		
		for(Tema temaAux: tema.getSubtemas()){
			if(posibleCambiarTema(temaAux) == false){
				return false;
			}
		}
		
		for(Ejercicio ejercicioAux: tema.getEjercicios()){
			if(ejercicioAux.getnRealizaciones() != 0){
				return false;
			}
		}
			return true; 
	}
	
	
	/**
	 * Expulsa temporalmente a un alumno de una asignatura (no podra acceder al contenido, mientras dure la sancion)
	 *  
	 *  @param alumno. Alumno que se expulsara
	 *  @return Retorna el valor de la funcion remove(Object o)
	 */
	public boolean expulsarAlumno(Alumno alumno){
		if (Sistema.getInstance().isProf() == true) {
			return this.alumnos.remove(alumno);
		}
		return false;
	}
	
	/**
	 * Consulta las calificaciones de un alumno en una asignatura
	 * 
	 * @param alumno, un alumno matriculado en esta asignatura
	 * @return nota, la nota media de un alumno en todos los ejercicios de esta asignatura
	 */
	public float consultarCalificacionesAlumno(Alumno alumno){
		float nota = 0;		
		for(Tema temaAux: this.getTemas()){
			nota += temaAux.consultarCalificacionesAlumno(alumno);
		}

		return 10 *(nota/calcularPesoPonderado());
	}
	
	/**
	 * Consulta las calificaciones de un alumno en una asignatura
	 * 
	 * @param alumno, un alumno matriculado en esta asignatura
	 * @return nota, la nota media de un alumno en todos los ejercicios de esta asignatura
	 */
	private float calcularPesoPonderado(){
		float pesoPonderado = 0;	
		
		for(Tema temaAux: this.getTemas()){
			pesoPonderado += temaAux.calcularPesoPonderado();
		}
		return pesoPonderado;
	}
}

