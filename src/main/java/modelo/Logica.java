package modelo;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import controlador.Coordinador;

public class Logica {
	
	private Coordinador miCoordinador;
	public static boolean consultaMateria=false;
	public static boolean modificaMateria=false;
	public static boolean consultaAlumno=false;
	public static boolean modificaAlumno=false;
	public static boolean modificaAlumnoMateria=false;
	
	public void setCoordinador(Coordinador miCoordinador) {
		this.miCoordinador=miCoordinador;
		
	}

	public void validarRegistro(AlumnoVO miAlumno) {
		
		String msg="";
		boolean hayError=false;
		
		if (miAlumno.getAlapynom().isEmpty()){
			msg+="\n no se ingresó Apellido y nombre";
			hayError=true;
		}
		
		
		if (hayError){
			JOptionPane.showMessageDialog(null, msg, "Error", JOptionPane.ERROR_MESSAGE);
		}
		else{
			AlumnoDAO miAlumnoDAO;
				miAlumnoDAO = new AlumnoDAO();
				miAlumnoDAO.registrarAlumno(miAlumno);	
		}
		
	}

	public  AlumnoVO validarConsulta(String codigoPersona) {
		AlumnoDAO miAlumnoDAO;
		
		try {
			int codigo=Integer.parseInt(codigoPersona);	
			if (codigo > 1000000) {
				miAlumnoDAO = new AlumnoDAO();
				consultaAlumno=true;
				return miAlumnoDAO.buscarAlumno(codigo);						
			}else{
				JOptionPane.showMessageDialog(null,"El documento no es válido","Advertencia",JOptionPane.WARNING_MESSAGE);
				consultaAlumno=false;
			}
			
		}catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null,"Debe ingresar un dato numerico","Error",JOptionPane.ERROR_MESSAGE);
			consultaAlumno=false;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Se ha presentado un Error","Error",JOptionPane.ERROR_MESSAGE);
			consultaAlumno=false;
		}
					
		return null;
	}
	public  AlumnoVO validarConsultaApellido(String apellidoAlumno) {
		AlumnoDAO miAlumnoDAO;
		
		try {
				miAlumnoDAO = new AlumnoDAO();
				consultaAlumno=true;
				return miAlumnoDAO.buscarAlumnoApellido(apellidoAlumno);						
			
		}catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null,"Debe ingresar un dato numerico","Error",JOptionPane.ERROR_MESSAGE);
			consultaAlumno=false;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Se ha presentado un Error","Error",JOptionPane.ERROR_MESSAGE);
			consultaAlumno=false;
		}
					
		return null;
	}
	
	public void validarModificacion(AlumnoVO miAlumno) {
		AlumnoDAO miAlumnoDAO;
		miAlumnoDAO = new AlumnoDAO();
		miAlumnoDAO.modificarAlumno(miAlumno);
		modificaAlumno=true;
	}
	
	public void validarEliminacion(int codigo) {
		AlumnoDAO miAlumnoDAO=new AlumnoDAO();
		miAlumnoDAO.eliminarAlumno(codigo);
	}

	public MateriaVO validarConsultaCodMat(String codigoMateria) {
		MateriaDAO miMateriaDAO;
		try {
			miMateriaDAO = new MateriaDAO();
			consultaMateria=true;
			return miMateriaDAO.buscarMateria(codigoMateria);						
		
		}catch (Exception e) {
		JOptionPane.showMessageDialog(null,"Se ha presentado un Error","Error",JOptionPane.ERROR_MESSAGE);
		consultaMateria=false;
		}
		
		return null;
	}

	public void validarRegistroMateria(MateriaVO miMateria) {
		String msg="Los siguientes errores ocurrieron";
		boolean hayError=false;
		int curso=Integer.valueOf(miMateria.getAnio());
		int modulos=Integer.valueOf(miMateria.getModulos());
		if (String.valueOf(miMateria.getCodmat()).isEmpty()){
			msg+="\n no se ingresó el código de la materia";
			hayError=true;
		}
		if (miMateria.getMatnom().isEmpty()){
			msg+="\n no se ingresó el nombre de la materia";
			hayError=true;
		}
		if (Integer.valueOf(miMateria.getAnio()) == null){
			msg+="\n no se ingresó el curso";
			hayError=true;
		}
		if (curso <1 || curso>4){
			msg+="\n el curso no es válido";
			hayError=true;
		}
		if (miMateria.getPlan().isEmpty()){
			msg+="\n no se ingresó el plan";
			hayError=true;
		}
		if (modulos<1 || modulos>6){
			msg+="\n cantidad incorrecta de módulos";
			hayError=true;
		}
		
		if (hayError){
			JOptionPane.showMessageDialog(null, msg, "Error", JOptionPane.ERROR_MESSAGE);
		}
		else{
			MateriaDAO miMateriaDAO;
				miMateriaDAO = new MateriaDAO();
				miMateriaDAO.registrarMateria(miMateria);	
		}
		
	}

	public void validarModificacionMateria(MateriaVO miMateria) {
		MateriaDAO miMateriaDAO;
		miMateriaDAO = new MateriaDAO();
		miMateriaDAO.modificarMateria(miMateria);
		modificaMateria=true;
		
	}

	public void validarEliminacionMateria(String codMat) {
		MateriaDAO miMateriaDAO=new MateriaDAO();
		miMateriaDAO.eliminarMateria(codMat);
	}

	public MateriaVO validarConsultaMateriaNombre(String matNom) {
		MateriaDAO miMateriaDAO;
		
		try {
				miMateriaDAO = new MateriaDAO();
				consultaMateria=true;
				return miMateriaDAO.buscarMateriaNombre(matNom);						
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Se ha presentado un Error","Error",JOptionPane.ERROR_MESSAGE);
			consultaAlumno=false;
		}
					
		return null;
	}
	// se anulo porque venia de VentanaAlumnoMateria y ahora las altas solo de
	// la VentanaAlumnoMateriaNueva
	/*public void validarRegistroAlumnoMateria(AlumnomateriaVO miAlumnomateria) {
		AlumnomateriaDAO miAlumnomateriaDAO = new AlumnomateriaDAO();
		miAlumnomateriaDAO.registrarAlumnoMateria(miAlumnomateria);	
	}*/

	public void validarModificacionAlumnoMateria(AlumnomateriaVO miAlumnomateria) {
		AlumnomateriaDAO miAlumnomateriaDAO = new AlumnomateriaDAO();
		miAlumnomateriaDAO.modificarAlumnoMateria(miAlumnomateria);	
		modificaAlumnoMateria=true;
		
	}

	public void validarRegistroMesa(MesaVO miMesa) {	
			MesaDAO miMesaDAO = new MesaDAO();
			String fecha=miMesa.getMesafecha();
			int mes=Integer.valueOf(fecha.substring(5, 7));
			String anio=fecha.substring(0,4);
			if (mes==2 || mes==3 || mes==7 || mes==8 || mes==11 || mes==12)
				miMesaDAO.validarRegistrarMesa(miMesa,anio,mes);
			else
				JOptionPane.showMessageDialog(null,"Se ingresó un mes equivocado","Error",JOptionPane.ERROR_MESSAGE);
	}

	public void validarModificacionMesa(MesaVO miMesa) {
		MesaDAO miMesaDAO = new MesaDAO();
		String fecha=miMesa.getMesafecha();
		int mes=Integer.valueOf(fecha.substring(5, 7));
		String anio=fecha.substring(0,4);
		if (mes==2 || mes==3 || mes==8 || mes==11 || mes==12)
			miMesaDAO.modificarMesa(miMesa);
		else
			JOptionPane.showMessageDialog(null,"Se ingresó un mes equivocado","Error",JOptionPane.ERROR_MESSAGE);
	}

	

	}

	




