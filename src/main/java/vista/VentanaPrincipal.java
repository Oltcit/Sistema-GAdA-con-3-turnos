package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Coordinador;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class VentanaPrincipal extends JFrame {
 
	private JPanel contentPane;
	private Coordinador miCoordinador;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
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
	public VentanaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 726, 480);
		setTitle("            Gestión Académica de Alumnos                                                                 GAdA v1.1");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAlumno = new JButton("Alumnos");
		btnAlumno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				miCoordinador.mostrarVentanaAlumno();
			}
		});
		btnAlumno.setBounds(265, 28, 89, 23);
		contentPane.add(btnAlumno);
		
		JButton btnMateria = new JButton("Materias");
		btnMateria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				miCoordinador.mostrarVentanaMateria();
			}
		});
		btnMateria.setBounds(377, 28, 89, 23);
		contentPane.add(btnMateria);
		
		JButton btnMesas = new JButton("Mesas");
		btnMesas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				miCoordinador.mostrarVentanaAlumnoMesa();
			}
		});
		btnMesas.setBounds(265, 77, 89, 23);
		contentPane.add(btnMesas);
		
		JButton btnAlumnoMateria = new JButton("Alumno - Materia");
		btnAlumnoMateria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				miCoordinador.mostrarVentanaAlumnoMateria();
			}
		});
		btnAlumnoMateria.setBounds(519, 28, 130, 23);
		contentPane.add(btnAlumnoMateria);
		
		JButton btnReportes = new JButton("Reportes");
		btnReportes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				miCoordinador.mostrarVentanaReportes();
			}
		});
		
		JButton btnCargarParciales = new JButton("Notas parciales");
		btnCargarParciales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				miCoordinador.mostrarVentanaAlumnoParciales();
			}
		});
		btnCargarParciales.setBounds(519, 77, 130, 23);
		contentPane.add(btnCargarParciales);
		btnReportes.setBounds(377, 77, 89, 23);
		contentPane.add(btnReportes);
		
		JLabel lblLogoIS = new JLabel("");
		lblLogoIS.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/imagenes/Is44 logo.JPG")));
		lblLogoIS.setBounds(10, 19, 211, 236);
		contentPane.add(lblLogoIS);
		
		JLabel lblFondoMadera = new JLabel("");
		lblFondoMadera.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/imagenes/Instituto frente.JPG")));
		lblFondoMadera.setBounds(0, 0, 726, 480);
		contentPane.add(lblFondoMadera);
	}
	
	public void setCoordinador(Coordinador miCoordinador){
		this.miCoordinador = miCoordinador;
	}
}
