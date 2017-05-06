package paneles;


import java.awt.Dimension;
import javax.swing.*;
import asignatura.*;

public class VistaApunteProf extends JPanel{

	private static final long serialVersionUID = 1L;
	private JLabel etiquetaNombre;
	private JLabel etiquetaTexto;
	private JButton botonCambiarApunte;
	
	
	public VistaApunteProf(Apunte apunte){
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		
		etiquetaNombre = new JLabel(apunte.getTitulo());
		this.add(etiquetaNombre);
		layout.putConstraint(SpringLayout.NORTH, etiquetaNombre, 10, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiquetaNombre, 0, SpringLayout.HORIZONTAL_CENTER, this);
		
		etiquetaTexto = new JLabel(apunte.getContenido());
		this.add(etiquetaTexto);
		layout.putConstraint(SpringLayout.NORTH, etiquetaTexto, 50, SpringLayout.SOUTH, this.etiquetaNombre);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiquetaTexto, 0, SpringLayout.HORIZONTAL_CENTER, this.etiquetaNombre);
		
		botonCambiarApunte = new JButton("Cambiar Apunte");
		botonCambiarApunte.setPreferredSize(new Dimension(150,75));
		this.add(botonCambiarApunte);
		
		layout.putConstraint(SpringLayout.SOUTH, botonCambiarApunte, -5, SpringLayout.SOUTH, this);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, botonCambiarApunte, 0, SpringLayout.HORIZONTAL_CENTER, this);
		
		
		this.setPreferredSize(new Dimension(800, 350));
	}
	
}
