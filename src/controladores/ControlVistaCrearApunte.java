package controladores;

import java.awt.event.*;
import javax.swing.JOptionPane;

import asignatura.Asignatura;
import asignatura.Tema;
import paneles.*;
import sistema.*;
import ventanas.*;

public class ControlVistaCrearApunte implements ActionListener{
	private VistaCrearApunte vista;
	private Sistema sistema;
	private Tema tema;
	private FramePrincipal ventana_principal;
	
	/**
	 * Constructor del controlador de la Vista LOGIN
	 * @author Álvaro Martinez de Navascues
	 * @param vista. Panel que ve el usuario
	 * @param sistema. Clase principal de nuestro proyecto
	 */
	public ControlVistaCrearApunte(VistaCrearApunte vista, FramePrincipal ventana_principal, Tema tema){
		this.vista = vista;
		this.sistema = Sistema.getInstance();
		this.ventana_principal = ventana_principal;
		this.tema = tema;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		// El primer paso es validar lo introducido por el usuario
		if (this.vista.getNombre().equals("") || this.vista.getContenido().equals("")) {
			JOptionPane.showMessageDialog(this.vista, "Es obligatorio rellenar todos los campos", "Error",
					JOptionPane.ERROR_MESSAGE);
		} else if (sistema.crearApunte(tema, this.vista.getNombre(), this.vista.getComboBoxSelected(), this.vista.getContenido()) == false){
			JOptionPane.showMessageDialog(this.vista, "Error al crear el tema", "Error",
					JOptionPane.ERROR_MESSAGE);
		}else{
			JOptionPane.showMessageDialog(this.vista, "El tema " + this.vista.getNombre() + " ha sido creada con exito", "CREACION DE TEMA", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}

