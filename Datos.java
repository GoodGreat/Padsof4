package paneles;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Datos extends JPanel{
	private JLabel etiquetaNia, etiquetaPassword;
	private JTextField textNia, textPassword;
	
	public Datos(){
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		
		//Creamos nuestros componentes
		etiquetaNia = new JLabel("NIA: ");
		etiquetaPassword = new JLabel ("PASSWORD: ");
		textNia = new JTextField("<Nia>", 20);
		textPassword = new JTextField("<Password>", 20);
		
		//Ponemos la izquierda de la etiqueta del NIA a 5 pixeles de la izquierda del contenedor 
		layout.putConstraint(SpringLayout.WEST, etiquetaNia, 5, SpringLayout.WEST, this);
		
		//Distanciamos 5 pixeles el NORTE de la etiqueta del NIA del NORTE del contenedor
		layout.putConstraint(SpringLayout.NORTH, etiquetaNia, 5, SpringLayout.NORTH, this);
		
		//Distanciamos 5 pixeles el NORTE de la etiqueta Password del SUR de la etiqueta Nia
		layout.putConstraint(SpringLayout.NORTH, etiquetaPassword, 5, SpringLayout.SOUTH, etiquetaNia);
	}
}
