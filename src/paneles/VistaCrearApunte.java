package paneles;

import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;

public class VistaCrearApunte extends JPanel{
	private static final long serialVersionUID = 1L;
	private JLabel titulo, etiquetaNombre, etiquetaVisible, etiquetaContenido;
	private JTextField textNombre, textContenido;
	private JComboBox<String> comboBoxVisible;
	private JButton botonCrearApunte;
	
	/**
	 * Constructor del panel de Crear Asignatura
	 * @author Alvaro Martinez de Navascues
	 */
	public VistaCrearApunte(){
		String opciones[] = {"Visible", "Oculto"};		
		
		//Layout del JPanel principal
		SpringLayout layoutPrincipal = new SpringLayout();
		this.setLayout(layoutPrincipal);
		
		titulo = new JLabel("CREANDO APUNTE");
		titulo.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 20));
		this.add(titulo);
		
		//Constraints
		layoutPrincipal.putConstraint(SpringLayout.HORIZONTAL_CENTER, titulo, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layoutPrincipal.putConstraint(SpringLayout.NORTH, titulo, 30, SpringLayout.NORTH, this);
		
		//Creamos nuestros componentes
		etiquetaNombre = new JLabel("Nombre del Apunte: ");
		this.add(etiquetaNombre);
		textNombre = new JTextField(20);
		this.add(textNombre);
		
		//Constraints
		layoutPrincipal.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiquetaNombre, -100, SpringLayout.HORIZONTAL_CENTER, this);
		layoutPrincipal.putConstraint(SpringLayout.VERTICAL_CENTER, etiquetaNombre, -70, SpringLayout.VERTICAL_CENTER, this);
		layoutPrincipal.putConstraint(SpringLayout.WEST, textNombre, 5, SpringLayout.EAST, etiquetaNombre);
		layoutPrincipal.putConstraint(SpringLayout.NORTH, textNombre, 0, SpringLayout.NORTH, etiquetaNombre);
		
		
		etiquetaContenido = new JLabel("Contenido: ");
		this.add(etiquetaContenido);
		textContenido = new JTextField(100);
		this.add(textContenido);
		
		
		//Constraints
		layoutPrincipal.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiquetaContenido, -100, SpringLayout.HORIZONTAL_CENTER, this);
		layoutPrincipal.putConstraint(SpringLayout.VERTICAL_CENTER, etiquetaContenido, -40, SpringLayout.VERTICAL_CENTER, this);
		layoutPrincipal.putConstraint(SpringLayout.WEST, textContenido, 5, SpringLayout.EAST, etiquetaContenido);
		layoutPrincipal.putConstraint(SpringLayout.NORTH, textContenido, 0, SpringLayout.NORTH, etiquetaContenido);
		
		
		etiquetaVisible = new JLabel("�Oculto o visible?: ");
		this.add(etiquetaVisible);
		comboBoxVisible = new JComboBox<String>(opciones);
		this.add(comboBoxVisible);
		
		//Constraints
		layoutPrincipal.putConstraint(SpringLayout.NORTH, etiquetaVisible, 20, SpringLayout.SOUTH, etiquetaNombre);
		layoutPrincipal.putConstraint(SpringLayout.EAST, etiquetaVisible, 0, SpringLayout.EAST, etiquetaNombre);		
		layoutPrincipal.putConstraint(SpringLayout.WEST, comboBoxVisible, 5, SpringLayout.EAST, etiquetaVisible);
		layoutPrincipal.putConstraint(SpringLayout.NORTH, comboBoxVisible, 0, SpringLayout.NORTH, etiquetaVisible);
		
		botonCrearApunte = new JButton("Crear Apunte");
		this.add(botonCrearApunte);
		
		//Constraints
		layoutPrincipal.putConstraint(SpringLayout.HORIZONTAL_CENTER, botonCrearApunte, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layoutPrincipal.putConstraint(SpringLayout.SOUTH, botonCrearApunte, -30, SpringLayout.SOUTH, this);
		
	} 
	
	/**
	 * Metodo que sirve para retornar el texto contenido en el Nombre de la ASignatura
	 * @author Alejandro Martin Climent
	 * @return String. El nombre de la asignatura
	 */
	public String getNombre(){
		return this.textNombre.getText();
	}
	
	/**
	 * Metodo que sirve para retornar el contenido del apunte
	 * @author Alvaro Martinez de Navascues
	 * @return String. El nombre de la asignatura
	 */
	public String getContenido(){
		return this.textContenido.getText();
	}
	
	/**
	 * Metodo que sirve para retornar el texto contenido en la PASSWORD
	 * @author Alvaro Martinez de Navascues
	 * @return String. La password del alumno/profesor
	 */
	public boolean getComboBoxSelected(){
		if (comboBoxVisible.getSelectedItem().equals("Visible")){
			return true;
		}else{
			return false;
		}		
	}
	
	/**
	 * Metodo que sirve para aniadir un controlador al boton de Crear Asignatura
	 * @author �lvaro Martinez de Navascues
	 * @param controlador. Controlador que se quiere asignar
	 */
	public void setControlador(ActionListener controlador){
		this.botonCrearApunte.addActionListener(controlador);
	}
}