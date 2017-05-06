package controladores;

import java.awt.event.*;
import javax.swing.JOptionPane;
import paneles.*;
import sistema.Sistema;
import sistema.Solicitud;
import ventanas.*;

public class ControlGestionMatriculas implements ActionListener{
	private VistaGestionMatriculas  vista;
	private VistaPrincipalProf vista_principalProf;	private Sistema sistema;
	/**
	 * Constructor del controlador de la Vista LOGIN
	 * @author Álvaro Martinez de Navascues
	 * @param vista. Panel que ve el usuario
	 * @param sistema. Clase principal de nuestro proyecto
	 */
	public ControlGestionMatriculas (VistaGestionMatriculas vista, VistaPrincipalProf vista_principalProf){
		this.vista = vista;
		this.vista_principalProf =  vista_principalProf;
		this.sistema = sistema.getInstance();
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
	
		if (event.getSource().equals(this.vista.getBotonAceptarMatricula())){
			
			this.vista_principalProf.mostrarVistaGestionMatriculas();
			
		}else if (event.getSource().equals(this.vista.getBotonDenegarMatricula())){
			
			this.vista_principalProf.mostrarVistaGestionMatriculas();
			
		}else if (event.getSource().equals(this.vista.getBotonVolver())){
			this.vista_principalProf.mostrarVistaPrincipalProf();
		}
	}
	
	
	
}
