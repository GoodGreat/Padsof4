package paneles;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableModel;

import sistema.*;

public class VistaGestionMatriculas extends JPanel{

	private String[] titulos = {"alumno", "NIA", "Asignatura"};
	private Object[][] filas;
	private JButton botonAceptarMatricula, botonDenegarMatricula ;
	private static final long serialVersionUID = 1L;
	
	public VistaGestionMatriculas(){
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		
		int i = 0;
		
		JLabel etiquetaTitulo = new JLabel("GESTION DE MATRICULAS");
		this.add(etiquetaTitulo);
		layout.putConstraint(SpringLayout.NORTH, etiquetaTitulo, 10, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiquetaTitulo, 0, SpringLayout.HORIZONTAL_CENTER, this);
		
		Sistema sistema = Sistema.getInstance();
		
		// Crear un array con el título de las columnas
		String[] titulos = {"alumno", "NIA", "Asignatura"};
		
		// Crear una matriz con las filas de la tabla
		//Object[][] filas = new Object[sistema.getSolicitudes().size()][];
		
		
		for(i=0; i < sistema.getSolicitudes().size(); i++){
			{
					filas[i][0] = sistema.getSolicitudes().get(i).getAlumno().getNombre();
					filas[i][1] = sistema.getSolicitudes().get(i).getAlumno().getNumA();
					filas[i][2] = sistema.getSolicitudes().get(i).getAsignatura().getNombre();
			}
		}
		
		// Crear un DefaultTableModel con las filas y los títulos de la tabla
		DefaultTableModel modeloDatos = new DefaultTableModel(filas, titulos);
		
		// Crear un DefaultTableModel con las filas y los títulos de la tabla
		JTable tabla = new JTable(modeloDatos);
		tabla.setPreferredSize(new Dimension(300,300));
		this.add(tabla);
		

		for(i=0; i < sistema.getSolicitudes().size(); i++){
			{
				Object[] nuevaFila = {sistema.getSolicitudes().get(i).getAlumno().getNombre(), sistema.getSolicitudes().get(i).getAlumno().getNumA(),filas[i][2] = sistema.getSolicitudes().get(i).getAsignatura().getNombre()};
				modeloDatos.addRow(nuevaFila);
			}
		}			
		
		layout.putConstraint(SpringLayout.NORTH, tabla, 20, SpringLayout.SOUTH, etiquetaTitulo);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, tabla, 0, SpringLayout.HORIZONTAL_CENTER, this);
		
		
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
		this.setPreferredSize(new Dimension(800, 350));
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
	
}
