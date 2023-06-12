package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import controlador.Coordinador;
import modelo.AlumnoVO;
import modelo.Conexion;
import modelo.Logica;
import modelo.MateriaDAO;
import modelo.MateriaVO;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JList;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JComboBox;
/**
 * Programa VentanaMateria
 * Tipo visual
 * Paquete vista
 * 
 * @author Edgardo
 * año 2018
 */
public class VentanaMateria extends JFrame {

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JList <String>listCorre;
	private DefaultListModel<String> modeCorre;
	private JTextField txtCodMat;
	private JTextField txtMatNom;
	private JTextField txtAnio;
	private JTextField txtPlan;
	private JTextField txtModulos;
	private Coordinador miCoordinador;
	private JButton btnBuscar;
	private JButton btnModificar;
	private JButton btnAgregar;
	private JButton btnEliminar;
	private JButton btnCancelar;
	private JButton btnGuardar;
	private JButton btnReporte;
	private JButton btnBuscarXCodigo;
	private JButton btnBuscarXNombre;
	
	private int accion;
	private JButton btnCorrelativa;
	private JButton btnEliminarCorrelativas;
	private JComboBox cbPlan;
	static DefaultComboBoxModel<String> modeloComboPlan;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaMateria frame = new VentanaMateria();
					frame.setVisible(true);
					frame.limpiar();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaMateria() {
		setTitle("Materia");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 478);
		setMinimumSize(new Dimension(800,419));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{774, 0};
		gbl_contentPane.rowHeights = new int[]{135, 202, 33, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		modeCorre = new DefaultListModel<String>();
		
		JPanel panelMateria = new JPanel();
		GridBagConstraints gbc_panelMateria = new GridBagConstraints();
		gbc_panelMateria.anchor = GridBagConstraints.NORTH;
		gbc_panelMateria.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelMateria.insets = new Insets(0, 0, 5, 0);
		gbc_panelMateria.gridx = 0;
		gbc_panelMateria.gridy = 0;
		contentPane.add(panelMateria, gbc_panelMateria);
		GridBagLayout gbl_panelMateria = new GridBagLayout();
		gbl_panelMateria.columnWidths = new int[] {55, 565, 85, 0};
		gbl_panelMateria.rowHeights = new int[]{23, 23, 23, 23, 23, 0};
		gbl_panelMateria.columnWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panelMateria.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelMateria.setLayout(gbl_panelMateria);
		
		JLabel lblCdDeMateria = new JLabel("Código:");
		lblCdDeMateria.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblCdDeMateria = new GridBagConstraints();
		gbc_lblCdDeMateria.fill = GridBagConstraints.BOTH;
		gbc_lblCdDeMateria.insets = new Insets(0, 0, 5, 5);
		gbc_lblCdDeMateria.gridx = 0;
		gbc_lblCdDeMateria.gridy = 0;
		panelMateria.add(lblCdDeMateria, gbc_lblCdDeMateria);
		
		txtCodMat = new JTextField();
		txtCodMat.setColumns(10);
		GridBagConstraints gbc_txtCodMat = new GridBagConstraints();
		gbc_txtCodMat.fill = GridBagConstraints.BOTH;
		gbc_txtCodMat.insets = new Insets(0, 0, 5, 5);
		gbc_txtCodMat.gridx = 1;
		gbc_txtCodMat.gridy = 0;
		panelMateria.add(txtCodMat, gbc_txtCodMat);
		
		btnBuscarXCodigo = new JButton("Buscar x código");
		btnBuscarXCodigo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				busquedaParcialCodigo();
			}
		});
		GridBagConstraints gbc_btnBuscarXCodigo = new GridBagConstraints();
		gbc_btnBuscarXCodigo.fill = GridBagConstraints.BOTH;
		gbc_btnBuscarXCodigo.insets = new Insets(0, 0, 5, 0);
		gbc_btnBuscarXCodigo.gridx = 2;
		gbc_btnBuscarXCodigo.gridy = 0;
		panelMateria.add(btnBuscarXCodigo, gbc_btnBuscarXCodigo);
		
		JLabel label_1 = new JLabel("Nombre:");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.fill = GridBagConstraints.BOTH;
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.gridx = 0;
		gbc_label_1.gridy = 1;
		panelMateria.add(label_1, gbc_label_1);
		
		txtMatNom = new JTextField();
		txtMatNom.setHorizontalAlignment(SwingConstants.LEFT);
		txtMatNom.setColumns(10);
		GridBagConstraints gbc_txtMatNom = new GridBagConstraints();
		gbc_txtMatNom.fill = GridBagConstraints.BOTH;
		gbc_txtMatNom.insets = new Insets(0, 0, 5, 5);
		gbc_txtMatNom.gridx = 1;
		gbc_txtMatNom.gridy = 1;
		panelMateria.add(txtMatNom, gbc_txtMatNom);
		
		btnBuscarXNombre = new JButton("Buscar x nombre");
		btnBuscarXNombre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				busquedaParcialNombre();
			}
		});
		GridBagConstraints gbc_btnBuscarXNombre = new GridBagConstraints();
		gbc_btnBuscarXNombre.fill = GridBagConstraints.BOTH;
		gbc_btnBuscarXNombre.insets = new Insets(0, 0, 5, 0);
		gbc_btnBuscarXNombre.gridx = 2;
		gbc_btnBuscarXNombre.gridy = 1;
		panelMateria.add(btnBuscarXNombre, gbc_btnBuscarXNombre);
		
		JLabel label_2 = new JLabel("Curso:");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.fill = GridBagConstraints.BOTH;
		gbc_label_2.insets = new Insets(0, 0, 5, 5);
		gbc_label_2.gridx = 0;
		gbc_label_2.gridy = 2;
		panelMateria.add(label_2, gbc_label_2);
		
		txtAnio = new JTextField();
		txtAnio.setColumns(10);
		GridBagConstraints gbc_txtAnio = new GridBagConstraints();
		gbc_txtAnio.fill = GridBagConstraints.BOTH;
		gbc_txtAnio.insets = new Insets(0, 0, 5, 5);
		gbc_txtAnio.gridx = 1;
		gbc_txtAnio.gridy = 2;
		panelMateria.add(txtAnio, gbc_txtAnio);
		
		btnCorrelativa = new JButton("Correlativas");
		btnCorrelativa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				agregarCorrelativa();
			}
		});
		GridBagConstraints gbc_btnCorrelativa = new GridBagConstraints();
		gbc_btnCorrelativa.fill = GridBagConstraints.BOTH;
		gbc_btnCorrelativa.insets = new Insets(0, 0, 5, 0);
		gbc_btnCorrelativa.gridx = 2;
		gbc_btnCorrelativa.gridy = 2;
		panelMateria.add(btnCorrelativa, gbc_btnCorrelativa);
		
		JLabel label_3 = new JLabel("Plan:");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_label_3 = new GridBagConstraints();
		gbc_label_3.fill = GridBagConstraints.BOTH;
		gbc_label_3.insets = new Insets(0, 0, 5, 5);
		gbc_label_3.gridx = 0;
		gbc_label_3.gridy = 3;
		panelMateria.add(label_3, gbc_label_3);
		
		txtPlan = new JTextField();
		txtPlan.setColumns(10);
		GridBagConstraints gbc_txtPlan = new GridBagConstraints();
		gbc_txtPlan.fill = GridBagConstraints.BOTH;
		gbc_txtPlan.insets = new Insets(0, 0, 5, 5);
		gbc_txtPlan.gridx = 1;
		gbc_txtPlan.gridy = 3;
		panelMateria.add(txtPlan, gbc_txtPlan);
		
		cbPlan = new JComboBox();
		cbPlan.setEditable(false);
		modeloComboPlan = new DefaultComboBoxModel<String>();
		cbPlan.setModel(modeloComboPlan);
		GridBagConstraints gbc_cbPlan = new GridBagConstraints();
		gbc_cbPlan.insets = new Insets(0, 0, 5, 0);
		gbc_cbPlan.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbPlan.gridx = 2;
		gbc_cbPlan.gridy = 3;
		panelMateria.add(cbPlan, gbc_cbPlan);
		
		// Esto es para que al crear el formulario se cargue el combobox
				try{
					Conexion conex = new Conexion();
					ResultSet resMat = null;
					Statement estatutoMat = conex.getConnection().createStatement();
					resMat = estatutoMat.executeQuery("SELECT distinct plan from basegada.materia order by plan desc");
					
					while (resMat.next()){
						
						modeloComboPlan.addElement((String) resMat.getObject(1));
					}
					resMat.close();
					estatutoMat.close();
					conex.desconectar();
				}		catch (SQLException e){
							JOptionPane.showMessageDialog(null, "Error al consultar materias","Error",JOptionPane.ERROR_MESSAGE);
				}
		
		JLabel lblMdulos = new JLabel("Módulos:");
		lblMdulos.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblMdulos = new GridBagConstraints();
		gbc_lblMdulos.anchor = GridBagConstraints.EAST;
		gbc_lblMdulos.fill = GridBagConstraints.VERTICAL;
		gbc_lblMdulos.insets = new Insets(0, 0, 0, 5);
		gbc_lblMdulos.gridx = 0;
		gbc_lblMdulos.gridy = 4;
		panelMateria.add(lblMdulos, gbc_lblMdulos);
		
		txtModulos = new JTextField();
		GridBagConstraints gbc_txtModulos = new GridBagConstraints();
		gbc_txtModulos.fill = GridBagConstraints.BOTH;
		gbc_txtModulos.insets = new Insets(0, 0, 0, 5);
		gbc_txtModulos.gridx = 1;
		gbc_txtModulos.gridy = 4;
		panelMateria.add(txtModulos, gbc_txtModulos);
		txtModulos.setColumns(10);
		
		btnEliminarCorrelativas = new JButton("Eliminar correlativas");
		btnEliminarCorrelativas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				eliminarCorrelativa();
			}
		});
		GridBagConstraints gbc_btnEliminarCorrelativas = new GridBagConstraints();
		gbc_btnEliminarCorrelativas.fill = GridBagConstraints.BOTH;
		gbc_btnEliminarCorrelativas.gridx = 2;
		gbc_btnEliminarCorrelativas.gridy = 4;
		panelMateria.add(btnEliminarCorrelativas, gbc_btnEliminarCorrelativas);
		
		JPanel panelCorrelativas = new JPanel();
		panelCorrelativas.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Correlativa de...", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GridBagConstraints gbc_panelCorrelativas = new GridBagConstraints();
		gbc_panelCorrelativas.anchor = GridBagConstraints.NORTHWEST;
		gbc_panelCorrelativas.insets = new Insets(0, 0, 5, 0);
		gbc_panelCorrelativas.gridx = 0;
		gbc_panelCorrelativas.gridy = 1;
		contentPane.add(panelCorrelativas, gbc_panelCorrelativas);
		GridBagLayout gbl_panelCorrelativas = new GridBagLayout();
		gbl_panelCorrelativas.columnWidths = new int[]{762, 0};
		gbl_panelCorrelativas.rowHeights = new int[]{180, 0};
		gbl_panelCorrelativas.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panelCorrelativas.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panelCorrelativas.setLayout(gbl_panelCorrelativas);
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		panelCorrelativas.add(scrollPane, gbc_scrollPane);
		listCorre = new JList();
		////// 	listaTotal.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
				
				scrollPane.setViewportView(listCorre);
		
		JPanel panelBotones = new JPanel();
		GridBagConstraints gbc_panelBotones = new GridBagConstraints();
		gbc_panelBotones.anchor = GridBagConstraints.NORTH;
		gbc_panelBotones.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelBotones.gridx = 0;
		gbc_panelBotones.gridy = 2;
		contentPane.add(panelBotones, gbc_panelBotones);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardarMateria();
			}
		});
		panelBotones.add(btnGuardar);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				agregarMateria();
			}
		});
		panelBotones.add(btnAgregar);
		
		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				accion=2;
				modificarMateria();
			}
		});
		panelBotones.add(btnModificar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				eliminarMateria();
			}
		});
		panelBotones.add(btnEliminar);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				buscarMaterias();
			}
		});
		panelBotones.add(btnBuscar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				volver();
			}
		});
		panelBotones.add(btnCancelar);
		
		btnReporte = new JButton("Reporte");
		btnReporte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String plan=JOptionPane.showInputDialog("Ingrese el Plan de estudios");
				miCoordinador.crearReporteMaterias(plan);
			}
		});
		panelBotones.add(btnReporte);
	}
	protected void eliminarCorrelativa() {
		/*
		 *  parecido a agregarCorrelativa pero la consulta carga la lista de la izquierda
		 *  con las materias que ya son correlativas y las que pasamos a la derecha se eliminan
		 */		
		int ven=2;
		String codMat, matNom, plan;
		int anio;
		codMat=txtCodMat.getText();
		matNom=txtMatNom.getText();
		plan=txtPlan.getText();
		anio=Integer.valueOf(txtAnio.getText().trim());
		
		miCoordinador.mostrarVentanaMateriaNueva(codMat, matNom, anio, plan,ven);
	}
