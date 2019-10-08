package isi.dds.tp.app;

import java.awt.*;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import isi.dds.tp.enums.EnumSiniestros;
import isi.dds.tp.modelo.*;
import net.miginfocom.swing.MigLayout;


@SuppressWarnings("serial")
public class AltaPoliza1 extends JPanel {
		
	//private JLabel ldatosCliente = new JLabel("DATOS CLIENTE");
	//private JLabel ldatosPoliza = new JLabel("DATOS P\u00d3LIZA");
	//FIJARSE SI PONER TITULO 
	
	private JLabel lnumeroCliente = new JLabel("N\u00famero cliente:");
	private JLabel ltipoDocumento = new JLabel("Tipo documento:");
	private JLabel ldocumento = new JLabel("Documento:");
	private JLabel lapellido = new JLabel("Apellido:");
	private JLabel lnombres = new JLabel("Nombres:");
	private JLabel lcalle = new JLabel("Calle:");
	private JLabel lnumeroDom = new JLabel("N\u00famero domicilio:");
	private JLabel ldepartamento = new JLabel("  Departamento:");
	private JLabel lprovincia = new JLabel("Provincia*:");
	private JLabel lciudad = new JLabel("Ciudad*:");
	private JLabel lmarca = new JLabel("Marca veh\u00edculo*:");
	private JLabel lmodelo = new JLabel("Modelo veh\u00edculo:*");
	private JLabel lanio = new JLabel("A\u00f1o modelo:*");
	private JLabel lmotor = new JLabel("Motor*:");
	private JLabel lchasis = new JLabel("Chasis*:");
	private JLabel lpatente = new JLabel("Patente*:");
	private JLabel lsumaAseg = new JLabel("Suma asegurada:");
	private JLabel lmoneda = new JLabel("$");
	private JLabel lkm = new JLabel("Km realizados por a\u00f1o:");
	private JLabel lsiniestros = new JLabel("N\u00famero de siniestros en el \u00faltimo año*:");
	private JLabel lgarage = new JLabel("¿Se guarda en garage?");
	private JLabel lalarma = new JLabel("¿Tiene alarma?");
	private JLabel lrastreo = new JLabel("¿Posee dispositivo de rastreo vehicular?");
	private JLabel ltuercas = new JLabel("¿Posee tuercas antirrobo?");
	private JLabel lcantidadHijos = new JLabel("Cantidad de hijos entre 18 y 30 a\u00f1os:");
	private JLabel ldatosObligatorios = new JLabel("(*) datos obligatios)");
		
	private JTextField tnumeroCliente = new JTextField(10);
	private JTextField ttipoDocumento = new JTextField(15);;
	private JTextField tdocumento = new JTextField(8);
	private JTextField tapellido = new JTextField(15);
	private JTextField tnombres = new JTextField(15);;
	private JTextField tcalle = new JTextField(15);
	private JTextField tnumeroDom = new JTextField(8);
	private JTextField tdepartamento = new JTextField(3);
	private JTextField tmotor = new JTextField(15);
	private JTextField tchasis = new JTextField(8);
	private JTextField tpatente = new JTextField(7);
	private JTextField tsumaAsegurada = new JTextField(15);
	
	private JButton btnBuscarCliente = new JButton("BUSCAR CLIENTES");
	private JButton btnAltaCliente = new JButton("DAR ALTA CLIENTES");
	private JButton btnAgregarHijo = new JButton("Agregar datos hijo");
	private JButton btnQuitarHijo = new JButton("Quitar datos hijo");
	private JButton btnConfirmarDatos = new JButton("Confirmar datos");
	private JButton btnCancelar = new JButton("Cancelar");
	
