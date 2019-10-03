package isi.dds.tp.app;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import isi.dds.tp.enums.EnumSiniestros;
import isi.dds.tp.modelo.*;
import net.miginfocom.swing.MigLayout;


@SuppressWarnings("serial")
public class AltaPoliza1 extends JPanel {
	
	//private Dimension botones = new Dimension(341, 70);
	
	private JLabel ldatosCliente = new JLabel("DATOS CLIENTE");
	private JLabel ldatosPoliza = new JLabel("DATOS P\u00d3LIZA");
	
	private JButton btnBuscarCliente = new JButton("BUSCAR CLIENTES");
	private JButton btnAltaCliente = new JButton("DAR ALTA CLIENTES");
	private JLabel lnumeroCliente = new JLabel("N\u00famero cliente:");
	private JLabel ltipoDocumento = new JLabel("Tipo documento:");
	private JLabel ldocumento = new JLabel("Documento:");
	private JLabel lapellido = new JLabel("Apellido:");
	private JLabel lnombres = new JLabel("Nombres:");
	private JLabel lcalle = new JLabel("Calle:");
	private JLabel lnumeroDom = new JLabel("N\u00famero domicilio:");
	private JLabel ldepartamento = new JLabel("  Departamento:");
	private JTextField tnumeroCliente = new JTextField(10);
	private JTextField ttipoDocumento = new JTextField(15);;
	private JTextField tdocumento = new JTextField(8);
	private JTextField tapellido = new JTextField(15);
	private JTextField tnombres = new JTextField(15);;
	private JTextField tcalle = new JTextField(15);
	private JTextField tnumeroDom = new JTextField(8);
	private JTextField tdepartamento = new JTextField(3);
	
	

	private JButton btnAgregarHijo = new JButton("Agregar datos hijo");
	private JButton btnQuitarHijo = new JButton("Quitar datos hijo");
	private JButton btnConfirmarDatos = new JButton("Confirmar datos");
	private JButton btnCancelar = new JButton("Cancelar");
	private JLabel lprovincia = new JLabel("Provincia*:");
	private JLabel lciudad = new JLabel("Ciudad*:");
	private JLabel lmarca = new JLabel("Marca veh\u00edculo*:");
	private JLabel lmodelo = new JLabel("Modelo veh\u00edculo:");
	private JLabel lanio = new JLabel("A\u00f1o modelo:");
	private JLabel lmotor = new JLabel("Motor*:");
	private JLabel lchasis = new JLabel("Chasis*:");
	private JLabel lpatente = new JLabel("Patente*:");
	private JLabel lsumaAseg = new JLabel("Suma asegurada:");
	private JLabel lmoneda = new JLabel("$");
	private JLabel lkm = new JLabel("Km realizados por a\u00f1o:");
	private JLabel lsiniestros = new JLabel("N\u00famero de siniestros en el \u00faltimo año*:");
	private JLabel lgarage = new JLabel("¿Se guarda en garage?");
	private JLabel lalarma = new JLabel("|  ¿Tiene alarma?");
	private JLabel lrastreo = new JLabel("|  ¿Posee dispositivo de rastreo vehicular?");
	private JLabel ltuercas = new JLabel("|  ¿Posee tuercas antirrobo?");
	private JLabel lcantidadHijos = new JLabel("Cantidad de hijos entre 18 y 30 a\u00f1os:");
	private JLabel ldatosObligatorios = new JLabel("(*) datos obligatios)");
	

