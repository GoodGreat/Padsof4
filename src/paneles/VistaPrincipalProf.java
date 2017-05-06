package paneles;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;
import sistema.*;
import ventanas.FramePrincipal;
import asignatura.*;
import controladores.ControlVistaCrearAsignatura;
import ejercicio.*;


public class VistaPrincipalProf extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private JPanel principalProf, panel_menu, panel_botones, panel_arbol;
	final static String PROF_ASIGPANEL = "Carta con la vista de asignatura del Profesor";
	final static String PROF_CREARASIGNATURA = "Carta con la vista de crear Asignaturas de un Profesor";
	final static String PRINCIPAL = "Carta con la vista principal Profesor";
	final static String GESTIONPANEL = "Carta para la gestion de matriculas";
	private JLabel etiquetaNombre;
	private JButton botonCrearAsig;
	private JButton botonRegistrarAlumno;
	private JButton botonGestionSolicitudes;
	private JButton botonMostrarAsig;
	private JButton botonLogout;
	private DefaultMutableTreeNode raiz;
	private DefaultTreeModel modelo;
	private JTree arbol;
	
	/**
	 * Constructor de la vista principal de profesor
	 */
	public VistaPrincipalProf(){
		CardLayout card_layout = new CardLayout();
		this.setLayout(card_layout);
		
		this.principalProf = new JPanel();

		//Panel de menu
		this.panel_menu = new JPanel();
		SpringLayout layout = new SpringLayout();
		panel_menu.setLayout(layout);
		panel_menu.setPreferredSize(new Dimension(800, 50));
		
		//Panel de botones 
		this.panel_botones = new JPanel();
		
		//Panel del arbol de contenido 
		this.panel_arbol = new JPanel();
				
		principalProf.setLayout(new BorderLayout());
		
		//Creamos nuestros componentes
		etiquetaNombre = new JLabel("MENU PRINCIPAL");
		etiquetaNombre.setFont(new Font("Times New Roman", Font.BOLD, 18));
		panel_menu.add(etiquetaNombre);
		
		layout.putConstraint(SpringLayout.NORTH, etiquetaNombre, 20, SpringLayout.NORTH, panel_menu);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiquetaNombre, 0, SpringLayout.HORIZONTAL_CENTER, panel_menu);
				
		botonCrearAsig = new JButton("Crear Asignatura");
		botonCrearAsig.setPreferredSize(new Dimension(150,75));
		panel_botones.add(botonCrearAsig);
		
		botonMostrarAsig = new JButton("Mostrar Asignaturas");
		botonMostrarAsig.setPreferredSize(new Dimension(175,75));
		panel_botones.add(botonMostrarAsig);
		
		botonRegistrarAlumno =  new JButton("Registrar Alumno");
		botonRegistrarAlumno.setPreferredSize(new Dimension(150,75));
		panel_botones.add(botonRegistrarAlumno);
		
		botonLogout = new JButton("Log-Out");
		botonLogout.setPreferredSize(new Dimension(120, 30));
		panel_menu.add(botonLogout);
		
		layout.putConstraint(SpringLayout.NORTH, botonLogout, 20, SpringLayout.NORTH, panel_menu);
		layout.putConstraint(SpringLayout.WEST, botonLogout, 10, SpringLayout.WEST, panel_menu);
		
		botonGestionSolicitudes = new JButton("Gestión Solicitudes");
		botonGestionSolicitudes.setPreferredSize(new Dimension(150,75));
		panel_botones.add(botonGestionSolicitudes);
		
		int i = 0;
		int j = 0;	
		
		// Crear el nodo raíz del árbol, pasando el texto que mostrará
	    raiz = new DefaultMutableTreeNode("Asignaturas");
	    
		// Crear el modelo de datos del árbol, pasando el nodo raíz
		modelo = new DefaultTreeModel(raiz);
		 
		// Crear el árbol, pasándole el modelo de datos
		arbol = new JTree (modelo);
		
		// Podemos fijar el tamaño del árbol
		arbol.setPreferredSize(new Dimension(200, 200));
		//arbol.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		Sistema sistema = Sistema.getInstance();
		
		for(Asignatura asignaturaAux: sistema.getAsignaturas()){
			DefaultMutableTreeNode nodo_asignatura = new DefaultMutableTreeNode(asignaturaAux);
			modelo.insertNodeInto(nodo_asignatura, raiz, i);
			j = 0;
			for(Tema temaAux: asignaturaAux.getTemas()){
				DefaultMutableTreeNode nodo_tema = new DefaultMutableTreeNode(temaAux);
				modelo.insertNodeInto(nodo_tema, nodo_asignatura, j);
				construirArbol(temaAux, nodo_tema);
				j++;
			}
			i++;
		}
		
		this.panel_arbol.add(arbol);

		this.principalProf.setPreferredSize(new Dimension(400, 150));
		this.principalProf.add(panel_menu, BorderLayout.NORTH);
		this.principalProf.add(panel_botones, BorderLayout.SOUTH);
		this.principalProf.add(panel_arbol, BorderLayout.EAST);
		//layout.putConstraint(SpringLayout.NORTH, arbol, 30, SpringLayout.NORTH, etiquetaNombre);
		
		this.add(principalProf, PRINCIPAL);
		VistaCrearAsignatura vista_crearAsignatura = new VistaCrearAsignatura();
		ControlVistaCrearAsignatura control_crearAsignatura = new ControlVistaCrearAsignatura(vista_crearAsignatura, this);
		vista_crearAsignatura.setControlador(control_crearAsignatura);
		this.add(vista_crearAsignatura, PROF_CREARASIGNATURA);
	}
	
	/**
	 * Metodo que sirve para actualizar el estado del arbol de asignaturas
	 * @author Álvaro Martinez de Navascues
	 */
	private void actualizarArbol(){
		
		//Borrar el arbol
		for (int i = 0; i < raiz.getChildCount(); i++){
			DefaultMutableTreeNode treeNodeAux = (DefaultMutableTreeNode) raiz.getChildAt(i);
			modelo.removeNodeFromParent(treeNodeAux);
		}
		int i = 0, j = 0;
				
		Sistema sistema = Sistema.getInstance();
		
		for(Asignatura asignaturaAux: sistema.getAsignaturas()){
			DefaultMutableTreeNode nodo_asignatura = new DefaultMutableTreeNode(asignaturaAux);
			modelo.insertNodeInto(nodo_asignatura, raiz, i);
			j = 0;
			for(Tema temaAux: asignaturaAux.getTemas()){
				DefaultMutableTreeNode nodo_tema = new DefaultMutableTreeNode(temaAux);
				modelo.insertNodeInto(nodo_tema, nodo_asignatura, j);
				construirArbol(temaAux, nodo_tema);
				j++;
			}
			i++;
		}
	}
	
	/**
	 * Metodo que construye el arbol estructurado de la Aplicacion, empezando en Asignaturas
	 * @author Alejandro Martin Climent
	 * @param tema. Tema a partir del cual se construyen las ramas del arbol
	 * @param nodo. Nodo raiz de la rama
	 */
	private void construirArbol(Tema tema, DefaultMutableTreeNode nodo){
		int i = 0;
		 
		for(Ejercicio ejercicioAux: tema.getEjercicios()){
			modelo.insertNodeInto(new DefaultMutableTreeNode(ejercicioAux), nodo, i);
			i++;
		}
		
		for(Apunte apunteAux: tema.getApuntes()){
			modelo.insertNodeInto(new DefaultMutableTreeNode(apunteAux), nodo, i);
			i++;
		}
		for(Tema temaAux: tema.getSubtemas()){
			DefaultMutableTreeNode nodo_subtema = new DefaultMutableTreeNode(temaAux);
			modelo.insertNodeInto(nodo_subtema, nodo, i);
			i++;
			
			construirArbol(temaAux, nodo_subtema);
		}
	}
	
	/**
	 * Metodo que sirve para aniadir un controlador a los botones de esta vista
	 * @author Álvaro Martinez de Navascues
	 * @param controlador. Controlador que se quiere asignar
	 */
    public void setControlador(ActionListener controlador){
		this.botonCrearAsig.addActionListener(controlador);
		this.botonMostrarAsig.addActionListener(controlador);
	    this.botonRegistrarAlumno.addActionListener(controlador);
	 	this.botonGestionSolicitudes.addActionListener(controlador);
	 	this.botonLogout.addActionListener(controlador);
	 	
	 	//Controlador del arbol
	 	/*arbol.addTreeSelectionListener(new TreeSelectionListener() {
	 		public void valueChanged(TreeSelectionEvent event){
	 			if (((DefaultMutableTreeNode) event.getPath().getLastPathComponent()).equals(raiz)){
	 				
	 			}else{
					String nombre = arbol.getLastSelectedPathComponent().getClass().getSimpleName();
					if (nombre == "Asignatura") {
						JOptionPane.showMessageDialog(new FramePrincipal(), nombre, "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					} else {
						JOptionPane.showMessageDialog(new FramePrincipal(), nombre, "Error",
								JOptionPane.ERROR_MESSAGE);
					}
	 			}
	 		}
	 	});*/
	}
    
    
    /**
	 * Getter del boton "CrearAsignatura"
	 * @author Alejandro Martin Climent
	 * @return JButton.
	 */
    public JButton getBotonCrearAsig() {
		return botonCrearAsig;
	}
    
    /**
	 * Getter del boton "LOGOUT"
	 * @author Alejandro Martin Climent
	 * @return JButton.
	 */
    public JButton getBotonLogout() {
		return botonLogout;
	}

    /**
	 * Getter del boton "Registrar Alumno"
	 * @author Álvaro Martinez de Navascues
	 * @return JButton.
	 */
	public JButton getBotonRegistrarAlumno() {
		return botonRegistrarAlumno;
	}

	/**
	 * Getter del boton "Gestion Solicitudes"
	 * @author Alejandro Martin Climent
	 * @return JButton.
	 */
	public JButton getBotonGestionSolicitudes() {
		return botonGestionSolicitudes;
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
	public void mostrarVistaPrincipalProf(){
		this.actualizarArbol();
		CardLayout cl = (CardLayout)(this.getLayout());
		cl.show(this, PRINCIPAL);
	}
	
	/**
	 * Metodo que muestra la vista de crear Asignatura
	 * @author Álvaro Martinez de Navascues
	 */
	public void mostrarVistaCrearAsignatura(){
		CardLayout cl = (CardLayout)(this.getLayout());
		cl.show(this, PROF_CREARASIGNATURA);
	}
	
	/**
	 * Metodo que muestra la vista de crear Asignatura
	 * @author Álvaro Martinez de Navascues
	 */
	public void mostrarVistaGestionMatriculas() {
		CardLayout cl = (CardLayout)(this.getLayout());
		cl.show(this, GESTIONPANEL);
		
	}
}