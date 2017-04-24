package controladores;

import java.awt.event.*;

import javax.swing.JOptionPane;

import sistema.*;
import ventanas.*;

public class ControlVistaLogin implements ActionListener{
	private VistaLogin vista;
	private Sistema sistema;
	
	/**
	 * Constructor del controlador de la Vista LOGIN
	 * @author Álvaro Martinez de Navascues
	 * @param vista. Panel que ve el usuario
	 * @param sistema. Clase principal de nuestro proyecto
	 */
	public ControlVistaLogin(VistaLogin vista, Sistema sistema){
		this.vista = vista;
		this.sistema = sistema;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		
		//El primer paso es validar lo introducido por el usuario
		if (this.vista.getNia().equals("") || this.vista.getPassword().equals("")){
			JOptionPane.showMessageDialog(this.vista, "Es obligatorio rellenar ambos campos", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		//Preguntar en clase, QUIEN DEBE DAR EL FEEDBACK, HACER COMPROBACIONES EN LA CORRECCION DE LO INTRODUCIDO
		//Realizamos la accion segun se pinche el boton LOG-IN
		this.sistema.log_in(this.vista.getNia(), this.vista.getPassword());
		
		//Mostrar nueva vista
	}

}
