package tester;

import java.util.*;
import sistema.*;
import ventanas.*;

public class TesterGUI {
	
	
	public static void main(String args[]){
		
		/**
		 * CARGAR Y GUARDAR DATOS
		 */
		System.out.println("\nProbando --> Cargar y Guardar Datos <--\n");
		//Creamos dos sistemas
		Sistema sistema = Sistema.getInstance();
		List<Alumno> alumnosAntes = new ArrayList<Alumno>();
		boolean comprobacion = false;
		try{
			//En el primer sistema introducimos datos de alumnos
			sistema.leerDatosAlumno("src/alumnos.txt");
			//Guardamos el sistema en un archivo
			sistema.guardarDatosSistema("src/Sistema.obj");
			
			//Comprobamos que los alumnos se han cargado correctamente
			for (Alumno alumno: sistema.getAlumnos()){
				alumnosAntes.add(alumno);
			}
			
			//Cargamos el sistema anteriormente guardado en un nuevo Sistema
			sistema.cargarDatosSistema("src/Sistema.obj");
			
			//Comprobamos que en el nuevo Sistema, la informacion no se ha alterado
			for (int i = 0; i < sistema.getAlumnos().size(); i++){
				if (alumnosAntes.get(i).getNombre().equals(sistema.getAlumnos().get(i).getNombre())){
					comprobacion = true;
				}else{
					comprobacion = false;
				}
			}
			
			//Comprobar que cada alumno queda totalmente inalterado
			if (comprobacion == true){
				System.out.print("Los alumnos no se han alterado, este mensaje SI debe aparecer");
			}
			
		}catch (Exception e){
			System.err.println("Error");
		}
		
		new FramePrincipal();
	}
}
