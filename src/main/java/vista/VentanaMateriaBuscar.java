package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controlador.Coordinador;
import modelo.AlumnoVO;
import modelo.MateriaDAO;
import modelo.MateriaVO;

public class VentanaMateriaBuscar extends JFrame {

	private JPanel contentPane;
	private Coordinador miCoordinador;
	private JScrollPane scrollPane;
	private JTable table;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaMateriaBuscar frame = new VentanaMateriaBuscar();
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
	public VentanaMateriaBuscar() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		JPanel panelBoton = new JPanel();
		contentPane.add(panelBoton, BorderLayout.SOUTH);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		panelBoton.add(btnVolver);
	}

	public void setCoordinador(Coordinador miCoordinador) {
		this.miCoordinador = miCoordinador;
	}
	
	public void mostrarDatosMateriaConTableModel(int btn, String codMat, String nomMat) {
		DefaultTableModel modeloMateria = new DefaultTableModel();
		table = new JTable();
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				/*
				 *  pasa los datos a la VentanaMateria 
				 */
				miCoordinador.pasarDatosMateria(pasarDatosMateria(e));
			}
		});
		
		table.setModel(modeloMateria);
		modeloMateria.addColumn("Código");
		modeloMateria.addColumn("Materia");
		modeloMateria.addColumn("Curso");
		modeloMateria.addColumn("Plan");
		modeloMateria.addColumn("Módulos");
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.getTableHeader().setReorderingAllowed(false);
		
		MateriaDAO miMateriaDAO = new MateriaDAO();
		if (btn==1){
			miMateriaDAO.buscarMaterias(modeloMateria);
			setTitle("Materias ordenadas por CURSO");
		}
		if (btn==2){
			miMateriaDAO.buscarParcialMateriaCodigo(modeloMateria,codMat);
			setTitle("Materias ordenadas por CODIGO");
		}
		if (btn==3){
			miMateriaDAO.buscarParcialMateriaNombre(modeloMateria,nomMat);
			setTitle("Materias ordenadas por NOMBRE");
		}
		scrollPane.setViewportView(table);
	}

	protected MateriaVO pasarDatosMateria(MouseEvent e) {
		
		MateriaVO miMateria = new MateriaVO();
		int row=table.rowAtPoint(e.getPoint());
		miMateria.setCodmat(table.getValueAt(row, 0).toString());
		miMateria.setMatnom(table.getValueAt(row, 1).toString());
		miMateria.setAnio(Integer.valueOf(table.getValueAt(row, 2).toString()));
		miMateria.setPlan(table.getValueAt(row, 3).toString());
		miMateria.setModulos(Integer.valueOf(table.getValueAt(row, 4).toString()));
			
		return miMateria;
	}
}
