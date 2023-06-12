package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class MateriaDAO {
	public void registrarMateria(MateriaVO miMateria)
	{
		Conexion conex= new Conexion();
		
		try {
			Statement estatuto = conex.getConnection().createStatement();
			estatuto.executeUpdate("INSERT INTO materia VALUES ('"+miMateria.getCodmat()+"', '"
					+miMateria.getMatnom()+"', '"+miMateria.getAnio()+"', '"
					+miMateria.getPlan()+"', '"+miMateria.getModulos()+"')");
			JOptionPane.showMessageDialog(null, "Se ha registrado Exitosamente","Información",JOptionPane.INFORMATION_MESSAGE);
			estatuto.close();
			conex.desconectar();
			
		} catch (SQLException e) {
            System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "No se Registro");
		}
	}

	public MateriaVO buscarMateria(String codigo) 
	{
		Conexion conex= new Conexion();
		MateriaVO materia= new MateriaVO();
		boolean existe=false;
		try {
			String consulta = "SELECT * FROM materia where codmat = ? ";
			PreparedStatement estatuto = conex.getConnection().prepareStatement(consulta);
			estatuto.setString(1,codigo);
			ResultSet res = estatuto.executeQuery();
					
			while(res.next()){
				existe=true;
				//se debe escribir el nombre de la columna de la tabla
				materia.setCodmat(res.getString("codmat"));
				materia.setMatnom(res.getString("matnom"));
				materia.setAnio(Integer.parseInt(res.getString("anio")));
				materia.setPlan(res.getString("plan"));
				materia.setModulos(Integer.parseInt(res.getString("modulos")));
			 }
			res.close();
			conex.desconectar();
					
					
			} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "Error, no se conecto");
					System.out.println(e);
			}
		
			if (existe) {
				return materia;
			}
			else return null;				
	}

	public MateriaVO buscarMateriaNombre(String nombre) 
	{
		Conexion conex= new Conexion();
		MateriaVO materia= new MateriaVO();
		boolean existe=false;
		try {
			String consulta = "SELECT * FROM materia where matnom = ? ";
			PreparedStatement estatuto = conex.getConnection().prepareStatement(consulta);
			estatuto.setString(1,nombre);
			ResultSet res = estatuto.executeQuery();
					
			while(res.next()){
				existe=true;
				//se debe escribir el nombre de la columna de la tabla
				materia.setCodmat(res.getString("codmat"));
				materia.setMatnom(res.getString("matnom"));
				materia.setAnio(Integer.parseInt(res.getString("anio")));
				materia.setPlan(res.getString("plan"));
				materia.setModulos(Integer.parseInt(res.getString("modulos")));
			 }
			res.close();
			conex.desconectar();
					
					
			} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "Error, no se conecto");
					System.out.println(e);
			}
		
			if (existe) {
				return materia;
			}
			else return null;				
	}

	public void modificarMateria(MateriaVO miMateria) {
		
		Conexion conex= new Conexion();
		try{
			String consulta="UPDATE materia SET codmat= ? ,matnom = ? , anio=? , plan=? , "
					+ "modulos= ? WHERE codmat= ? ";
			PreparedStatement estatuto = conex.getConnection().prepareStatement(consulta);
			
            estatuto.setString(1, miMateria.getCodmat());
            estatuto.setString(2, miMateria.getMatnom());
            estatuto.setInt(3, miMateria.getAnio());
            estatuto.setString(4, miMateria.getPlan());
            estatuto.setInt(5, miMateria.getModulos());
            estatuto.setString(6, miMateria.getCodmat());
            estatuto.executeUpdate();

			
          JOptionPane.showMessageDialog(null, " Se ha Modificado Correctamente ","Confirmación",JOptionPane.INFORMATION_MESSAGE);
         

        }catch(SQLException	 e){

            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Error al Modificar","Error",JOptionPane.ERROR_MESSAGE);

        }
	}

	public void eliminarMateria(String codigo)
	{
		Conexion conex= new Conexion();
		try {
			Statement estatuto = conex.getConnection().createStatement();
			estatuto.executeUpdate("DELETE FROM materia WHERE codmat='"+codigo+"'");
            JOptionPane.showMessageDialog(null, " Se ha Eliminado Correctamente","Información",JOptionPane.INFORMATION_MESSAGE);
			estatuto.close();
			conex.desconectar();
			
		} catch (SQLException e) {
            System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "<html>No se Elimino, puede tener alumnos anotados</b> o correlativas asignadas");
		}
		
	
	}

	public void buscarMateria(DefaultTableModel modelo) {
		Conexion conex = new Conexion();
		try{
			Statement estatuto2 = conex.getConnection().createStatement();
			ResultSet res2 = estatuto2.executeQuery("SELECT codmat,matnom,anio,plan,"
					+ "modulos from materia order by anio");
			
			while (res2.next()){
				//crea un vector de objetos para guardar los datos del ResultSet y luego poner en el JTable
				Object fila[]= new Object[5];
				//llena cada columna de la fila
				for (int i=0; i<5;i++)
					fila[i]=res2.getObject(i+1);
				//ahora carga la fila a la tabla modelo
				modelo.addRow(fila);
			}
			res2.close();
			estatuto2.close();
			conex.desconectar();
		}		catch (SQLException e){
					JOptionPane.showMessageDialog(null, "Error al consultar","Error",JOptionPane.ERROR_MESSAGE);
		}
		
	}

	public void buscarMaterias(DefaultTableModel modeloMateria, String plan) {
		Conexion conex = new Conexion();
		try{
			Statement estatutoMat = conex.getConnection().createStatement();
			ResultSet resMat = estatutoMat.executeQuery("SELECT codmat,matnom,anio,plan,"
					+ "modulos from materia where plan='"+plan+"' order by anio");
			
			while (resMat.next()){
				//crea un vector de objetos para guardar los datos del ResultSet y luego poner en el JTable
				Object fila[]= new Object[5];
				//llena cada columna de la fila
				for (int i=0; i<5;i++)
					fila[i]=resMat.getObject(i+1);
				//ahora carga la fila a la tabla modelo
				modeloMateria.addRow(fila);
			}
			resMat.close();
			estatutoMat.close();
			conex.desconectar();
		}		catch (SQLException e){
					JOptionPane.showMessageDialog(null, "Error al consultar","Error",JOptionPane.ERROR_MESSAGE);
		}
		
	}

	public void cargarComboMaterias(DefaultComboBoxModel<String> modeloComboMateria) {
		Conexion conex = new Conexion();
		ResultSet resMat = null;
		try{
			Statement estatutoMat = conex.getConnection().createStatement();
			resMat = estatutoMat.executeQuery("SELECT codmat from materia order by anio");
			
			while (resMat.next()){
				
				modeloComboMateria.addElement((String) resMat.getObject(1));
			}
			resMat.close();
			estatutoMat.close();
			conex.desconectar();
		}		catch (SQLException e){
					JOptionPane.showMessageDialog(null, "Error al consultar materias","Error",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	

	public void cargarComboMateriasNuevasAlumno(DefaultComboBoxModel<String> modeloComboMateria, int dniAlum, String nomAlum) {
		Conexion conex = new Conexion();

		try{
			String consulta = "SELECT codmat FROM materia where codmat not in(SELECT codmat2 FROM alumnomateria where aldni2 = ?)"
					+ "  order by anio";
			PreparedStatement estatutoMatAl = conex.getConnection().prepareStatement(consulta);
			estatutoMatAl.setInt(1,dniAlum);
			ResultSet resMatAl = estatutoMatAl.executeQuery();
			
			while (resMatAl.next()){
				
				modeloComboMateria.addElement((String) resMatAl.getObject(1));
			}
			resMatAl.close();
			estatutoMatAl.close();
			conex.desconectar();
		}		catch (SQLException e){
					JOptionPane.showMessageDialog(null, "Error al consultar materias","Error",JOptionPane.ERROR_MESSAGE);
		}
		
	}

	public void cargarListadoMateriasNuevasAlumno(DefaultListModel<String> modeloTotal, int doc) {
		Conexion conex = new Conexion();
		try{
			String consulta = "SELECT anio,codmat,matnom FROM materia where codmat not in(SELECT codmat2 FROM alumnomateria where aldni2 = ?)"
					+ "  order by anio";
			PreparedStatement estatutoMatAl = conex.getConnection().prepareStatement(consulta);
			estatutoMatAl.setInt(1,doc);
			ResultSet resMatAl = estatutoMatAl.executeQuery();
			
			while (resMatAl.next()){

					String cadena="";
					for (int i=0; i<3;i++)
						cadena+=resMatAl.getObject(i+1)+"  ";
					
					modeloTotal.addElement(cadena);
		
			}
			resMatAl.close();
			estatutoMatAl.close();
			conex.desconectar();
		}		catch (SQLException e){
					JOptionPane.showMessageDialog(null, "Error al consultar materias","Error",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void cargarTodasLasMaterias(DefaultListModel<String> modeloTotal) {
		// para VentanaReporteListaAlumnos
		Conexion conex = new Conexion();
		ResultSet resMat = null;
		try{
			String consulta = "SELECT anio,codmat,matnom FROM materia order by anio";
			Statement estatutoMat = conex.getConnection().createStatement();
			
			resMat = estatutoMat.executeQuery(consulta);
			
			while (resMat.next()){

					String cadena="";
					for (int i=0; i<3;i++)
						cadena+=resMat.getObject(i+1)+"  ";
					
					modeloTotal.addElement(cadena);
		
			}
			resMat.close();
			estatutoMat.close();
			conex.desconectar();
		}		catch (SQLException e){
					JOptionPane.showMessageDialog(null, "Error al consultar materias","Error",JOptionPane.ERROR_MESSAGE);
		}
		
	}
	public void buscarParcialMateriaCodigo(DefaultTableModel modeloMateria, String codMat, String plan) {
		Conexion conex = new Conexion();
		try{
			String consulta = "SELECT * FROM materia where codmat like ? and plan=? order by codmat";
			PreparedStatement estatuto = conex.getConnection().prepareStatement(consulta);
			estatuto.setString(1,codMat+"%");
			estatuto.setString(2, plan);
			ResultSet res = estatuto.executeQuery();
					
			while (res.next()){
				//crea un vector de objetos para guardar los datos del ResultSet y luego poner en el JTable
				Object fila[]= new Object[5];
				//llena cada columna de la fila
				for (int i=0; i<5;i++)
					fila[i]=res.getObject(i+1);
				//ahora carga la fila a la tabla modelo
				modeloMateria.addRow(fila);
			}
			res.close();
			estatuto.close();
			conex.desconectar();
		}		catch (SQLException e){
					JOptionPane.showMessageDialog(null, "Error al consultar","Error",JOptionPane.ERROR_MESSAGE);
		}		
		
	}

	public void buscarParcialMateriaNombre(DefaultTableModel modeloMateria, String nomMat, String plan) {
		
		Conexion conex = new Conexion();
		try{
			String consulta = "SELECT * FROM materia where matnom like ? and plan=? order by matnom";
			PreparedStatement estatuto = conex.getConnection().prepareStatement(consulta);
			estatuto.setString(1,nomMat+"%");
			estatuto.setString(2, plan);
			ResultSet res = estatuto.executeQuery();
					
			while (res.next()){
				//crea un vector de objetos para guardar los datos del ResultSet y luego poner en el JTable
				Object fila[]= new Object[5];
				//llena cada columna de la fila
				for (int i=0; i<5;i++)
					fila[i]=res.getObject(i+1);
				//ahora carga la fila a la tabla modelo
				modeloMateria.addRow(fila);
			}
			res.close();
			estatuto.close();
			conex.desconectar();
		}		catch (SQLException e){
					JOptionPane.showMessageDialog(null, "Error al consultar","Error",JOptionPane.ERROR_MESSAGE);
		}		
	}

	public String[] obtenerCorrelativas(String codMateria) {
		Conexion conex = new Conexion();
		try{
			String consulta = "SELECT corr1,corr2,corr3,corr4,corr5 FROM materia where codmat = ? ";
			PreparedStatement estatuto = conex.getConnection().prepareStatement(consulta);
			estatuto.setString(1,codMateria);
			ResultSet res = estatuto.executeQuery();
			
			/*int cant=0;
			while (res.next()){
				cant++;
			}
					
			String fila[]= new String [cant];*/
			while (res.next()){
				//crea un vector de objetos para guardar los datos del ResultSet 
				String fila[]= new String[5];
				//llena cada columna de la fila
				for (int i=0; i<5;i++)
					fila[i]=(String) res.getObject(i+1);
				//ahora retorna el vector de String con las correlativas
				return fila;
			}
			res.close();
			estatuto.close();
			conex.desconectar();
		}		catch (SQLException e){
					JOptionPane.showMessageDialog(null, "Error al consultar","Error",JOptionPane.ERROR_MESSAGE);
		}		
		return null;		
	}
	
	public String darNombre(String codigo) {
		Conexion conex = new Conexion();
		try{
			String nom="";
			String consulta2 = "SELECT materia.matnom FROM materia where materia.codmat=?";
			PreparedStatement estatuto2 = conex.getConnection().prepareStatement(consulta2);
			estatuto2.setString(1,codigo);
			ResultSet res2 = estatuto2.executeQuery();
			while (res2.next()){
				return nom=res2.getString("matnom");	
			}
			
			res2.close();
			estatuto2.close();
			conex.desconectar();
			//return nom;
		}		catch (SQLException e){
					JOptionPane.showMessageDialog(null, "Error al consultar nombre","Error",JOptionPane.ERROR_MESSAGE);
		}
			
		return null;
	}
	public String darNombreMateria(String codigo) {
		Conexion conex = new Conexion();
		try{
			String nom="";
			String consulta2 = "SELECT matnom FROM materia where codmat=?";
			PreparedStatement estatuto2 = conex.getConnection().prepareStatement(consulta2);
			estatuto2.setString(1,codigo);
			ResultSet res2 = estatuto2.executeQuery();
			while (res2.next()){
				//return nom=res2.getString("matnom");
				return nom=(String) res2.getObject(1);
			}			
			res2.close();
			estatuto2.close();
			conex.desconectar();
			
		}		catch (SQLException e){
					JOptionPane.showMessageDialog(null, "Error al consultar nombre de Materia","Error",JOptionPane.ERROR_MESSAGE);
		}			
		return null;
	}
	
	public int darAnioMateria(String codigo) {
		Conexion conex = new Conexion();
		try{
			int anio;
			String consulta2 = "SELECT anio FROM materia where codmat=?";
			PreparedStatement estatuto2 = conex.getConnection().prepareStatement(consulta2);
			estatuto2.setString(1,codigo);
			ResultSet res2 = estatuto2.executeQuery();
			while (res2.next()){
				return anio=res2.getInt("anio");	
			}			
			res2.close();
			estatuto2.close();
			conex.desconectar();
			
		}		catch (SQLException e){
					JOptionPane.showMessageDialog(null, "Error al consultar nombre de Materia","Error",JOptionPane.ERROR_MESSAGE);
		}			
		return (Integer) null;
	}

	public void cargarListadoCorrelativas(DefaultListModel<String> modeloTotal, DefaultListModel<String> modeloCorre, String codMat, int anio, String plan, int ven) {
		Conexion conex = new Conexion();
		try{
		 if (ven==1){
			String consulta = "SELECT anio,codmat,matnom FROM materia where materia.anio < ? and plan =? "
					+ "and codmat not in(SELECT codmat1 FROM materiacorrelativa where codmatcorr = ?) order by anio";
			String consulta2 = "SELECT codmat1,matnom FROM materiacorrelativa, materia"
					+ " where codmatcorr = ? and materiacorrelativa.codmat1=materia.codmat";
			
			PreparedStatement estatutoMatAl = conex.getConnection().prepareStatement(consulta);
			estatutoMatAl.setInt(1,anio);
			estatutoMatAl.setString(2, plan);
			estatutoMatAl.setString(3, codMat);
			ResultSet resMatCorre = estatutoMatAl.executeQuery();
			
			while (resMatCorre.next()){
				
					String cadena="";
					for (int i=0; i<3;i++)
						cadena+=resMatCorre.getObject(i+1)+"  ";
					
					System.out.println(cadena);
					modeloTotal.addElement(cadena);
		
			}
			resMatCorre.close();
			estatutoMatAl.close();
			
			PreparedStatement estatuto = conex.getConnection().prepareStatement(consulta2);
			estatuto.setString(1, codMat);
			ResultSet res = estatuto.executeQuery();
			while (res.next()){
				String cadena2="";
				for (int i=0;i<2;i++)
					cadena2+=res.getObject(i+1)+" ";
				modeloCorre.addElement(cadena2);
			}
			
			res.close();
			estatuto.close();
			conex.desconectar();
		 }else {
			 String consulta2 = "SELECT codmat1,matnom FROM materiacorrelativa, materia"
						+ " where codmatcorr = ? and materiacorrelativa.codmat1=materia.codmat";
			 
			 PreparedStatement estatuto = conex.getConnection().prepareStatement(consulta2);
				estatuto.setString(1, codMat);
				ResultSet res = estatuto.executeQuery();
				while (res.next()){
					String cadena2="";
					for (int i=0;i<2;i++)
						cadena2+=res.getObject(i+1)+" ";
					modeloCorre.addElement(cadena2);
				}
				
				res.close();
				estatuto.close();
				conex.desconectar();
			 
		 }
		}		catch (SQLException e){
					JOptionPane.showMessageDialog(null, "Error al consultar materias","Error",JOptionPane.ERROR_MESSAGE);
		}
		
	}

	public void cargarListadoVentanaMateria(DefaultListModel<String> modeCorre, String codMat) {
		Conexion conex = new Conexion();
		try{
			String consulta2 = "SELECT codmat1,matnom FROM materiacorrelativa, materia"
					+ " where codmatcorr = ? and materiacorrelativa.codmat1=materia.codmat";
			PreparedStatement estatuto = conex.getConnection().prepareStatement(consulta2);
			estatuto.setString(1, codMat);
			ResultSet res = estatuto.executeQuery();
			while (res.next()){
				String cadena2="";
				for (int i=0;i<2;i++)
					cadena2+=res.getObject(i+1)+"   ";
			
				modeCorre.addElement(cadena2);
			}
			
			res.close();
			estatuto.close();
			conex.desconectar();
		}catch (SQLException e){
			JOptionPane.showMessageDialog(null, "Error al consultar materias","Error",JOptionPane.ERROR_MESSAGE);
		}
		
	}

	

}
