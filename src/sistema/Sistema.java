package sistema;

import java.io.*;
import java.util.*;
import asignatura.*;
import ejercicio.*;
import es.uam.eps.padsof.emailconnection.*;

/**
 * La clase sistema es la principal de nuestro proyecto. Gestiona, por asi decirlo, la funcionalidad general de la aplicacion. 
 * En esta clase se guardan la lista de solicitudes, expulsiones, asignaturas y alumnos. Tambien desde esta clase
 * se cargaran y guardaran los datos que en ella se encuentren.
 * Principalmente, esta clase será la utilizada por los profesores.
 * 
 * @author Álvaro Martinez de Navascues y Alejandro Martin Climent
 */
public class Sistema implements Serializable {
	private static final long serialVersionUID = 1L;
	private static Sistema instancia = null;		//Singleton Pattern
	private Alumno alumnoLogueado;
	private final String loginProf = "Profesor";
	private final String passwordProf = "profeduudle";
	private List<Asignatura> asignaturas;
	private List<Solicitud> solicitudes; 
	private List<Alumno> alumnos;
	private List<Expulsion> expulsiones;
	private boolean isProf;
	
	/**
	 * Constructor de la clase Sistema. Inicializa los arrays de Alumnos y Asignaturas
	 * @author Alejandro Martin Climent 
	 */
	private Sistema(){
		this.alumnoLogueado = null;
		this.asignaturas = new ArrayList<Asignatura>();
		this.alumnos = new ArrayList<Alumno>();
		this.solicitudes = new ArrayList<Solicitud>();
		this.expulsiones = new ArrayList<Expulsion>();
	}
	
	/**
	 * Este metodo crea una instancia con el constructor privado de Sistema
	 * 
	 * @author Alvaro Martinez de Navascues
	 * @return Devuelve la instancia creada de Sistema
	 */
	public static Sistema getInstance(){
		if (instancia == null){
			instancia = new Sistema();
			return instancia;
		}else{
			return instancia;
		}
	}
	
	/**
	 * Este metodo destruye la instancia ya creada de Sistema
	 * @author Alejandro Martin Climent
	 */
	public static void destroyInstance(){
		instancia = null;
	}
	
	/**
	 *  Getter del atributo loginProf
	 *  
	 *  @author Álvaro Martinez de Navascues
	 *  @return loginProf, cadena especial con el que se logueara un usuario que quiera tener permisos
	 */
	public String getloginProf(){
		return this.loginProf;
	}
	
	/**
	 *  Getter del atributo passwordProf
	 *  
	 *  @author Alejandro Martin Climent
	 *  @return passwordProf, contrasena especial con la que se logueara un usuario que quiera tener permisos
	 */
	public String getpasswordProf(){
		return this.passwordProf;
	}
	
	/**
	 *  Getter del atributo isProf
	 *  
	 *  @author Alvaro Martinez de Navascues
	 *  @return isProf, booleano que controla si el usuario se ha logueado como alumno o profesor
	 */
	public boolean isProf(){
		return this.isProf;
	}
	
	/**
	 * Getter del array de Alumnos
	 * 
	 * @author Alejandro Martin Climent
	 * @return Lista de alumnos (de forma inmodificable)
	 */
	public List<Alumno> getAlumnos(){
		return this.alumnos;
	}
	
	/**
	 * Getter del array de Asignaturas
	 * 
	 * @author Álvaro Martinez de Navascues
	 * @return Lista de Asignaturas (de forma inmodificable)
	 */
	public List<Asignatura> getAsignaturas(){
		return this.asignaturas;
	}
	
	/**
	 * Getter de la lista de Solicitudes
	 * 
	 * @author Alvaro Martinez de Navascues
	 * @return Lista de Solicitudes
	 */
	public List<Solicitud> getSolicitudes(){
		return this.solicitudes;
	}
	
	/**
	 * Getter de la lista de Expulsiones
	 * 
	 * @author Alejandro Martin Climent
	 * @return Lista de Expulsiones
	 */
	public List<Expulsion> getExpulsiones(){
		return this.expulsiones;
	}
	
	/**
	 * Setter del array de asignaturas
	 * 
	 * @author Alejandro Martin Climent
	 * @param asignaturas. Lista de Asignaturas
	 */
	public void setAsignaturas(List<Asignatura> asignaturas){
		this.asignaturas = asignaturas;
	}
	
