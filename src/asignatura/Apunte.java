package asignatura;

import java.io.Serializable;
import sistema.*;

/**
 * Clase Apunte, contiene los apuntes que consultaran los alumnos para saber
 * resolver los ejercicios
 * 
 * @author Alejandro Martin Climent y Alvaro Martinez de Navascues
 *
 */
public class Apunte implements Serializable {

	private static final long serialVersionUID = 1L;
	private String titulo;
	private boolean visible;
	private String contenido;
	private Tema temaSuperior;

	/**
	 * Constructor de la clase Apunte
	 * 
	 * @author Alejandro Martin Climent
	 * 
	 * @param titulo,
	 *            el titulo de los apuntes
	 * @param visible,
	 *            indica si los apuntes son publicos o privados
	 * @param contenido,
	 *            los apuntes que redacta el profesor
	 */
	public Apunte(String titulo, boolean visible, String contenido) {
		this.titulo = titulo;
		this.visible = visible;
		this.contenido = contenido;
		this.temaSuperior = null;
	}

	/**
	 * Getter del titulo
	 * 
	 * @return titulo del apunte
	 */
	public String getTitulo() {
		return this.titulo;
	}

	/**
	 * Setter del titulo
	 * 
	 * @param titulo,
	 *            titulo del apunte
	 */
	public void setTitulo(String titulo) {
		if (Sistema.getInstance().isProf() == true) {
			this.titulo = titulo;
		}
	}

	/**
	 * Getter del atributo de visibilidad
	 * 
	 * @return Estado de visibilidad: false si es privado y true si es publico
	 */
	public boolean getVisible() {
		return this.visible;
	}

	/**
	 * Getter del tema superior, el tema en el que esta contenido este ejercicio
	 * 
	 * @return tema superior, el tema directamente superior a este,en el que
	 *         esta contenido
	 */
	public Tema getTemaSuperior() {
		return this.temaSuperior;
	}

	/**
	 * Setter del tema superior, el tema principal en el que esta contenido un
	 * subtema
	 * 
	 * @param temaSup. El tema en el que esta contenido
	 */
	public void setTemaSuperior(Tema temaSup) {
		this.temaSuperior = temaSup;
	}

	/**
	 * Pone a true el atributo de visibilidad
	 */
	public void publicarApunte() {
		if (Sistema.getInstance().isProf() == true) {
			for (Alumno alumnoAux : this.getTemaSuperior().getAsignatura().getAlumnos()) {
				try {
					Sistema.getInstance().notificarPorEmail(alumnoAux, "Publicacion Nuevo Apunte",
							"Se ha publicado el apunte " + this.getTitulo() + " en la Asignatura "
									+ this.getTemaSuperior().getAsignatura());
				} catch (Exception e) {
					System.out.println("Error: FailedInternetConnection");
				}
			}
			this.visible = true;
		}

	}

	/**
	 * Pone a false el atributo de visibilidad
	 */
	public void ocultarApunte() {
		if (Sistema.getInstance().isProf() == true) {
			this.visible = false;
		}
	}

	/**
	 * Getter del contenido
	 * 
	 * @return contenido, el contenido del apunte
	 */
	public String getContenido() {
		return this.contenido;
	}

	/**
	 * Setter del contenido dle apunte
	 * 
	 * @param contenido,
	 *            lo redactado por el profesor
	 */
	public void setContenido(String contenido) {
		if (Sistema.getInstance().isProf() == true) {
			this.contenido = contenido;
		}
	}

}
