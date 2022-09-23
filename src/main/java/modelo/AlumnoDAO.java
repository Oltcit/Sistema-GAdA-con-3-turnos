package modelo;

//prueba Git
//otra prueba

	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;

	import javax.swing.JOptionPane;
	import javax.swing.table.DefaultTableModel;
	/**
	 * Programa AlumnoDAO
	 * Paquete modelo
	 */
	public class AlumnoDAO
	{
		/**
		 * Registra los alumnos solo una vez
		 * @param miAlumno
		 */
		public void registrarAlumno(AlumnoVO miAlumno)
		{
			Conexion conex= new Conexion();
			
			try {
				Statement estatuto = conex.getConnection().createStatement();
				estatuto.executeUpdate("INSERT INTO alumno VALUES ('"+miAlumno.getAldni()+"', '"
						+miAlumno.getAlapynom()+"', '"+miAlumno.getAlfnac()+"', '"
						+miAlumno.getAldir()+"', '"+miAlumno.getAlloc()+"', '"+miAlumno.getAlmail()+"', '"+miAlumno.getAltel()
						+"', '"+miAlumno.getAlcel()+"', '"+miAlumno.getAltitulo()+"', '"+miAlumno.getAldoc()+"')");
				JOptionPane.showMessageDialog(null, "Se ha registrado Exitosamente","Información",JOptionPane.INFORMATION_MESSAGE);
				estatuto.close();
				conex.desconectar();
				
			} catch ( java.sql.SQLIntegrityConstraintViolationException e){
				JOptionPane.showMessageDialog(null, "No se Registro, ese alumno ya existe");
			}
			catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "No se Registro");
			}
		}

		/**
		 * Busca alumnos por dni completo
		 * @param codigo
		 * @return
		 */
		public AlumnoVO buscarAlumno(int codigo) 
		{
			Conexion conex= new Conexion();
			AlumnoVO alumno= new AlumnoVO();
			boolean existe=false;
			try {
				String consulta = "SELECT * FROM alumno where aldni = ? ";
				PreparedStatement estatuto = conex.getConnection().prepareStatement(consulta);
				estatuto.setInt(1,codigo);
				ResultSet res = estatuto.executeQuery();
						
				while(res.next()){
					existe=true;
					//se debe escribir el nombre de la columna de la tabla
					alumno.setAldni(Integer.parseInt(res.getString("aldni")));
					alumno.setAlapynom(res.getString("alapynom"));
					alumno.setAlfnac(res.getString("alfnac"));
					alumno.setAldir(res.getString("aldir"));
					alumno.setAlloc(res.getString("alloc"));
					alumno.setAlmail(res.getString("almail"));
					alumno.setAltel(res.getString("altel"));
					alumno.setAlcel(res.getString("alcel"));
					alumno.setAltitulo(res.getString("altitulo"));
					alumno.setAldoc(Byte.parseByte(res.getString("aldoc")));
				 }
				res.close();
				estatuto.close();
				conex.desconectar();
						
						
				} catch (SQLException e) {
						JOptionPane.showMessageDialog(null, "Error, no se conecto");
						System.out.println(e);
				}
			
				if (existe) {
					return alumno;
				}
				else return null;				
		}
		/**
		 * Busca alumnos por Apellido y nombre completos
		 * @param apellido
		 * @return
		 */
		public AlumnoVO buscarAlumnoApellido(String apellido) 
		{
			Conexion conex= new Conexion();
			AlumnoVO alumno= new AlumnoVO();
			boolean existe=false;
			try {
				String consulta = "SELECT * FROM alumno where alapynom = ? ";
				PreparedStatement estatuto = conex.getConnection().prepareStatement(consulta);
				estatuto.setString(1,apellido);
				ResultSet res = estatuto.executeQuery();
						
				while(res.next()){
					existe=true;
					//se debe escribir el nombre de la columna de la tabla
					alumno.setAldni(Integer.parseInt(res.getString("aldni")));
					alumno.setAlapynom(res.getString("alapynom"));
					alumno.setAlfnac(res.getString("alfnac"));
					alumno.setAldir(res.getString("aldir"));
					alumno.setAlloc(res.getString("alloc"));
					alumno.setAlmail(res.getString("almail"));
					alumno.setAltel(res.getString("altel"));
					alumno.setAlcel(res.getString("alcel"));
					alumno.setAltitulo(res.getString("altitulo"));
					alumno.setAldoc(Byte.parseByte(res.getString("aldoc")));
				 }
				res.close();
				estatuto.close();
				conex.desconectar();
						
						
				} catch (SQLException e) {
						JOptionPane.showMessageDialog(null, "Error, no se conecto");
						System.out.println(e);
				}
			
				if (existe) {
					return alumno;
				}
				else return null;				
		}
	
		/**
		 * Carga todos los alumnos en un TableModel
		 * @param modelo
		 */
		public void buscarAlumnos(DefaultTableModel modelo) {
			Conexion conex = new Conexion();
			try{
				Statement estatuto2 = conex.getConnection().createStatement();
				ResultSet res2 = estatuto2.executeQuery("SELECT aldni,alapynom,alfnac,aldir,alloc,almail,altel,"
						+ "alcel,altitulo,aldoc from alumno order by alapynom");
				
				while (res2.next()){
					//crea un vector de objetos para guardar los datos del ResultSet y luego poner en el JTable
					Object fila[]= new Object[10];
					//llena cada columna de la fila
					for (int i=0; i<10;i++)
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
		
		/**
		 * Carga solo los alumnos cuyo Apellido coincide con los datos parciales
		 * @param modelo
		 * @param ape
		 */
		public void buscarParcialAlumnoApellido(DefaultTableModel modelo, String ape) {
			Conexion conex = new Conexion();
			try{
				String consulta = "SELECT * FROM alumno where alapynom like ? order by alapynom";
				PreparedStatement estatuto = conex.getConnection().prepareStatement(consulta);
				estatuto.setString(1,ape+"%");
				ResultSet res = estatuto.executeQuery();
						
				while (res.next()){
					System.out.println("entre al buscarparcialAlumnoApellido en while res.next()");
					//crea un vector de objetos para guardar los datos del ResultSet y luego poner en el JTable
					Object fila[]= new Object[10];
					//llena cada columna de la fila
					for (int i=0; i<10;i++)
						fila[i]=res.getObject(i+1);
					//ahora carga la fila a la tabla modelo
					modelo.addRow(fila);
				}
				res.close();
				estatuto.close();
				conex.desconectar();
			}		catch (SQLException e){
						JOptionPane.showMessageDialog(null, "Error al consultar","Error",JOptionPane.ERROR_MESSAGE);
			}			
		}
		/**
		 * Carga solo los alumnos cuyo DNI coincide con los datos parciales
		 * @param modelo
		 * @param doc
		 */
		public void buscarParcialAlumnoDni(DefaultTableModel modelo, int doc) {
			Conexion conex = new Conexion();
			try{
				String consulta = "SELECT * FROM alumno where aldni like ? order by alapynom";
				PreparedStatement estatuto = conex.getConnection().prepareStatement(consulta);
				estatuto.setString(1,doc+"%");
				ResultSet res = estatuto.executeQuery();
						
				while (res.next()){
					System.out.println("entre al buscarparcialAlumnoDni en while res.next()");
					//crea un vector de objetos para guardar los datos del ResultSet y luego poner en el JTable
					Object fila[]= new Object[10];
					//llena cada columna de la fila
					for (int i=0; i<10;i++){
						fila[i]=res.getObject(i+1);
					}
					//ahora carga la fila a la tabla modelo
					modelo.addRow(fila);
				}
				res.close();
				estatuto.close();
				conex.desconectar();
			}		catch (SQLException e){
						JOptionPane.showMessageDialog(null, "Error al consultar","Error",JOptionPane.ERROR_MESSAGE);
			}			
			
		}	
		/**
		 * Modifica los datos de un Alumno
		 * @param miAlumno
		 */
		public void modificarAlumno(AlumnoVO miAlumno) {
			
			Conexion conex= new Conexion();
			try{
				String consulta="UPDATE alumno SET aldni= ? ,alapynom = ? , alfnac=? , aldir=? , alloc= ?, almail=? ,altel= ?"
						+ ",alcel= ?,altitulo= ?,aldoc= ? WHERE aldni= ? ";
				PreparedStatement estatuto = conex.getConnection().prepareStatement(consulta);
				
				System.out.println("miAlumno.fnac: "+miAlumno.getAlfnac());
				
	            estatuto.setInt(1, miAlumno.getAldni());
	            estatuto.setString(2, miAlumno.getAlapynom());
	            estatuto.setString(3, miAlumno.getAlfnac());
	            estatuto.setString(4, miAlumno.getAldir());
	            estatuto.setString(5, miAlumno.getAlloc());
	            estatuto.setString(6, miAlumno.getAlmail());
	            estatuto.setString(7, miAlumno.getAltel());
	            estatuto.setString(8, miAlumno.getAlcel());
	            estatuto.setString(9, miAlumno.getAltitulo());
	            estatuto.setByte(10, miAlumno.getAldoc());
	            estatuto.setInt(11, miAlumno.getAldni());
	            estatuto.executeUpdate();
			
	          JOptionPane.showMessageDialog(null, " Se ha Modificado Correctamente ","Confirmación",JOptionPane.INFORMATION_MESSAGE);
	         
				estatuto.close();
				conex.desconectar();
	        }catch(SQLException	 e){

	            System.out.println(e);
	            JOptionPane.showMessageDialog(null, "Error al Modificar","Error",JOptionPane.ERROR_MESSAGE);
	        }
		}
		/**
		 * Borra un Alumno de la tabla Alumno
		 * @param codigo
		 */
		public void eliminarAlumno(int codigo)
		{
			Conexion conex= new Conexion();
			try {
				Statement estatuto = conex.getConnection().createStatement();
				estatuto.executeUpdate("DELETE FROM alumno WHERE aldni='"+codigo+"'");
	            JOptionPane.showMessageDialog(null, " Se ha Eliminado Correctamente","Información",JOptionPane.INFORMATION_MESSAGE);
				estatuto.close();
				conex.desconectar();
				
			} catch (SQLException e) {
	            System.out.println(e.getMessage());
				JOptionPane.showMessageDialog(null, "No se Elimino, puede tener materias asignadas");
			}		
		}
		/**
		 * Devuelve el Apellido y nombre de un Alumno correspondiente a un DNI
		 * @param codigo
		 * @return
		 */
		public String darNombre(int codigo) {
			Conexion conex = new Conexion();
			try{
				String nom="";
				String consulta2 = "SELECT alumno.alapynom FROM alumno where alumno.aldni=?";
				PreparedStatement estatuto2 = conex.getConnection().prepareStatement(consulta2);
				estatuto2.setInt(1,codigo);
				ResultSet res2 = estatuto2.executeQuery();
				while (res2.next()){
					return nom=res2.getString("alapynom");	
				}			
				res2.close();
				estatuto2.close();
				conex.desconectar();
				
			}		catch (SQLException e){
						JOptionPane.showMessageDialog(null, "Error al consultar nombre","Error",JOptionPane.ERROR_MESSAGE);
			}			
			return null;
		}

		
	}


