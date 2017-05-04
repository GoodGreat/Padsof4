package paneles;

import java.awt.*;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

import asignatura.*;
import ejercicio.Ejercicio;

public class VistaTemaProf extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private JLabel etiquetaNombre;
	private JButton botonCrearSubtema;
	private JButton botonCrearEjercicio;
	private JButton botonCrearApunte;
	private JButton botonCambiarTema;
	private DefaultMutableTreeNode raiz;
	private DefaultTreeModel modelo;
	
	public VistaTemaProf(Tema tema){
		
		//Panel de etiquetas
		JPanel panel_etiquetas = new JPanel();
						
		//Panel de botones
		JPanel panel_botones = new JPanel();
				
		//Panel del arbol de contenido 
		JPanel panel_arbol = new JPanel();
	
		this.setLayout(new BorderLayout());

				
		//Creamos nuestros componentes
		etiquetaNombre = new JLabel(tema.getNombre());
		panel_etiquetas.add(etiquetaNombre);
		
		botonCrearSubtema = new JButton("Crear Subtema");
		botonCrearSubtema.setPreferredSize(new Dimension(150,75));
		panel_botones.add(botonCrearSubtema);
		
		botonCrearEjercicio = new JButton("Crear Ejercicio");
		botonCrearEjercicio.setPreferredSize(new Dimension(150,75));
		panel_botones.add(botonCrearEjercicio);
		
		botonCrearApunte = new JButton("Crear Apunte");
		botonCrearApunte.setPreferredSize(new Dimension(150,75));
		panel_botones.add(botonCrearApunte);
		
		botonCambiarTema = new JButton("Cambiar Tema");
		botonCambiarTema.setPreferredSize(new Dimension(150,75));
		panel_botones.add(botonCambiarTema);
		
		//Ponemos el norte de la etiqueta del Nombre de la asignatura a 5 pixeles al norte del contenedor 
		//layout.putConstraint(SpringLayout.NORTH, etiquetaNombre, 5, SpringLayout.NORTH, this);
		//Ponemos la derecha del botonCrearTema a 5 pixeles de la izquierda del contenedor 
		//layout.putConstraint(SpringLayout.EAST, botonCrearSubtema, 5, SpringLayout.WEST, this);
		//Ponemos el norte del botonGestionAlum a 5 pixeles al sur del botonCrearTema
		//layout.putConstraint(SpringLayout.NORTH, botonCrearEjercicio, 5, SpringLayout.SOUTH, botonCrearSubtema);		
		//Ponemos el norte del botonCambiarAsig a 5 pixeles de la sur del botonGestionAlum
		//layout.putConstraint(SpringLayout.NORTH, botonCrearApunte, 5, SpringLayout.SOUTH,  botonCrearEjercicio);
		//Ponemos el norte del botonCalcularNota a 5 pixeles de la sur del botonCambiarAsig
		//layout.putConstraint(SpringLayout.NORTH, botonCambiarTema, 5, SpringLayout.SOUTH, botonCrearApunte);
	
		
		// Crear el nodo raíz del árbol, pasando el texto que mostrará
	    raiz = new DefaultMutableTreeNode(tema.getNombre());
	    
		// Crear el modelo de datos del árbol, pasando el nodo raíz
		 modelo = new DefaultTreeModel(raiz);
		 
		// Crear el árbol, pasándole el modelo de datos
		JTree arbol = new JTree (modelo);

				
		// Podemos fijar el tamaño del árbol
		arbol.setPreferredSize(new Dimension(200, 40));
		arbol.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);		
		// Para añadir hijos a un nodo usamos el método insertNodeInto del modelo. El método recibe el nodo
		// a insertar, el nodo padre donde se inserta, y la posición del nodo entre los hijos del padre.
		construirArbol(tema ,raiz);

		panel_arbol.add(arbol);

		this.setPreferredSize(new Dimension(450, 150));
		this.add(panel_etiquetas, BorderLayout.NORTH);
		this.add(panel_botones, BorderLayout.SOUTH);
		this.add(panel_arbol, BorderLayout.EAST);
		
		//Ponemos el ComboBox a 30 pixeles al sur de la etiquetaNombre
		//layout.putConstraint(SpringLayout.NORTH, arbol, 30, SpringLayout.NORTH, etiquetaNombre);
			
	}
	
	public void construirArbol(Tema tema, DefaultMutableTreeNode nodo){
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
}