	/**
	  * Metodo de login para que los usuarios puedan acceder al sistema
	  * 
	  * @author Alejandro Martin Climent  
	  * @param numA. Se refiere al NIA del alumno
	  * @param contr. Se refiere a la contraseña del alumno
	  * @return passwordProf, contrasena especial con la que se logueara un usuario que quiera tener permisos
	 */
	public boolean log_in(String numA, String contr){
		if(numA.equals(this.loginProf) && contr.equals(this.passwordProf)){
			this.isProf = true;
			System.out.println("Accediendo a la aplicacion como PROFESOR");
			return true;			
		}else{
			this.isProf = false;
			for (Alumno alum: this.alumnos){				//for(Recorrer arraylist para ver si algun alumno coincide con el login )
				if (alum.getNumA().equals(numA) && alum.getPassword().equals(contr)){
					System.out.println("Accediendo a la aplicacion como " + alum.getNombre() + " " + alum.getApellido());
					this.setAlumnoLogueado(alum);
					return true;
				}
			}
			return false;
		}
	}
	
	/**
	 * Este metodo crea una asignatura y la añade al array de Asignaturas
	 * 
	 * @author Álvaro Martinez de Navascues
	 * @param nombre. Nombre de la asignatura
	 * @param visible. Booleano que determina la visibilidad de la asignatura
	 * @return booleano. Devuelve true si la asignatura se añadio correctamente. 'false' en caso contrario
	 */
	public boolean crearAsignatura(String nombre, boolean visible){
		if (this.isProf == true){
			Asignatura asignatura = new Asignatura(nombre, visible);
			return this.asignaturas.add(asignatura);
		}else{
			return false;
		}
	}
	
	/**
	 * Este metodo crea un alumno y lo añade al array de Alumno
	 * 
	 * @author Alejandro Martin Climent
	 * @param nombre. Nombre del alumno
	 * @param apellido. Apellido del alumno
	 * @param correo. Correo del alumno
	 * @param numA. Numero de identificacion del alumno
	 * @param password. Contrasenia del alumno
	 * @return booleano. Devuelve true si el alumno se añadio correctamente. 'false' en caso contrario
	 */
	public boolean crearAlumno(String nombre, String apellido, String correo, String numA, String password){
		if (this.isProf == true){
			Alumno alumno = new Alumno(nombre, apellido, correo, numA, password);
			return this.alumnos.add(alumno);
		}else{
			return false;
		}
	}

	/**
	 * Este metodo crea un tema y lo añade al array de una Asignatura
	 * 
	 * @author Álvaro Martinez de Navascues
	 * @param asignatura. Asignatura a la cual pertenece el tema
	 * @param nombre. Nombre del tema
	 * @param visible. Booleano que determina la visibilidad del tema
	 * @return booleano. Devuelve true si el tema se añadio correctamente. 'false' en caso contrario
	 */
	public boolean crearTema(Asignatura asignatura, String nombre, boolean visible){
		if (this.isProf == true){
			Tema tema = new Tema(nombre, visible);
			return asignatura.aniadirTema(tema);
		}else{
			return false;
		}
	}
	
	/**
	 * Este metodo crea un ejercicio y lo añade a un tema
	 * 
	 * @author Alejandro Martin Climent
	 * @param tema. Tema en el que se quiere añadir el ejercicio
	 * @param nombre. Nombre del ejercicio
	 * @param peso. Peso del ejercicio con respecto al total de la nota
	 * @param anyoFin. Año en el que expira el ejercicio
	 * @param mesFin. Mes en el que expira el ejercicio
	 * @param diaFin. Dia en el que expira el ejercicio
	 * @param horasFin. Hora en la que expira el ejercicio
	 * @param minsFin. Minuto en el que expira el ejercicio
	 * @param anyoIni. Año de comienzo de la actividad
	 * @param mesIni. Mes de comienzo de la actividad
	 * @param diaIni. Dia de comienzo de la actividad
	 * @param horaIni. Hora de comienzo de la actividad
	 * @param minIni. Minuto de comienzo de la actividad
	 * @param visible. Booleano que determina la visibilidad del ejercicio
	 * @return booleano. Devuelve true si el ejercicio se añadio correctamente. 'false' en caso contrario
	 */
	public boolean crearEjercicio(Tema tema, String nombre, float peso, int anyoFin, int mesFin, int diaFin, int horasFin, int minsFin,
			int anyoIni, int mesIni, int diaIni, int horaIni, int minIni, boolean visible){		
		if (this.isProf == true){
			Ejercicio ejercicio = new Ejercicio(nombre, peso, anyoFin, mesFin, diaFin, horasFin, minsFin, anyoIni, mesIni, diaIni, horaIni, minIni, visible);
			return tema.aniadirEjercicio(ejercicio);
		}else{
			return false;
		}
	}

