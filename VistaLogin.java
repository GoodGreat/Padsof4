package paneles;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class VistaLogin extends JPanel{
	private static final long serialVersionUID = 1L;
	private JLabel etiquetaNia, etiquetaPassword;
	private JTextField textNia, textPassword;
	private JButton botonLogin;
	
	/**
	 * Constructor del panel de Datos, con Spring Layout
	 * @author Alvaro Martinez de Navascues
	 */
	public VistaLogin(){
		//Panel de etiquetas
		SpringLayout layout = new SpringLayout();	
		JPanel panel_etiquetas = new JPanel();
		panel_etiquetas.setLayout(layout);
		//Panel de botones
		JPanel panel_botones = new JPanel();
		
		//Layout del JPanel principal
		this.setLayout(new BorderLayout());
		
		
		
		//Creamos nuestros componentes
		etiquetaNia = new JLabel("NIA: ", JLabel.TRAILING);
		panel_etiquetas.add(etiquetaNia);
		textNia = new JTextField(15);
		etiquetaNia.setLabelFor(textNia);
		panel_etiquetas.add(textNia);
		
		etiquetaPassword = new JLabel("Password: ", JLabel.TRAILING);
		panel_etiquetas.add(etiquetaPassword);
		textPassword = new JTextField(15);
		etiquetaPassword.setLabelFor(textPassword);
		panel_etiquetas.add(textPassword);
		
		botonLogin = new JButton("LOG-IN");
		
		SpringUtilities.makeCompactGrid(panel_etiquetas, 2, 2, 6, 6, 6, 6);
		botonLogin.setPreferredSize(new Dimension(100,50));
		
		panel_botones.add(botonLogin);
		this.setPreferredSize(new Dimension(400, 150));
		this.add(panel_etiquetas, BorderLayout.CENTER);
		this.add(panel_botones, BorderLayout.SOUTH);
	} 
	
	/**
	 * Metodo que sirve para retornar el texto contenido en el NIA
	 * @author Alejandro Martin Climent
	 * @return String. El NIA del alumno/Login del profesor
	 */
	public String getNia(){
		return this.textNia.getText();
	}
	
	/**
	 * Metodo que sirve para retornar el texto contenido en la PASSWORD
	 * @author Alvaro Martinez de Navascues
	 * @return String. La password del alumno/profesor
	 */
	public String getPassword(){
		return this.textPassword.getText();
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
