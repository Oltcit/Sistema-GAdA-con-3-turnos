package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import controlador.Coordinador;
import modelo.AlumnomesaDAO;
import modelo.MateriaCorrelativaDAO;
import modelo.MateriaCorrelativaVO;
import modelo.MateriaDAO;

public class VentanaMateriaNueva extends JFrame {

	private JPanel contentPane;
	private Coordinador miCoordinador;

	private JScrollPane scrollPaneTotal;
	private JList <String>listaTotal;
	private DefaultListModel<String>modeloTotal;
	private JScrollPane scrollPaneCorre;
	private JList <String>listaCorre;
	private DefaultListModel<String>modeloCorre;
	private JLabel lblMatNom;
	private JLabel lblCodMat;
	
	public Coordinador getMiCoordinador() {
		return miCoordinador;
	}

	public void setMiCoordinador(Coordinador miCoordinador) {
		this.miCoordinador = miCoordinador;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaMateriaNueva frame = new VentanaMateriaNueva();
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
	public VentanaMateriaNueva() {
		setTitle("Alta de Materias correlativas");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 755, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(null);
		
		scrollPaneTotal = new JScrollPane();
		scrollPaneTotal.setBounds(10, 11, 293, 282);
		panelCentro.add(scrollPaneTotal);
		
		modeloTotal=new DefaultListModel<String>();
		listaTotal = new JList();
		listaTotal.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		scrollPaneTotal.setViewportView(listaTotal);
		
		
		
		scrollPaneCorre = new JScrollPane();
		scrollPaneCorre.setBounds(426, 11, 293, 282);
		panelCentro.add(scrollPaneCorre);
		
		modeloCorre = new DefaultListModel<String>();
		listaCorre = new JList();
		scrollPaneCorre.setViewportView(listaCorre);
		
		JButton btnPasar = new JButton(">");
		btnPasar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pasarMateria();
			}
		});
		btnPasar.setBounds(336, 78, 58, 23);
		panelCentro.add(btnPasar);
		
		JButton btnTraer = new JButton("<");
		btnTraer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				traerMateria();
			}
		});
		btnTraer.setBounds(336, 156, 58, 23);
		panelCentro.add(btnTraer);
		
		JPanel panelSur = new JPanel();
		contentPane.add(panelSur, BorderLayout.SOUTH);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardarMaterias();
			}
		});
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				eliminaCorrelativas();
			}
		});
		panelSur.add(btnEliminar);
		panelSur.add(btnGuardar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		panelSur.add(btnCancelar);
		
		JPanel panelNorte = new JPanel();
		panelNorte.setBorder(new TitledBorder(null, "Correlativa de...", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panelNorte, BorderLayout.NORTH);
		panelNorte.setLayout(new GridLayout(0, 2, 5, 3));
		
		JLabel lblNewLabel = new JLabel("Código:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		panelNorte.add(lblNewLabel);
		
		lblCodMat = new JLabel("");
		panelNorte.add(lblCodMat);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		panelNorte.add(lblNewLabel_1);
		
		lblMatNom = new JLabel("");
		panelNorte.add(lblMatNom);
	}

	protected void eliminaCorrelativas() {
		String fila,codMat,codCorr;
		
		codCorr=lblCodMat.getText();
		int cantMat= modeloTotal.size();
			
			if (modeloTotal.size()>0){
				for (int i=0; i<modeloTotal.size();i++){
					codMat="";
				
					// guardo la fila completa en la variable String fila
					int desde=0;
					int hasta=1;
					fila=modeloTotal.get(i);
					String letra=fila.substring(desde,hasta);
					while (!letra.equals(" ")){
						codMat+=letra;
						//arma el codigo con cada letra que agrega
						desde++;
						hasta++;
						//se corre a la siguiente posicion
						letra=fila.substring(desde,hasta);
						//saca la siguiente letra y vuelve al while
					}
					
					MateriaCorrelativaVO miMateriaCorrelativaVO = new MateriaCorrelativaVO();
					miMateriaCorrelativaVO.setCodCorrelativa(codCorr);
					miMateriaCorrelativaVO.setCodMateria(codMat);
					System.out.println("esta es codCorr "+codCorr);
					System.out.println("Esta es codMat "+codMat);
					cantMat--;
					// creo el objeto DAO para dar de baja en la base de datos
					MateriaCorrelativaDAO miMateriaCorrelativaDAO=new MateriaCorrelativaDAO();
					miMateriaCorrelativaDAO.eliminarCorrelativa(miMateriaCorrelativaVO,cantMat);
					
				}
				modeloTotal.clear();
				//JOptionPane.showMessageDialog(null, " La correlativase eliminó correctamente ","Información",JOptionPane.INFORMATION_MESSAGE);
			//	limpiar();
			}else
				JOptionPane.showMessageDialog(null, "Debe seleccionar materias para quitar de las correlativas","Error",JOptionPane.ERROR_MESSAGE);	
		}
		
	protected void guardarMaterias() {
		/*
		 * Genera un registro por cada fila del modeloCorre donde están todas las
		 * materias seleccionadas de años anteriores a la correlativa
		 */
		String fila,codMat,codCorr;
	 
		codCorr=lblCodMat.getText();
		int cantMat= modeloCorre.size();
		if (modeloCorre.size()>0){
			// if (tiene filas el modelo)
			for(int i=0;i<modeloCorre.size();i++){
				//para cada fila realiza un ciclo
				codMat="";
				fila=modeloCorre.get(i);	
				// guardo la fila completa en la variable String fila
				int desde=3;
				int hasta=4;
				/*
				 * declaro dos variable enteras y las inicializo en 3 y 4 para poder
				 * obtener el codigo de la materia (en la fila aparece primero el
				 * año ocupando la posición 1, un espacio en la posición 2 y a partir de
				 * la posición 3 comienza el codigo, pero como estos codigos son de distinta
				 * longitud voy sacando letras hasta encontrar un espacio)  
				 */
				String letra=fila.substring(desde,hasta);
				String letraCorr=fila.substring(2, 3);
				//saca la primer letra
				if (letraCorr.equalsIgnoreCase(" ")){
				while (!letra.equals(" ")){
					codMat+=letra;
					//arma el codigo con cada letra que agrega
					desde++;
					hasta++;
					//se corre a la siguiente posicion
					letra=fila.substring(desde,hasta);
					//saca la siguiente letra y vuelve al while
				}
				
				// creo el objeto MateriaCorrelativaVO y le asigno los dos codigos, el
				// de la correlativa Ej: Pro2 y la anterior Pro1
				MateriaCorrelativaVO miMateriaCorrelativaVO = new MateriaCorrelativaVO();
				miMateriaCorrelativaVO.setCodCorrelativa(codCorr);
				miMateriaCorrelativaVO.setCodMateria(codMat);
				cantMat--;
				// creo el objeto DAO para dar el alta en la base de datos
				MateriaCorrelativaDAO miMateriaCorrelativaDAO=new MateriaCorrelativaDAO();
				miMateriaCorrelativaDAO.registrarCorrelativa(miMateriaCorrelativaVO,cantMat);
				}
			}
			modeloCorre.clear();
		}
		else
			JOptionPane.showMessageDialog(null, "Debe seleccionar materias para asignar","Error",JOptionPane.ERROR_MESSAGE);		
	}

	protected void traerMateria() {
		modeloTotal.addElement(listaCorre.getSelectedValue());
		listaTotal.setModel(modeloTotal);
		modeloCorre.removeElement(listaCorre.getSelectedValue());
	}

	protected void pasarMateria() {
		List<String> seleccion = listaTotal.getSelectedValuesList();
		Iterator i= seleccion.iterator();
		int a=0;
		while (i.hasNext()){
			modeloCorre.addElement((String) i.next());
			modeloTotal.removeElement(seleccion.get(a));
			a++;
		}
		listaCorre.setModel(modeloCorre);
	}

	public void mostrarListadoDeMaterias(String codMat, String nomMat, int anio, String plan, int ven) {
		modeloTotal.clear();
		modeloCorre.clear();
		
		lblCodMat.setText(codMat);
		lblMatNom.setText(nomMat);
		
		MateriaDAO miMateriaDAO = new MateriaDAO();
		miMateriaDAO.cargarListadoCorrelativas(modeloTotal,modeloCorre,codMat,anio,plan,ven);
		listaTotal.setModel(modeloTotal);
		listaCorre.setModel(modeloCorre);
		if (ven==1)
		setTitle("Alta de Materias correlativas");
		else
			setTitle("Baja de Materias correlativas");
	}
	
}
