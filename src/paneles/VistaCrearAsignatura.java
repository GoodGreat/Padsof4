package paneles;

import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;

public class VistaCrearAsignatura extends JPanel{
	private static final long serialVersionUID = 1L;
	private JLabel titulo, etiquetaNombre, etiquetaVisible;
	private JTextField textNombre;
	private JComboBox<String> comboBoxVisible;
	private JButton botonCrearAsignatura;
	
	/**
	 * Constructor del panel de Crear Asignatura
	 * @author Alvaro Martinez de Navascues
	 */
	public VistaCrearAsignatura(){
		String opciones[] = {"Visible", "Oculta"};		
		
		//Layout del JPanel principal
		SpringLayout layoutPrincipal = new SpringLayout();
		this.setLayout(layoutPrincipal);
		
		titulo = new JLabel("CREANDO ASIGNATURA");
		titulo.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 20));
		this.add(titulo);
		
		//Constraints
		layoutPrincipal.putConstraint(SpringLayout.HORIZONTAL_CENTER, titulo, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layoutPrincipal.putConstraint(SpringLayout.NORTH, titulo, 30, SpringLayout.NORTH, this);
		
		//Creamos nuestros componentes
		etiquetaNombre = new JLabel("Nombre de la Asignatura: ");
		this.add(etiquetaNombre);
		textNombre = new JTextField(20);
		this.add(textNombre);
		
		//Constraints
		layoutPrincipal.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiquetaNombre, -100, SpringLayout.HORIZONTAL_CENTER, this);
		layoutPrincipal.putConstraint(SpringLayout.VERTICAL_CENTER, etiquetaNombre, -70, SpringLayout.VERTICAL_CENTER, this);
		layoutPrincipal.putConstraint(SpringLayout.WEST, textNombre, 5, SpringLayout.EAST, etiquetaNombre);
		layoutPrincipal.putConstraint(SpringLayout.NORTH, textNombre, 0, SpringLayout.NORTH, etiquetaNombre);
		
		etiquetaVisible = new JLabel("¿Oculta o visible?: ");
		this.add(etiquetaVisible);
		comboBoxVisible = new JComboBox<String>(opciones);
		this.add(comboBoxVisible);
		
		//Constraints
		layoutPrincipal.putConstraint(SpringLayout.NORTH, etiquetaVisible, 20, SpringLayout.SOUTH, etiquetaNombre);
		layoutPrincipal.putConstraint(SpringLayout.EAST, etiquetaVisible, 0, SpringLayout.EAST, etiquetaNombre);		
		layoutPrincipal.putConstraint(SpringLayout.WEST, comboBoxVisible, 5, SpringLayout.EAST, etiquetaVisible);
		layoutPrincipal.putConstraint(SpringLayout.NORTH, comboBoxVisible, 0, SpringLayout.NORTH, etiquetaVisible);
		
		botonCrearAsignatura = new JButton("Crear Asignatura");
		this.add(botonCrearAsignatura);
		
		//Constraints
		layoutPrincipal.putConstraint(SpringLayout.HORIZONTAL_CENTER, botonCrearAsignatura, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layoutPrincipal.putConstraint(SpringLayout.SOUTH, botonCrearAsignatura, -30, SpringLayout.SOUTH, this);
		
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
	 * Metodo que sirve para retornar el texto contenido en la ComboBox
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
	 * @author Álvaro Martinez de Navascues
	 * @param controlador. Controlador que se quiere asignar
	 */
	public void setControlador(ActionListener controlador){
		this.botonCrearAsignatura.addActionListener(controlador);
	}
}