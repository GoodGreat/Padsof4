package ejercicio;

import java.io.Serializable;
import java.util.*;

import sistema.Alumno;
import sistema.Sistema;

/**
 * Clase abstracta Pregunta, que implementa los metodos para operar con
 * los diferentes tipos de pregunta
 * 
 * @author Alejandro Martin Climent y Alvaro Martinez de Navascues
 *
 */

public abstract class Pregunta implements Serializable{

	private static final long serialVersionUID = 1L;
	private String enunciado;
	private float puntuacion;
	private boolean falloResta;
	private float resta;
	private String tipo;
	private Ejercicio ejercicioSup;
	private List<Opcion> opciones;
	private Respuesta respuestaProf;
	private List<Respuesta> respuestas;
	private boolean aleatorio;

	/**
	 *  Constructor de la clase Pregunta
	 * 
	 * @param enunciado, el enunciado de la pregunta
	 * @param puntuacion, la puntuacion que suma en caso de estar correctamente solucionada
	 * @param falloResta, indica si al fallar una pregunta penalizara o no
	 * @param resta, la nota que resta al ejercicio en caso de solucionar mal una pregunta
	 * @param tipo, el tipo de pregunta que es esta pregunta
	 * @param aleatorio, a true si las opciones se mostraran de forma aleaoria
	 */
	public Pregunta(String enunciado, float puntuacion, boolean falloResta, float resta, String tipo){
		this.enunciado = enunciado;
		this.puntuacion = puntuacion;
		this.falloResta = falloResta;
		this.resta = resta;
		this.tipo = tipo;
		this.respuestaProf = null;
		this.ejercicioSup = null;
		this.opciones = new ArrayList<Opcion>();
		this.respuestas = new ArrayList<Respuesta>();
	}
	
	
	/**
	 *  Getter del enunciado
	 *  
	 *  @return enunciado, el enunciado de la pregunta
	 */
	public String getEnunciado() {
		return this.enunciado;
	}
	
	/**
	 *  Getter de la puntuacion
	 *  
	 *  @return puntuacion, la puntuacion de la pregunta si es respondida correctamente
	 */
	public float getPuntuacion() {
		return this.puntuacion;
	}
	
	/**
	 *  Getter de falloresta
	 *  
	 *  @return falloresta, atributo que indica si el fallo en la pregunta puntua negativamente
	 */
	public boolean getFalloResta() {
		return this.falloResta;
	}
	
	/**
	 *  Getter de resta
	 *  
	 *  @return resta, puntuacion total que penaliza fallar una pregunta
	 */
	public float getResta() {
		return this.resta;
	}
	
	/**
	 *  Getter del tipo de pregunta
	 *  
	 *  @return tipo, una de los 4 tipos de preguntas posibles
	 */
	public String getTipo() {
		return this.tipo;
	}
	
	/**
	 *  Getter del Ejercicio al que pertenece una pregunta
	 *  
	 *  @return Ejercicio, el ejercicio al que pertenece esta pregunta
	 */
	public Ejercicio getEjercicioSuperior() {
		return this.ejercicioSup;
	}

	/**
	 *  Setter del Ejercicio al que pertenece una pregunta
	 *  
	 *  @return Ejercicio, el ejercicio al que pertenece esta pregunta
	 */
	public void setEjercicioSuperior(Ejercicio ejerciciosup) {
	     this.ejercicioSup = ejerciciosup;
	}
	
	/**
	 *  Getter del booleano aleatorio, indica si las opciones se tienen que mostrar de forma aleatoria
	 *  
	 *  @return aleatorio, a true si el profesor quiere las opciones aleatorias, a false en caso contrario
	 */
	public boolean getAleatorio(){
		return this.aleatorio;
	}
	
	/**
	 *  Setter del booleano aleatorio, indica si las preguntas y opciones se tienen que mostrar de forma aleatoria
	 *  
	 *  @param aleatorio, a true si el profesor quiere las preguntas y opciones aleatorias, a false en caso contrario
	 */
	public void setAleatorio(boolean aleat){
	     this.aleatorio = aleat;
	}
	
	/**
	 *  Getter del atrbuto respuestaProf
	 *  
	 *  @return respuestaProf, la respuesta que da como solucion un profesor
	 *  a una pregunta planteada por el profesor
	 */
	public Respuesta getRespuestaProf() {
		return this.respuestaProf;
	}

	/**
	 *  Setter del atrbuto respuestaProf
	 *  
	 *  @param respuestaProf, la respuesta que da como solucion un profesor
	 *  a una pregunta planteada por el profesor
	 */
	public void setRespuestaProf(Respuesta respuestaProf){
		if (Sistema.getInstance().isProf() == true) {
			this.respuestaProf = respuestaProf;
		}
	}
	
