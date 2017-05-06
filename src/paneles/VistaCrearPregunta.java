package paneles;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;

import ejercicio.*;

public class VistaCrearPregunta extends JPanel{
	private static final long serialVersionUID = 1L;
	private JLabel titulo, etiquetaEnunciado, etiquetaPuntuacion, etiquetaResta, etiquetaFalloResta, etiquetaAleatorio;
	private JTextField textEnunciado, textPuntuacion;
	private JComboBox<String> comboBoxAleatorio, comboBoxResta;
	private JButton botonCrearPregunta, botonAniadirOpcion, botonAniadirSolucion;
	
	/**
	 * Constructor del panel de Crear Asignatura
	 * @author Alvaro Martinez de Navascues
	 */
	public VistaCrearPregunta(){
		String opciones[] = {"Resta", "No Resta"};
		String opciones2[] = {"Aleatorio", "No Aleatorio"};
		
		//Layout del JPanel principal
		SpringLayout layoutPrincipal = new SpringLayout();
		this.setLayout(layoutPrincipal);
		
		titulo = new JLabel("CREANDO PREGUNTA");
		titulo.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 20));
		this.add(titulo);
		
		//Constraints
		layoutPrincipal.putConstraint(SpringLayout.HORIZONTAL_CENTER, titulo, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layoutPrincipal.putConstraint(SpringLayout.NORTH, titulo, 20, SpringLayout.NORTH, this);
		
		//Creamos nuestros componentes
		etiquetaEnunciado = new JLabel("Enunciado de la Pregunta: ");
		this.add(etiquetaEnunciado);
		textEnunciado = new JTextField(50);
		this.add(textEnunciado);
		
		//Constraints
		layoutPrincipal.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiquetaEnunciado, -100, SpringLayout.HORIZONTAL_CENTER, this);
		layoutPrincipal.putConstraint(SpringLayout.VERTICAL_CENTER, etiquetaEnunciado, -70,SpringLayout.VERTICAL_CENTER, this);
		layoutPrincipal.putConstraint(SpringLayout.WEST, textEnunciado, 5, SpringLayout.EAST, etiquetaEnunciado);
		layoutPrincipal.putConstraint(SpringLayout.NORTH, textEnunciado, 0, SpringLayout.NORTH, etiquetaEnunciado);
		
		

		etiquetaPuntuacion = new JLabel("Puntuacion de la Pregunta: ");
		this.add(etiquetaPuntuacion);
		textPuntuacion = new JTextField(20);
		this.add(textPuntuacion);
		
		//Constraints
		layoutPrincipal.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiquetaPuntuacion, -100, SpringLayout.HORIZONTAL_CENTER, this);
		layoutPrincipal.putConstraint(SpringLayout.VERTICAL_CENTER, etiquetaPuntuacion, -60, SpringLayout.VERTICAL_CENTER, this);
		layoutPrincipal.putConstraint(SpringLayout.WEST, textPuntuacion, 5, SpringLayout.EAST, etiquetaPuntuacion);
		layoutPrincipal.putConstraint(SpringLayout.NORTH, textPuntuacion, 0, SpringLayout.NORTH, etiquetaPuntuacion);
		
		//IMPLEMENTAR CAMBIO DE STRING A NUMERO
		//String numCadena = "1";
		//int numEntero = Integer.parseInt(numCadena);
		
		
		etiquetaFalloResta = new JLabel("¿Resta en caso de fallo?: ");
		this.add(etiquetaFalloResta);
		comboBoxResta = new JComboBox<String>(opciones);
		this.add(comboBoxResta);

		
		//Constraints
		layoutPrincipal.putConstraint(SpringLayout.NORTH, etiquetaFalloResta, 10, SpringLayout.SOUTH, etiquetaPuntuacion);
		layoutPrincipal.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiquetaFalloResta, -100, SpringLayout.HORIZONTAL_CENTER, this);
		layoutPrincipal.putConstraint(SpringLayout.WEST, comboBoxAleatorio, 5, SpringLayout.EAST, etiquetaFalloResta);
		layoutPrincipal.putConstraint(SpringLayout.NORTH, comboBoxAleatorio, 0, SpringLayout.NORTH, etiquetaFalloResta);
		
		/*if(this.getResta() == true) {
			
			etiquetaFallo = new JLabel("¿Resta en caso de fallo?: ");
			this.add(etiquetaResta);
			comboBoxResta = new JComboBox<String>(opciones);
			this.add(comboBoxResta);
			
			
		}*/
		
		etiquetaAleatorio = new JLabel("¿Orden Aleatorio?: ");
		this.add(etiquetaAleatorio);
		comboBoxAleatorio = new JComboBox<String>(opciones2);
		this.add(comboBoxAleatorio);

		//Constraints
		layoutPrincipal.putConstraint(SpringLayout.NORTH, etiquetaAleatorio, 10, SpringLayout.SOUTH, etiquetaAleatorio);
		layoutPrincipal.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiquetaAleatorio, -100, SpringLayout.HORIZONTAL_CENTER, this);	
		layoutPrincipal.putConstraint(SpringLayout.WEST, comboBoxAleatorio, 5, SpringLayout.EAST, etiquetaAleatorio);
		layoutPrincipal.putConstraint(SpringLayout.NORTH, comboBoxAleatorio, 0, SpringLayout.NORTH, etiquetaAleatorio);
		
		
		
		botonAniadirOpcion = new JButton("Aniadir Opcion");
		botonAniadirOpcion.setPreferredSize(new Dimension(150,70));
		this.add(botonAniadirOpcion);
		
		//Constraints
		layoutPrincipal.putConstraint(SpringLayout.HORIZONTAL_CENTER, botonAniadirOpcion, -75, SpringLayout.HORIZONTAL_CENTER, this);
		layoutPrincipal.putConstraint(SpringLayout.SOUTH, botonAniadirOpcion, -75, SpringLayout.SOUTH, this);
		
		botonAniadirSolucion = new JButton("Aniadir Solucion");
		botonAniadirSolucion.setPreferredSize(new Dimension(150,70));
		this.add(botonAniadirSolucion);
		
		//Constraints
		layoutPrincipal.putConstraint(SpringLayout.HORIZONTAL_CENTER, botonCrearPregunta, 75, SpringLayout.HORIZONTAL_CENTER, this);
		layoutPrincipal.putConstraint(SpringLayout.SOUTH, botonCrearPregunta, -75, SpringLayout.SOUTH, this);
		
		
		
		
		botonCrearPregunta = new JButton("Aniadir Pregunta");
		botonCrearPregunta.setPreferredSize(new Dimension(150,70));
		this.add(botonCrearPregunta);
		
		//Constraints
		layoutPrincipal.putConstraint(SpringLayout.HORIZONTAL_CENTER, botonCrearPregunta, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layoutPrincipal.putConstraint(SpringLayout.SOUTH, botonCrearPregunta, -10, SpringLayout.SOUTH, this);
		
	} 
	
	/**
	 * Metodo que sirve para retornar el texto contenido en el Nombre del ejercicio
	 * @author Alejandro Martin Climent
	 * @return String. El nombre del ejercicio
	 */
	public String getEnunciado(){
		return this.textEnunciado.getText();
	}

	
	
	
	
	/**
	 * Metodo que sirve para retornar el texto contenido en el Nombre del ejercicio
	 * @author Alvaro Martinez de Navascues
	 * @return String. El nombre del ejercicio
	 */
	public String getPuntuacion(){
		return this.textPuntuacion.getText();
	}
	
	
	/**
	 * Metodo que sirve para retornar la opcion seleccionada en la ComboBox
	 * @author Alvaro Martinez de Navascues
	 * @return boolean, true si es visible, false en caso contrario
	 */
	public boolean getComboBoxSelectedResta(){
		if (comboBoxResta.getSelectedItem().equals("Resta")){
			return true;
		}else{
			return false;
		}		
	}
	
	
	/**
	 * Metodo que sirve para retornar la opcion seleccionada en la ComboBox
	 * @author Alvaro Martinez de Navascues
	 * @return boolean, true si es aleatorio, false en caso contrario
	 */
	public boolean getComboBoxSelectedAleat(){
		if (comboBoxAleatorio.getSelectedItem().equals("Aleatorio")){
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
		this.botonAniadirOpcion.addActionListener(controlador);
		this.botonAniadirSolucion.addActionListener(controlador);
		this.botonCrearPregunta.addActionListener(controlador);
	}
}