package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controlador.Coordinador;
import modelo.AlumnoVO;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
/**
 * Programa VentanaAlumno
 * Tipo visual
 * Paquete vista
 * 
 * @author Edgardo
 * año 2018
 */
public class VentanaAlumno extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtAlDni;
	private JTextField txtAlApyNom;
	private JTextField txtAlDir;
	private JTextField txtAlMail;
	private JTextField txtAlTel;
	private JTextField txtAlCel;
	private JTextField txtAlTitulo;

	private Coordinador miCoordinador;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JButton btnAgregar;
	private JCheckBox chkAlDoc;
	private JButton btnGuardar;
	private JButton btnCancelar;
	private JButton btnBuscarXDni;
	private JButton btnBuscarXApellido;
	private JLabel label_10;
	private JLabel label_11;
	private JLabel label_12;
	private JLabel label_13;
	private JLabel label_14;
	private JLabel label_15;
	
	private int accion;
	private JButton btnBuscar;
	private JLabel label_9;
	private JDateChooser selectorFecha;
	private JComboBox<String> cbLocalidad;

	/**
	 * Inicio aplicación.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAlumno frame = new VentanaAlumno();
					frame.setVisible(true);
					frame.limpiar();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Crea la ventana.
	 */
	public VentanaAlumno() {
		setTitle("Alumno");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 640, 419);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panelAlumno = new JPanel();
		contentPane.add(panelAlumno, BorderLayout.CENTER);
		panelAlumno.setLayout(new GridLayout(0, 3, 5, 5));

		JLabel label = new JLabel("DNI:");
		panelAlumno.add(label);

		txtAlDni = new JTextField();
		txtAlDni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				busquedaParcialDni();
			}
		});
		txtAlDni.setColumns(10);
		panelAlumno.add(txtAlDni);

		btnBuscarXDni = new JButton("Buscar x Dni");
		btnBuscarXDni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				busquedaParcialDni();
			}
		});
		panelAlumno.add(btnBuscarXDni);

		JLabel label_1 = new JLabel("Apellido y nombre:");
		panelAlumno.add(label_1);

		txtAlApyNom = new JTextField();
		txtAlApyNom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				busquedaParcial();
			}
		});
		txtAlApyNom.setColumns(10);
		panelAlumno.add(txtAlApyNom);

		btnBuscarXApellido = new JButton("Buscar x Apellido y nombre");
		btnBuscarXApellido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				busquedaParcial();
			}
		});
		panelAlumno.add(btnBuscarXApellido);

		JLabel label_2 = new JLabel("Fecha de nacimiento:");
		panelAlumno.add(label_2);
		
		selectorFecha = new JDateChooser();
		panelAlumno.add(selectorFecha);
		
		label_9 = new JLabel("");
		panelAlumno.add(label_9);

		JLabel label_3 = new JLabel("Direcci\u00F3n:");
		panelAlumno.add(label_3);

		txtAlDir = new JTextField();
		txtAlDir.setColumns(10);
		panelAlumno.add(txtAlDir);

		label_10 = new JLabel("");
		panelAlumno.add(label_10);

		JLabel label_4 = new JLabel("Localidad:");
		panelAlumno.add(label_4);
		
		cbLocalidad = new JComboBox<String>();
		cbLocalidad.setModel(new DefaultComboBoxModel<String>(new String[] {"", "Cañuelas", "Gral. Las Heras", "Marcos Paz", "Mariano Acosta", "Merlo", "Navarro"}));
		cbLocalidad.setEditable(true);
		panelAlumno.add(cbLocalidad);

		label_11 = new JLabel("");
		panelAlumno.add(label_11);

		JLabel label_5 = new JLabel("Mail:");
		panelAlumno.add(label_5);

		txtAlMail = new JTextField();
		txtAlMail.setColumns(10);
		panelAlumno.add(txtAlMail);

		label_12 = new JLabel("");
		panelAlumno.add(label_12);

		JLabel label_6 = new JLabel("Tel\u00E9fono:");
		panelAlumno.add(label_6);

		txtAlTel = new JTextField();
		txtAlTel.setColumns(10);
		panelAlumno.add(txtAlTel);

		label_13 = new JLabel("");
		panelAlumno.add(label_13);

		JLabel label_7 = new JLabel("Celular:");
		panelAlumno.add(label_7);

		txtAlCel = new JTextField();
		txtAlCel.setColumns(10);
		panelAlumno.add(txtAlCel);

		label_14 = new JLabel("");
		panelAlumno.add(label_14);

		JLabel label_8 = new JLabel("T\u00EDtulo:");
		panelAlumno.add(label_8);

		txtAlTitulo = new JTextField();
		txtAlTitulo.setColumns(10);
		panelAlumno.add(txtAlTitulo);

		label_15 = new JLabel("");
		panelAlumno.add(label_15);

		chkAlDoc = new JCheckBox("Documentaci\u00F3n");
		panelAlumno.add(chkAlDoc);

		JPanel panelBotones = new JPanel();
		contentPane.add(panelBotones, BorderLayout.SOUTH);
		panelBotones.setLayout(new FlowLayout());

		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				accion=1;
				agregarAlumno();
			}
		});

		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardarAlumno();
			}
		});
		panelBotones.add(btnGuardar);
		panelBotones.add(btnAgregar);

		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				accion=2;
				habilita(false,true, true, true, true, true, true, true, true, true,false,false,true,false,false,false,true);
			}
		});
		panelBotones.add(btnModificar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				eliminarAlumno();				
			}
		});
		panelBotones.add(btnEliminar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				volver();
			}
		});
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				buscarAlumno();
			}
		});
		panelBotones.add(btnBuscar);
		panelBotones.add(btnCancelar);
	}
	

	/**
	 * Muestra en un JTable todos los alumnos 
	 */
	protected void buscarAlumno() {
		/*
		 * para variable btn==1 trae todos los alumnos
		 *               btn==2 trae busqueda parcial
		 *               btn==3 trae busqueda parcial por dni
		 * para variable ventana==1 indica que es la ventana VentanaAlumno la que llama
		 * 				 ventana==2 indica que es la ventana VentanaAlumnoMateria la que llama
		 */
		int btn=1,ventana=1;
		String ape=txtAlApyNom.getText();
		int doc;
		if (txtAlDni.getText().isEmpty())
			doc=0;
		else
			doc=Integer.valueOf(txtAlDni.getText());
		miCoordinador.mostrarVentanaAlumnoBuscar(btn,doc,ape,ventana);
	}

	/**
	 *  Guarda un alumno nuevo o uno modificado
	 */
	private void guardarAlumno() {
		try {
			Date date =selectorFecha.getDate();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			AlumnoVO miAlumno = new AlumnoVO();
			miAlumno.setAldni(Integer.parseInt(txtAlDni.getText()));
			miAlumno.setAlapynom(txtAlApyNom.getText());
			miAlumno.setAlfnac(sdf.format(date));
			
			System.out.println("Fecha de nacimiento"+miAlumno.getAlfnac());
			miAlumno.setAldir(txtAlDir.getText());
			miAlumno.setAlloc((String) cbLocalidad.getSelectedItem());
			miAlumno.setAlmail(txtAlMail.getText());
			miAlumno.setAltel(txtAlTel.getText());
			miAlumno.setAlcel(txtAlCel.getText());
			miAlumno.setAltitulo(txtAlTitulo.getText());
			if (chkAlDoc.isSelected())
				// hay que hacer un casting porque si no lo toma como entero
				miAlumno.setAldoc((byte) 1);
			else
				miAlumno.setAldoc((byte) 0);

			if (accion==1){
			miCoordinador.registrarAlumno(miAlumno);
		       limpiar();
			}
			else 
				miCoordinador.modificarAlumno(miAlumno);
				limpiar();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Error en el Ingreso de Datos", "Error",
					JOptionPane.ERROR_MESSAGE);
			limpiar();
		}
	}

	/**
	 * Muestra en un JTable los alumnos a partir de una
	 * búsqueda parcial del Dni
	 */
	protected void busquedaParcialDni() {
		/*
		 * para variable btn==1 trae todos los alumnos
		 *               btn==2 trae busqueda parcial por apellido
		 *               btn==3 trae busqueda parcial por dni
		 * para variable ventana==1 indica que es la ventana VentanaAlumno la que llama
		 *               ventana==2 indica que es la ventana VentanaAlumnoMateria la que llama
		 */
		int btn=3,ventana=1;
		String ape=txtAlApyNom.getText();
		int doc;
		if (txtAlDni.getText().isEmpty())
		{
		    JOptionPane.showMessageDialog(null, "<html>Ingrese los primeros números del Documento <br>"
		    		+ "o el número completo </html>", "Información",JOptionPane.WARNING_MESSAGE);
		}
		else
		{
			try{
			doc=Integer.valueOf(txtAlDni.getText().trim());
			miCoordinador.mostrarVentanaAlumnoBuscar(btn,doc,ape,ventana);
			} catch (NumberFormatException e){
				JOptionPane.showMessageDialog(null, "<html>Ingrese los primeros números del Documento <br>"
			    		+ "sin espacios o letras </html>  ", "Información",JOptionPane.ERROR_MESSAGE);
			}
		}	
	}
	/**
	 * Muestra en un JTable los alumnos a partir de una
	 * búsqueda parcial del Apellido y nombre
	 */
	protected void busquedaParcial() {
		/*
		 * para variable btn==1 trae todos los alumnos
		 *               btn==2 trae busqueda parcial por apellido
		 *               btn==3 trae busqueda parcial por dni
		 * para variable ventana==1 indica que es la ventana VentanaAlumno la que llama
		 *               ventana==2 indica que es la ventana VentanaAlumnoMateria la que llama
		 */
		int btn=2,ventana=1;
		String ape=txtAlApyNom.getText();
		int doc;
		if (txtAlDni.getText().isEmpty())
		{
			doc=0;
		}
		else
			doc=Integer.valueOf(txtAlDni.getText());
		miCoordinador.mostrarVentanaAlumnoBuscar(btn,doc,ape,ventana);
	}
	
	/**
	 * Elimina el registro de un alumno
	 */
	public void eliminarAlumno() {
		if (!txtAlDni.getText().equals(""))
		{
			int respuesta=JOptionPane.showConfirmDialog(null, "Está seguro de eliminar ese Alumno?", "Confirmación", JOptionPane.YES_NO_OPTION);
					
			
			if (respuesta == JOptionPane.YES_NO_OPTION)
			{
				miCoordinador.eliminarAlumno(Integer.parseInt(txtAlDni.getText()));
				limpiar();
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Ingrese un número de Documento", "Información",JOptionPane.WARNING_MESSAGE);
		}
	}
	
	/**
	 * Método setCoordinador
	 * @param miCoordinador
	 */
	public void setCoordinador(Coordinador miCoordinador) {
		this.miCoordinador = miCoordinador;
	}
	
	/**
	 * Habilita y deshabilita botones y cuadros de textos
	 * 
	 * @param dni
	 * @param nombre
	 * @param fnac
	 * @param dir
	 * @param loc
	 * @param mail
	 * @param tel
	 * @param cel
	 * @param tit
	 * @param doc
	 * @param bBuscarxDni
	 * @param bBuscarxApyNom
	 * @param bGuardar
	 * @param bAgregar
	 * @param bModificar
	 * @param bEliminar
	 * @param bCancelar
	 * @param bReporte
	 */
	private void habilita(boolean dni, boolean nombre, boolean fnac, boolean dir, boolean loc, boolean mail, boolean tel,
			boolean cel, boolean tit, boolean doc, boolean bBuscarxDni, boolean bBuscarxApyNom, boolean bGuardar,
			boolean bAgregar, boolean bModificar, boolean bEliminar, boolean bCancelar) {
		txtAlDni.setEditable(dni);
		txtAlApyNom.setEditable(nombre);
		selectorFecha.setEnabled(fnac);
		txtAlDir.setEditable(dir);
		cbLocalidad.setEnabled(loc);
		txtAlMail.setEditable(mail);
		txtAlTel.setEditable(tel);
		txtAlCel.setEditable(cel);
		txtAlTitulo.setEditable(tit);
		chkAlDoc.setEnabled(doc);
		btnBuscarXDni.setEnabled(bBuscarxDni);
		btnBuscarXApellido.setEnabled(bBuscarxApyNom);
		btnGuardar.setVisible(bGuardar);
		btnAgregar.setEnabled(bAgregar);
		btnModificar.setEnabled(bModificar);
		btnEliminar.setEnabled(bEliminar);
		btnCancelar.setVisible(bCancelar);
	}
	
	/**
	 * Limpia cuadros de texto y checkbox
	 */
	public void limpiar() {
		txtAlDni.setText("");
		txtAlApyNom.setText("");
		selectorFecha.setCalendar(null);
		txtAlDir.setText("");
		cbLocalidad.setSelectedIndex(0);
		txtAlMail.setText("");
		txtAlTel.setText("");
		txtAlCel.setText("");
		txtAlTitulo.setText("");
		chkAlDoc.setSelected(false);
		/*
		 * boolean dni, boolean nombre, boolean fnac, boolean dir, boolean loc,
		 * boolean mail, boolean tel, boolean cel,boolean tit,boolean
		 * doc,boolean bBuscarxDni,boolean bBuscarxApyNom,boolean
		 * bGuardar,boolean bAgregar,boolean bModificar, boolean bEliminar,
		 * boolean bCancelar, boolean bReporte)
		 */
		habilita(true, true, false, false, false, false, false, false, false, false, true, true, false, true, false,
				false, false);
	}
	
	/**
	 * Agrega un alumno nuevo
	 */
	private void agregarAlumno() {
		habilita(true, true, true, true, true, true, true, true, true, true, false, false, true, false, false, false,
				true);
	}

	/**
	 * Limpia los componentes (botón Cancelar)
	 */
	private void volver() {
		limpiar();
	}

	/**
	 * Muestra los datos del alumno encontrado
	 * 
	 * @param miAlumno
	 */
	public void muestraAlumno(AlumnoVO miAlumno) {
		SimpleDateFormat formato= new SimpleDateFormat("yyyy-MM-dd");
		Date miDia=new Date();
		GregorianCalendar miGCalendar = new GregorianCalendar();
		try {
			miDia=formato.parse(miAlumno.getAlfnac());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		miGCalendar.setTime(miDia);
		txtAlDni.setText(String.valueOf(miAlumno.getAldni()));
		txtAlApyNom.setText(miAlumno.getAlapynom());
		selectorFecha.setCalendar(miGCalendar);
		txtAlDir.setText(miAlumno.getAldir());
		cbLocalidad.setSelectedItem(miAlumno.getAlloc());
		txtAlMail.setText(miAlumno.getAlmail());
		txtAlTel.setText(miAlumno.getAltel());
		txtAlCel.setText(miAlumno.getAlcel());
		txtAlTitulo.setText(miAlumno.getAltitulo());

		if (miAlumno.getAldoc() == 0)
			chkAlDoc.setSelected(false);
		else
			chkAlDoc.setSelected(true);
			habilita(false,false, false, false, false, false, false, false, false, false,false,false,false,false,true,true,true);
	}

}
