package paneles;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

import asignatura.Asignatura;
import sistema.*;

public class VistaSolicitudMatricula extends JPanel{
	private static final long serialVersionUID = 1L;
	private JButton botonAtras, botonEnviarSolicitud;
	private JLabel titulo, etiqueta_asig;
	private JComboBox<String> box_asignaturas;
	
	public VistaSolicitudMatricula(){
		String nombres[] = new String[Sistema.getInstance().getAsignaturas().size()];
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		
		titulo = new JLabel("ASIGNATURAS");
		titulo.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 20));
		this.add(titulo);
		
		//Constraints
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, titulo, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, titulo, 20, SpringLayout.NORTH, this);
		
		for (int i = 0; i < Sistema.getInstance().getAsignaturas().size(); i++){
			nombres[i] = Sistema.getInstance().getAsignaturas().get(i).getNombre();
		}
		
		etiqueta_asig = new JLabel("Seleccione una asignatura: ");
		this.add(etiqueta_asig);
		
		//Constraints
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiqueta_asig, -100, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, etiqueta_asig, -70, SpringLayout.VERTICAL_CENTER, this);
		
		box_asignaturas = new JComboBox<String>(nombres);
		this.add(box_asignaturas);

		//Constraints
		layout.putConstraint(SpringLayout.WEST, box_asignaturas, 10, SpringLayout.EAST, etiqueta_asig);
		layout.putConstraint(SpringLayout.NORTH, box_asignaturas, 0, SpringLayout.NORTH, etiqueta_asig);
		
		botonAtras = new JButton("Volver atras");
		botonAtras.setPreferredSize(new Dimension(120, 30));
		this.add(botonAtras);
		
		botonEnviarSolicitud = new JButton("Enviar Solicitud");
		botonEnviarSolicitud.setPreferredSize(new Dimension(200, 50));
		this.add(botonEnviarSolicitud);
		
		//Constraints
		layout.putConstraint(SpringLayout.NORTH, botonAtras, 0, SpringLayout.NORTH, titulo);
		layout.putConstraint(SpringLayout.EAST, botonAtras, 0, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, botonEnviarSolicitud, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.SOUTH, botonEnviarSolicitud, -50, SpringLayout.SOUTH, this);
		
		this.actualizarComboBox();
	}
	
	/**
	 * Metodo que sirve para retornar el texto contenido en la ComboBox
	 * @author Alvaro Martinez de Navascues
	 * @return String. El nombre de la Asignatura Seleccionada
	 */
	public JComboBox<String> getComboBox(){
		return box_asignaturas;
	}
	
	/**
	 * Metodo que sirve para aniadir un controlador a los botones de Solicitud de Matricula
	 * @author Alejandro Martin Climent
	 * @param controlador. Controlador que se quiere asignar
	 */
	public void setControlador(ActionListener controlador){
		this.botonAtras.addActionListener(controlador);
		this.botonEnviarSolicitud.addActionListener(controlador);
	}

	/**
	 * Metodo que actualiza el contenido de la Combo Box
	 * @author Álvaro Martinez de Navascues
	 */
	public void actualizarComboBox(){
		this.getComboBox().removeAllItems();
		
		for (Asignatura asigAux: Sistema.getInstance().getAsignaturas()){
			this.getComboBox().addItem(asigAux.getNombre());
		}
	}
	/**
	 * Getter del boton Atras
	 * @author Álvaro Martinez de Navascues
	 * @return JButton. El botonAtras
	 */
	public JButton getBotonAtras() {
		return botonAtras;
	}

	/**
	 * Getter del boton Enviar Solicitud
	 * @author Alejandro Martin Climent
	 * @return JButton. El boton Enviar Solicitud
	 */
	public JButton getBotonEnviarSolicitud() {
		return botonEnviarSolicitud;
	}
}