	/**
	 *  Getter de las opciones
	 *  
	 *  @return Opciones de esta pregunta, propuestas por el profesor
	 */
	public List<Opcion> getOpciones() {
		return this.opciones;
	}
	
	/**
	 * Metodo que barajea las opciones de una pregunta
	 */
	public void barajarOpciones(){
		if(this.aleatorio == true){
			Collections.shuffle(this.opciones);
		}
	}	
	
	/**
	 *  Getter de las respuestas de los alumnos
	 *  
	 *  @return Respuestas de los alumnos a esta pregunta
	 */
	public List<Respuesta> getRespuestas() {
		return this.respuestas;
	}
	
	/**
	 *  Funcion que aniade una opcion a una pregunta
	 *  
	 *  @param opcion, opcion que se quiere aniadir
	 *  @return true si la aniadido correctamente, false en caso contrario 
	 */
	public boolean pregAniadirOpcion(Opcion opcion){
		if (Sistema.getInstance().isProf() == true) {
			for(Opcion opcionaux: this.getOpciones()){
				if(opcionaux.getOpcion().equals(opcion.getOpcion())){
					return false;
				}
			}
			return opciones.add(opcion);
		}
		return false;
	}

	/**
	 *  Funcion que aniade una respuesta a una pregunta
	 *  
	 *  @param respuesta, respuestas que se quiere aniadir
	 *  @return booleano a true si se ha podido aniadir o false en caso contrario
	 */
	public abstract boolean pregAniadirRespuesta(Alumno alumno, List<Opcion> opciones);
	
	/**
	 *  Funcion que calcula el porcentaje de alumnos que contestaron una pregunta
	 *  
	 *  @return porcentaje de alumnos que contestaron a la pregunta
	 */
	public float alumnosContestados(){
		if (Sistema.getInstance().isProf() == true) {
			return (float)respuestas.size() * 100 / getEjercicioSuperior().getTemaSuperior().getAsignatura().getAlumnos().size();
		}
		return 0;
	}
	
	/**
	 *  Funcion que calcula el porcentaje de alumnos que contestaron bien una pregunta
	 *  
	 *  @return porcentaje de alumnos que contestaron correctamente a la pregunta
	 */
	public float alumnosContestadosCorrecto(){
		int contador = 0;
		if (Sistema.getInstance().isProf() == true) {
			for(int i = 0; respuestas.size() > i;i++){
				if( obtenerNotaAlumno(respuestas.get(i).getAlumno()) > 0){
					contador++;
				}
				
			}
			return (float)contador  * 100/respuestas.size();
		}
		return 0;
	}
	
	
	/**
	 *  Funcion que calcula la nota obtenida por un alumno en una pregunta
	 *  
	 *  @param alumno, el alumno del que queremos obtener su nota en esta pregunta
	 *  @return true si todas las opciones marcadas con las del alumno coinciden
	 *  con las del profesor, false en caso contrario
	 */
	public float obtenerNotaAlumno(Alumno alumno){
		
		for(int i = 0; this.respuestas.size() > i ;i++){
			if(this.respuestas.get(i).getAlumno().equals(alumno)){ // comprueba cual es la respuesta de ese alumno
					if(corregirRespuesta(respuestas.get(i)) == true){ //si la respuesta es correcta, suma la puntuacion

						return puntuacion;
					}else {
					
						if(falloResta == true){  //si la respuesta es incorrecta, penaliza Resta o bien no penaliza (devuelve 0)
							return 0 - resta;
						}else{
							
							return 0;
						}
					}
			}
		}
		
		return 0; // en caso de no encontrar la respuesta devuelve 0
	}
	
	/**
	 *  Funcion que indica si una respuesta es correcta
	 *  
	 *  @param respuesta, la respuesta que queremos corregir
	 *  @return true si todas las opciones marcadas con las del alumno coinciden
	 *  con las del profesor, false en caso contrario
	 */
	public boolean corregirRespuesta(Respuesta respuesta){
		int opsCorrectas = 0;
		for(int i = 0; respuestaProf.getOpciones().size() > i; i++){  //Recorremos las opciones de la respuesta del profesor
			for(int j = 0; respuesta.getOpciones().size() > j; j++){ 
				if(respuestaProf.getOpciones().size() == respuesta.getOpciones().size()) // Comprobamos que hayan dado mismo numero de opciones
					// Comprobamos que para cada opcion de la respuesta del profesor haya una igual del alumno
					if(respuestaProf.getOpciones().get(i) == respuesta.getOpciones().get(j)){ 
						opsCorrectas++;
					}
			}
		}
		if(opsCorrectas == respuestaProf.getOpciones().size()){
			return true;
		} else{
			return false;
		}
	}
}
	
