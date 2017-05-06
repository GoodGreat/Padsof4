package ventanas;

import java.awt.*;
import javax.swing.*;

import asignatura.Asignatura;
import asignatura.Tema;
import controladores.*;
import paneles.*;

public class FramePrincipal extends JFrame {
	private static final long serialVersionUID = 1L;
	final static String LOGINPANEL = "Carta con el LOGIN";
	final static String GESTIONPANEL = "Carta para la gestion de matriculas";
	final static String PROF_PRINCIPANEL = "Carta con la vista del menu del Profesor";
	final static String ESTUD_PRINCIPANEL = "Carta con la vista del menu Alumno";
	final static String ESTUD_ASIGPANEL = "Carta con la vista de asignatura del Alumno";
	final static String PROF_TEMAPANEL = "Carta con la vista de tema del Profesor";
	final static String ESTUD_TEMAPANEL = "Carta con la vista de tema del Alumno";
	final static String PROF_EJERPANEL = "Carta con la vista de un ejercicio del Profesor";
	final static String ESTUD_EJERPANEL = "Carta con la vista de un ejercicio del Alumno";
	final static String PROF_APUNTEPANEL = "Carta con la vista de un apuntedel Profesor";
	final static String ESTUD_APUNTEPANEL = "Carta con la vista de un apunte del Alumno";
	final static String PROF_CREARTEMA = "Carta con la vista de crear Tema de un Profesor";
	
	/**
	 * Constructor de la vista LOGIN
	 */
	public FramePrincipal (){
		super("E-DUUDLE");
		super.setFont(new Font("Times New Roman", Font.BOLD, 13));
		//Layout de Card
		
		Container container = this.getContentPane();
		container.setLayout(new CardLayout());
		
		VistaLogin vista_login = new VistaLogin();
		VistaPrincipalProf vista_princiProf = new VistaPrincipalProf();
		VistaPrincipalAlum vista_princiAlum = new VistaPrincipalAlum();
		VistaGestionMatriculas vistaGestion = new VistaGestionMatriculas();
		//VistaAsignaturaProf vista_asigProf = new VistaAsignaturaProf(asignatura);
		//VistaAsignaturaAlum vista_asigAlum = new VistaAsignaturaAlum(asignatura);
		//VistaTemaProf vista_temaProf = new VistaTemaProf(tema);
		//VistaTemaAlum vista_temaAlum = new VistaTemaAlum(tema);
		//VistaEjercicioProf vista_ejercicioProf = new VistaEjercicioProf();
		//VistEjercicioAlum vista_ejercicioAlum = new VistaEjercicioAlum();
		//VistaApunteProf vista_apunteProf = new VistaApunteProf();
		//VistaApunteAlum vista_apunteAlum = new VistaApunteAlum();
		//VistaCrearAsignatura vista_crearAsignatura = new VistaCrearAsignatura();
		//VistaCrearTema vista_crearTema = new VistaCrearTema();

		ControlVistaLogin controladorLogin = new ControlVistaLogin(vista_login, this);
		ControlVistaPrincipalProf controladorPrincipalProf = new ControlVistaPrincipalProf(vista_princiProf, this);
		ControlVistaPrincipalAlum controlador = new ControlVistaPrincipalAlum(vista_princiAlum, this);
		//ControlVistaGestionMatriculas controladorMats = new ControlVistaGestionMatriculas();
		//ControlVistaAsignaturaProf controlador = new ControlVistaAsignaturaProf(vista_asigProf, asignatura, this);
		//ControlVistaAsignaturaAlum controlador = new ControlVistaAsignaturalAlum(vista_asigAlum, this);
		//ControlVistaTemaProf controlador = new ControlVistaTemaProf(vista_temaProf, this);
		//ControlVistaTemaAlum controlador = new ControlVistaTemaAlum(vista_temaAlum, this);
		//ControlVistaEjercicioProf controlador = new ControlVistaEjercicioProf(vista_ejercicioProf, this);
		//ControlVistaEjercicioAlum controlador = new ControlVistaEjercicioAlum(vista_ejercicioAlum, this);
		//ControlVistaApunteProf controlador = new ControlVistaApunteProf(vista_apunteProf, this);
		//ControlVistaAPunteAlum controlador = new ControlVistaApunteAlum(vista_apunteAlum, this);
		//ControlVistaCrearAsignatura controlador = new ControlVistaCrearAsignatura(vista_crearAsignatura, this);
		//ControlVistaCrearTema controlador = new ControlVistaCrearTema(vista_crearTema, this);
		
		vista_login.setControlador(controladorLogin);
		vista_princiProf.setControlador(controladorPrincipalProf);
		vista_princiAlum.setControlador(controlador);
	
		//vista_asigProf.setControlador(controlador);
		//vista_asigAlum.setControlador(controlador);
		//vista_temaProf.setControlador(controlador);
		//vista_temaAlum.setControlador(controlador);
		//vista_ejercicioProf.setControlador(controlador);
		//vista_ejercicioAlum.setControlador(controlador);
		//vista_crearAsignatura.setControlador(controlador);
		//vista_crearTema.setControlador(controlador);
		
		//Aniadimos los componentes al container
		container.add(vista_login, LOGINPANEL);
		container.add(vista_princiProf, PROF_PRINCIPANEL);
		container.add(vista_princiAlum, ESTUD_PRINCIPANEL);
		container.add(vistaGestion, GESTIONPANEL);
		//container.add(vista_asigProf, PROF_ASIGPANEL);
		//container.add(vista_asigAlum, ESTUD_ASIGPANEL);
		//container.add(vista_temaProf, PROF_TEMAPANEL);
		//container.add(vista_temaAlum, ESTUD_TEMAPANEL);
		//container.add(vista_crearAsignatura, PROF_CREARASIGNATURA);
		//container.add(vista_crearTema, PROF_CREARTEMA);
		
	
		//Colocar los componentes de acuerdo a sus tamaños
		this.setPreferredSize(new Dimension(800, 450));
		this.pack();
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void mostrarLogin(){
		CardLayout cl = (CardLayout)(this.getContentPane().getLayout());
		cl.show(this.getContentPane(), LOGINPANEL);
	}
	
	public void mostrarVistaPrinciProf(){
		CardLayout cl = (CardLayout)(this.getContentPane().getLayout());
		cl.show(this.getContentPane(), PROF_PRINCIPANEL);
	}
	
	public void mostrarVistaPrinciEstudiante(){
		CardLayout cl = (CardLayout)(this.getContentPane().getLayout());
		cl.show(this.getContentPane(), ESTUD_PRINCIPANEL);
	}

	public void mostrarVistaGestionMatriculas() {
		CardLayout cl = (CardLayout)(this.getContentPane().getLayout());
		cl.show(this.getContentPane(), GESTIONPANEL);
		
	}
}
