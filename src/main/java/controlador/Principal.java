package controlador;

import modelo.Logica;
import modelo.Reportes;
import vista.VentanaActualizaNotaMesa;
import vista.VentanaAlumno;
import vista.VentanaAlumnoBuscar;
import vista.VentanaAlumnoMateria;
import vista.VentanaAlumnoMateriaNueva;
import vista.VentanaAlumnoMesa;
import vista.VentanaAlumnoParciales;
import vista.VentanaMateria;
import vista.VentanaMateriaBuscar;
import vista.VentanaMateriaNueva;
import vista.VentanaMesa;
import vista.VentanaMesaBuscar;
import vista.VentanaPrincipal;
import vista.VentanaReporteListaAlumnos;
import vista.VentanaReportePlanillasParciales;
import vista.VentanaReportes;

public class Principal {

	//comentario
	VentanaPrincipal miVentanaPrincipal;
	VentanaAlumno miVentanaAlumno;
	VentanaAlumnoBuscar miVentanaAlumnoBuscar;
	VentanaMateria miVentanaMateria;
	VentanaMateriaBuscar miVentanaMateriaBuscar;
	VentanaAlumnoMateria miVentanaAlumnoMateria;
	VentanaAlumnoMateriaNueva miVentanaAlumnoMateriaNueva;
	VentanaAlumnoMesa miVentanaAlumnoMesa;
	VentanaMesa miVentanaMesa;
	VentanaMesaBuscar miVentanaMesaBuscar;
	VentanaActualizaNotaMesa miVentanaActualizaNotaMesa;
	VentanaReportes miVentanaReportes;
	VentanaReporteListaAlumnos miVentanaReporteListaAlumnos;
	VentanaReportePlanillasParciales miVentanaReportePlanillasParciales;
	VentanaAlumnoParciales miVentanaAlumnoParciales;
	VentanaMateriaNueva miVentanaMateriaNueva;
	Logica miLogica;
	Coordinador miCoordinador;	
	Reportes miReporte;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Principal miPrincipal = new Principal();
		miPrincipal.iniciar();
	}

	/**
	 * Permite instanciar todas las clases con las que trabaja
	 * el sistema
	 */

	private void iniciar() {
		/*Se instancian las clases*/
		miVentanaPrincipal = new VentanaPrincipal();
		miVentanaAlumno = new VentanaAlumno();
		miVentanaMateria = new VentanaMateria();
		miVentanaMateriaBuscar = new VentanaMateriaBuscar();
		miVentanaAlumnoMateria = new VentanaAlumnoMateria();
		miVentanaAlumnoMateriaNueva = new VentanaAlumnoMateriaNueva();
		miVentanaAlumnoMesa =  new VentanaAlumnoMesa();
		miVentanaMesa = new VentanaMesa();
		miVentanaMesaBuscar = new VentanaMesaBuscar();
		miLogica = new Logica();
		miVentanaAlumnoBuscar = new VentanaAlumnoBuscar();
		miVentanaActualizaNotaMesa = new VentanaActualizaNotaMesa();
		miVentanaReportes = new VentanaReportes();
		miCoordinador = new Coordinador();
		miReporte = new Reportes();
		miVentanaReporteListaAlumnos = new VentanaReporteListaAlumnos();
		miVentanaReportePlanillasParciales = new VentanaReportePlanillasParciales();
		miVentanaAlumnoParciales = new VentanaAlumnoParciales();
		miVentanaMateriaNueva = new VentanaMateriaNueva();
		
		/*Se establecen las relaciones entre clases*/
		miVentanaPrincipal.setCoordinador(miCoordinador);
		miVentanaAlumno.setCoordinador(miCoordinador);		
		miVentanaAlumnoBuscar.setCoordinador(miCoordinador);
		miVentanaMateria.setCoordinador(miCoordinador);
		miVentanaMateriaBuscar.setCoordinador(miCoordinador);
		miVentanaAlumnoMateria.setCoordinador(miCoordinador);
		miVentanaAlumnoMateriaNueva.setCoordinador(miCoordinador);
		miVentanaAlumnoMesa.setCoordinador(miCoordinador);
		miVentanaMesa.setCoordinador(miCoordinador);
		miVentanaMesaBuscar.setCoordinador(miCoordinador);
		miVentanaActualizaNotaMesa.setCoordinador(miCoordinador);
		miVentanaReportes.setCoordinador(miCoordinador);
		miLogica.setCoordinador(miCoordinador);
		miReporte.setCoordinador(miCoordinador);
		miVentanaReporteListaAlumnos.setMiCoordinador(miCoordinador);
		miVentanaReportePlanillasParciales.setMiCoordinador(miCoordinador);
		miVentanaAlumnoParciales.setMiCoordinador(miCoordinador);
		miVentanaMateriaNueva.setMiCoordinador(miCoordinador);
		
		/*Se establecen relaciones con la clase coordinador*/
		miCoordinador.setMiLogica(miLogica);
		miCoordinador.setMiVentanaPrincipal(miVentanaPrincipal);
		miCoordinador.setMiVentanaAlumno(miVentanaAlumno);
		miCoordinador.setMiVentanaAlumnoBuscar(miVentanaAlumnoBuscar);
		miCoordinador.setMiReporteAlumnos(miReporte);
		miCoordinador.setMiVentanaMateria(miVentanaMateria);
		miCoordinador.setMiVentanaMateriaBuscar(miVentanaMateriaBuscar);
		miCoordinador.setMiVentanaAlumnoMateria(miVentanaAlumnoMateria);
		miCoordinador.setMiVentanaAlumnoMateriaNueva(miVentanaAlumnoMateriaNueva);
		miCoordinador.setMiVentanaAlumnoMesa(miVentanaAlumnoMesa);
		miCoordinador.setMiVentanaMesa(miVentanaMesa);
		miCoordinador.setMiVentanaMesaBuscar(miVentanaMesaBuscar);
		miCoordinador.setMiVentanaActualizaNotaMesa(miVentanaActualizaNotaMesa);
		miCoordinador.setMiVentanaReportes(miVentanaReportes);
		miCoordinador.setMiVentanaReporteListaAlumnos(miVentanaReporteListaAlumnos);
		miCoordinador.setMiVentanaReportePlanillasParciales(miVentanaReportePlanillasParciales);
		miCoordinador.setMiVentanaAlumnoParciales(miVentanaAlumnoParciales);
		miCoordinador.setMiVentanaMateriaNueva(miVentanaMateriaNueva);
		
		miVentanaPrincipal.setVisible(true);
	}

}
