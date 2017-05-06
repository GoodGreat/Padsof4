package ejercicio;

import java.util.List;

import sistema.Alumno;

/**
 * Clase PreguntaUnica, que implementa las preguntas en las que se le presentan
 * a los alumnos distintas opciones y solo una de ellas sera la correcta
 * 
 * @author Alejandro Martin Climent y Alvaro Martinez de Navascues
 *
 */

public class PreguntaUnica extends Pregunta{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 *  Constructor de la clase PreguntaUnica
	 * 
	 * @param enunciado, el enunciado de la pregunta
	 * @param puntuacion, la puntuacion que suma en caso de estar correctamente solucionada
	 * @param falloResta, indica si al fallar una pregunta penalizara o no
	 * @param resta, la nota que resta al ejercicio en caso de solucionar mal una pregunta
	 * @param tipo, el tipo de pregunta que es esta pregunta
	 */
	public PreguntaUnica(String enunciado, float puntuacion, boolean falloResta, float resta, String tipo){
		super(enunciado, puntuacion, falloResta, resta, tipo);
	}
	
	/**
	 *  Getter del enunciado
	 *  
	 *  @return enunciado, el enunciado de la pregunta
	 */
	public String getEnunciado() {
		return super.getEnunciado();
	}
	
	/**
	 *  Getter de la puntuacion
	 *  
	 *  @return puntuacion, la puntuacion de la pregunta
	 */
	public float getPuntuacion() {
		return super.getPuntuacion();
	}
	
	/**
	 *  Getter de falloresta
	 *  
	 *  @return falloresta, atributo que indica si el fallo en la pregunta puntua negativo
	 */
	public boolean getFalloResta() {
		return super.getFalloResta();
	}
	
	/**
	 *  Getter de resta
	 *  
	 *  @return resta, puntuacion total que penaliza fallar una pregunta
	 */
	public float getResta() {
		return super.getResta();
	}
	
	/**
	 *  Getter del tipo de pregunta
	 *  
	 *  @return tipo, una de los 4 tipos de preguntas posibles
	 */
	public String getTipo() {
		return super.getTipo();
	}
	
	/**
	 *  Getter del Ejercicio al que pertenece una pregunta de tipo Unica
	 *  
	 *  @return Ejercicio, el ejercicio al que pertenece esta pregunta
	 */
	public Ejercicio getEjercicioSuperior() {
		return super.getEjercicioSuperior();
	}

	/**
	 *  Setter del Ejercicio al que pertenece una pregunta de tipo Unica
	 *  
	 *  @return Ejercicio, el ejercicio al que pertenece esta pregunta
	 */
	public void setEjercicioSuperior(Ejercicio ejerciciosup) {
		super.setEjercicioSuperior(ejerciciosup);
	}
	
	/**
	 *  Getter del atrbuto respuestaProf
	 *  
	 *  @return respuestaProf, la respuesta que da como solucion un profesor
	 *  a una pregunta planteada por el profesor
	 */
	public Respuesta getRespuestaProf() {
		return super.getRespuestaProf();
	}

	/**
	 *  Setter del atrbuto respuestaProf
	 *  
	 *  @param respuestaProf, la respuesta que da como solucion un profesor
	 *  a una pregunta planteada por el profesor
	 */
	public void setRespuestaProf(RespuestaUnica respuesta){
			super.setRespuestaProf(respuesta);
		}
	
	/**
	 *  Getter de las opciones
	 *  
	 *  @return Opciones de esta pregunta, propuestas por el profesor
	 */
	public List<Opcion> getOpciones() {
		return super.getOpciones();
	}
	
	/**
	 *  Getter de las respuestas de los alumnos
	 *  
	 *  @return Respuestas de los alumnos a esta pregunta
	 */
	public List<Respuesta> getRespuestas() {
		return super.getRespuestas();
	}
	/**
	 *  Funcion que aniade una opcion a una pregunta de tipo Libre
	 *  
	 *  @param opcion, opcion que se quiere aniadir
	 *  @return super.aniadirOpcion(opcion), que devolvera true
	 */
	public boolean pregAniadirOpcion(Opcion opcion){	
		return super.pregAniadirOpcion(opcion);
	}
	
	/**
	 *  Funcion que aniade una respuesta a una pregunta de tipo Unica
	 *  
	 *  @param respuesta, respuestas que se quiere aniadir
	 *  @return booleano a true si se ha podido aniadir o false en caso contrario
	 */
	public boolean pregAniadirRespuesta(Alumno alumno, List<Opcion> opciones){
		for(Respuesta respuestaAux: this.getRespuestas()){
			if(respuestaAux.getAlumno().equals(alumno)){
				return false;
			}
		}
		RespuestaUnica respuesta = new RespuestaUnica(alumno);
		respuesta.resAniadirOpcion(opciones.get(0));
		return this.getRespuestas().add(respuesta);
	}
	
	/**
	 * Funcion que llama a la de la clase superior para corregir una respuesta
	 *  
	 *  @param respuesta, la respuesta que se quiere corregir
	 *  @return true si la solucion es correcta o false en caso contrario
	 */
	public boolean corregirRespuesta(Respuesta respuesta){
		return super.corregirRespuesta(respuesta);
	}
	
	/**
 	 *  Funcion que llama a la de la clase superior para calcular el porcentaje
	 *  de alumnos que contestaron a una pregunta de tipo Unica
	 *  
	 *  @return porcentaje de alumnos que contestaron a la pregunta
	 */
	public float alumnosContestados(){
		return super.alumnosContestados();
	}
	
	/**
	 *  Funcion que llama a la de la clase superior para calcular el porcentaje
	 *  de alumnos que contestaron bien una pregunta de tipo Unica
	 * 
	 *  @return porcentaje de alumnos que contestaron correctamente a la pregunta
	 */
	public float alumnosContestadosCorrecto(){
		return super.alumnosContestadosCorrecto();
	}
	
	/**
	 *  Funcion que llama a la de la clase Pregunta para calcular la nota obtenida por un alumno
	 *  en una pregunta de tipo Unica
	 *  
	 *  @param alumno, el alumno del que queremos obtener su nota en esta pregunta
	 *  @return true si todas las opciones marcadas con las del alumno coinciden
	 *  con las del profesor, false en caso contrario
	 */
	public float obtenerNotaAlumno(Alumno alumno){
		return super.obtenerNotaAlumno(alumno);
	}

}
