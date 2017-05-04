package paneles;

import java.awt.Dimension;

import javax.swing.*;
import ejercicio.*;

public class VistaPreguntaLibreProf extends JPanel{

	private static final long serialVersionUID = 1L;
	private JLabel etiquetaEnunciado, etiquetaPuntuacion;
	private JLabel respuesta;
	private JTextField textRespuesta;
	private JButton botonCambiarPregunta; 
	
	public VistaPreguntaLibreProf(PreguntaLibre pregunta){
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		
		etiquetaEnunciado = new JLabel(pregunta.getEnunciado());
		this.add(etiquetaEnunciado);
		//Ponemos el norte de la etiqueta del Nombre del Apunte a 5 pixeles al norte del contenedor 
		layout.putConstraint(SpringLayout.NORTH, etiquetaEnunciado, 10, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiquetaEnunciado, 0, SpringLayout.HORIZONTAL_CENTER, this);
		
		if(pregunta.getFalloResta() == true){
			etiquetaPuntuacion = new JLabel("Esta pregunta vale: " + pregunta.getPuntuacion() +
					" puntos, y en caso de fallo resta: "+ pregunta.getResta() + "puntos");
		} else {
			etiquetaPuntuacion = new JLabel("Esta pregunta vale: " + pregunta.getPuntuacion() +
					" y  no resta en caso de fallo ");
		}
		
		this.add(etiquetaPuntuacion);
		
		//Ponemos el norte de la etiqueta del Nombre del Apunte a 5 pixeles al norte del contenedor 
		layout.putConstraint(SpringLayout.NORTH, etiquetaPuntuacion, 10, SpringLayout.SOUTH, etiquetaEnunciado);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiquetaPuntuacion, 0, SpringLayout.HORIZONTAL_CENTER, this);
		
		
		//Ponemos el norte de la etiquetaTexto distanciada a 5 del norte de la etiquetaNombre
		respuesta = new JLabel("RESPUESTA: ");
		this.add(respuesta);
		layout.putConstraint(SpringLayout.NORTH, respuesta, 60, SpringLayout.SOUTH, etiquetaEnunciado);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, respuesta, 0, SpringLayout.HORIZONTAL_CENTER, this);
		

		//Ponemos el norte de la etiquetaTexto distanciada a 5 del norte de la etiquetaNombre
		textRespuesta = new JTextField(30);
		this.add(textRespuesta);
		layout.putConstraint(SpringLayout.NORTH, textRespuesta, 20, SpringLayout.SOUTH, respuesta);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, textRespuesta, 0, SpringLayout.HORIZONTAL_CENTER, this);
				
		
		botonCambiarPregunta = new JButton("Cambiar Pregunta");
		botonCambiarPregunta.setPreferredSize(new Dimension(150,75));
		this.add(botonCambiarPregunta);
		
		layout.putConstraint(SpringLayout.SOUTH, botonCambiarPregunta, -5, SpringLayout.SOUTH, this);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, botonCambiarPregunta, 0, SpringLayout.HORIZONTAL_CENTER, this);
		
		this.setPreferredSize(new Dimension(800, 350));
	}
	
}