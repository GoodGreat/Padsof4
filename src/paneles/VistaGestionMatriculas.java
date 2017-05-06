package paneles;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableModel;

import sistema.*;

public class VistaGestionMatriculas extends JPanel{

	private String[] titulos = {"alumno", "NIA", "Asignatura"};
	private Object[][] filas;
	private JButton botonAceptarMatricula, botonDenegarMatricula, botonVolver ;
	private static final long serialVersionUID = 1L;
	
	public VistaGestionMatriculas(){
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		
		int i = 0;
		
		JLabel etiquetaTitulo = new JLabel("GESTION DE MATRICULAS");
		this.add(etiquetaTitulo);
		layout.putConstraint(SpringLayout.NORTH, etiquetaTitulo, 10, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiquetaTitulo, 0, SpringLayout.HORIZONTAL_CENTER, this);
		
		
		botonVolver = new JButton("Volver");
		botonVolver.setPreferredSize(new Dimension(100,60));
		this.add(botonVolver);
		
		layout.putConstraint(SpringLayout.NORTH, botonVolver, 10, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, botonVolver, -320, SpringLayout.HORIZONTAL_CENTER, this);
		
		Sistema sistema = Sistema.getInstance();
		
		// Crear un array con el título de las columnas
		String[] titulo = {"alumno", "NIA", "Asignatura"};
		
		// Crear una matriz con las filas de la tabla
		Object[][] filas = new Object[sistema.getSolicitudes().size()][3];
		
		
		for(i=0; i < sistema.getSolicitudes().size(); i++){
			{
					filas[i][0] = sistema.getSolicitudes().get(i).getAlumno().getNombre();
					filas[i][1] = sistema.getSolicitudes().get(i).getAlumno().getNumA();
					filas[i][2] = sistema.getSolicitudes().get(i).getAsignatura().getNombre();
			}
		}
		
		// Crear un DefaultTableModel con las filas y los títulos de la tabla
		DefaultTableModel modeloDatos = new DefaultTableModel(filas, titulo);
		
		// Crear un DefaultTableModel con las filas y los títulos de la tabla
		JTable tabla = new JTable(modeloDatos);
		//tabla.setPreferredSize(new Dimension(300,150));
		//this.add(tabla);
		
		//tabla.setSelectionModel(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		JScrollPane jscroll = new JScrollPane(tabla);
		JPanel panel = new JPanel();
		panel.add(jscroll);
		this.add(panel);
		panel.setPreferredSize(new Dimension(400,200));
		layout.putConstraint(SpringLayout.NORTH, panel, 20, SpringLayout.SOUTH, etiquetaTitulo);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, panel, 0, SpringLayout.HORIZONTAL_CENTER, this);
		
		
		botonAceptarMatricula = new JButton("Aceptar Matricula");
		botonAceptarMatricula.setPreferredSize(new Dimension(150,75));
		this.add(botonAceptarMatricula);
		
		layout.putConstraint(SpringLayout.SOUTH, botonAceptarMatricula, -5, SpringLayout.SOUTH, this);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, botonAceptarMatricula, -75, SpringLayout.HORIZONTAL_CENTER, this);
		
		botonDenegarMatricula = new JButton("Denegar Matricula");
		botonDenegarMatricula.setPreferredSize(new Dimension(150,75));
		this.add(botonDenegarMatricula);
		
		layout.putConstraint(SpringLayout.SOUTH, botonDenegarMatricula, -5, SpringLayout.SOUTH, this);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, botonDenegarMatricula, 75, SpringLayout.HORIZONTAL_CENTER, this);
		this.setPreferredSize(new Dimension(800, 500));
	}
	
	// La clase debe implementar obligatoriamente los métodos getColumnCount, getRowCount y getValueAt
	public int getColumnCount() {
		return titulos.length;
	}
	
	// Método que devuelve el número de columnas
	public int getRowCount() {
		return filas.length;
	}
	
	// Método que devuelve el número de filas
	public Object getValueAt(int row, int col) {
		return filas[row][col];
	} // Método que devuelve el contenido de una celda
	
	
	 /**
		 * Getter del boton "CrearAsignatura"
		 * @author Alejandro Martin Climent
		 * @return JButton.
		 */
	    public JButton getBotonAceptarMatricula() {
			return botonAceptarMatricula;
		}
	    
	    /**
		 * Getter del boton "CrearAsignatura"
		 * @author Alejandro Martin Climent
		 * @return JButton.
		 */
	    public JButton getBotonDenegarMatricula() {
			return botonDenegarMatricula;
		}
	    
	    
	    /**
		 * Getter del boton "CrearAsignatura"
		 * @author Alejandro Martin Climent
		 * @return JButton.
		 */
	    public JButton getBotonVolver() {
			return botonVolver;
		}

		/**
		 * Metodo que muestra la vista de crear Asignatura
		 * @author Álvaro Martinez de Navascues
		 */
		public void mostrarVistaPrincipalProf(){
			CardLayout cl = (CardLayout)(this.getLayout());
			cl.show(this, vista_prof);
		}
		
	  public void setControlador(ActionListener controlador){
			this.botonVolver.addActionListener(controlador);
			this.botonAceptarMatricula.addActionListener(controlador);
			this.botonDenegarMatricula.addActionListener(controlador);
	  }
	
}
