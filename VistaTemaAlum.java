package paneles;

import java.awt.*;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

import asignatura.*;
import ejercicio.Ejercicio;

public class VistaTemaAlum extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private JLabel etiquetaNombre;
	private JButton botonCrearSubtema;
	private JButton botonCrearEjercicio;
	private JButton botonCrearApunte;
	private JButton botonCambiarTema;
	private DefaultMutableTreeNode raiz;
	private DefaultTreeModel modelo;
	
	public VistaTemaAlum(Tema tema){
		//SpringLayout layout = new SpringLayout();
		//this.setLayout(layout);
		
		//Panel de etiquetas
		JPanel panel_etiquetas = new JPanel();
		//panel_etiquetas.setLayout(layout1);
								
		//Panel de botones
		JPanel panel_botones = new JPanel();
		//panel_botones.setLayout(layout2);
								
		//Panel del arbol de contenido 
		JPanel panel_arbol = new JPanel();
		//panel_arbol.setLayout(layout3);
						
		this.setLayout(new BorderLayout());

				
		//Creamos nuestros componentes
		etiquetaNombre = new JLabel(tema.getNombre());
		panel_etiquetas.add(etiquetaNombre);
		
		
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
		arbol.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
				
		// Podemos fijar el tamaño del árbol
		arbol.setPreferredSize(new Dimension(200, 40));
				
		
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
			if(ejercicioAux.getVisible() == true){
				modelo.insertNodeInto(new DefaultMutableTreeNode(ejercicioAux), nodo, i);
				i++;
			}
		}
		
		for(Apunte apunteAux: tema.getApuntes()){
			if(apunteAux.getVisible() == true){
				modelo.insertNodeInto(new DefaultMutableTreeNode(apunteAux), nodo, i);
				i++;
			}
		}
		for(Tema temaAux: tema.getSubtemas()){
			if(temaAux.getVisible() == true){
				DefaultMutableTreeNode nodo_subtema = new DefaultMutableTreeNode(temaAux);
				modelo.insertNodeInto(nodo_subtema, nodo, i);
				i++;
				construirArbol(temaAux, nodo_subtema);
			}
		}	
	}
}