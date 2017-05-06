package controladores;

import java.awt.event.*;
import javax.swing.JOptionPane;

import asignatura.Asignatura;
import paneles.*;
import sistema.*;

public class ControlVistaCambiarAsignatura implements ActionListener{
	private VistaCambiarAsignatura vista;
	private Sistema sistema;
	private VistaPrincipalProf vista_principalProf;
	private Asignatura asignatura;
	
	/**
	 * Constructor del controlador de la Vista LOGIN
	 * @author Álvaro Martinez de Navascues
	 * @param vista. Panel que ve el usuario
	 * @param sistema. Clase principal de nuestro proyecto
	 */
	public ControlVistaCambiarAsignatura(VistaCambiarAsignatura vista, VistaPrincipalProf vista_principalProf, Asignatura asignatura){
		this.vista = vista;
		this.sistema = Sistema.getInstance();
		this.vista_principalProf = vista_principalProf;
		this.asignatura = asignatura;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		// El primer paso es validar lo introducido por el usuario
		if()
		if (this.vista.getNombre().equals(asignatura.getNombre()) == false) {
			if(this.vista.getNombre().equals("") == false)
			this.asignatura.setNombre(this.vista.getNombre());
		} else if
		
		}
	}
}