/*
 * ven = 1 es la ventana para agregar correlativas
 * ven = 2 es para quitar correlativas
 */
	public void agregarCorrelativa() {
		int ven=1;
		String codMat, matNom, plan;
		int anio;
		codMat=txtCodMat.getText();
		matNom=txtMatNom.getText();
		plan=txtPlan.getText();
		anio=Integer.valueOf(txtAnio.getText().trim());
		
		miCoordinador.mostrarVentanaMateriaNueva(codMat, matNom, anio, plan,ven);
	}

	/**
	 * Habilita campos y botones para modificar una materia
	 */
	protected void modificarMateria() {
		habilita(false,true, true, true, true,false,false,false,false,true,false,false,false,true,false);
		
	}
	/**
	 * Elimina una materia pero solo si no tiene ningún alumno inscripto
	 */
	public void eliminarMateria() {
		if (!txtCodMat.getText().equals(""))
		{
			int respuesta=JOptionPane.showConfirmDialog(null, "Está seguro de eliminar esa Materia?", "Confirmación", JOptionPane.YES_NO_OPTION);
					
			
			if (respuesta == JOptionPane.YES_NO_OPTION)
			{
				miCoordinador.eliminarMateria(txtCodMat.getText());
				limpiar();
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Ingrese un código de Materia", "Información",JOptionPane.WARNING_MESSAGE);
		}
		
	}
	/**
	 * Llama al método limpiar()
	 */
	protected void volver() {
		//this.dispose();
		limpiar();
	}
	/**
	 * Lista en un JTable todas las materias ordenadas por curso
	 */
	protected void buscarMaterias() {
		/*
		 * para variable btn==1 trae todos las materias
		 * 				 btn==2 trae busqueda parcial codigo de materia
		 * 				 btn==3 trae busqueda parcial nombre de materia
		 */
		int btn=1;
		String codMat=txtCodMat.getText();
		String nomMat=txtMatNom.getText();
		String plan=(String) cbPlan.getSelectedItem();
		
		System.out.println(plan);
		
		miCoordinador.mostrarVentanaMateriaBuscar(btn,codMat,nomMat,plan);	
	}
	/**
	 * Lista en un JTable todas las materias ordenadas por código
	 */
	protected void busquedaParcialCodigo() {
		
		int btn=2;
		String codMat=txtCodMat.getText();
		String nomMat=txtMatNom.getText();
		String plan=(String) cbPlan.getSelectedItem();
		
		miCoordinador.mostrarVentanaMateriaBuscar(btn,codMat,nomMat,plan);
	}
	/**
	 * Lista en un JTable todas las materias ordenadas por nombre
	 */
	protected void busquedaParcialNombre() {

		int btn=3;
		String codMat=txtCodMat.getText();
		String nomMat=txtMatNom.getText();
		String plan=(String) cbPlan.getSelectedItem();
		
		miCoordinador.mostrarVentanaMateriaBuscar(btn,codMat,nomMat,plan);
		
	}
	/**
	 * Establece relación con el Coordinador
	 * @param miCoordinador
	 */
	public void setCoordinador(Coordinador miCoordinador) {
		this.miCoordinador = miCoordinador;
	}
	/**
	 * Recibe el objeto Materia desde el JTable y lo muestra en los cuadros de texto
	 * @param miMateria
	 */
	public void muestraMateria(MateriaVO miMateria) {
		
		String codMat = miMateria.getCodmat();
		String nomMat = miMateria.getMatnom();
		int anio = miMateria.getAnio();
		String plan = miMateria.getPlan();
		
		//if (accion==1){
			//es un alta de materia con sus correlativas o no
		//	miCoordinador.mostrarVentanaMateriaNueva(codMat,nomMat,anio,plan);
	//	}else{
		
			System.out.println("Entré al else y acción "+accion);
			txtCodMat.setText(miMateria.getCodmat());
			txtMatNom.setText(miMateria.getMatnom());
			txtAnio.setText(String.valueOf(miMateria.getAnio()));
			txtPlan.setText(miMateria.getPlan());
			txtModulos.setText(String.valueOf(miMateria.getModulos()));
			
			
				modeCorre.clear();
				
				MateriaDAO miMateriaDAO = new MateriaDAO();
				miMateriaDAO.cargarListadoVentanaMateria(modeCorre,codMat);
				listCorre.setModel(modeCorre);
				
		//	}
			
			
			
			habilita(false,false, false, false, false,false,false,true,true,false,false,true,true,true,false);
		}
	
	/**
	 * Habilita botones y campos de la VentanaMateria
	 */
	public void habilita(boolean cod, boolean nombre, boolean anio, boolean plan,  boolean mod, boolean bBuscarxDni, boolean bBuscarxNom, boolean bCorre,
			boolean bEliminaCorre, boolean bGuardar,boolean bAgregar, boolean bModificar, boolean bEliminar, boolean bCancelar,boolean bReporte) {
		txtCodMat.setEditable(cod);
		txtMatNom.setEditable(nombre);
		txtAnio.setEditable(anio);
		txtPlan.setEditable(plan);
		txtModulos.setEditable(mod);
		
		btnBuscarXCodigo.setEnabled(bBuscarxDni);
		btnBuscarXNombre.setEnabled(bBuscarxNom);
		btnCorrelativa.setEnabled(bCorre);
		btnEliminarCorrelativas.setEnabled(bEliminaCorre);
		btnGuardar.setVisible(bGuardar);
		btnAgregar.setEnabled(bAgregar);
		btnModificar.setEnabled(bModificar);
		btnEliminar.setEnabled(bEliminar);
		btnCancelar.setVisible(bCancelar);
		btnReporte.setEnabled(bReporte);
	}
	/**
	 * Limpia los campos de la VentanaMateria
	 */
	public void limpiar(){
		txtCodMat.setText("");
		txtMatNom.setText("");
		txtAnio.setText("");
		txtPlan.setText("");
		txtModulos.setText("");
		modeCorre.clear();
		
		habilita(true,true,false,false,false,true,true,false,false,false,true,false,false,true,true);
	}
	/**
	 * Habilita campos y botones para agregar una materia nueva
	 */
	public void agregarMateria() {
		accion=1;
		habilita(true, true, true, true, true, false, false, false, false, true,false, false, false, true,
				false);
		//busquedaParcialNombre();
		txtCodMat.grabFocus();
	}
	/**
	 *  Guarda en la BD una materia nueva o una modificada
	 */
	private void guardarMateria(){
		try {
			String cadenaConEspacios = txtCodMat.getText();
			String cadenaSinEspacios = cadenaConEspacios.replaceAll("\\s+", "").toUpperCase();
			MateriaVO miMateria = new MateriaVO();
			miMateria.setCodmat(cadenaSinEspacios);
			miMateria.setMatnom(txtMatNom.getText().toUpperCase());
			miMateria.setAnio(Integer.valueOf(txtAnio.getText().substring(0, 1)));
			miMateria.setPlan(txtPlan.getText());
			miMateria.setModulos(Integer.valueOf(txtModulos.getText()));
		
			if (accion==1){
			miCoordinador.registrarMateria(miMateria);
			
			/*int respuesta=JOptionPane.showConfirmDialog(null, "Agrega correlativas?", 
					"Confirmación", JOptionPane.YES_NO_OPTION);
					
			
				if (respuesta == JOptionPane.YES_NO_OPTION)
				{
					agregarCorrelativa();
				}*/
		
			}else 
				miCoordinador.modificarMateria(miMateria);
				limpiar();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "<html>Error en el Ingreso de Datos, se<br>deben completar todos los campos", "Error",
					JOptionPane.ERROR_MESSAGE);
			limpiar();
		}
	}

	
	
	public JButton getBtnEliminarCorrelativas() {
		return btnEliminarCorrelativas;
	}
}