	/**
	 * Este metodo crea un apunte y lo añade a un tema
	 * 
	 * @author Álvaro Martinez de Navascues
	 * @param tema. Tema en el que se quiere añadir el apunte
	 * @param titulo. Titulo del apunte
	 * @param visible. Booleano que determina la visibilidad del apunte
	 * @param contenido. Contenido del apunte
	 * @return booleano. Devuelve true si el apunte se añadio correctamente. 'false' en caso contrario
	 */
	public boolean crearApunte(Tema tema, String titulo, boolean visible, String contenido){
		if (this.isProf == true){
			Apunte apunte = new Apunte(titulo, visible, contenido);
			return tema.aniadirApunte(apunte);
		}else{
			return false;
		}
	}	
	
	/**
	 * Este metodo permite a los profesores expulsar a alumnos de asignaturas
	 * 
	 * @author Alejandro Martin Climent
	 * @param alumno. Alumno que se quiere expulsar
	 * @param asignatura. Asignatura de la cual se quiere expulsar al alumno
	 * @return booleano. Devuelve true si el alumno se expulso correctamente. 'false' en caso contrario
	 */
	public boolean expulsarAlumno(Alumno alumno, Asignatura asignatura){
		if (this.isProf == true){	
			Expulsion expulsion = new Expulsion(alumno, asignatura);
			if (asignatura.expulsarAlumno(alumno) && alumno.expulsarAlumno(asignatura)){
				try{
					this.notificarPorEmail(alumno, "Expulsion", "Has sido expulsado de " + asignatura.getNombre());
				}catch (Exception e){
					System.out.println("Error FailedInternetConnection");
				}
				return this.expulsiones.add(expulsion);
			}
		}
		return false;
	}

	/**
	 * Este metodo permite a los profesores dar por finalizada una expulsion de un alumno
	 * 
	 * @author Alejandro Martin Climent
	 * @param expulsion. Expulsion que se quiere revocar
	 * @return booleano que determina si se revoco con exito la expulsion o no
	 */
	public boolean revocarExpulsion(Expulsion expulsion){
		if (this.isProf == true){
			expulsion.revocarExpulsion();
			try{
				this.notificarPorEmail(expulsion.getAlumno(), "Expulsion", "Has sido readmitido en " + expulsion.getAsignatura().getNombre());
			}catch (Exception e){
				System.out.println("Error FailedInternetConnection");
			}
			return this.expulsiones.remove(expulsion);
		}
		return false;
	}

	/**
	 * Este metodo crea una solicitud de un alumno a una asignatura. La añade tanto al array de solicitudes que hay en la clase Sistema, como
	 * al que hay en la clase Alumno
	 * 
	 * @author Álvaro Martinez de Navascues
	 * @param asignatura. Asignatura en la cual se quiere matricular
	 * @return booleano. Devuelve true si es el alumno el que ha creado la solicitud correctamente. 'false'  en caso contrario.
	 */
	public boolean crearSolicitud(Asignatura asignatura){
		boolean yaSolicitado = false;
		if (this.isProf == false){ 			//Solo los alumnos pueden crear solicitudes
			for (Asignatura asigAux: this.getAlumnoLogueado().getAsignaturas()){
				if (asigAux.equals(asignatura)){
					yaSolicitado = true;
				}
			}
			//Si el alumno ya esta en esa asignatura, no podra solicitar otra vez matricularse
			if (yaSolicitado == false){
				Solicitud solicitud = new Solicitud(this.getAlumnoLogueado(), asignatura);
				if (this.getAlumnoLogueado().crearSolicitud(solicitud)){
					return this.solicitudes.add(solicitud);
				}
			}
		}
		return false;
	}
	
	/**
	 * Este metodo añade un alumno al array de Alumno
	 * 
	 * @author Alejandro Martin Climent
	 * @param alumno. Alumno que queremos aniadir al sistema
	 * @return booleano. Devuelve 'true' si el alumno se ha añadido correctamente. 'false' en caso contrario
	 */
	public boolean aniadirAlumno(Alumno alumno){
		if (this.isProf == true){
			return this.alumnos.add(alumno);
		}else{
			return false;
		}
	}	
	
	/**
	 * Mediante este metodo, los profesores podran consultar las notas de un alumno en una asignatura
	 * 
	 * @author Álvaro Martinez de Navascues
	 * @param alumno. Alumno del cual se quieren saber sus calificaciones
	 * @param asignatura. Asignatura de la cual queremos saber las calificaciones del alumno
	 * @return float. Devuelve la nota media del alumno en esa asignatura
	 */
	public float consultarCalificacionesAlumno(Alumno alumno, Asignatura asignatura){
		if (this.isProf == true){
			return asignatura.consultarCalificacionesAlumno(alumno);			
		}else{
			return 0;
		}
	}
	
