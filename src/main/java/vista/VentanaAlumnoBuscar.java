package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import controlador.Coordinador;
import modelo.AlumnoDAO;
import modelo.AlumnoVO;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
/**
 * Programa VentanaAlumnoBuscar
 * Paquete vista
 * 
 * @author Edgardo
 *
 */
public class VentanaAlumnoBuscar extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private Coordinador miCoordinador;
	private JScrollPane scrollPane;
	private JPanel panel;
	private JButton btnVolver;

	/**
	 * Inicia la aplicación
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAlumnoBuscar frame = new VentanaAlumnoBuscar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Crea la ventana
	 */
	public VentanaAlumnoBuscar() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1200, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		
		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		panel.add(btnVolver);
	}
	/**
	 * Método setCoordinador
	 * @param miCoordinador
	 */
	public void setCoordinador(Coordinador miCoordinador) {
		this.miCoordinador = miCoordinador;
	}
	
	/**
	 * Carga y muestra el JTable para seleccionar el alumno
	 * @param btn
	 * @param doc
	 * @param ape
	 * @param ventana
	 */

	public void mostrarDatosConTableModel(int  btn, int doc, String ape,int ventana) {
		DefaultTableModel modelo = new DefaultTableModel(
		new Object[][] {
		},
		new String[] {
			"DNI", "Apellido y nombre", "Fecha de Nac.", "Dirección", "Localidad", "Mail", "Teléfono", "Celular", 
			"Título", "Documentación"
		}
	) {
		Class[] columnTypes = new Class[] {
			Integer.class, String.class, String.class, String.class, String.class, String.class, String.class, 
			String.class, String.class, Boolean.class
		};
		public Class getColumnClass(int columnIndex) {
			return columnTypes[columnIndex];
		}
		boolean[] columnEditables = new boolean[] {
			false, false, false, false, false, false, false, false, false, false
		};
		public boolean isCellEditable(int row, int column) {
			return columnEditables[column];
		}
	};
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//
				// si ventana == 1 pasa los datos a la VentanaAlumno
				// 	          == 2 pasa los datos a la VentanaAlumnoMateria
				//
				 int fila = table.getSelectedRow();
				if (ventana==1)
					miCoordinador.pasarDatosAlumno(pasarDatosAlumno(fila));
				else					
					miCoordinador.pasarDatosAlumnoMateria(pasarDatosAlumno(fila));
					dispose();
			}
		});
		
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				  if (e.getKeyCode() == KeyEvent.VK_ENTER) {
	                    // Obtener la fila seleccionada
	                    int fila = table.getSelectedRow();
	                    if (ventana==1)
	    					miCoordinador.pasarDatosAlumno(pasarDatosAlumno(fila));
	    				else					
	    					miCoordinador.pasarDatosAlumnoMateria(pasarDatosAlumno(fila));
	    					dispose();
				  }     
			}
		});
		
		
		
		table.setModel(modelo);
		table.getTableHeader().setReorderingAllowed(false);
		
		// centralizado
		DefaultTableCellRenderer centrar = new DefaultTableCellRenderer();
		centrar.setHorizontalAlignment(SwingConstants.CENTER);
		
		// justificado a izquierda
		DefaultTableCellRenderer izquierda = new DefaultTableCellRenderer();
		izquierda.setHorizontalAlignment(SwingConstants.LEFT);
		
		
		//columna DNI
		table.getColumnModel().getColumn(0).setMaxWidth(78);
		table.getColumnModel().getColumn(0).setMinWidth(78);
		table.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(78);
		table.getTableHeader().getColumnModel().getColumn(0).setMinWidth(78);
		table.getColumnModel().getColumn(0).setCellRenderer(centrar);
		
		//columna Apellido y nombre
		//table.getColumnModel().getColumn(1).setMaxWidth(80);
		table.getColumnModel().getColumn(1).setMinWidth(200);
		//table.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(80);
		table.getTableHeader().getColumnModel().getColumn(1).setMinWidth(200);
		table.getColumnModel().getColumn(1).setCellRenderer(izquierda);
		
		// columna Fecha de nacimiento
		//table.getColumnModel().getColumn(1).setMaxWidth(80);
		table.getColumnModel().getColumn(2).setMinWidth(80);
		//table.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(80);
		table.getTableHeader().getColumnModel().getColumn(2).setMinWidth(80);
		table.getColumnModel().getColumn(2).setCellRenderer(centrar);
		
		// columna Direccion
		//table.getColumnModel().getColumn(1).setMaxWidth(80);
		table.getColumnModel().getColumn(3).setMinWidth(100);
		//table.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(80);
		table.getTableHeader().getColumnModel().getColumn(3).setMinWidth(100);
		table.getColumnModel().getColumn(3).setCellRenderer(izquierda);
		
		// columna Mail
		//table.getColumnModel().getColumn(1).setMaxWidth(80);
		table.getColumnModel().getColumn(5).setMinWidth(100);
		//table.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(80);
		table.getTableHeader().getColumnModel().getColumn(5).setMinWidth(100);
		table.getColumnModel().getColumn(5).setCellRenderer(izquierda);
		
		// columna Telefono
		//table.getColumnModel().getColumn(1).setMaxWidth(80);
		table.getColumnModel().getColumn(6).setMinWidth(80);
		//table.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(80);
		table.getTableHeader().getColumnModel().getColumn(6).setMinWidth(80);
		table.getColumnModel().getColumn(6).setCellRenderer(centrar);
		
		// columna Celular
		//table.getColumnModel().getColumn(1).setMaxWidth(80);
		table.getColumnModel().getColumn(7).setMinWidth(80);
		//table.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(80);
		table.getTableHeader().getColumnModel().getColumn(7).setMinWidth(80);
		table.getColumnModel().getColumn(7).setCellRenderer(centrar);
		
		// columna Titulo
		//table.getColumnModel().getColumn(1).setMaxWidth(80);
		table.getColumnModel().getColumn(8).setMinWidth(100);
		//table.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(80);
		table.getTableHeader().getColumnModel().getColumn(8).setMinWidth(100);
		table.getColumnModel().getColumn(8).setCellRenderer(izquierda); 
		
		
		// especifica cual es la columna que va a tener el renderizado (el checkBox)
		
		agregarCheck(table.getColumnModel().getColumn(9));
		
		AlumnoDAO miAlumnoDAO = new AlumnoDAO();
		if (btn==1){
		miAlumnoDAO.buscarAlumnos(modelo);
		setTitle("Listado completo de Alumnos ordenados por APELLIDO");
		}
		if (btn==2){
			miAlumnoDAO.buscarParcialAlumnoApellido(modelo,ape);
			setTitle("Selección de Alumnos ordenados por APELLIDO");
		}
		if (btn==3){
			miAlumnoDAO.buscarParcialAlumnoDni(modelo,doc);
			setTitle("Selección de Alumnos por DNI ordenados por APELLIDO");
		}
		scrollPane.setViewportView(table);
	}
	private void agregarCheck(TableColumn columna) {
		JCheckBox chkDoc = new JCheckBox();
		columna.setCellEditor(new DefaultCellEditor(chkDoc));
		
	}
	/*
	public void mostrarDatosConTableModel(int  btn, int doc, String ape,int ventana) {
		DefaultTableModel modelo = new DefaultTableModel();
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//
				// si ventana == 1 pasa los datos a la VentanaAlumno
				// 	          == 2 pasa los datos a la VentanaAlumnoMateria
				//
				if (ventana==1)
					miCoordinador.pasarDatosAlumno(pasarDatosAlumno(e));
				else					
					miCoordinador.pasarDatosAlumnoMateria(pasarDatosAlumno(e));
			}
		});
		
		table.setModel(modelo);
		modelo.addColumn("DNI");
		modelo.addColumn("Apellido y nombre");
		modelo.addColumn("Fecha de nacimiento");
		modelo.addColumn("Dirección");
		modelo.addColumn("Localidad");
		modelo.addColumn("Mail");
		modelo.addColumn("Teléfono");
		modelo.addColumn("Celular");
		modelo.addColumn("Título");
		modelo.addColumn("Documentación");
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getTableHeader().setReorderingAllowed(false);
		
		AlumnoDAO miAlumnoDAO = new AlumnoDAO();
		if (btn==1){
		miAlumnoDAO.buscarAlumnos(modelo);
		setTitle("Listado completo de Alumnos ordenados por APELLIDO");
		}
		if (btn==2){
			miAlumnoDAO.buscarParcialAlumnoApellido(modelo,ape);
			setTitle("Selección de Alumnos ordenados por APELLIDO");
		}
		if (btn==3){
			miAlumnoDAO.buscarParcialAlumnoDni(modelo,doc);
			setTitle("Selección de Alumnos por DNI ordenados por APELLIDO");
		}
		scrollPane.setViewportView(table);
	}*/
	/**
	 * Pasa los datos del JTable a los campos de la ventana Alumno
	 * @param e
	 * @return
	 */
	//protected AlumnoVO pasarDatosAlumno(MouseEvent e) {
		protected AlumnoVO pasarDatosAlumno(int fila) {
		AlumnoVO miAlumno = new AlumnoVO();
	//	int row=table.rowAtPoint(e.getPoint());
		miAlumno.setAldni(Integer.valueOf(table.getValueAt(fila, 0).toString()));
		miAlumno.setAlapynom(table.getValueAt(fila, 1).toString());
		miAlumno.setAlfnac(table.getValueAt(fila, 2).toString());
		miAlumno.setAldir(table.getValueAt(fila, 3).toString());
		miAlumno.setAlloc(table.getValueAt(fila, 4).toString());
		miAlumno.setAlmail(table.getValueAt(fila, 5).toString());
		miAlumno.setAltel(table.getValueAt(fila, 6).toString());
		miAlumno.setAlcel(table.getValueAt(fila, 7).toString());
		miAlumno.setAltitulo(table.getValueAt(fila, 8).toString());
	
		String estado = table.getValueAt(fila, 9).toString();
		
		if (estado.equals("false")){
			miAlumno.setAldoc((byte) 0);
		}else{
			miAlumno.setAldoc((byte) 1);
		}
			
		return miAlumno;
	}
	
}
