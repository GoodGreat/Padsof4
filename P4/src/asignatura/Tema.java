package asignatura;

import java.io.Serializable;
import java.util.*;
import ejercicio.*;
import sistema.Alumno;
import sistema.Sistema;

/**
 * Clase Tema, con ejercicios y apuntes con un contenido comun. Puede a su vez
 * contener otros temas, los subtemas.
 * 
 * @author Alejandro Martin Climent y Alvaro Martinez de Navascues
 *
 */
public class Tema implements Serializable{

	private static final long serialVersionUID = 1L;
	private Asignatura asignatura;
	private String nombre;
	private boolean visible;
	private List<Tema> subtemas;
	private List<Ejercicio> ejercicios;
	private List<Apunte> apuntes;
	
	
	/**
	 * Constructor de la clase Tema
	 * 
	 * @author Alejandro Martin Climent
	 * 
	 * @param nombre, el nombre del tema
	 * @param visible, indica si los apuntes son publicos o privados
	 */
	public Tema(String nombre, boolean visible){
		this.asignatura = null;
		this.nombre= nombre;
		this.visible = visible;	
		this.subtemas = new ArrayList<Tema>();
		this.ejercicios = new ArrayList<Ejercicio>();
		this.apuntes = new ArrayList<Apunte>();
	}
	
	/**
	 *  Getter del nombre
	 *  
	 *  @return nombre del tema
	 */
	public String getNombre(){
		return this.nombre;
	}
	
	
	/**
	 *  Setter del nombre
	 *  
	 *  @param nombre del tema
	 */
	public void setNombre(String nombre){
		if (Sistema.getInstance().isProf() == true) {
			this.nombre = nombre;
		}
	}
	/**
	 *  Getter del atributo de visibilidad
	 *  
	 *  @return Estado de visibilidad: false si es privado y true si es publica
	 */
	public boolean getVisible(){
		return this.visible;
	}
	
	/**
	 *  Getter de la asignatura
	 *  
	 *  @return asignatura, asignatura en la que esta contenido este tema
	 */
	public Asignatura getAsignatura(){
		return this.asignatura;
	}
	
	/**
	 *  Setter de la asignatura
	 *  
	 *  @return asignatura en la que esta contenido este tema
	 */
	public void setAsignatura(Asignatura asignatura){
		this.asignatura = asignatura;
	}
	
	/**
	 * Pone a true la visibilidad de todo lo contenido en el tema
	 */
	public void publicarTema(){
		if (Sistema.getInstance().isProf() == true) {
			
			
			 for(Tema temaAux: getSubtemas()){
				temaAux.publicarTema();
			 }
			
			 for(Apunte apunteAux: getApuntes()){
				    apunteAux.publicarApunte();
			 }
				
			 for(Ejercicio ejercicioAux: getEjercicios()){
			    	ejercicioAux.publicarEjercicio();
			 }
			 
			/* for (Alumno alumnoAux : this.getAsignatura().getAlumnos()) {
					try {
						Sistema.getInstance().notificarPorEmail(alumnoAux, "Publicacion Nuevo Tema",
								"Se ha publicado el tema " + this.getNombre() + " en la Asignatura "
										+ this.getAsignatura());
					} catch (Exception e) {
						System.out.println("Error: FailedInternetConnection");
					}
				}
			 */
			 this.visible = true;
		}
	}
	
	/**
	 * Pone a false la visibilidad de todo lo contenido en el tema
	 */
	public void ocultarTema(){
		if (Sistema.getInstance().isProf() == true) {
			 for(Tema temaAux: getSubtemas()){
				temaAux.ocultarTema();
			 }
			
			 for(Apunte apunteAux: getApuntes()){
				    apunteAux.ocultarApunte();
				}
				
			 for(Ejercicio ejercicioAux: getEjercicios()){
			    	ejercicioAux.ocultarEjercicio();
			 }
			 this.visible = false;
		}
	}
	
	/**
	 *  Getter de los subtemas de un tema
	 *  
	 *  @return subtemas, los subtemas de este tema
	 */
	public  List<Tema> getSubtemas(){
		return this.subtemas;
	}
	
	/**
	 *  Getter de los ejercicios del tema
	 *  
	 *  @return lista de ejercicios, los ejercicios de este tema
	 */
	public  List<Ejercicio> getEjercicios(){
		return this.ejercicios;
	}
	
	
	/**
	 *  Getter de los apuntes de un tema 
	 *  
	 *  @return lista de los apuntes, los apuntes de este tema
	 */
	public  List<Apunte> getApuntes(){
		return this.apuntes;
	}
	
