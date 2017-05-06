package controladores;

import java.awt.event.*;
import javax.swing.JOptionPane;

import asignatura.Tema;
import paneles.*;
import sistema.*;
import ventanas.*;

public class ControlVistaCrearEjercicio implements ActionListener{
	private VistaCrearEjercicio vista;
	private Sistema sistema;
	private FramePrincipal ventana_principal;
	private Tema tema;
	
	/**
	 * Constructor del controlador de la Vista LOGIN
	 * @author Álvaro Martinez de Navascues
	 * @param vista. Panel que ve el usuario
	 * @param sistema. Clase principal de nuestro proyecto
	 */
	public ControlVistaCrearEjercicio(VistaCrearEjercicio vista, FramePrincipal ventana_principal, Tema tema){
		this.vista = vista;
		this.sistema = Sistema.getInstance();
		this.ventana_principal = ventana_principal;
		this.tema = tema;
		
	}
	

	@Override
	public void actionPerformed(ActionEvent event) {
		// El primer paso es validar lo introducido por el usuario
		
		float peso = Float.parseFloat(this.vista.getAnoIni().getText());
		int anoIni = Integer.parseUnsignedInt(this.vista.getAnoIni().getText());
		int anoFin = Integer.parseUnsignedInt(this.vista.getAnoFin().getText());
		int mesIni = Integer.parseUnsignedInt(this.vista.getMesIni().getText());
		int mesFin = Integer.parseUnsignedInt(this.vista.getMesFin().getText());
		int diaIni = Integer.parseUnsignedInt(this.vista.getDiaIni().getText());
		int diaFin = Integer.parseUnsignedInt(this.vista.getDiaFin().getText());
		int horaIni = Integer.parseUnsignedInt(this.vista.getHoraIni().getText());
		int horaFin = Integer.parseUnsignedInt(this.vista.getHoraFin().getText());
		int minIni = Integer.parseUnsignedInt(this.vista.getMinIni().getText());
		int minFin = Integer.parseUnsignedInt(this.vista.getMinIni().getText());
		
		
		if (this.vista.getNombre().equals("") || this.vista.getPeso().equals("") || this.vista.getAnoIni().equals("") ||
		this.vista.getAnoFin().equals("") || this.vista.getMesIni().equals("") || this.vista.getMesFin().equals("") || 
		this.vista.getDiaIni().equals("") || this.vista.getDiaFin().equals("") || this.vista.getHoraIni().equals("") ||
		this.vista.getHoraFin().equals("") || this.vista.getMinIni().equals("") || this.vista.getMinFin().equals("")){
			JOptionPane.showMessageDialog(this.vista, "Es obligatorio rellenar todos los campos", "Error",
					JOptionPane.ERROR_MESSAGE);
		} else if (sistema.crearEjercicio(tema, this.vista.getNombre(), peso, anoIni, mesIni, diaIni, horaIni, minIni,  
				 anoFin, mesFin, diaFin, horaFin, minFin, this.vista.getComboBoxSelectedVis(), this.vista.getComboBoxSelectedAleat()) == false){
			JOptionPane.showMessageDialog(this.vista, "Error al crear el ejercicio", "Error",
					JOptionPane.ERROR_MESSAGE);
		}else{
			JOptionPane.showMessageDialog(this.vista, "El ejerciico " + this.vista.getNombre() + " ha sido creado con exito", "CREACION DE EJERCICIO", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
