package paneles;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class VistaLogin extends JPanel{
	private static final long serialVersionUID = 1L;
	private JLabel titulo, etiquetaNia, etiquetaPassword;
	private JTextField textNia;
	private JPasswordField textPassword;
	private JButton botonLogin;
	
	/**
	 * Constructor del panel de Login, con Spring Layout y Spring Utilities
	 * @author Alvaro Martinez de Navascues
	 */
	public VistaLogin(){
		SpringLayout layout = new SpringLayout();		
		
		//Layout del JPanel principal
		this.setLayout(layout);	
		
		titulo = new JLabel("BIENVENIDO A E-DUUDLE");
		titulo.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 20));
		this.add(titulo);
		
		//Constraints
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, titulo, 10, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, titulo, 50, SpringLayout.NORTH, this);
		
		//Creamos nuestros componentes
		etiquetaNia = new JLabel("NIA: ");
		etiquetaNia.setFont(new Font("Times New Roman", Font.ITALIC, 14));
		this.add(etiquetaNia);
		textNia = new JTextField(20);
		this.add(textNia);
		
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiquetaNia, -100, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, etiquetaNia, -20, SpringLayout.VERTICAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, textNia, 0, SpringLayout.NORTH, etiquetaNia);
		layout.putConstraint(SpringLayout.WEST, textNia, 6, SpringLayout.EAST, etiquetaNia);
		
		etiquetaPassword = new JLabel ("PASSWORD: ");
		etiquetaPassword.setFont(new Font("Times New Roman", Font.ITALIC, 14));
		this.add(etiquetaPassword);
		textPassword = new JPasswordField(20);
		this.add(textPassword);
		
		layout.putConstraint(SpringLayout.NORTH, etiquetaPassword, 20, SpringLayout.SOUTH, etiquetaNia);
		layout.putConstraint(SpringLayout.EAST, etiquetaPassword, 0, SpringLayout.EAST, etiquetaNia);
		layout.putConstraint(SpringLayout.NORTH, textPassword, 0, SpringLayout.NORTH, etiquetaPassword);
		layout.putConstraint(SpringLayout.WEST, textPassword, 6, SpringLayout.EAST, etiquetaPassword);
		
		botonLogin = new JButton("LOGIN");
		botonLogin.setPreferredSize(new Dimension(150, 50));
		this.add(botonLogin);
		
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, botonLogin, 30, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.SOUTH, botonLogin, -30, SpringLayout.SOUTH, this);
		this.setPreferredSize(new Dimension(400, 150));
	} 
	
	/**
	 * Metodo que sirve para retornar el texto contenido en el NIA
	 * @author Alejandro Martin Climent
	 * @return String. El NIA del alumno/Login del profesor
	 */
	public JTextField getNia(){
		return this.textNia;
	}
	
	/**
	 * Metodo que sirve para retornar el texto contenido en la PASSWORD
	 * @author Alvaro Martinez de Navascues
	 * @return String. La password del alumno/profesor
	 */
	public JPasswordField getPassword(){
		return this.textPassword;
	}
	
	/**
	 * Metodo que sirve para aniadir un controlador al boton de Login
	 * @author Álvaro Martinez de Navascues
	 * @param controlador. Controlador que se quiere asignar
	 */
	public void setControlador(ActionListener controlador){
		this.botonLogin.addActionListener(controlador);
	}
}
