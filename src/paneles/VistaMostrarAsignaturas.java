package paneles;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;
import sistema.*;
import asignatura.*;

public class VistaMostrarAsignaturas extends JPanel{
	private static final long serialVersionUID = 1L;
	private DefaultMutableTreeNode raiz;
	private DefaultTreeModel modelo;
	private JButton botonAtras;
	private JTree arbol;
	private JLabel titulo;
	
	/**
	 * Constructor de la vista de Asignaturas Matriculadas
	 * @author Alejandro Martin Climent
	 */
	public VistaMostrarAsignaturas(){
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		
		titulo = new JLabel("ASIGNATURAS");
		titulo.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 20));
		this.add(titulo);
		
		//Constraints
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, titulo, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, titulo, 20, SpringLayout.NORTH, this);
		
		
		botonAtras = new JButton("Volver atras");
		botonAtras.setPreferredSize(new Dimension(120, 30));
		this.add(botonAtras);
		
		// Crear el nodo raíz del árbol, pasando el texto que mostrará
	    raiz = new DefaultMutableTreeNode("Asignaturas");
	    
		// Crear el modelo de datos del árbol, pasando el nodo raíz
		modelo = new DefaultTreeModel(raiz);
		 
		// Crear el árbol, pasándole el modelo de datos
		arbol = new JTree (modelo);
		
		// Podemos fijar el tamaño del árbol
		arbol.setPreferredSize(new Dimension(800, 300));
		arbol.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		
		this.add(arbol);
		layout.putConstraint(SpringLayout.NORTH, arbol, 50, SpringLayout.NORTH, this);
		
		layout.putConstraint(SpringLayout.NORTH, botonAtras, 0, SpringLayout.NORTH, titulo);
		layout.putConstraint(SpringLayout.EAST, botonAtras, 0, SpringLayout.EAST, this);
	}

	
	/**
	 * Metodo que sirve para actualizar el estado del arbol de asignaturas
	 * @author Álvaro Martinez de Navascues
	 */
	public void actualizarArbol(){
		
		//Borrar el arbol
		for (int i = 0; i < raiz.getChildCount(); i++){
			DefaultMutableTreeNode treeNodeAux = (DefaultMutableTreeNode) raiz.getChildAt(i);
			modelo.removeNodeFromParent(treeNodeAux);
		}
		int i = 0;
				
		Sistema sistema = Sistema.getInstance();
		
		for(Asignatura asignaturaAux: sistema.getAlumnoLogueado().getAsignaturas()){
			DefaultMutableTreeNode nodo_asignatura = new DefaultMutableTreeNode(asignaturaAux.getNombre());
			modelo.insertNodeInto(nodo_asignatura, raiz, i);
			i++;
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
	 * Metodo que sirve para aniadir un controlador a los botones de Solicitud de Matricula
	 * @author Alejandro Martin Climent
	 * @param controlador. Controlador que se quiere asignar
	 */
	public void setControlador(ActionListener controlador){
		this.botonAtras.addActionListener(controlador);
	}
}