	private JComboBox<Provincia> cprovincia = new JComboBox<Provincia>();
	private JComboBox<Ciudad> cciudad = new JComboBox<Ciudad>();
	private JComboBox<Marca> cmarca = new JComboBox<Marca>();
	private JComboBox<Modelo> cmodelo = new JComboBox<Modelo>();
	private JComboBox<AnioModelo> canio = new JComboBox<AnioModelo>();
	private JComboBox<String> ckm = new JComboBox<String>();
	private JComboBox<EnumSiniestros> csiniestros = new JComboBox<EnumSiniestros>();
	private JTextField tmotor = new JTextField(15);
	private JTextField tchasis = new JTextField(8);
	private JTextField tpatente = new JTextField(7);
	private JTextField tsumaAsegurada = new JTextField(15);
	private ButtonGroup garage = new ButtonGroup();
	private JRadioButton rgarageSi = new JRadioButton("SI");
	private JRadioButton rgarageNo = new JRadioButton("NO");
	private ButtonGroup alarma = new ButtonGroup();
	private JRadioButton ralarmaSi = new JRadioButton("SI");
	private JRadioButton ralarmaNo = new JRadioButton("NO");
	private ButtonGroup rastreo = new ButtonGroup();
	private JRadioButton rrastreoSi = new JRadioButton("SI");
	private JRadioButton rrastreoNo = new JRadioButton("NO");
	private ButtonGroup tuercas = new ButtonGroup(); 
	private JRadioButton rtuercasSi = new JRadioButton("SI");
	private JRadioButton rtuercasNo = new JRadioButton("NO");
	
	private JTable tablaHijos = new JTable();
	/*TODO crear metodo par insertar dato en columnas desde la ventana de resgristos de hijos*/
	private String[] columnas = {" Hijo ", " Fecha nacimiento ", " Sexo ",  " Estado civil "};
	private Object[][] datosTabla = {{"1", "27/03/1992", "Masculino", "Casado"}};

	
	
	@SuppressWarnings({ "deprecation", "rawtypes", "unchecked" })
	public AltaPoliza1(JFrame f) {

		setFont(new Font("Open Sans", Font.PLAIN, 13));
		//setBackground(new Color(210, 180, 140));
		setBackground(Color.GRAY);
		setBounds(0,0,1024,600);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new MigLayout());
		
		
		ldatosCliente.setFont(new Font("Open Sans", Font.BOLD, 15));
		add(ldatosCliente,"cell 0 0, wrap, alignx center, span 12 1");
		
		
		//BOTON BUSCAR CLIENTE
		btnBuscarCliente.setFont(new Font("Open Sans", Font.PLAIN, 13));
		btnBuscarCliente.setEnabled(true);
		btnBuscarCliente.setBackground(Color.LIGHT_GRAY);
		add(btnBuscarCliente);
		
		
		//NUMERO CLIENTE
		lnumeroCliente.setFont(new Font("Open Sans", Font.PLAIN, 13));
		add(lnumeroCliente, "gap left 30, alignx right");
		tnumeroCliente.disable();
		add(tnumeroCliente);

		//TIPO DOCUMENTO CLIENTE
		ltipoDocumento.setFont(new Font("Open Sans", Font.PLAIN, 13));
		add(ltipoDocumento, "gapleft 0, alignx right");
		ttipoDocumento.disable();
		add(ttipoDocumento);

		
		//NUMERO DOCUMENTO CLIENTE
		ldocumento.setFont(new Font("Open Sans", Font.PLAIN, 13));
		add(ldocumento, "alignx right");
		tdocumento.disable();
		add(tdocumento, "WRAP");
		
		
		//BOTON ALTA CLIENTE
		btnAltaCliente.setFont(new Font("Open Sans", Font.PLAIN, 13));
		btnAltaCliente.setEnabled(true);
		btnAltaCliente.setBackground(Color.LIGHT_GRAY);
		add(btnAltaCliente);
		
		
		//APELLIDO CLIENTE
		lapellido.setFont(new Font("Open Sans", Font.PLAIN, 13));
		add(lapellido, "gap left 30, alignx right");
		tapellido.disable();
		add(tapellido);
		