	/**
	 * Mediante este metodo, los alumnos logueados podran consultar su nota en una asignatura
	 * 
	 * @author Alejandro Martin Climent
	 * @param asignatura. Asignatura de la cual el alumno quiere saber sus calificaciones
	 * @return float. Devuelve la nota media del alumno en esa asignatura
	 */
	public float consultarCalificacionesPropias(Asignatura asignatura){
		if (this.isProf == false){
			System.out.println(asignatura.consultarCalificacionesAlumno(this.getAlumnoLogueado()));
			return asignatura.consultarCalificacionesAlumno(this.getAlumnoLogueado());
		}else{
			return 0;
		}
	}

	/**
	 * Getter del atributo alumnoLogueado
	 * 
	 * @author Alejandro Martin Climent
	 * @return Alumno. Devuelve el alumno logueado, o null en caso de que sea un profesor el que esta logueado
	 */
	public Alumno getAlumnoLogueado() {
		return this.alumnoLogueado;
	}

	/**
	 * Setter del atributo alumnoLogueado
	 * 
	 * @author Álvaro Martinez de Navascues
	 * @param alumnoLogueado. Alumno que se ha logueado en la aplicacion
	 */
	public void setAlumnoLogueado(Alumno alumnoLogueado) {
		this.alumnoLogueado = alumnoLogueado;
	}
	
	/**
	 * Mediante este metodo, los profesores aceptan a alumnos en asignaturas. Este metodo llama al homonimo de la clase Solicitud
	 * 
	 * @author Alejandro Martin Climent
	 * @param solicitud. Solicitud que se quiere aceptar
	 * @return booleano. Devuelve true si la solicitud se acepto correctamente. 'false' en caso contrario
	 */
	public boolean aceptarSolicitud(Solicitud solicitud){
		if (this.isProf == true){
			solicitud.aceptarSolicitud();
			try{
				this.notificarPorEmail(solicitud.getAlumno(), "Solicitud", "Has sido aceptado");
			}catch (Exception e){
				System.out.println("Error FailedInternetConnection");
			}
			return this.solicitudes.remove(solicitud);
		}else{
			return false;
		}
	}
	
	/**
	 * Mediante este metodo, los profesor deniegan la solicitud de los alumnos para matricularse.
	 * 
	 * @author Álvaro Martinez de Navascues
	 * @param solicitud. La solicitud que se quiere denegar
	 * @return booleano. Devuelve true si la solicitud se denego correctamente. 'false' en caso contrario
	 */
	public boolean denegarSolicitud(Solicitud solicitud){
		if (this.isProf == true){
			solicitud.denegarSolicitud();
			try{
				this.notificarPorEmail(solicitud.getAlumno(), "Solicitud", "Has sido rechazado");
			}catch (Exception e){
				System.out.println("Error FailedInternetConnection");
			}
			return this.solicitudes.remove(solicitud);
		}else{
			return false;
		}
	}
	
	/**
	 * Este metodo se encarga de leer los datos de Alumnos de un fichero, e introducirlos al sistema
	 * 
	 * @author Álvaro Martinez de Navascues
	 * @param archivo. Ruta del fichero de donde se quieren leer los datos
	 * @throws IOException Fallo de INPUT OUTPUT
	 * @throws ClassNotFoundException Fallo de clase no encontrada
	 */
	public void leerDatosAlumno(String archivo) throws IOException, ClassNotFoundException{
		// Abrimos el fichero
		FileInputStream fstream = new FileInputStream(archivo);
		// Conseguimos el input del fichero que hemos abierto
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String strLine;
		// Leemos el archivo linea por linea
		while ((strLine = br.readLine()) != null) {
			String[] tokens = strLine.split(";");
			String nombre = tokens[0];
			String apellido = tokens[1];
			String email = tokens[2];
			String numA = tokens[3];
			String contrasenia = tokens[4];
			//Se anulan los alumnos con correo no valido
			if (EmailSystem.isValidEmailAddr(email) == true){
				Alumno alumno = new Alumno(nombre, apellido, email, numA, contrasenia);
				this.alumnos.add(alumno);
			}else{
				System.out.println("Alumno denegado, correo erroneo");
			}
		}
		// Cerramos el input
		in.close();
	}

