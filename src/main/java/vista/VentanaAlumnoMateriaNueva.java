package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import net.miginfocom.swing.MigLayout;
import javax.swing.JSpinner;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Calendar;
import com.toedter.calendar.JYearChooser;

import controlador.Coordinador;
import modelo.AlumnomateriaDAO;
import modelo.AlumnomateriaVO;
import modelo.AlumnomesaDAO;
import modelo.CalculaAi;
import modelo.Conexion;
import modelo.MateriaDAO;
import modelo.MesaDAO;

import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;

public class VentanaAlumnoMateriaNueva extends JFrame {

	private JPanel contentPane;
	
	private Coordinador miCoordinador;
	private JLabel lblDni;
	private JLabel lblApyNom;
	private JScrollPane scrollPaneTotal;
	private JList<String> listaTotal;
	private DefaultListModel<String> modeloTotal;
	private JScrollPane scrollPaneCursa;
	private JList<String> listaCursa;
	private DefaultListModel<String> modeloCursa;
	private JYearChooser selectorAnio;
	private JComboBox cbPlan;
	static DefaultComboBoxModel<String> modeloComboPlan;

	private int numAi;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rbTv;
	private JRadioButton rbTt;
	private JRadioButton rbTm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAlumnoMateriaNueva frame = new VentanaAlumnoMateriaNueva();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.       
	 */
	public VentanaAlumnoMateriaNueva() {
		setTitle("Materias a cursar en el año");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 791, 472);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panelNorte = new JPanel();
		panelNorte.setBorder(new TitledBorder(null, "Datos Alumno", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panelNorte, BorderLayout.NORTH);
		panelNorte.setLayout(new MigLayout("", "[95.00][441.00,grow][80.00,grow][80.00,grow]", "[19.00,grow][grow]"));
		
		JLabel labelApyNom = new JLabel("Apellido y Nombre:");
		panelNorte.add(labelApyNom, "cell 0 0,alignx right");
		
		lblApyNom = new JLabel("");
		panelNorte.add(lblApyNom, "cell 1 0,alignx left");
		
		JLabel labelAnioCursada = new JLabel("Año de cursada");
		panelNorte.add(labelAnioCursada, "cell 2 0,alignx center");
	
		selectorAnio = new JYearChooser();
		selectorAnio.setValue(2023);
		panelNorte.add(selectorAnio, "cell 3 0,grow");
		
		JLabel labelDni = new JLabel("Dni:");
		panelNorte.add(labelDni, "cell 0 1,alignx right");
		
		lblDni = new JLabel("");
		panelNorte.add(lblDni, "cell 1 1");
		
		Calendar cal = Calendar.getInstance();
		int anioActual = cal.get(Calendar.YEAR);
		
		JLabel lblNewLabel = new JLabel("Plan de estudio");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panelNorte.add(lblNewLabel, "cell 2 1,alignx trailing");
		
		cbPlan = new JComboBox();
		cbPlan.setEditable(false);
		cbPlan.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {		
				cambiaListaMateriasPorCombo();	
			}
		});
		modeloComboPlan = new DefaultComboBoxModel<String>();
		cbPlan.setModel(modeloComboPlan);
		panelNorte.add(cbPlan, "cell 3 1,growx");
		
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
		
		JPanel panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(null);
		
		scrollPaneTotal = new JScrollPane();
		scrollPaneTotal.setBounds(0, 11, 348, 247);
		panelCentro.add(scrollPaneTotal);
		
		modeloTotal = new DefaultListModel<String>();
		listaTotal = new JList<String>();
		listaTotal.setForeground(Color.BLUE);
		listaTotal.setBackground(new Color(255, 250, 240));
		listaTotal.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		scrollPaneTotal.setViewportView(listaTotal);
		
		scrollPaneCursa = new JScrollPane();
		scrollPaneCursa.setBounds(417, 11, 348, 247);
		panelCentro.add(scrollPaneCursa);
		
		modeloCursa = new DefaultListModel<String>();
		listaCursa = new JList<String>();
		listaCursa.setForeground(Color.BLACK);
		listaCursa.setBackground(new Color(255,250,240));
		scrollPaneCursa.setViewportView(listaCursa);
		
		JButton btnPasar = new JButton(">");
		btnPasar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pasarMateria();
			}
		});
		btnPasar.setBounds(358, 93, 49, 23);
		panelCentro.add(btnPasar);
		
		JButton btnTraer = new JButton("<");
		btnTraer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				traerMateria();
			}
		});
		btnTraer.setBounds(358, 156, 49, 23);
		panelCentro.add(btnTraer);
		
		JPanel panelRadioButton = new JPanel();
		panelRadioButton.setBounds(10, 269, 745, 31);
		panelCentro.add(panelRadioButton);
		
		rbTm = new JRadioButton("Turno mañana");
		buttonGroup.add(rbTm);
		panelRadioButton.add(rbTm);
		
		rbTt = new JRadioButton("Turno tarde");
		buttonGroup.add(rbTt);
		panelRadioButton.add(rbTt);
		
		rbTv = new JRadioButton("Turno vespertino");
		buttonGroup.add(rbTv);
		panelRadioButton.add(rbTv);
		
		JPanel panelSur = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelSur.getLayout();
		flowLayout.setHgap(20);
		contentPane.add(panelSur, BorderLayout.SOUTH);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardarMaterias();
				volver();
			}
		});
		panelSur.add(btnGuardar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				volver();
			}
		});
		panelSur.add(btnCancelar);
	}

	

	protected void volver() {
		this.dispose();
	}

	protected void guardarMaterias() {
	
		String fila,codMat;
		int fecha=selectorAnio.getValue();
		int doc;
		int cantMat=modeloCursa.size();
		if (modeloCursa.size()>0){
			if (rbTm.isSelected() || rbTt.isSelected() || rbTv.isSelected()) {
			for(int i=0;i<modeloCursa.size();i++){
				codMat="";
				fila=modeloCursa.get(i);				
				int desde=3;
				int hasta=4;
				String letra=fila.substring(desde,hasta);
				while (!letra.equals(" ")){
					codMat+=letra;
					desde++;
					hasta++;
					letra=fila.substring(desde,hasta);
				}
				
				CalculaAi cai = new CalculaAi();
				numAi = cai.calculaIdAlumnoMateria();				
				doc=Integer.valueOf(lblDni.getText().trim());
				AlumnomateriaVO miAlumnoMateriaVO = new AlumnomateriaVO();
				miAlumnoMateriaVO.setAldni2(doc);
				miAlumnoMateriaVO.setCodalumnomateria(numAi);
				miAlumnoMateriaVO.setCodmat2(codMat);
				miAlumnoMateriaVO.setFechaDeCursada(fecha);
				miAlumnoMateriaVO.setParcial1("");
				miAlumnoMateriaVO.setParcial2("");
				miAlumnoMateriaVO.setRecup1("");
				miAlumnoMateriaVO.setRecup2("");
				miAlumnoMateriaVO.setRecup3("");
				miAlumnoMateriaVO.setSituacion("");
				
				
				if (rbTm.isSelected())
					miAlumnoMateriaVO.setTurno("TM");
				if (rbTt.isSelected())
					miAlumnoMateriaVO.setTurno("TT");
				if (rbTv.isSelected())
					miAlumnoMateriaVO.setTurno("TV");
				
				cantMat--;
				AlumnomateriaDAO miAlumnoMateriaDAO = new AlumnomateriaDAO();
				miAlumnoMateriaDAO.registrarAlumnoMateria(miAlumnoMateriaVO, cantMat);
			}
			modeloCursa.clear();
		
		}
			else
				JOptionPane.showMessageDialog(null, "Debe seleccionar un turno para la cursada","Error",JOptionPane.ERROR_MESSAGE);
		}
		else
			JOptionPane.showMessageDialog(null, "Debe seleccionar materias para cursar","Error",JOptionPane.ERROR_MESSAGE);	
	}

	public Coordinador getCoordinador() {
		return miCoordinador;
	}

	public void setCoordinador(Coordinador miCoordinador) {
		this.miCoordinador = miCoordinador;
	}

	protected void cambiaListaMateriasPorCombo() {
		
		if (!lblDni.getText().isEmpty()) {
			int doc=Integer.valueOf(lblDni.getText());
		
		String nom=lblApyNom.getText();
		mostrarListaDeMaterias(doc, nom);
		}
	}
	
	//public void mostrarListaDeMaterias(int numAi, int doc, String nom) {
		public void mostrarListaDeMaterias(int doc, String nom) {
		modeloTotal.clear();
		modeloCursa.clear();
		lblApyNom.setText(nom);
		lblDni.setText(String.valueOf(doc));
		String plan=(String) cbPlan.getSelectedItem();
		MateriaDAO miMateriaDAO = new MateriaDAO();
		miMateriaDAO.cargarListadoMateriasNuevasAlumno(modeloTotal,doc,plan);
		listaTotal.setModel(modeloTotal);
	}

	protected void traerMateria() {
		modeloTotal.addElement(listaCursa.getSelectedValue());
		listaTotal.setModel(modeloTotal);
		modeloCursa.removeElement(listaCursa.getSelectedValue());
	}

	protected void pasarMateria() {
		
		List<String> seleccion =listaTotal.getSelectedValuesList();
		Iterator i=seleccion.iterator();
		int a =0;
		while (i.hasNext()){
			modeloCursa.addElement((String) i.next());
			modeloTotal.removeElement(seleccion.get(a));
			a++;
		}
		listaCursa.setModel(modeloCursa);
	}
}