	/**
	 * Aniade un subtema al tema
	 *  
	 *  @param subtema, subtema que formara parte de este tema
	 *  @return true si lo ha aniadido correctectamente, false en caso contrario
	 */
	public boolean aniadirSubtema(Tema subtema){
		if (Sistema.getInstance().isProf() == true) {
			subtema.setAsignatura(this.asignatura);
			return subtemas.add(subtema);
		}
		return false;
	}
	
	/**
	 * Elimina un subtema, un tema contenido en este
	 *  
	 *  @param tema, subtema que se quiere eliminar, solo si no contiene ejercicios ya realizados
	 */
	public void eliminarSubtema(Tema subtema){
		boolean puedeBorrarse;
		
		if (Sistema.getInstance().isProf() == true) {
			puedeBorrarse = posibleCambiarSubtema(subtema);
			
			if(puedeBorrarse == false){
				System.out.println("No puede borrarse este tema porque al menos un alumno ha realizado un ejercicio. TE VOY A DEVOLVER FALSE MUAHAHAHAHA");
			
			} else{
				subtemas.remove(subtema);
			}
		}
	}
		
	
	/**
	 * Indica si un subtema puede eliminarse u ocultarse
	 *  
	 *  @param false si ha encontrado algun ejercicio ya resuelto, true en caso contrario
	 */
	public boolean posibleCambiarSubtema(Tema subtema){
		
		for(Tema temaAux: subtema.getSubtemas()){
			if(temaAux.posibleCambiarSubtema(temaAux) == false){
				return false;
			}
		}
		
		for(Ejercicio ejercicioAux: subtema.getEjercicios()){
			if(ejercicioAux.getnRealizaciones() != 0){
				return false;
			}
		}
			return true; 
	}
	
	/**
	 * Aniade un ejercicio al tema
	 *  
	 *  @param ejercicio, ejercicio que deberan realizar los alumnos
	 *  @return true si se ha aniadido correctamente, false en caso contrario
	 */
	public boolean aniadirEjercicio(Ejercicio ejercicio){
		if (Sistema.getInstance().isProf() == true) {
			ejercicio.setTemaSuperior(this);
			return ejercicios.add(ejercicio);
		}
		return false;
	}

	/**
	 * Elimina un ejercicio del tema, si aun no ha sido realizado
	 *  
	 * @param ejercicio que se quiere eliminar
	 * @return true si se ha eliminado correctamente, false en caso contrario
	 */
	public boolean eliminarEjercicio(Ejercicio ejercicio){
		if(ejercicio.getnRealizaciones() == 0 && Sistema.getInstance().isProf() == true){
			return ejercicios.remove(ejercicio);
		} else{
			System.out.println("No puede borrarse este ejercicio porque al menos un alumno lo ha realizado.");
			return false;
		}
	}
	
	/**
	 * Aniade un apunte al tema
	 *  
	 *  @param apunte, apunte que se aniadira al tema
	 *  @return true si se ha aniadido correctamente, false en caso contrario
	 */
	public boolean aniadirApunte(Apunte apunte){
		if (Sistema.getInstance().isProf() == true) {
			apunte.setTemaSuperior(this);
			return apuntes.add(apunte);
		}
		return false;
	}

	/**
	 * Elimina un apunte del tema
	 *  
	 *  @param apunte, apunte que se desea eliminar
	 *  @return true si se ha eliminado correctamente, false en caso contrario
	 */
	public boolean eliminarApunte(Apunte apunte){
		if (Sistema.getInstance().isProf() == true) {
			return apuntes.remove(apunte);
		}
		return false;
	}
	

	/**
	 * Consulta las calificaciones de un alumno en una asignatura
	 */
	public float consultarCalificacionesAlumno(Alumno alumno){
		float nota = 0;
		for(Tema temaAux: this.getSubtemas()){
			nota += temaAux.consultarCalificacionesAlumno(alumno);
			}
		
		for(Ejercicio ejercicioAux: this.getEjercicios()){
			nota += ejercicioAux.obtenerNotaAlumno(alumno) * ejercicioAux.getPeso();
			}
		return nota;
	}
	
	/**
	 * Consulta las calificaciones de un alumno en una asignatura
	 * 
	 * @param alumno, un alumno matriculado en esta asignatura
	 * @return nota, la nota media de un alumno en todos los ejercicios de esta asignatura
	 */
	public float calcularPesoPonderado(){
		float pesoPonderado = 0;	
		
		for(Tema temaAux: this.getSubtemas()){
			pesoPonderado += temaAux.calcularPesoPonderado();
		}

		for(Ejercicio ejercicioAux: this.getEjercicios()){
			pesoPonderado += 10 * ejercicioAux.getPeso();
		}
		
		return pesoPonderado;
	}
}
