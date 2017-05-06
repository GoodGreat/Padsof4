package paneles;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;

public class VistaCrearEjercicio extends JPanel{
	private static final long serialVersionUID = 1L;
	private JLabel titulo, etiquetaNombre, etiquetaPeso, etiquetaVisible, etiquetaAleatorio, etiquetaIni, etiquetaFin;
	private JLabel etiquetaAno, etiquetaMes, etiquetaDia, etiquetaHora, etiquetaMinuto;
	private JTextField textNombre, textPeso, textAnoIni, textAnoFin, textMesIni, textMesFin, textDiaIni, textDiaFin, textHoraIni, textHoraFin, textMinIni, textMinFin;
	private JComboBox<String> comboBoxVisible, comboBoxAleatorio;
	private JButton botonCrearEjercicio, botonAniadirPregunta;
	
	/**
	 * Constructor del panel de Crear Asignatura
	 * @author Alvaro Martinez de Navascues
	 */
	public VistaCrearEjercicio(){
		String opciones[] = {"Visible", "Oculto"};		
		String opciones2[] = {"Aleatorio", "No Aleatorio"};
		
		//Layout del JPanel principal
		SpringLayout layoutPrincipal = new SpringLayout();
		this.setLayout(layoutPrincipal);
		
		titulo = new JLabel("CREANDO EJERCICIO");
		titulo.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 20));
		this.add(titulo);
		
		//Constraints
		layoutPrincipal.putConstraint(SpringLayout.HORIZONTAL_CENTER, titulo, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layoutPrincipal.putConstraint(SpringLayout.NORTH, titulo, 30, SpringLayout.NORTH, this);
		
		//Creamos nuestros componentes
		etiquetaNombre = new JLabel("Nombre del Ejercicio: ");
		this.add(etiquetaNombre);
		textNombre = new JTextField(20);
		this.add(textNombre);
		
		//Constraints
		layoutPrincipal.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiquetaNombre, -100, SpringLayout.HORIZONTAL_CENTER, this);
		layoutPrincipal.putConstraint(SpringLayout.VERTICAL_CENTER, etiquetaNombre, -70, SpringLayout.VERTICAL_CENTER, this);
		layoutPrincipal.putConstraint(SpringLayout.WEST, textNombre, 5, SpringLayout.EAST, etiquetaNombre);
		layoutPrincipal.putConstraint(SpringLayout.NORTH, textNombre, 0, SpringLayout.NORTH, etiquetaNombre);
		
		etiquetaPeso = new JLabel("Peso del Ejercicio: ");
		this.add(etiquetaPeso);
		textPeso = new JTextField(20);
		this.add(textPeso);
				
		//Constraints
		layoutPrincipal.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiquetaPeso, -100, SpringLayout.HORIZONTAL_CENTER, this);
		layoutPrincipal.putConstraint(SpringLayout.VERTICAL_CENTER, etiquetaPeso, -60, SpringLayout.VERTICAL_CENTER, this);
		layoutPrincipal.putConstraint(SpringLayout.WEST, textPeso, 5, SpringLayout.EAST, etiquetaPeso);
		layoutPrincipal.putConstraint(SpringLayout.NORTH, textPeso, 0, SpringLayout.NORTH, etiquetaPeso);
		
		//IMPLEMENTAR CAMBIO DE STRING A NUMERO
		//String numCadena = "1";
		//int numEntero = Integer.parseInt(numCadena);
		
		etiquetaVisible = new JLabel("¿Oculto o visible?: ");
		this.add(etiquetaVisible);
		comboBoxVisible = new JComboBox<String>(opciones);
		this.add(comboBoxVisible);

		//Constraints
		layoutPrincipal.putConstraint(SpringLayout.NORTH, etiquetaVisible, 10, SpringLayout.SOUTH, etiquetaPeso);
		layoutPrincipal.putConstraint(SpringLayout.EAST, etiquetaVisible, 0, SpringLayout.EAST, etiquetaPeso);		
		layoutPrincipal.putConstraint(SpringLayout.WEST, comboBoxVisible, 5, SpringLayout.EAST, etiquetaVisible);
		layoutPrincipal.putConstraint(SpringLayout.NORTH, comboBoxVisible, 0, SpringLayout.NORTH, etiquetaVisible);
		
		etiquetaAleatorio = new JLabel("¿Orden Aleatorio?: ");
		this.add(etiquetaAleatorio);
		comboBoxAleatorio = new JComboBox<String>(opciones2);
		this.add(comboBoxAleatorio);

		//Constraints
		layoutPrincipal.putConstraint(SpringLayout.NORTH, etiquetaAleatorio, 10, SpringLayout.SOUTH, etiquetaVisible);
		layoutPrincipal.putConstraint(SpringLayout.EAST, etiquetaAleatorio, 0, SpringLayout.EAST, etiquetaVisible);		
		layoutPrincipal.putConstraint(SpringLayout.WEST, comboBoxAleatorio, 5, SpringLayout.EAST, etiquetaAleatorio);
		layoutPrincipal.putConstraint(SpringLayout.NORTH, comboBoxAleatorio, 0, SpringLayout.NORTH, etiquetaAleatorio);
		
		
		etiquetaIni = new JLabel("Fecha de Inicio: ");
		this.add(etiquetaIni);
		layoutPrincipal.putConstraint(SpringLayout.NORTH, etiquetaIni, 20, SpringLayout.SOUTH, etiquetaAleatorio);
		layoutPrincipal.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiquetaIni, -150, SpringLayout.HORIZONTAL_CENTER, this);
		
		etiquetaFin = new JLabel("Fecha de Fin: ");
		this.add(etiquetaFin);
		layoutPrincipal.putConstraint(SpringLayout.NORTH, etiquetaFin, 10, SpringLayout.SOUTH, etiquetaIni);
		layoutPrincipal.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiquetaFin, -150, SpringLayout.HORIZONTAL_CENTER, this);
		
		
		
		
		etiquetaAno = new JLabel("Año: ");
		this.add(etiquetaAno);
		layoutPrincipal.putConstraint(SpringLayout.NORTH, etiquetaAno, 10, SpringLayout.SOUTH, etiquetaAleatorio);
		layoutPrincipal.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiquetaAno, -130, SpringLayout.HORIZONTAL_CENTER, this);
		
		
		textAnoIni = new JTextField(15);
		this.add(textAnoIni);
		layoutPrincipal.putConstraint(SpringLayout.NORTH, textAnoIni, 10, SpringLayout.SOUTH, etiquetaAno);
		layoutPrincipal.putConstraint(SpringLayout.HORIZONTAL_CENTER, textAnoIni, -130, SpringLayout.HORIZONTAL_CENTER, this);
		
		textAnoFin = new JTextField(15);
		this.add(textAnoFin);
		layoutPrincipal.putConstraint(SpringLayout.NORTH, textAnoFin, 10, SpringLayout.SOUTH, textAnoIni);
		layoutPrincipal.putConstraint(SpringLayout.HORIZONTAL_CENTER, textAnoFin, -130, SpringLayout.HORIZONTAL_CENTER, this);
		
		
		
		
		etiquetaMes = new JLabel("Mes: ");
		this.add(etiquetaMes);
		layoutPrincipal.putConstraint(SpringLayout.NORTH, etiquetaMes, 10, SpringLayout.SOUTH, etiquetaAleatorio);
		layoutPrincipal.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiquetaMes, -100, SpringLayout.HORIZONTAL_CENTER, this);
		
		
		textMesIni = new JTextField(15);
		this.add(textMesIni);
		layoutPrincipal.putConstraint(SpringLayout.NORTH, textMesIni, 10, SpringLayout.SOUTH, etiquetaMes);
		layoutPrincipal.putConstraint(SpringLayout.HORIZONTAL_CENTER, textMesIni, -100, SpringLayout.HORIZONTAL_CENTER, this);
		
		textAnoFin = new JTextField(15);
		this.add(textAnoFin);
		layoutPrincipal.putConstraint(SpringLayout.NORTH, textMesFin, 10, SpringLayout.SOUTH, textMesIni);
		layoutPrincipal.putConstraint(SpringLayout.HORIZONTAL_CENTER, textMesFin, -100, SpringLayout.HORIZONTAL_CENTER, this);
		
		
		
		
		
		etiquetaDia = new JLabel("Dia: ");
		this.add(etiquetaDia);
		layoutPrincipal.putConstraint(SpringLayout.NORTH, etiquetaDia, 10, SpringLayout.SOUTH, etiquetaAleatorio);
		layoutPrincipal.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiquetaDia, -70, SpringLayout.HORIZONTAL_CENTER, this);
		
		
		textDiaIni = new JTextField(15);
		this.add(textDiaIni);
		layoutPrincipal.putConstraint(SpringLayout.NORTH, textDiaIni, 10, SpringLayout.SOUTH, etiquetaDia);
		layoutPrincipal.putConstraint(SpringLayout.HORIZONTAL_CENTER, textDiaIni, -100, SpringLayout.HORIZONTAL_CENTER, this);
		
		textDiaFin = new JTextField(15);
		this.add(textAnoFin);
		layoutPrincipal.putConstraint(SpringLayout.NORTH, textDiaFin, 10, SpringLayout.SOUTH, textDiaIni);
		layoutPrincipal.putConstraint(SpringLayout.HORIZONTAL_CENTER, textDiaFin, -100, SpringLayout.HORIZONTAL_CENTER, this);
		
		
		
		
		
		
		etiquetaHora = new JLabel("Hora: ");
		this.add(etiquetaHora);
		layoutPrincipal.putConstraint(SpringLayout.NORTH, etiquetaHora, 10, SpringLayout.SOUTH, etiquetaAleatorio);
		layoutPrincipal.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiquetaHora, -40, SpringLayout.HORIZONTAL_CENTER, this);
		
		textHoraIni = new JTextField(15);
		this.add(textHoraIni);
		layoutPrincipal.putConstraint(SpringLayout.NORTH, textHoraIni, 10, SpringLayout.SOUTH, etiquetaHora);
		layoutPrincipal.putConstraint(SpringLayout.HORIZONTAL_CENTER, textHoraIni, -100, SpringLayout.HORIZONTAL_CENTER, this);
		
		textHoraFin = new JTextField(15);
		this.add(textHoraFin);
		layoutPrincipal.putConstraint(SpringLayout.NORTH, textHoraFin, 10, SpringLayout.SOUTH, textHoraIni);
		layoutPrincipal.putConstraint(SpringLayout.HORIZONTAL_CENTER, textHoraFin, -100, SpringLayout.HORIZONTAL_CENTER, this);
		
		
		
	
		etiquetaMinuto = new JLabel("Minuto: ");
		this.add(etiquetaMinuto);
		layoutPrincipal.putConstraint(SpringLayout.NORTH, etiquetaMinuto, 10, SpringLayout.SOUTH, etiquetaAleatorio);
		layoutPrincipal.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiquetaMinuto, -10, SpringLayout.HORIZONTAL_CENTER, this);
		
		textMinIni = new JTextField(15);
		this.add(textMinIni);
		layoutPrincipal.putConstraint(SpringLayout.NORTH, textMinIni, 10, SpringLayout.SOUTH, etiquetaMinuto);
		layoutPrincipal.putConstraint(SpringLayout.HORIZONTAL_CENTER, textMinIni, -100, SpringLayout.HORIZONTAL_CENTER, this);
		
		textMinFin = new JTextField(15);
		this.add(textMinFin);
		layoutPrincipal.putConstraint(SpringLayout.NORTH, textMinFin, 10, SpringLayout.SOUTH, textMinIni);
		layoutPrincipal.putConstraint(SpringLayout.HORIZONTAL_CENTER, textMinFin, -100, SpringLayout.HORIZONTAL_CENTER, this);
		
		botonAniadirPregunta = new JButton("Aniadir Pregunta");
		botonAniadirPregunta.setPreferredSize(new Dimension(150,70));
		this.add(botonAniadirPregunta);
		
		//Constraints
		layoutPrincipal.putConstraint(SpringLayout.HORIZONTAL_CENTER, botonAniadirPregunta, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layoutPrincipal.putConstraint(SpringLayout.SOUTH, botonAniadirPregunta, -100, SpringLayout.SOUTH, this);
		
		botonCrearEjercicio = new JButton("Crear Ejercicio");
		botonCrearEjercicio.setPreferredSize(new Dimension(150,70));
		this.add(botonCrearEjercicio);
		
		//Constraints
		layoutPrincipal.putConstraint(SpringLayout.HORIZONTAL_CENTER, botonCrearEjercicio, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layoutPrincipal.putConstraint(SpringLayout.SOUTH, botonCrearEjercicio, -10, SpringLayout.SOUTH, this);
		
	} 
	
	/**
	 * Metodo que sirve para retornar el texto contenido en el Nombre del ejercicio
	 * @author Alejandro Martin Climent
	 * @return String. El nombre del ejercicio
	 */
	public String getNombre(){
		return this.textNombre.getText();
	}
	
	/**
	 * Metodo que sirve para retornar el texto contenido en el Nombre del ejercicio
	 * @author Alvaro Martinez de Navascues
	 * @return String. El nombre del ejercicio
	 */
	public String getPeso(){
		return this.textPeso.getText();
	}
	
	/**
	 * Metodo que sirve para retornar la opcion seleccionada en la ComboBox
	 * @author Alvaro Martinez de Navascues
	 * @return boolean, true si es visible, false en caso contrario
	 */
	public boolean getComboBoxSelectedVis(){
		if (comboBoxVisible.getSelectedItem().equals("Visible")){
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
	 * Metodo que sirve para retornar el texto contenido en 
	 * @author Alejandro Martin Climent
	 * @return String. El NIA del alumno/Login del profesor
	 */
	public JTextField getAnoIni(){
		return this.textAnoIni;
	}
	
	/**
	 * Metodo que sirve para retornar el texto contenido en 
	 * @author Alejandro Martin Climent
	 * @return String. El NIA del alumno/Login del profesor
	 */
	public JTextField getAnoFin(){
		return this.textAnoFin;
	}
	
	/**
	 * Metodo que sirve para retornar el texto contenido en el NIA
	 * @author Alejandro Martin Climent
	 * @return String. El NIA del alumno/Login del profesor
	 */
	public JTextField getMesIni(){
		return this.textMesIni;
	}
	
	/**
	 * Metodo que sirve para retornar el texto contenido en el NIA
	 * @author Alejandro Martin Climent
	 * @return String. El NIA del alumno/Login del profesor
	 */
	public JTextField getMesFin(){
		return this.textMesFin;
	}
	
	/**
	 * Metodo que sirve para retornar el texto contenido en el NIA
	 * @author Alejandro Martin Climent
	 * @return String. El NIA del alumno/Login del profesor
	 */
	public JTextField getDiaIni(){
		return this.textDiaIni;
	}
	
	/**
	 * Metodo que sirve para retornar el texto contenido en el NIA
	 * @author Alejandro Martin Climent
	 * @return String. El NIA del alumno/Login del profesor
	 */
	public JTextField getDiaFin(){
		return this.textDiaFin;
	}
	
	
	/**
	 * Metodo que sirve para retornar el texto contenido en el NIA
	 * @author Alejandro Martin Climent
	 * @return String. El NIA del alumno/Login del profesor
	 */
	public JTextField getHoraIni(){
		return this.textHoraIni;
	}
	
	
	/**
	 * Metodo que sirve para retornar el texto contenido en el NIA
	 * @author Alejandro Martin Climent
	 * @return String. El NIA del alumno/Login del profesor
	 */
	public JTextField getHoraFin(){
		return this.textHoraFin;
	}
	
	/**
	 * Metodo que sirve para retornar el texto contenido en el NIA
	 * @author Alejandro Martin Climent
	 * @return String. El NIA del alumno/Login del profesor
	 */
	public JTextField getMinIni(){
		return this.textMinIni;
	}
	
	/**
	 * Metodo que sirve para retornar el texto contenido en el NIA
	 * @author Alejandro Martin Climent
	 * @return String. El NIA del alumno/Login del profesor
	 */
	public JTextField getMinFin(){
		return this.textMinFin;
	}
	
	
	/**
	 * Metodo que sirve para aniadir un controlador al boton de Crear Asignatura
	 * @author Álvaro Martinez de Navascues
	 * @param controlador. Controlador que se quiere asignar
	 */
	public void setControlador(ActionListener controlador){
		this.botonCrearEjercicio.addActionListener(controlador);
	}
}