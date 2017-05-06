package controladores;

import java.awt.event.*;
import javax.swing.JOptionPane;
import paneles.*;
import sistema.*;
import ventanas.*;

public class ControlVistaLogin implements ActionListener{
	private VistaLogin vista;
	private Sistema sistema;
	private FramePrincipal ventana_principal;
	
	/**
	 * Constructor del controlador de la Vista LOGIN
	 * @author Álvaro Martinez de Navascues
	 * @param vista. Panel que ve el usuario
	 * @param sistema. Clase principal de nuestro proyecto
	 */
	public ControlVistaLogin(VistaLogin vista, FramePrincipal ventana_principal){
		this.vista = vista;
		this.sistema = Sistema.getInstance();
		this.ventana_principal = ventana_principal;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		// El primer paso es validar lo introducido por el usuario
		if (this.vista.getNia().equals("") || this.vista.getPassword().equals("")) {
			JOptionPane.showMessageDialog(this.vista, "Es obligatorio rellenar ambos campos", "Error",
					JOptionPane.ERROR_MESSAGE);
			
		} else if (this.sistema.log_in(this.vista.getNia().getText(), String.valueOf(this.vista.getPassword().getPassword())) == true
				&& this.sistema.isProf() == true) {
			JOptionPane.showMessageDialog(this.vista, "Entrando a la aplicacion como profesor", "LOG-IN",
					JOptionPane.INFORMATION_MESSAGE);
			this.vista.getNia().setText("");
			this.vista.getPassword().setText("");
			this.ventana_principal.mostrarVistaPrinciProf();
					
		} else if (this.sistema.log_in(this.vista.getNia().getText(), String.valueOf(this.vista.getPassword().getPassword())) == true
				&& this.sistema.isProf() == false) {
			JOptionPane.showMessageDialog(this.vista, "Bienvenido " + this.sistema.getAlumnoLogueado().getNombre(),
					"LOG-IN", JOptionPane.INFORMATION_MESSAGE);
			/*Se abrira la ventana del estudiante*/
			this.vista.getNia().setText("");
			this.vista.getPassword().setText("");
			this.ventana_principal.mostrarVistaPrinciEstudiante();
			
		} else {
			JOptionPane.showMessageDialog(this.vista,
					"El Nia o la Contraseña no se encuentran en la base de Datos. Pruebe de nuevo", "Error", JOptionPane.ERROR_MESSAGE);
			this.vista.getNia().setText("");
			this.vista.getPassword().setText("");
		}		
	}
}
