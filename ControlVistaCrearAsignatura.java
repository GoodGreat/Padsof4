package controladores;

import java.awt.event.*;
import javax.swing.JOptionPane;
import paneles.*;
import sistema.*;
import ventanas.*;

public class ControlVistaCrearAsignatura implements ActionListener{
	private VistaCrearAsignatura vista;
	private Sistema sistema;
	private FramePrincipal ventana_principal;
	
	/**
	 * Constructor del controlador de la Vista LOGIN
	 * @author Álvaro Martinez de Navascues
	 * @param vista. Panel que ve el usuario
	 * @param sistema. Clase principal de nuestro proyecto
	 */
	public ControlVistaCrearAsignatura(VistaCrearAsignatura vista, FramePrincipal ventana_principal){
		this.vista = vista;
		this.sistema = Sistema.getInstance();
		this.ventana_principal = ventana_principal;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		// El primer paso es validar lo introducido por el usuario
		if (this.vista.getNombre().equals("")) {
			JOptionPane.showMessageDialog(this.vista, "Es obligatorio rellenar ambos campos", "Error",
					JOptionPane.ERROR_MESSAGE);
		} else if (sistema.crearAsignatura(this.vista.getNombre(), this.vista.getComboBoxSelected()) == false){
			JOptionPane.showMessageDialog(this.vista, "Error al crear la asignatura", "Error",
					JOptionPane.ERROR_MESSAGE);
		}else{
			JOptionPane.showMessageDialog(this.vista, "La asignatura " + this.vista.getNombre() + " ha sido creada con exito", "CREACION DE ASIGNATURA", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}