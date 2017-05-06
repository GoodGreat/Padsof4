package controladores;

import java.awt.event.*;
import javax.swing.JOptionPane;

import asignatura.Asignatura;
import paneles.*;
import sistema.*;
import ventanas.*;

public class ControlVistaCrearTema implements ActionListener{
	private VistaCrearTema vista;
	private Sistema sistema;
	private Asignatura asignatura;
	private FramePrincipal ventana_principal;
	
	/**
	 * Constructor del controlador de la Vista LOGIN
	 * @author Álvaro Martinez de Navascues
	 * @param vista. Panel que ve el usuario
	 * @param sistema. Clase principal de nuestro proyecto
	 */
	public ControlVistaCrearTema(VistaCrearTema vista, FramePrincipal ventana_principal, Asignatura asignatura){
		this.vista = vista;
		this.sistema = Sistema.getInstance();
		this.ventana_principal = ventana_principal;
		this.asignatura = asignatura;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		// El primer paso es validar lo introducido por el usuario
		if (this.vista.getNombre().equals("")) {
			JOptionPane.showMessageDialog(this.vista, "Es obligatorio rellenar ambos campos", "Error",
					JOptionPane.ERROR_MESSAGE);
		} else if (sistema.crearTema(asignatura, this.vista.getNombre(), this.vista.getComboBoxSelected()) == false){
			JOptionPane.showMessageDialog(this.vista, "Error al crear el tema", "Error",
					JOptionPane.ERROR_MESSAGE);
		}else{
			JOptionPane.showMessageDialog(this.vista, "El tema " + this.vista.getNombre() + " ha sido creada con exito", "CREACION DE TEMA", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
