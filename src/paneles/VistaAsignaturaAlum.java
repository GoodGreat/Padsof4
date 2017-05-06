package paneles;

import java.awt.*;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import asignatura.*;
import ejercicio.*;

public class VistaAsignaturaAlum extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private JLabel etiquetaNombre;
	private DefaultMutableTreeNode raiz;
	private DefaultTreeModel modelo;
	
	public VistaAsignaturaAlum(Asignatura asignatura){
		//SpringLayout layout = new SpringLayout();
		//this.setLayout(layout);
		int i = 0;
		
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
		etiquetaNombre = new JLabel(asignatura.getNombre());
		panel_etiquetas.add(etiquetaNombre);
		
		//Ponemos el norte de la etiqueta del Nombre de la asignatura a 5 pixeles al norte del contenedor 
		//layout.putConstraint(SpringLayout.NORTH, etiquetaNombre, 5, SpringLayout.NORTH, this);
		
		// Crear el nodo ra�z del �rbol, pasando el texto que mostrar�
	    raiz = new DefaultMutableTreeNode(asignatura.getNombre());
	    
		// Crear el modelo de datos del �rbol, pasando el nodo ra�z
		 modelo = new DefaultTreeModel(raiz);
		 
		// Crear el �rbol, pas�ndole el modelo de datos
		JTree arbol = new JTree (modelo);
		
		// Podemos fijar el tama�o del �rbol
		arbol.setPreferredSize(new Dimension(200, 40));
				
		// Para a�adir hijos a un nodo usamos el m�todo insertNodeInto del modelo. El m�todo recibe el nodo
		// a insertar, el nodo padre donde se inserta, y la posici�n del nodo entre los hijos del padre.
		
		for(Tema temaAux: asignatura.getTemas()){
			DefaultMutableTreeNode nodo_tema = new DefaultMutableTreeNode(temaAux);
			modelo.insertNodeInto(nodo_tema, raiz, i);
			construirArbol(temaAux, nodo_tema);
			i++;
		}


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