	private JComboBox<Provincia> cmbProvincia = new JComboBox<Provincia>();
	private JComboBox<Ciudad> cmbCiudad = new JComboBox<Ciudad>();
	private JComboBox<Marca> cmbMarca = new JComboBox<Marca>();
	private JComboBox<Modelo> cmbModelo = new JComboBox<Modelo>();
	private JComboBox<AnioModelo> cmbAnio = new JComboBox<AnioModelo>();
	private JComboBox<String> cmbKm = new JComboBox<String>();
	private JComboBox<EnumSiniestros> cmbSiniestros = new JComboBox<EnumSiniestros>();

	private ButtonGroup garage = new ButtonGroup(), alarma = new ButtonGroup(), rastreo = new ButtonGroup(), tuercas = new ButtonGroup(); 
	private JRadioButton rgarageSi = new JRadioButton("SI");
	private JRadioButton rgarageNo = new JRadioButton("NO");
	private JRadioButton ralarmaSi = new JRadioButton("SI");
	private JRadioButton ralarmaNo = new JRadioButton("NO");
	private JRadioButton rrastreoSi = new JRadioButton("SI");
	private JRadioButton rrastreoNo = new JRadioButton("NO");
	private JRadioButton rtuercasSi = new JRadioButton("SI");
	private JRadioButton rtuercasNo = new JRadioButton("NO");
	
	private JTable tablaHijos = new JTable(6, 4);
	private JScrollPane tablaHijosScroll = new JScrollPane(tablaHijos);
	private Object[][] datosTabla = {{""},{""},{""},{""}};

	
	public AltaPoliza1(JFrame ventana, Object[] tema) {
		
		inicializarComponentes();
		inicializarTema((Color) tema[0], (Color) tema[1], (Color)tema[2], (Color) tema[3], (Font) tema[4], (Font) tema[5]);
		ubicarComponentes();

		
		ventana.setBounds(0,0,1024,600);
		ventana.setLocationRelativeTo(null);
		ventana.setTitle("Dar de alta póliza: INGRESAR DATOS");
		ventana.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		ventana.setVisible(true);
		
	}

