package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
import modelo.Logica;
import modelo.MateriaDAO;
import modelo.MateriaVO;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JList;
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
		setBounds(100, 100, 629, 419);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panelCorrelativas = new JPanel();
		panelCorrelativas.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Correlativa de...", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		contentPane.add(panelCorrelativas, BorderLayout.CENTER);
		panelCorrelativas.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 26, 552, 138);
		panelCorrelativas.add(scrollPane);
		
		modeCorre = new DefaultListModel<String>();
		listCorre = new JList();
////// 	listaTotal.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
		scrollPane.setViewportView(listCorre);
		
		JPanel panelBotones = new JPanel();
		contentPane.add(panelBotones, BorderLayout.SOUTH);
		
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
		
		JPanel panelMateria = new JPanel();
		contentPane.add(panelMateria, BorderLayout.NORTH);
		panelMateria.setLayout(new GridLayout(0, 3, 5, 5));
		
		JLabel label = new JLabel("Código de Materia:");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		panelMateria.add(label);
		
		txtCodMat = new JTextField();
		txtCodMat.setColumns(10);
		panelMateria.add(txtCodMat);
		
		btnBuscarXCodigo = new JButton("Buscar x código");
		btnBuscarXCodigo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				busquedaParcialCodigo();
			}
		});
		panelMateria.add(btnBuscarXCodigo);
		
		JLabel label_1 = new JLabel("Nombre:");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		panelMateria.add(label_1);
		
		txtMatNom = new JTextField();
		txtMatNom.setColumns(10);
		panelMateria.add(txtMatNom);
		
		btnBuscarXNombre = new JButton("Buscar x nombre");
		btnBuscarXNombre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				busquedaParcialNombre();
			}
		});
		panelMateria.add(btnBuscarXNombre);
		
		JLabel label_2 = new JLabel("Curso:");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		panelMateria.add(label_2);
		
		txtAnio = new JTextField();
		txtAnio.setColumns(10);
		panelMateria.add(txtAnio);
		
		btnCorrelativa = new JButton("Correlativas");
		btnCorrelativa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				agregarCorrelativa();
			}
		});
		panelMateria.add(btnCorrelativa);
		
		JLabel label_3 = new JLabel("Plan:");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		panelMateria.add(label_3);
		
		txtPlan = new JTextField();
		txtPlan.setColumns(10);
		panelMateria.add(txtPlan);
		
		btnEliminarCorrelativas = new JButton("Eliminar correlativas");
		btnEliminarCorrelativas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				eliminarCorrelativa();
			}
		});
		panelMateria.add(btnEliminarCorrelativas);
		
		JLabel lblMdulos = new JLabel("Cantidad de módulos:");
		lblMdulos.setHorizontalAlignment(SwingConstants.RIGHT);
		panelMateria.add(lblMdulos);
		
		txtModulos = new JTextField();
		panelMateria.add(txtModulos);
		txtModulos.setColumns(10);
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
		
		miCoordinador.mostrarVentanaMateriaBuscar(btn,codMat,nomMat);	
	}
	/**
	 * Lista en un JTable todas las materias ordenadas por código
	 */
	protected void busquedaParcialCodigo() {
		
		int btn=2;
		String codMat=txtCodMat.getText();
		String nomMat=txtMatNom.getText();
		
		miCoordinador.mostrarVentanaMateriaBuscar(btn,codMat,nomMat);
	}
	/**
	 * Lista en un JTable todas las materias ordenadas por nombre
	 */
	protected void busquedaParcialNombre() {

		int btn=3;
		String codMat=txtCodMat.getText();
		String nomMat=txtMatNom.getText();
		
		miCoordinador.mostrarVentanaMateriaBuscar(btn,codMat,nomMat);
		
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
			MateriaVO miMateria = new MateriaVO();
			miMateria.setCodmat(txtCodMat.getText());
			miMateria.setMatnom(txtMatNom.getText());
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
