package ventanas;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class VistaLogin extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField nia;
	private JTextField password;
	private JButton botonLogin;
	
	/**
	 * Constructor de la vista LOGIN
	 */
	public VistaLogin (){
		super("E-DUUDLE");
		//Layout
		this.setLayout(new BorderLayout());
		
		//Creamos los componentes necesarios para la vista de LOGIN
		JLabel etiquetaNia = new JLabel("NIA: ");
		this.nia = new JTextField(20);
		JLabel etiquetaPassword = new JLabel("PASSWORD: ");
		this.password = new JTextField(20);
		this.botonLogin = new JButton("LOG-IN");
		
		//Añadimos los componentes
		this.add(etiquetaNia, BorderLayout.CENTER);
		this.add(etiquetaPassword, BorderLayout.CENTER);
		this.add(this.nia);
		this.add(this.password);
		this.add(this.botonLogin, BorderLayout.PAGE_END);
	}
	
	/**
	 * Metodo que sirve para aniadir un controlador al boton de Login
	 * @author Álvaro Martinez de Navascues
	 * @param controlador. Controlador que se quiere asignar
	 */
	public void setControlador(ActionListener controlador){
		this.botonLogin.addActionListener(controlador);
	}
	
	/**
	 * Metodo que sirve para retornar el texto contenido en el NIA
	 * @author Alejandro Martin Climent
	 * @return String. El NIA del alumno/Login del profesor
	 */
	public String getNia(){
		return this.nia.getText();
	}
	
	/**
	 * Metodo que sirve para retornar el texto contenido en la PASSWORD
	 * @author Alvaro Martinez de Navascues
	 * @return String. La password del alumno/profesor
	 */
	public String getPassword(){
		return this.password.getText();
	}
	
}
