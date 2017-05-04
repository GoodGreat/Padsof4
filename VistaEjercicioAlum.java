package paneles;

import java.awt.Dimension;
import javax.swing.*;
import ejercicio.*;


public class VistaEjercicioAlum extends JPanel{

	private static final long serialVersionUID = 1L;
	private JLabel etiquetaNombre;
	private JLabel etiquetaFechaIni;
	private JLabel etiquetaFechaFin;	
	
	public VistaEjercicioAlum(Ejercicio ejercicio){
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);

		etiquetaNombre = new JLabel(ejercicio.getNombre());
		this.add(etiquetaNombre);
		layout.putConstraint(SpringLayout.NORTH, etiquetaNombre, 10, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiquetaNombre, 0, SpringLayout.HORIZONTAL_CENTER, this);
	
	 	etiquetaFechaIni = new JLabel("Fecha de inicio: " + ejercicio.getFechaIni().getTime());
	 	this.add(etiquetaFechaIni);
		layout.putConstraint(SpringLayout.NORTH, etiquetaFechaIni, 10, SpringLayout.SOUTH, this.etiquetaNombre);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiquetaFechaIni, 0, SpringLayout.HORIZONTAL_CENTER, this.etiquetaNombre);
		
		
		etiquetaFechaFin = new JLabel("Fecha de final: " + ejercicio.getFechaFin().getTime());
		this.add(etiquetaFechaFin);
		layout.putConstraint(SpringLayout.NORTH, etiquetaFechaFin, 10, SpringLayout.SOUTH, this.etiquetaFechaIni);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiquetaFechaFin, 0, SpringLayout.HORIZONTAL_CENTER,this.etiquetaFechaIni);
			
		
		//barajeamos las preguntas para que salgan en un orden aleatorio, distinto al introducido por el profesor
		if(ejercicio.getAleatorio() == true){
			ejercicio.barajarPreguntas();
		}
		
		int i = 0;
		
		for(Pregunta preguntaAux: ejercicio.getPreguntas()){
			JButton pregunta = new JButton(preguntaAux.getEnunciado());
			pregunta.setPreferredSize(new Dimension(200,75));
			this.add(pregunta);
			layout.putConstraint(SpringLayout.NORTH, pregunta, i, SpringLayout.SOUTH, etiquetaNombre);
			layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, pregunta, 0, SpringLayout.HORIZONTAL_CENTER, this);
			i += 30; 
		}

		i += 300; //REVISAAAAAR
		this.setPreferredSize(new Dimension(i,350));
	}
	
}
