package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import paneles.VistaMostrarAsignaturas;
import paneles.VistaPrincipalAlum;

public class ControlVistaMostrarAsignaturas implements ActionListener{
	private VistaMostrarAsignaturas vista;
	private VistaPrincipalAlum vista_principal;
	
	public ControlVistaMostrarAsignaturas(VistaMostrarAsignaturas vista, VistaPrincipalAlum vista_principal){
		this.vista = vista;
		this.vista_principal = vista_principal;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(this.vista.getBotonAtras())){
			this.vista_principal.mostrarVistaPrincipalAlum();
		}		
	}
}
