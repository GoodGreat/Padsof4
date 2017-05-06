package controladores;

import java.awt.event.*;
import paneles.*;
import sistema.Sistema;
import ventanas.*;

public class ControlVistaPrincipalAlum implements ActionListener{
	private VistaPrincipalAlum  vista;
	private FramePrincipal ventana_principal;
	
	/**
	 * Constructor del controlador de la Vista LOGIN
	 * @author Alejandro Martin Climent
	 * @param vista. Panel que ve el usuario
	 * @param ventana_principal Clase principal de nuestro proyecto
	 */
	public ControlVistaPrincipalAlum(VistaPrincipalAlum  vista, FramePrincipal ventana_principal){
		this.vista = vista;
		this.ventana_principal = ventana_principal;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource().equals(this.vista.getBotonMostrarAsig())){
			this.vista.getVista_mostrarAsignaturas().actualizarArbol();
			this.vista.mostrarVistaAsignaturasMat();
		}else if (event.getSource().equals(this.vista.getBotonSolicitarMat())){
			this.vista.mostrarVistaSolicitudMat();
		}else if (event.getSource().equals(this.vista.getBotonLogout())){
			Sistema.getInstance().log_out();
			this.ventana_principal.mostrarLogin();
		}
	}
}