		//NOMBRES CLIENTE
		lnombres.setFont(new Font("Open Sans", Font.PLAIN, 13));
		add(lnombres, "gap left 0, alignx right");
		tnombres.disable();
		add(tnombres, "wrap");
		
		
		//NUMERO CALLE
		lcalle.setFont(new Font("Open Sans", Font.PLAIN, 13));
		add(lcalle, "cell 1 3, alignx right");
		tcalle.disable();
		add(tcalle);
		
		
		//NUMERO DOMICILIO
		lnumeroDom.setFont(new Font("Open Sans", Font.PLAIN, 13));
		add(lnumeroDom, "gapleft 0, alignx right");
		tnumeroDom.disable();
		add(tnumeroDom);
	
		
		//NUMERO DEPARTAMENTO
		ldepartamento.setFont(new Font("Open Sans", Font.PLAIN, 13));
		add(ldepartamento, "gapleft 20, alignx right");
		tdepartamento.disable();
		add(tdepartamento, "wrap");
		
		
		//TITULO DATOS POLIZA
		ldatosPoliza.setFont(new Font("Open Sans", Font.BOLD, 15));
		add(ldatosPoliza, "gaptop 5, cell 0 5, wrap, alignx center, span 10 1");
		
		
		//COMBO BOX PROVINCIA
		lprovincia.setFont(new Font("Open Sans", Font.PLAIN, 13));
		add(lprovincia, "gaptop 5, cell 0 6, alignx left, span 4 1");
		cprovincia.setModel(new DefaultComboBoxModel(new String[] {"Santiago del Estero"}));
		cprovincia.setBackground(Color.LIGHT_GRAY);
		cprovincia.setFont(new Font("Open Sans", Font.PLAIN, 13));
		add(cprovincia, "cell 0 6, alignx left, span 4 1");
				
		
		//COMBO BOX CIUDAD
		lciudad.setFont(new Font("Open Sans", Font.PLAIN, 13));
		add(lciudad, "gapleft 20, cell 0 6, alignx left, span 4 1");
		cciudad.setModel(new DefaultComboBoxModel(new String[] {"Santiago del Estero"}));
		cciudad.setBackground(Color.LIGHT_GRAY);
		cciudad.setFont(new Font("Open Sans", Font.PLAIN, 13));
		add(cciudad,"cell 0 6, alignx left, span 4 1, wrap");
	
		
		//COMBO BOX MARCA
		lmarca.setFont(new Font("Open Sans", Font.PLAIN, 13));
		add(lmarca,"gaptop 10, cell 0 7, alignx left, span 6 1");
		cmarca.setModel(new DefaultComboBoxModel(new String[] {"Seleccionar marca"}));
		cmarca.setBackground(Color.LIGHT_GRAY);
		cmarca.setFont(new Font("Open Sans", Font.PLAIN, 13));
		add(cmarca, "cell 0 7, alignx left, span 6 1");
		
		
		//COMBO BOX MODELO
		lmodelo.setFont(new Font("Open Sans", Font.PLAIN, 13));
		add(lmodelo, "gapleft 20, cell 0 7, alignx left, span 6 1");
		cmodelo.setModel(new DefaultComboBoxModel(new String[] {"Seleccionar modelo"}));
		cmodelo.setEnabled(false);
		cmodelo.setBackground(Color.LIGHT_GRAY);
		cmodelo.setFont(new Font("Open Sans", Font.PLAIN, 13));
		add(cmodelo, "cell 0 7, alignx left, span 6 1");
		
		
		//COMBO BOX ANIO MODELO
		lanio.setFont(new Font("Open Sans", Font.PLAIN, 13));
		add(lanio, "gapleft 20, cell 0 7, alignx left, span 6 1");
		canio.setModel(new DefaultComboBoxModel(new String[] {"Seleccionar a\u00f1o"}));
		canio.setEnabled(false);
		canio.setBackground(Color.LIGHT_GRAY);
		canio.setFont(new Font("Open Sans", Font.PLAIN, 13));
		add(canio, "cell 0 7, alignx left, span 6 1, wrap");
		
		
		//ENTRADA DE MOTOR
		lmotor.setFont(new Font("Open Sans", Font.PLAIN, 13));
		add(lmotor, "gaptop 10, cell 0 8, alignx left, span 6 1");
		add(tmotor, "cell 0 8, alignx left, span 6 1");
		
		
		//ENTRADA DE CHASIS
		lnombres.setFont(new Font("Open Sans", Font.PLAIN, 13));
		add(lchasis, "gapleft 30, cell 0 8, alignx left, span 6 1");
		add(tchasis, "cell 0 8, alignx left, span 6 1");
		
		
		//ENTRADA DE PATENTE
		lpatente.setFont(new Font("Open Sans", Font.PLAIN, 13));
		add(lpatente, "gapleft 30, cell 0 8, alignx left, span 6 1");
		add(tpatente, "cell 0 8, alignx left, span 6 1, WRAP");

		
		//ENTRADA SUMA ASEGURADA
		lsumaAseg.setFont(new Font("Open Sans", Font.PLAIN, 13));
		add(lsumaAseg, "gaptop 10, cell 0 9, alignx left, span 3 1");
		add(tsumaAsegurada, "cell 0 9, alignx left, span 3 1");
		lmoneda.setFont(new Font("Open Sans", Font.PLAIN, 13));
		add(lmoneda, "cell 0 9, alignx left, span 3 1, wrap");
		
		
		//COMBO BOX KILOMETROS POR AÑO
		lkm.setFont(new Font("Open Sans", Font.PLAIN, 13));
		add(lkm, "gaptop 10, cell 0 10, alignx left, span 3 1");
		ckm.setModel(new DefaultComboBoxModel(new String[] {"Seleccionar rango"}));
		ckm.setBackground(Color.LIGHT_GRAY);
		ckm.setFont(new Font("Open Sans", Font.PLAIN, 13));
		add(ckm, "cell 0 10, alignx left, span 3 1, wrap");
		
		
		//COMBO BOX NUMERO DE SINIESTROS POR AÑO
		lsiniestros.setFont(new Font("Open Sans", Font.PLAIN, 13));
		add(lsiniestros, "gaptop 10, cell 0 11, alignx left, span 3 1");
		csiniestros.setBackground(Color.LIGHT_GRAY);
		csiniestros.setModel(new DefaultComboBoxModel(new String[] {"AGRANDAR PARA ESPACIO DE 10"}));
		csiniestros.setFont(new Font("Open Sans", Font.PLAIN, 13));
		add(csiniestros, "cell 0 11, alignx left, span 6 1, wrap");
		

