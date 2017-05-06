package controladores;

import java.awt.event.*;
import javax.swing.JOptionPane;

import asignatura.Asignatura;
import paneles.*;
import sistema.*;

public class ControlVistaSolicitudMatricula implements ActionListener{
	private VistaSolicitudMatricula vista;
	private VistaPrincipalAlum vista_principalAlum;
	
	/**
	 * Constructor del controlador de la Vista LOGIN
	 * @author Álvaro Martinez de Navascues
	 * @param vista. Panel que ve el usuario
	 * @param sistema. Clase principal de nuestro proyecto
	 */
	public ControlVistaSolicitudMatricula(VistaSolicitudMatricula vista, VistaPrincipalAlum vista_principalAlum){
		this.vista = vista;
		this.vista_principalAlum = vista_principalAlum;
	}
	Sistema sistema = Sistema.getInstance();
	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource().equals(this.vista.getBotonEnviarSolicitud())){
			for (Asignatura asigAux: Sistema.getInstance().getAsignaturas()){
				if (asigAux.getNombre().equals((String) this.vista.getComboBox().getSelectedItem())){
					if (Sistema.getInstance().crearSolicitud(asigAux) == true){
						JOptionPane.showMessageDialog(this.vista, "La solicitud ha sido enviada con exito",
								"ENVIO DE SOLICITUD", JOptionPane.INFORMATION_MESSAGE);
								sistema.crearSolicitud(asigAux);
						this.vista_principalAlum.mostrarVistaPrincipalAlum();
					}else{
						JOptionPane.showMessageDialog(this.vista, "Hubo un error en el envio de la Solicitud", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}			
		}else if (event.getSource().equals(this.vista.getBotonAtras())){
			this.vista_principalAlum.mostrarVistaPrincipalAlum();
		}		
	}
}