	private void ubicarComponentes() {
		
		setLayout (new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		
		//FILA 1
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.insets.set(5, 5, 5, 15);
		add(btnBuscarCliente, constraints);
		
		constraints.gridx = 2;
		constraints.gridwidth = 1;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets.set(5, 0, 5, 5);
		add(lnumeroCliente, constraints);
		
		constraints.gridx = 3;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets.set(5, 0, 5, 15);
		add(tnumeroCliente, constraints);
		
		constraints.gridx = 4;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets.set(5, 0, 5, 5);
		add(ltipoDocumento, constraints);
		
		constraints.gridx = 5;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets.set(5, 0, 5, 15);
		add(ttipoDocumento, constraints);
		
		constraints.gridx = 6;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets.set(5, 0, 5, 5);
		add(ldocumento, constraints);
		
		constraints.gridx = 7;
		constraints.gridwidth = 1;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets.set(5, 0, 5, 0);
		add(tdocumento, constraints);
		
		//FILA 2
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 2;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.insets.set(5, 5, 5, 15);
		add(btnAltaCliente, constraints);
		
		constraints.gridx = 2;
		constraints.gridwidth = 1;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets.set(5, 0, 5, 5);
		add(lapellido, constraints);
		
		constraints.gridx = 3;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets.set(5, 0, 5, 15);
		add(tapellido, constraints);
		
		constraints.gridx = 4;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets.set(5, 0, 5, 5);
		add(lnombres, constraints);
		
		constraints.gridx = 5;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets.set(5, 0, 5, 0);
		add(tnombres, constraints);
		
		//FILA 3
		constraints.gridy = 2;
		constraints.gridx = 2;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets.set(5, 0, 5, 5);
		add(lcalle, constraints);
		
		constraints.gridx = 3;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets.set(5, 0, 5, 20);
		add(tcalle, constraints);
		
		constraints.gridx = 4;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets.set(5, 0, 5, 5);
		add(lnumeroDom, constraints);
		
		constraints.gridx = 5;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets.set(5, 0, 5, 15);
		add(tnumeroDom, constraints);
		
		constraints.gridx = 6;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets.set(5, 0, 5, 5);
		add(ldepartamento, constraints);
		
		constraints.gridx = 7;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets.set(5, 0, 5, 0);
		add(tdepartamento, constraints);
		
		//FILA 4
		constraints.gridy = 3;
		constraints.gridx = 0;
		constraints.gridwidth = 15;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.insets.set(5, 0, 5, 0);
		add(new JLabel("___________________________________________________________________________________________________________________________"), constraints);
		
		//FILA 5
		constraints.gridy = 4;
		constraints.gridx = 0;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets.set(5, 5, 5, 5);
		add(lprovincia, constraints);
		constraints.insets.set(5, 75, 5, 5);
		add(cmbProvincia, constraints);
		constraints.insets.set(5, 295, 5, 5);
		add(lciudad, constraints);
		constraints.insets.set(5, 355, 5, 5);
		add(cmbCiudad, constraints);
		
		//FILA 6
		constraints.gridy = 5;
		constraints.insets.set(5, 5, 5, 5);
		add(lmarca, constraints);
		constraints.insets.set(5, 111, 5, 5);
		add(cmbMarca, constraints);
		constraints.insets.set(5, 295, 5, 5);
		add(lmodelo, constraints);
		constraints.insets.set(5, 410, 5, 5);
		add(cmbModelo, constraints);
		constraints.insets.set(5, 575, 5, 5);
		add(lanio, constraints);
		constraints.insets.set(5, 664, 5, 5);
		add(cmbAnio, constraints);

		//FILA 7
		constraints.gridy = 6;
		constraints.insets.set(5, 5, 5, 5);
		add(lmotor, constraints);
		constraints.insets.set(5, 60, 5, 5);
		add(tmotor, constraints);
		constraints.insets.set(5, 295, 5, 5);
		add(lchasis, constraints);
		constraints.insets.set(5, 349, 5, 5);
		add(tchasis, constraints);
		constraints.insets.set(5, 575, 5, 5);
		add(lpatente, constraints);
		constraints.insets.set(5, 638, 5, 5);
		add(tpatente, constraints);
		
		//FILA 8
		constraints.gridy = 7;
		constraints.insets.set(5, 5, 5, 5);
		add(lsumaAseg, constraints);
		constraints.insets.set(5, 113, 5, 5);
		add(tsumaAsegurada, constraints);
		constraints.insets.set(5, 295, 5, 5);
		add(lmoneda, constraints);

		//FILA 9
		constraints.gridy = 8;
		constraints.insets.set(5, 5, 5, 5);
		add(lkm, constraints);
		constraints.insets.set(5, 145, 5, 5);
		add(cmbKm, constraints);
		
		//FILA 10
		constraints.gridy = 9;
		constraints.insets.set(5, 5, 5, 5);
		add(lsiniestros, constraints);
		constraints.insets.set(5, 247, 5, 5);
		add(cmbSiniestros, constraints);
		
		//FILA 11
		constraints.gridy = 10;
		constraints.insets.set(5, 5, 5, 5);
		add(lgarage, constraints);
		constraints.insets.set(5, 140, 0, 5);
		add(rgarageSi, constraints);
		constraints.insets.set(5, 175, 0, 5);
		add(rgarageNo, constraints);
		
		constraints.insets.set(5, 250, 0, 5);
		add(lalarma, constraints);
		constraints.insets.set(5, 340, 0, 5);
		add(ralarmaSi, constraints);
		constraints.insets.set(5, 375, 0, 5);
		add(ralarmaNo, constraints);
		
		//FILA 11
		constraints.gridy = 11;
		constraints.insets.set(2, 5, 5, 5);
		add(lrastreo, constraints);
		constraints.insets.set(2, 245, 3, 5);
		add(rrastreoSi, constraints);
		constraints.insets.set(2, 280, 3, 5);
		add(rrastreoNo, constraints);
		
		constraints.insets.set(2, 355, 5, 5);
		add(ltuercas, constraints);
		constraints.insets.set(2, 515, 3, 5);
		add(rtuercasSi, constraints);
		constraints.insets.set(2, 550, 3, 5);
		add(rtuercasNo, constraints);
		
		//FILA 12
		constraints.gridy = 12;
		constraints.insets.set(5, 5, 2, 5);
		add(lcantidadHijos, constraints);
		
		//FILA 13
		constraints.gridy = 13;
		constraints.insets.set(3, 5, 2, 5);
		add(tablaHijosScroll, constraints);
		
		constraints.insets.set(0, 425, 40, 5);
		add(btnAgregarHijo, constraints);
		
		constraints.insets.set(40, 425, 2, 5);
		add(btnQuitarHijo, constraints);
		
		
		//FILA 14
		constraints.gridy = 14;
		constraints.insets.set(3, 5, 0, 5);
		add(ldatosObligatorios, constraints);
		
		constraints.insets.set(0, 655, 0, 5);
		add(btnConfirmarDatos, constraints);
		
		constraints.insets.set(0, 830, 0, 5);
		add(btnCancelar, constraints);

	}
		
	private void inicializarTema(Color colorBoton, Color colorFondoPantalla, Color colorFondoTexto, Color borde, Font letra, Font letraTitulo) {
		setBounds(0,0,1024,600);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setFont(letra);
		setBackground(colorFondoPantalla);
		
		//ldatosCliente.setFont(letraTitulo);
		//ldatosPoliza.setFont(letraTitulo);
		
		//subrayado
		//Font subrayado = ldatosCliente.getFont();
		//Map<TextAttribute, Object> attributes = new HashMap<>(subrayado.getAttributes());
		//attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		//ldatosCliente.setFont(subrayado.deriveFont(attributes));
		//ldatosPoliza.setFont(subrayado.deriveFont(attributes));
		
		lnumeroCliente.setFont(letra);
		ltipoDocumento.setFont(letra);
		ldocumento.setFont(letra);
		lapellido .setFont(letra);
		lnombres.setFont(letra);
		lcalle.setFont(letra);
		lnumeroDom.setFont(letra);
		ldepartamento.setFont(letra);
		lprovincia.setFont(letra);
		lciudad.setFont(letra);
		lmarca.setFont(letra);
		lmodelo.setFont(letra);
		lanio.setFont(letra);
		lmotor.setFont(letra);
		lchasis.setFont(letra);
		lpatente.setFont(letra);
		lsumaAseg.setFont(letra);
		lmoneda.setFont(letra);
		lkm.setFont(letra);
		lsiniestros.setFont(letra);
		lgarage.setFont(letra);
		lalarma.setFont(letra);
		lrastreo.setFont(letra);
		ltuercas.setFont(letra);
		lcantidadHijos.setFont(letra);
		ldatosObligatorios.setFont(new Font("Open Sans", Font.ITALIC, 9));
			
		tnumeroCliente.setFont(letra);
		tnumeroCliente.setBackground(colorFondoTexto);
		tnumeroCliente.setBorder(new LineBorder(borde));
		ttipoDocumento.setFont(letra);
		ttipoDocumento.setBackground(colorFondoTexto);
		ttipoDocumento.setBorder(new LineBorder(borde));
		tdocumento.setFont(letra);
		tdocumento.setBackground(colorFondoTexto);
		tdocumento.setBorder(new LineBorder(borde));
		tapellido.setFont(letra);
		tapellido.setBackground(colorFondoTexto);
		tapellido.setBorder(new LineBorder(borde));
		tnombres.setFont(letra);
		tnombres.setBackground(colorFondoTexto);
		tnombres.setBorder(new LineBorder(borde));
		tcalle.setFont(letra);
		tcalle.setBackground(colorFondoTexto);
		tcalle.setBorder(new LineBorder(borde));
		tnumeroDom.setFont(letra);
		tnumeroDom.setBackground(colorFondoTexto);
		tnumeroDom.setBorder(new LineBorder(borde));
		tdepartamento.setFont(letra);
		tdepartamento.setBackground(colorFondoTexto);
		tdepartamento.setBorder(new LineBorder(borde));
		tmotor.setFont(letra);
		tmotor.setBackground(colorFondoTexto);
		tmotor.setBorder(new LineBorder(borde));
		tchasis.setFont(letra);
		tchasis.setBackground(colorFondoTexto);
		tchasis.setBorder(new LineBorder(borde));
		tpatente.setFont(letra);
		tpatente.setBackground(colorFondoTexto);
		tpatente.setBorder(new LineBorder(borde));
		tsumaAsegurada.setFont(letra);
		tsumaAsegurada.setBackground(colorFondoTexto);
		tsumaAsegurada.setBorder(new LineBorder(borde));
		
		btnBuscarCliente.setBackground(colorBoton);
		btnBuscarCliente.setFont(letra);
		btnAltaCliente.setBackground(colorBoton);
		btnAltaCliente.setFont(letra);
		btnAgregarHijo.setBackground(colorBoton);
		btnAgregarHijo.setFont(letra);
		btnQuitarHijo.setBackground(colorBoton);
		btnQuitarHijo.setFont(letra);
		btnConfirmarDatos.setBackground(colorBoton);
		btnConfirmarDatos.setFont(letra);
		btnCancelar.setBackground(colorBoton);
		btnCancelar.setFont(letra);
		
		cmbProvincia.setBackground(colorFondoTexto);
		cmbProvincia.setFont(letra);
		cmbCiudad.setBackground(colorFondoTexto);
		cmbCiudad.setFont(letra);
		cmbMarca.setBackground(colorFondoTexto);
		cmbMarca.setFont(letra);
		cmbModelo.setBackground(colorFondoTexto);
		cmbModelo.setFont(letra);
		cmbAnio.setBackground(colorFondoTexto);
		cmbAnio.setFont(letra);
		cmbKm.setBackground(colorFondoTexto);
		cmbKm.setFont(letra);
		cmbSiniestros.setBackground(colorFondoTexto);
		cmbSiniestros.setFont(letra);

		rgarageSi.setBackground(colorFondoPantalla);
		rgarageSi.setFont(letra);
		rgarageNo.setBackground(colorFondoPantalla);
		rgarageNo.setFont(letra);
		ralarmaSi.setBackground(colorFondoPantalla);
		ralarmaSi.setFont(letra);
		ralarmaNo.setBackground(colorFondoPantalla);
		ralarmaNo.setFont(letra);
		rrastreoSi.setBackground(colorFondoPantalla);
		rrastreoSi.setFont(letra);
		rrastreoNo.setBackground(colorFondoPantalla);
		rrastreoNo.setFont(letra);
		rtuercasSi.setBackground(colorFondoPantalla);
		rtuercasSi.setFont(letra);
		rtuercasNo.setBackground(colorFondoPantalla);
		rtuercasNo.setFont(letra);
		
		
		tablaHijos.setBackground(colorFondoTexto);
		tablaHijos.setFont(letra);
		
		DefaultTableModel tableModel = new DefaultTableModel( datosTabla, 6) {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		
		tablaHijos.setModel(tableModel);
		
		tablaHijos.setFillsViewportHeight(true);
		tablaHijos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		DefaultTableCellRenderer centrado = new DefaultTableCellRenderer();
		centrado.setHorizontalAlignment( JLabel.CENTER );
		
		tablaHijos.getColumnModel().getColumn(0).setCellRenderer(centrado);
		tablaHijos.getColumnModel().getColumn(1).setCellRenderer(centrado);
		tablaHijos.getColumnModel().getColumn(2).setCellRenderer(centrado);
		tablaHijos.getColumnModel().getColumn(3).setCellRenderer(centrado);
		
		tablaHijos.getColumnModel().getColumn(0).setPreferredWidth(200);
		tablaHijos.getColumnModel().getColumn(1).setPreferredWidth(100);
		tablaHijos.getColumnModel().getColumn(2).setPreferredWidth(200);
		tablaHijos.getColumnModel().getColumn(3).setPreferredWidth(100);
		
		tablaHijos.getColumnModel().getColumn(0).setHeaderValue("Hijo");
		tablaHijos.getColumnModel().getColumn(1).setHeaderValue("Fecha nacimiento");
		tablaHijos.getColumnModel().getColumn(2).setHeaderValue("Sexo");
		tablaHijos.getColumnModel().getColumn(3).setHeaderValue("Estado civil");
		
		tablaHijosScroll.setPreferredSize(new Dimension(400, 100));
		
		/*TODO cambiar borde*/
		tablaHijosScroll.setBorder(new LineBorder(borde));
		tablaHijosScroll.getViewport().setBackground(colorFondoTexto);
		tablaHijosScroll.setBorder(new LineBorder(borde));
	}
	
	@SuppressWarnings({ "unused", "deprecation", "rawtypes", "unchecked" })
	private void inicializarComponentes() {
		tnumeroCliente.disable();
		ttipoDocumento.disable();
		tdocumento.disable();
		tapellido.disable();
		tnombres.disable();
		tcalle.disable();
		tnumeroDom.disable();
		tdepartamento.disable();
		tsumaAsegurada.disable();
		
		cmbProvincia.setModel(new DefaultComboBoxModel(new String[] {"SANTIAGO DEL ESTERO", "Santa FE"}));
		cmbProvincia.setPreferredSize(new Dimension(199, 25));
		cmbCiudad.setModel(new DefaultComboBoxModel(new String[] {"Santiago del Estero"}));
		cmbCiudad.setPreferredSize(new Dimension(199, 25));
		cmbMarca.setModel(new DefaultComboBoxModel(new String[] {"Seleccionar marca"}));
		cmbMarca.setPreferredSize(new Dimension(163, 25));
		cmbModelo.setModel(new DefaultComboBoxModel(new String[] {"VOLKSWAGEN"}));
		cmbModelo.setPreferredSize(new Dimension(145, 25));
		cmbModelo.setEnabled(false);
		cmbAnio.setModel(new DefaultComboBoxModel(new String[] {"Seleccionar a\u00f1o"}));
		cmbAnio.setPreferredSize(new Dimension(130, 25));
		cmbAnio.setEnabled(false);
		cmbKm.setModel(new DefaultComboBoxModel(new String[] {"Seleccionar rango"}));
		cmbKm.setPreferredSize(new Dimension(220, 25));
		cmbSiniestros.setModel(new DefaultComboBoxModel(new String[] {"AGRANDAR PARA ESPACIO DE 10"}));
		cmbSiniestros.setPreferredSize(new Dimension(220, 25));
		
		garage.add(rgarageSi);
		garage.add(rgarageNo);
		rgarageNo.setSelected(true);
		alarma.add(ralarmaSi);
		alarma.add(ralarmaNo);
		ralarmaNo.setSelected(true);
		rastreo.add(rrastreoSi);
		rastreo.add(rrastreoNo);
		rrastreoNo.setSelected(true);
		tuercas.add(rtuercasSi);
		tuercas.add(rtuercasNo);
		rtuercasNo.setSelected(true);
		
		btnQuitarHijo.setEnabled(false);
		btnConfirmarDatos.setEnabled(false);
		
		btnBuscarCliente.setPreferredSize(new Dimension(160, 25));
		btnAltaCliente.setPreferredSize(new Dimension(160, 25));
		btnAgregarHijo.setPreferredSize(new Dimension(160, 25));
		btnQuitarHijo.setPreferredSize(new Dimension(160, 25));
		btnConfirmarDatos.setPreferredSize(new Dimension(160, 25));
		btnCancelar.setPreferredSize(new Dimension(160, 25));
		
		
	}
	
}