		//RADIOS BUTTON "SE GUARDA EN GARAGE"
		lgarage.setFont(new Font("Open Sans", Font.PLAIN, 13));
		add(lgarage, "gaptop 10, cell 0 12, alignx left, span 12 1");
		garage.add(rgarageSi);
		garage.add(rgarageNo);
		rgarageSi.setBackground(Color.GRAY);
		add(rgarageSi, "cell 0 12, alignx left, span 12 1");
		rgarageNo.setBackground(Color.GRAY);
		rgarageNo.setSelected(true);
		add(rgarageNo, "cell 0 12, alignx left, span 12 1");
		
		
		//RADIO BUTTON "TIENE ALARMA"
		add(lalarma, "gapleft 3, cell 0 12, alignx right, span 12 1");
		alarma.add(ralarmaSi);
		alarma.add(ralarmaNo);
		ralarmaSi.setBackground(Color.GRAY);
		add(ralarmaSi, "cell 0 12, alignx left, span 12 1");
		ralarmaNo.setBackground(Color.GRAY);
		ralarmaNo.setSelected(true);
		add(ralarmaNo, "cell 0 12, alignx left, span 12 1");
		
		
		//RADIOS BUTTON DE "TIENE DISPOSITIVO DE RASTREO"
		add(lrastreo, "gapleft 3, cell 0 12, alignx left, span 12 1");
		rastreo.add(rrastreoSi);
		rastreo.add(rrastreoNo);
		rrastreoSi.setBackground(Color.GRAY);
		add(rrastreoSi, "cell 0 12, alignx left, span 12 1");
		rrastreoNo.setSelected(true);
		rrastreoNo.setBackground(Color.GRAY);
		add(rrastreoNo, "cell 0 12, alignx left, span 12 1");
		
		
		//RADIO BUTON "TUECAS ANTIRROBO"
		add(ltuercas, "gapleft 3, cell 0 12, alignx left, span 12 1");
		tuercas.add(rtuercasSi);
		tuercas.add(rtuercasNo);
		rtuercasSi.setBackground(Color.GRAY);
		add(rtuercasSi, "cell 0 12, alignx left, span 12 1");
		rtuercasNo.setSelected(true);
		rtuercasNo.setBackground(Color.GRAY);
		add(rtuercasNo, "cell 0 12, alignx left, span 12 1");
		
			
		//CANTIDAD DE HIJOS
		lcantidadHijos.setFont(new Font("Open Sans", Font.PLAIN, 13));
		add(lcantidadHijos, "gaptop 10, cell 0 13, align left, span 2 1, wrap");
	
		
		