	/**
	 * Este metodo se encarga de guardar el objeto Sistema en un archivo, de forma Serializable(se guarda en formato de bytes) 
	 * 
	 * @author Alejandro Martin Climent
	 * @param archivo. Ruta del fichero en el que se quiere guardar el objeto
	 * @throws FileNotFoundException Fallo de Fichero no encontrado
	 * @throws IOException Fallo de INPUT o OUTPUT
	 */
	public void guardarDatosSistema(String archivo) throws FileNotFoundException, IOException {
			//Antes de guardar el sistema, dejaremos el login de profesor en false
			this.isProf = false;
			this.alumnoLogueado = null;
			//Abrimos el fichero
			File file = new File(archivo);
			FileOutputStream fout = new FileOutputStream(file);
			//Escribimos en el fichero la informacion del objeto
			ObjectOutputStream oos = new ObjectOutputStream(fout);
			oos.writeObject(this);
			//Cerramos el fichero
			oos.close();
	}
	
	/**
	 * Este metodo carga el objeto sistema desde un fichero en formato de bytes
	 * 
	 * @author Álvaro Martinez de Navascues
	 * @param archivo. Ruta del fichero del cual se quiere cargar el objeto Sistema
	 * @throws IOException Fallo de INPUT o OUTPUT 
	 * @throws ClassNotFoundException Fallo de clase no encontrada
	 */
	public void cargarDatosSistema(String archivo) throws IOException, ClassNotFoundException{
		Sistema sistema = null;
			//Abrimos el fichero
			FileInputStream fin = new FileInputStream(archivo);
			//Creamos el objeto donde vamos a leer la informacion
			ObjectInputStream ios = new ObjectInputStream(fin);
			sistema = (Sistema) ios.readObject();
			//Cerramos el input
			ios.close();
			this.asignaturas = sistema.asignaturas;
			this.alumnoLogueado = sistema.alumnoLogueado;
			this.solicitudes = sistema.solicitudes;
			this.alumnos = sistema.alumnos;
			this.expulsiones = sistema.expulsiones;
			this.isProf = sistema.isProf;
	}
	
	/**
	 * Este metodo se encarga de crear una pregunta y añadirla a un ejercicio
	 * 
	 * @author Alejandro Martin Climent
	 * @param ejercicio. Ejercicio al que se quiere añadir la pregunta
	 * @param enunciado. Enunciado de la pregunta
	 * @param puntuacion. Puntuacion de la pregunta
	 * @param falloResta. Booleano que determina si fallar la pregunta resta puntuacion ('true' = SI RESTA)
	 * @param resta. Determina la cantidad de puntuacion que se resta al fallar la pregunta
	 * @param tipo. Tipo de pregunta (multiple, booleana, unica, libre...)
	 * @return booleano. Devuelve 'true' si la pregunta se añadio correctamente. 'false' en caso contrario
	 */
	public boolean crearPregunta(Ejercicio ejercicio, String enunciado, float puntuacion, boolean falloResta, float resta, String tipo){ 
		if( tipo == "Libre" || tipo == "libre"){
			Pregunta preg = new PreguntaLibre(enunciado, puntuacion, falloResta, resta, tipo);
			return ejercicio.aniadirPregunta(preg);
		} else if( tipo == "Booleana" || tipo == "booleana"){
			Pregunta preg = new PreguntaBooleana(enunciado, puntuacion, falloResta, resta, tipo);
			return ejercicio.aniadirPregunta(preg);
		}else if( tipo == "Unica" || tipo == "unica"){
			Pregunta preg = new PreguntaUnica(enunciado, puntuacion, falloResta, resta, tipo);
			return ejercicio.aniadirPregunta(preg);
		}else if( tipo == "Multipregunta" || tipo == "multipregunta"){
			Pregunta preg = new Multipregunta(enunciado, puntuacion, falloResta, resta, tipo);
			return ejercicio.aniadirPregunta(preg);
		} else {
			System.out.println("Fallo");
			return false;
		}
	}
	
	/**
	 * Este metodo envia emails a alumnos para notificarles de alguna noticia
	 * 
	 * @author Alvaro Martinez de Navascues
	 * @param alumno. Alumno que se quiere notificar
	 * @param asunto. Asunto del correo
	 * @param cuerpo. Mensaje del correo
	 * @throws InvalidEmailAddressException Fallo de Email no valido
	 * @throws FailedInternetConnectionException Fallo la conexion
	 */
	public void notificarPorEmail(Alumno alumno, String asunto, String cuerpo) throws InvalidEmailAddressException, FailedInternetConnectionException{
			EmailSystem.send(alumno.getCorreo(), asunto, cuerpo);
	}

}
