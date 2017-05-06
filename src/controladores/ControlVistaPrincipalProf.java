package controladores;

import java.awt.event.*;
import javax.swing.JOptionPane;
import paneles.*;
import sistema.Sistema;
import ventanas.*;

public class ControlVistaPrincipalProf implements ActionListener{
	private VistaPrincipalProf  vista;
	private FramePrincipal ventana_principal;
	
	/**
	 * Constructor del controlador de la Vista LOGIN
	 * @author Álvaro Martinez de Navascues
	 * @param vista. Panel que ve el usuario
	 * @param sistema. Clase principal de nuestro proyecto
	 */
	public ControlVistaPrincipalProf (VistaPrincipalProf  vista, FramePrincipal ventana_principal){
		this.vista = vista;
		this.ventana_principal = ventana_principal;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource().equals(this.vista.getBotonCrearAsig())){
			this.vista.mostrarVistaCrearAsignatura();
		}else if (event.getSource().equals(this.vista.getBotonGestionSolicitudes())){
			//JOptionPane.showMessageDialog(this.vista, "Se ha pulsado Gestion Solicitudes", "Error",
			//		JOptionPane.ERROR_MESSAGE);
			this.ventana_principal.mostrarVistaGestionMatriculas();
		}else if (event.getSource().equals(this.vista.getBotonMostrarAsig())){
			JOptionPane.showMessageDialog(this.vista, "Se pulso Mostrar Asig", "Error",
					JOptionPane.ERROR_MESSAGE);
		}else if (event.getSource().equals(this.vista.getBotonRegistrarAlumno())){
			JOptionPane.showMessageDialog(this.vista, "Se pulso Registrar Alumno", "Error",
					JOptionPane.ERROR_MESSAGE);
		}else if (event.getSource().equals(this.vista.getBotonLogout())){
			Sistema.getInstance().log_out();
			this.ventana_principal.mostrarLogin();
		}
	}
}