		//Con esto no permito que las celdas se puedan modificar
		DefaultTableModel tableModel = new DefaultTableModel(datosTabla, columnas) {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		tablaHijos.setModel(tableModel);
		tablaHijos.setPreferredScrollableViewportSize(new Dimension(450, 70));
		tablaHijos.getColumnModel().getColumn(0).setPreferredWidth(50);
		tablaHijos.getColumnModel().getColumn(0).setResizable(false);
		tablaHijos.getColumnModel().getColumn(1).setPreferredWidth(120);
		tablaHijos.getColumnModel().getColumn(1).setResizable(false);
		tablaHijos.getColumnModel().getColumn(2).setPreferredWidth(100);
		tablaHijos.getColumnModel().getColumn(2).setResizable(false);
		tablaHijos.getColumnModel().getColumn(3).setPreferredWidth(90);
		tablaHijos.getColumnModel().getColumn(3).setResizable(false);
		JScrollPane scrollPane = new JScrollPane(tablaHijos);
		add(scrollPane, "cell 0 14, span 4 8");
		

				
		//BOTON AGREGAR HIJOS
		btnAgregarHijo.setFont(new Font("Open Sans", Font.PLAIN, 10));
		btnAgregarHijo.setEnabled(true);
		btnAgregarHijo.setBackground(Color.LIGHT_GRAY);
		add(btnAgregarHijo, "gaptop 5, cell 3 15, growx 1");
		
		
		// BOTON QUITAR HIJOS
		btnQuitarHijo.setFont(new Font("Open Sans", Font.PLAIN, 10));
		btnQuitarHijo.setEnabled(false);
		btnQuitarHijo.setBackground(Color.LIGHT_GRAY);
		add(btnQuitarHijo, "cell 3 18, growx 1");
		
		btnConfirmarDatos.setFont(new Font("Open Sans", Font.PLAIN, 13));
		btnConfirmarDatos.setEnabled(false);
		btnConfirmarDatos.setBackground(Color.LIGHT_GRAY);
		add(btnConfirmarDatos, "cell 5 18");
		
		btnCancelar.setFont(new Font("Open Sans", Font.PLAIN, 13));
		btnCancelar.setEnabled(true);
		btnCancelar.setBackground(Color.LIGHT_GRAY);
		add(btnCancelar, "cell 6 18, growx 1");
		
		//DATOS OBLIGATORIOS
		ldatosObligatorios.setFont(new Font("Open Sans", Font.PLAIN, 9));
		add(ldatosObligatorios, "dock south");
		
		
		

			
		

		
		
						
	}

}


