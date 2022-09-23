package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class MesaDAO {

	/**
	 * Registra una mesa nueva ya validada
	 * @param modeloMesa
	 */
	public void registrarMesa(MesaVO miMesa) {
		Conexion conex= new Conexion();
		try {
			Statement estatuto = conex.getConnection().createStatement();
			estatuto.executeUpdate("INSERT INTO mesa VALUES ('"+miMesa.getCodmesa()+"', '"
					+miMesa.getCodmat()+"', '"+miMesa.getMesafecha()+"', '"
					+miMesa.getMesallamado()+"', '"+miMesa.getMesalibro()+"', '"+miMesa.getMesafolio()+"', '"+miMesa.getMesasituacion()
					+"', '"+miMesa.getMesaarmada()+"', '"+miMesa.getTurno()+"')");
			JOptionPane.showMessageDialog(null, "Se ha registrado Exitosamente","Información",JOptionPane.INFORMATION_MESSAGE);
			estatuto.close();
			conex.desconectar();
			
		} catch ( java.sql.SQLIntegrityConstraintViolationException e){
			JOptionPane.showMessageDialog(null, "No se Registro, esa mesa ya existe");
		}
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "No se Registro");
		}		
	}
	/**
	 * Valida que no exista la mesa antes de registrarla
	 * @param modeloMesa
	 */
	public void validarRegistrarMesa(MesaVO miMesa, String anio, int mes){
		Conexion conex= new Conexion();
		boolean existe=false;
		String mfecha = null;
		try{
			String codmat=miMesa.getCodmat();
			int mllamado=miMesa.getMesallamado();
			String msituacion=miMesa.getMesasituacion();			
			int mes1 = 0,mes2 = 0;
			
			if (mes==2 || mes==3){
				mes1=2;
				mes2=3;
			}
			
			if (mes==7 || mes==8){
				mes1=7;
				mes2=8;
			}
			
			if (mes==11 || mes==12){
				mes1=11;
				mes2=12;
			}
			
			String consulta = "SELECT * FROM mesa "
					+ "where codmat=? and mesallamado=? and mesasituacion=? and year(mesafecha)=? and month(mesafecha)"
					+ "between ? and ? ";
			
			PreparedStatement estatuto = conex.getConnection().prepareStatement(consulta);
			estatuto.setString(1, codmat);
			estatuto.setInt(2, mllamado);
			estatuto.setString(3, msituacion);
			estatuto.setString(4, anio);
			estatuto.setInt(5, mes1);
			estatuto.setInt(6, mes2);
			
			ResultSet res = estatuto.executeQuery();
			
			while (res.next()){
				existe=true;                  //obtener fecha de la mesa
				mfecha=res.getString("mesafecha");
			}
			res.close();
			
		} catch (SQLException e){
			JOptionPane.showMessageDialog(null, "Hubo un error,no se registró");
		}
		finally {
			if (existe){
				JOptionPane.showMessageDialog(null, "No se Registro, esa mesa ya existe en fecha "+mfecha);
			}
			else{
				registrarMesa(miMesa);
			}
		}
	}
	/**
	 * Modifica fecha, libro, folio y situación de una mesa	
	 * @param miMesa
	 */
	public void modificarMesa(MesaVO miMesa) {
		
		Conexion conex= new Conexion();	
	
	try{
		String consulta="UPDATE mesa SET mesafecha=? , mesalibro= ?, mesafolio=? ,"
				+ "mesasituacion= ? WHERE codmesa= ? ";
		PreparedStatement estatuto = conex.getConnection().prepareStatement(consulta);
		
        estatuto.setString(1, miMesa.getMesafecha());
        estatuto.setInt(2, miMesa.getMesalibro());
        estatuto.setInt(3, miMesa.getMesafolio());
        estatuto.setString(4, miMesa.getMesasituacion());
        estatuto.setInt(5, miMesa.getCodmesa());
        estatuto.executeUpdate();
	
      JOptionPane.showMessageDialog(null, " Se ha Modificado Correctamente ","Confirmación",JOptionPane.INFORMATION_MESSAGE);
     
    }catch(SQLException	 e){
        JOptionPane.showMessageDialog(null, "Error al Modificar","Error",JOptionPane.ERROR_MESSAGE);
    }	
}
	/**
	 * Carga todas las mesas en una tabla ordenadas por fecha
	 * @param modeloMesa
	 */
	public void buscarMesas(DefaultTableModel modeloMesa) {
		Conexion conex = new Conexion();
		try{
			Statement estatuto2 = conex.getConnection().createStatement();
			ResultSet res2 = estatuto2.executeQuery("SELECT codmat,mesafecha,mesallamado,mesalibro,mesafolio,mesasituacion,codmesa,"
					+ "mesaarmada from mesa order by mesafecha desc");
			
			while (res2.next()){
				//crea un vector de objetos para guardar los datos del ResultSet y luego poner en el JTable
				Object fila[]= new Object[8];
				//llena cada columna de la fila
				for (int i=0; i<8;i++)
					fila[i]=res2.getObject(i+1);
				//ahora carga la fila a la tabla modelo
				modeloMesa.addRow(fila);
			}
			res2.close();
			estatuto2.close();
			conex.desconectar();
		}		catch (SQLException e){
					JOptionPane.showMessageDialog(null, "Error al consultar","Error",JOptionPane.ERROR_MESSAGE);
		}	
	}
	/**
	 * Carga todas las mesas de una materia ordenadas por fecha
	 * @param modeloMesa
	 * @param codMat
	 */
	public void buscarMesasMateria(DefaultTableModel modeloMesa, String codMat) {
			Conexion conex = new Conexion();
			try{
				String consulta = "SELECT codmat,mesafecha,mesallamado,mesalibro,mesafolio,mesasituacion,codmesa,"
						+ "mesaarmada from mesa where codmat= ? order by mesafecha desc";
				PreparedStatement estatuto = conex.getConnection().prepareStatement(consulta);
				estatuto.setString(1,codMat);
				ResultSet res = estatuto.executeQuery();
						
				while (res.next()){
					Object fila[]= new Object[8];
					for (int i=0; i<8;i++)
						fila[i]=res.getObject(i+1);
					modeloMesa.addRow(fila);
				}
				res.close();
				estatuto.close();
				conex.desconectar();
			}		catch (SQLException e){
						JOptionPane.showMessageDialog(null, "Error al consultar","Error",JOptionPane.ERROR_MESSAGE);
			}			
	}
	/**
	 * Carga todas las mesas (sin armar) de una materia ordenadas por fecha
	 * @param modeloMesa
	 * @param codMat
	 */
	public void buscarMesasNuevasMateria(DefaultTableModel modeloMesa, String codMat) {
			Conexion conex = new Conexion();
			try{
				String consulta = "SELECT codmat,mesafecha,mesallamado,mesalibro,mesafolio,mesasituacion,turno,codmesa,"
						+ " mesaarmada from mesa where codmat= ? and mesaarmada='abierta' order by mesafecha desc";
				PreparedStatement estatuto = conex.getConnection().prepareStatement(consulta);
				estatuto.setString(1,codMat);
				ResultSet res = estatuto.executeQuery();
						
				while (res.next()){
					Object fila[]= new Object[9];
					for (int i=0; i<9;i++)
						fila[i]=res.getObject(i+1);
					modeloMesa.addRow(fila);
				}
				res.close();
				estatuto.close();
				conex.desconectar();
			}		catch (SQLException e){
						JOptionPane.showMessageDialog(null, "Error al consultar","Error",JOptionPane.ERROR_MESSAGE);
			}			
	}
	/**
	 * Carga todas las mesas ya cerradas de una materia ordenadas por fecha para seleccionar una y comenzar
	 * a cargar N° folio, N° libro y las notas de todos los alumnos
	 * 
	 * @param modeloMesa
	 * @param codMat
	 */
	public void buscarMesasCerradasMateria(DefaultTableModel modeloMesa, String codMat) {
		Conexion conex = new Conexion();
		try{
			String consulta = "SELECT codmat,mesafecha,mesallamado,mesalibro,mesafolio,mesasituacion,codmesa,"
					+ " mesaarmada from mesa where codmat= ? and mesaarmada='cerrada' order by mesafecha desc";
			PreparedStatement estatuto = conex.getConnection().prepareStatement(consulta);
			estatuto.setString(1,codMat);
			ResultSet res = estatuto.executeQuery();
					
			while (res.next()){
				Object fila[]= new Object[8];
				for (int i=0; i<8;i++)
					fila[i]=res.getObject(i+1);
				modeloMesa.addRow(fila);
			}
			res.close();
			estatuto.close();
			conex.desconectar();
		}		catch (SQLException e){
					JOptionPane.showMessageDialog(null, "Error al consultar","Error",JOptionPane.ERROR_MESSAGE);
		}			
	}
	/**
	 * Carga todas las mesas ya cerradas y con libro y folio == 0 de una materia, para elegir una
	 * e imprimir el Acta volante
	 *  
	 * @param modeloMesa
	 * @param codMat
	 */
	public void buscarMesasParaActaVolante(DefaultTableModel modeloMesa, String codMat) {
		Conexion conex = new Conexion();
		try{
			String consulta = "SELECT codmat,mesafecha,mesallamado,mesalibro,mesafolio,mesasituacion,codmesa,"
					+ " mesaarmada from mesa where codmat= ? and mesaarmada='cerrada' and mesalibro=0 "
					+ "and mesafolio=0 order by mesafecha desc";
			PreparedStatement estatuto = conex.getConnection().prepareStatement(consulta);
			estatuto.setString(1,codMat);
			ResultSet res = estatuto.executeQuery();
					
			while (res.next()){
				Object fila[]= new Object[8];
				for (int i=0; i<8;i++)
					fila[i]=res.getObject(i+1);
				modeloMesa.addRow(fila);
			}
			res.close();
			estatuto.close();
			conex.desconectar();
		}		catch (SQLException e){
					JOptionPane.showMessageDialog(null, "Error al consultar","Error",JOptionPane.ERROR_MESSAGE);
		}			
		
	}
	
	/**
	 * Elimina una mesa si no tiene alumnos inscriptos
	 * @param codigoDeMesa
	 */
	public void eliminarMesa(int codigoDeMesa) {
		Conexion conex= new Conexion();
		try {
			Statement estatuto = conex.getConnection().createStatement();
			estatuto.executeUpdate("DELETE FROM mesa WHERE codmesa='"+codigoDeMesa+"'");
            JOptionPane.showMessageDialog(null, " Se ha Eliminado Correctamente","Información",JOptionPane.INFORMATION_MESSAGE);
			estatuto.close();
			conex.desconectar();
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "No se Elimino, puede tener alumnos inscriptos");
		}	
		
	}
	public void cerrarMesa(int codMesa) {
		Conexion conex= new Conexion();
		boolean flag=false;
		try{
			String consulta="UPDATE mesa SET mesaarmada= ? WHERE codmesa= ? ";
			PreparedStatement estatuto = conex.getConnection().prepareStatement(consulta);
			
            estatuto.setString(1, "cerrada");
            estatuto.setInt(2, codMesa);
            estatuto.executeUpdate();
            
            flag=true;
         
        }catch(SQLException	 e){

            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Error al Modificar","Error",JOptionPane.ERROR_MESSAGE);
        }
		if(flag)
			JOptionPane.showMessageDialog(null, " La Mesa se creó correctamente ","Información",JOptionPane.INFORMATION_MESSAGE);
	}
}
