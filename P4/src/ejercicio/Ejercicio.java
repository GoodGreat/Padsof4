package ejercicio;
import java.io.Serializable;
import java.util.*;


import asignatura.Tema;
import sistema.Alumno;
import sistema.Sistema;

/**
 * Clase Ejercicio, que implementará las distintas funconalidades relaconadas con los ejercicios
 * que los alumnos pueden realizar de las asignaturas
 * 
 * @author Alejandro Martin Climent y Alvaro Martinez de Navascues
 *
 */
public class Ejercicio implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String nombre;
	private float peso;
	private Calendar fechaIni;
	private Calendar fechaFin;
	private boolean visible;
	private Tema temaSuperior;
	private int nRealizaciones;
	private List<Pregunta> preguntas;
	private boolean aleatorio;
	/**
	 * Constructor sencillo del objeto Ejercicio. Simplemente sirve para instanciar la clase
	 * @author Alvaro Martinez de Navascues
	 */
	public Ejercicio(){}
	
	/**
	 * Otro constructor del objeto Ejercicio. Este constructor recibe todos los argumentos necesarios para crear el ejercicio
	 * 
	 * @author Alvaro Martinez de Navascues
	 * 
	 * @param nombre. Hace referencia al nombre del ejercicio
	 * @param peso. Hace referencia a la ponderación del ejercicio con respecto al total de la nota
	 * @param anyoFin. El anyo a partir del que expirara el ejercicio
	 * @param mesFin. El mes a partir del que expirara el ejercicio
	 * @param diaFin. El dia a partir del que expirara el ejercicio
	 * @param horasFin. La hora partir de la que expirara el ejercicio
	 * @param minsFin. El minuto a partir del que expirara el ejercicio
	 * @param anyoIni. El anyo a partir del que se puede realizar el ejercicio
	 * @param mesIni. El mes a partir del que se puede realizar el ejercicio
	 * @param diaIni. El dia a partir del que se puede realizar el ejercicio
	 * @param horasIni. La hora partir de la que se puede realizar el ejercicio
	 * @param minsIni. El minuto a partir del que se puede realizar el ejercicio
	 * @param visible. Indica si el ejercicio es visible (true) o no (false)
	 * @param aleat. Indica si las preguntas y opciones se mostraran aleatoriamente o no
	 */
	public Ejercicio(String nombre, float peso, int anyoIni, int mesIni, int diaIni, int horasIni, int minsIni,
			int anyoFin, int mesFin, int diaFin, int horasFin, int minsFin, 
			 boolean visible, boolean aleat){
		
		this.nRealizaciones = 0;
		this.nombre = nombre;
		this.visible = visible;
		this.peso = peso;
		this.fechaIni = Calendar.getInstance();
		this.fechaFin = Calendar.getInstance();
		this.fechaIni.set(anyoIni, mesIni, diaIni, horasIni, minsIni);
		this.fechaFin.set(anyoFin, mesFin, diaFin, horasFin, minsFin);
		this.temaSuperior = null;
		this.preguntas = new ArrayList<Pregunta>();
		this.aleatorio = aleat;
	}
	
	/**
	 *  Getter del atributo nRealizaciones
	 *  
	 *  @return int nRealizaciones, numero de alumnos que han resuelto el ejercicio
	 */
	public int getnRealizaciones(){
		return this.nRealizaciones;
	}
	
	/**
	 * Aumenta uno en el contador del numero de realizaciones de un ejercicio
	 */
	public void aumentarRealizaciones(){
		this.nRealizaciones += 1;
	}
	
	/**
	 *  Getter del tema superior, el tema en el que esta contenido este ejercicio
	 *  
	 *  @return tema superior, el tema directamente superior a este,en el que esta contenido
	 */
	public Tema getTemaSuperior(){
		return this.temaSuperior;
	}
	
	/**
	 *  Setter del tema superior, el tema principal en el que esta contenido un subtema
	 *  
	 *  @param tema superior, el tema en el que esta contenido
	 */
	public void setTemaSuperior(Tema temaSup){
	     this.temaSuperior = temaSup;
	}
	
	/**
	 *  Getter del booleano aleatorio, indica si las preguntas y opciones se tienen que mostrar de forma aleatoria
	 *  
	 *  @return aleatorio, a true si el profesor quiere las preguntas y opciones aleatorias, a false en caso contrario
	 */
	public boolean getAleatorio(){
		return this.aleatorio;
	}
	
	/**
	 *  Setter del booleano aleatorio, indica si las opciones se tienen que mostrar de forma aleatoria
	 *  
	 *  @param aleatorio, a true si el profesor quiere las opciones aleatorias, a false en caso contrario
	 */
	public void setAleatorio(boolean aleat){
		if (this.nRealizaciones > 0){
			return;
		} else if(Sistema.getInstance().isProf() == true){
		     this.aleatorio = aleat;
		     for(Pregunta preguntaAux : this.getPreguntas()){
		    	 preguntaAux.setAleatorio(aleat);
		     }
		}	
	}
	
	/**
	 * Cambia a true el atributo de visibilidad
	 */
	public void ocultarEjercicio(){
		if (nRealizaciones == 0 &&Sistema.getInstance().isProf() == true){
			this.visible = false;
		}
	}
	
	/**
	 * Cambia a false el atributo de visibilidad
	 */
	public void publicarEjercicio(){
		if (Sistema.getInstance().isProf() == true) {
			for (Alumno alumnoAux : this.getTemaSuperior().getAsignatura().getAlumnos()) {
				try {
					Sistema.getInstance().notificarPorEmail(alumnoAux, "Publicacion Nuevo Ejercicio",
							"Se ha publicado el ejercicio " + this.getNombre() + " en la Asignatura "
									+ this.getTemaSuperior().getAsignatura());
				} catch (Exception e) {
					System.out.println("Error: FailedInternetConnection");
				}
			}
			this.visible = true;
		}
	}
	
	/**
	 *  Funcion que aniade una Pregunta a un ejercicio, si aun este no ha sido realizado
	 *  
	 *  @param pregunta, la pregunta que se aniadira
	 */
	public boolean aniadirPregunta(Pregunta pregunta){
		if (nRealizaciones > 0 || Sistema.getInstance().isProf() == false){
			System.out.println("No se puede modificar el ejercicio.");
		}else{
			pregunta.setEjercicioSuperior(this);
			pregunta.setAleatorio(this.aleatorio);
			return preguntas.add(pregunta);
		}
		return false;
	}
	
	/**
	 * Metodo que barajea las preguntas de un ejercicio
	 */
	public void barajarPreguntas(){
		if(this.aleatorio == true){
			Collections.shuffle(this.preguntas);
		}
	}	
	
	/**
	 *  Funcion que elimina una pregunta de un ejercicio
	 *  
	 *  @param pregunta, la pregunta que se eliminara
	 */
	public void eliminarPregunta(Pregunta pregunta){
		if (nRealizaciones > 0 || Sistema.getInstance().isProf() == false){
			System.out.println("El ejercicio ya ha sido realizado, no se puede modificar de ninguna forma.");
		}else{
			preguntas.remove(pregunta);
		}
	}
	
	/**
	 *  Getter del nombre
	 *  
	 *  @return nombre,el nombre del ejercicio
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 *  Setter del atributo nombre
	 *  
	 *  @param nombre, el nombre del ejercicio
	 */
	public void setNombre(String nombre) {
		if (Sistema.getInstance().isProf() == true) {
			this.nombre = nombre;
		}
	}

	/**
	 *  Getter del peso del ejercicio
	 *  
	 *  @return peso del ejercicio, el porcentaje de nota que se ponderará con el resto de los ejercicios
	 *  para calcular la nota de un alumno en una asignatura
	 */
	public float getPeso() {
		return peso;
	}

	/**
	 *  Setter del peso del ejercicio
	 *  
	 *  @param peso del ejercicio, el porcentaje de nota que se ponderará con el resto de los ejercicios
	 *  para calcular la nota de un alumno en una asignatura
	 */
	public void setPeso(float peso) {
		if (Sistema.getInstance().isProf() == true) {
			this.peso = peso;
		}
	}
	
	/**
	 *  Getter del atributo fechaIni
	 *  
	 *  @return fechaIni, fecha en la que se abre el plazo de realizacion de un ejercicio
	 */
	public Calendar getFechaIni() {
		return fechaIni;
	}

	/**
	 *  Setter del atributo fechaIni
	 *  
	 *  @return fechaFin, fecha en la que se abre el plazo de realizacion de un ejercicio
	 */
	public void setFechaIni(Calendar fechaIni) {
		if (Sistema.getInstance().isProf() == true) {
			for (Alumno alumnoAux : this.getTemaSuperior().getAsignatura().getAlumnos()) {
				try {
					Sistema.getInstance().notificarPorEmail(alumnoAux, "Modificacion de Fecha",
							"Se ha modificado la fecha de inicio del ejercicio " + this.getNombre() + " en la Asignatura "
									+ this.getTemaSuperior().getAsignatura() + " a la fecha " + fechaIni);
				} catch (Exception e) {
					System.out.println("Error: FailedInternetConnection");
				}
			}
			this.fechaIni = fechaIni;
		}	
	}
	
	/**
	 *  Getter del atributo fechaFin
	 *  
	 *  @return fechaFin, fecha en la que se cierra el plazo de realizacion de un ejercicio
	 */
	public Calendar getFechaFin() {
		return fechaFin;
	}

	/**
	 *  Setter del atributo fechaFin
	 *  
	 *  @return fechaFin, fecha en la que se cierra el plazo de realizacion de un ejercicio
	 */
	public void setFechaFin(Calendar fechaFin) {
		if (Sistema.getInstance().isProf() == true && this.getnRealizaciones() == 0) {
			for (Alumno alumnoAux : this.getTemaSuperior().getAsignatura().getAlumnos()) {
				try {
					Sistema.getInstance().notificarPorEmail(alumnoAux, "Modificacion de Fecha",
							"Se ha modificado la fecha de fin del ejercicio " + this.getNombre() + " en la Asignatura "
									+ this.getTemaSuperior().getAsignatura() + " a la fecha " + fechaFin);
				} catch (Exception e) {
					System.out.println("Error: FailedInternetConnection");
				}
			}
			this.fechaFin = fechaFin;
		}		
	}

	/**
	 *  Getter del atributo visible
	 *  
	 *  @return true si el ejercicio es visible y false en caso contrario
	 */
	public boolean getVisible() {
		return visible;
	}

	/**
	 *  Comprueba si un ejercicio esta en plazo o no 
	 *  
	 *  @return booleano a true si lo esta y a false en caso contrario
	 */
	public boolean estaEnPlazo() {
		Calendar ahoramismo = Calendar.getInstance();
		if(ahoramismo.after(getFechaIni()) == true && ahoramismo.before(getFechaFin()) == true){
			return true;
		}else
			return false;
	}

	/**
	 *  Getter de las preguntas de un ejercicio
	 *  
	 *  @return Lista de preguntas totales del ejercicio
	 */
	public List<Pregunta> getPreguntas() {
		return preguntas;
	}
	
	/**
	 * Funcion que aniade una respuesta a una pregunta de un ejercicio
	 */
	public boolean registrarRespuestaAlumno(Alumno alumno, Pregunta pregunta, List<Opcion> opciones){
		
		if (Sistema.getInstance().isProf() == false && this.visible == true && estaEnPlazo() == true) {
			this.aumentarRealizaciones();
			return pregunta.pregAniadirRespuesta(alumno, opciones);
		}
		return false;
	}
	
	/**
	 * Funcion que obtiene la nota de un alumno para un ejercicio
	 * 
	 * @param alumno
	 * @return nota, la nota del alumno en este ejercicio
	 */
	public float obtenerNotaAlumno(Alumno alumno){
		float puntuacionMaxima = 0;
		float nota = 0;
		
		for(int i = 0; preguntas.size() > i; i++){
			puntuacionMaxima += preguntas.get(i).getPuntuacion();

			nota += preguntas.get(i).obtenerNotaAlumno(alumno);
		}
		if(nota < 0){
			return 0;
		}
		return 10 * (nota/puntuacionMaxima);
	}
	

}
