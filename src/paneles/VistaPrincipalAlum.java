package paneles;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

import controladores.ControlVistaMostrarAsignaturas;
import controladores.ControlVistaSolicitudMatricula;


public class VistaPrincipalAlum extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private JPanel principalAlum;
	final static String SOLICITAR_MATRICULA = "Carta con la vista de Solicitar Matricula";
	final static String PRINCIPAL = "Carta con la vista principal del Alumno";
	final static String MOSTRAR_ASIGNATURAS = "Carta con la vista de muestreo de Asignaturas";
	private JLabel etiquetaMenu;
	private VistaMostrarAsignaturas vista_mostrarAsignaturas;
	private VistaSolicitudMatricula vista_solicitudMatricula;
	private JButton botonSolicitarMat;
	private JButton botonMostrarAsig;
	private JButton botonLogout;
	
	/**
	 * Constructor de la vista principal del Alumno
	 * @author Álvaro Martinez de Navascues
	 */
	public VistaPrincipalAlum(){
		CardLayout card_layout = new CardLayout();
		this.setLayout(card_layout);
		
		this.principalAlum = new JPanel();
		SpringLayout layout = new SpringLayout();
				
		principalAlum.setLayout(layout);
		
		//Creamos nuestros componentes
		etiquetaMenu = new JLabel("MENU PRINCIPAL");
		etiquetaMenu.setFont(new Font("Times New Roman", Font.BOLD, 18));
		principalAlum.add(etiquetaMenu);		
		
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiquetaMenu, 0, SpringLayout.HORIZONTAL_CENTER, principalAlum);
		layout.putConstraint(SpringLayout.NORTH, etiquetaMenu, 10, SpringLayout.NORTH, principalAlum);
				
		botonSolicitarMat = new JButton("Solicitar Matricula");
		botonSolicitarMat.setPreferredSize(new Dimension(300,30));
		principalAlum.add(botonSolicitarMat);
		
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, botonSolicitarMat, 0, SpringLayout.HORIZONTAL_CENTER, principalAlum);
		layout.putConstraint(SpringLayout.NORTH, botonSolicitarMat, 100, SpringLayout.SOUTH, etiquetaMenu);
		
		botonLogout = new JButton("Log-Out");
		botonLogout.setPreferredSize(new Dimension(120, 30));
		principalAlum.add(botonLogout);
		
		layout.putConstraint(SpringLayout.NORTH, botonLogout, 0, SpringLayout.NORTH, etiquetaMenu);
		layout.putConstraint(SpringLayout.WEST, botonLogout, 10, SpringLayout.WEST, principalAlum);
		
		botonMostrarAsig = new JButton("Mostrar Asignaturas");
		botonMostrarAsig.setPreferredSize(new Dimension(300, 30));
		principalAlum.add(botonMostrarAsig);
		
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, botonMostrarAsig, 0, SpringLayout.HORIZONTAL_CENTER, principalAlum);
		layout.putConstraint(SpringLayout.NORTH, botonMostrarAsig, 50, SpringLayout.SOUTH, etiquetaMenu);
		
		this.principalAlum.setPreferredSize(new Dimension(500, 300));
		
		this.add(principalAlum, PRINCIPAL);
		this.vista_mostrarAsignaturas = new VistaMostrarAsignaturas();
		ControlVistaMostrarAsignaturas control_mostrarAsignaturas = new ControlVistaMostrarAsignaturas(vista_mostrarAsignaturas, this);
		vista_mostrarAsignaturas.setControlador(control_mostrarAsignaturas);
		this.add(vista_mostrarAsignaturas, MOSTRAR_ASIGNATURAS);
		
		this.vista_solicitudMatricula = new VistaSolicitudMatricula();
		ControlVistaSolicitudMatricula control_solicitud = new ControlVistaSolicitudMatricula(vista_solicitudMatricula, this);
		vista_solicitudMatricula.setControlador(control_solicitud);
		this.add(vista_solicitudMatricula, SOLICITAR_MATRICULA);
	}
	
	/**
	 * Metodo que sirve para aniadir un controlador a los botones de esta vista
	 * @author Alejandro Martin Climent
	 * @param controlador. Controlador que se quiere asignar
	 */
    public void setControlador(ActionListener controlador){
		this.botonSolicitarMat.addActionListener(controlador);
		this.botonMostrarAsig.addActionListener(controlador);
		this.botonLogout.addActionListener(controlador);
	}
    
    /**
	 * Getter del boton "CrearAsignatura"
	 * @author Alejandro Martin Climent
	 * @return JButton.
	 */
    public JButton getBotonSolicitarMat() {
		return botonSolicitarMat;
	}
    
    /**
   	 * Getter del boton "Log out"
   	 * @author Alvaro Martinez de Navascues
   	 * @return JButton.
   	 */
       public JButton getBotonLogout() {
   		return botonLogout;
   	}
       
	/**
	 * Getter del boton "MostrarAsignatura"
	 * @author Álvaro Martinez de Navascues
	 * @return JButton.
	 */
	public JButton getBotonMostrarAsig() {
		return botonMostrarAsig;
	}
	
	/**
	 * Metodo que muestra la vista principal del profesor
	 * @author Alejandro Martin Climent 
	 */
	public void mostrarVistaPrincipalAlum(){
		CardLayout cl = (CardLayout)(this.getLayout());
		cl.show(this, PRINCIPAL);
	}
	
	/**
	 * Metodo que muestra la vista de Solicitud  de Matricula
	 * @author Alvaro Martinez de Navascues
	 */
	public void mostrarVistaSolicitudMat(){
		this.vista_solicitudMatricula.actualizarComboBox();
		CardLayout cl = (CardLayout)(this.getLayout());
		cl.show(this, SOLICITAR_MATRICULA);
	}
	
	/**
	 * Metodo que muestra la vista de las Asignaturas Matriculadas
	 * @author Alejandro Martin Climent 
	 */
	public void mostrarVistaAsignaturasMat(){
		CardLayout cl = (CardLayout)(this.getLayout());
		cl.show(this, MOSTRAR_ASIGNATURAS);
	}

	/**
	 * Getter de la Vista de Mostrar Asignaturas
	 * @author Álvaro Martinez de Navascues
	 * @return La vista de Mostrar asignaturas
	 */
	public VistaMostrarAsignaturas getVista_mostrarAsignaturas() {
		return vista_mostrarAsignaturas;
	}
}