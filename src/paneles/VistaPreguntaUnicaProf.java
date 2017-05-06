package paneles;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.*;
import ejercicio.*;

public class VistaPreguntaUnicaProf extends JPanel{

	private static final long serialVersionUID = 1L;
	private JLabel etiquetaEnunciado, etiquetaPuntuacion;
	private JButton botonCambiarPregunta;
	
	public VistaPreguntaUnicaProf(PreguntaUnica pregunta){
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		
		etiquetaEnunciado = new JLabel(pregunta.getEnunciado());
		this.add(etiquetaEnunciado);
		layout.putConstraint(SpringLayout.NORTH, etiquetaEnunciado, 10, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiquetaEnunciado, 0, SpringLayout.HORIZONTAL_CENTER, this);
		
		if(pregunta.getFalloResta() == true){
			etiquetaPuntuacion = new JLabel("Esta pregunta vale: " + pregunta.getPuntuacion() +
					" puntos, y en caso de fallo resta: "+ pregunta.getResta() + "puntos");
		} else {
			etiquetaPuntuacion = new JLabel("Esta pregunta vale:" + pregunta.getPuntuacion() +
					"y  no resta en caso de fallo");
		}
		
		this.add(etiquetaPuntuacion);
		
		//Ponemos el norte de la etiqueta del Nombre del Apunte a 5 pixeles al norte del contenedor 
		layout.putConstraint(SpringLayout.NORTH, etiquetaPuntuacion, 20, SpringLayout.SOUTH, etiquetaEnunciado);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiquetaPuntuacion, 0, SpringLayout.HORIZONTAL_CENTER, this);
		
		GridLayout glayout = new GridLayout(3,1);
		JPanel Checkbox = new JPanel((glayout));
		
		Checkbox.add(new JLabel("Seleccione las opciones correctas: "));
		this.add(Checkbox);
		
		//JCheckBox casilla = new JCheckBox(pregunta.getOpciones().get(0).getOpcion());
		//Checkbox.add(casilla);

		for(Opcion opcionAux: pregunta.getOpciones()){
			JCheckBox opcion = new JCheckBox(opcionAux.getOpcion());
			Checkbox.add(opcion);
		}

		layout.putConstraint(SpringLayout.NORTH, Checkbox, 20, SpringLayout.SOUTH, this.etiquetaPuntuacion);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, Checkbox, 0, SpringLayout.HORIZONTAL_CENTER, this);
		
		this.setPreferredSize(new Dimension(800, 350));
		
		botonCambiarPregunta = new JButton("Cambiar Pregunta");
		botonCambiarPregunta.setPreferredSize(new Dimension(150,75));
		this.add(botonCambiarPregunta);
		
		layout.putConstraint(SpringLayout.SOUTH, botonCambiarPregunta, -5, SpringLayout.SOUTH, this);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, botonCambiarPregunta, 0, SpringLayout.HORIZONTAL_CENTER, this);
		
		
	}
	
}
