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
		
	private JLabel ldatosCliente = new JLabel("DATOS CLIENTE");
	private JLabel ldatosPoliza = new JLabel("DATOS P\u00d3LIZA");
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

	
	@SuppressWarnings({ "deprecation", "rawtypes", "unchecked" })
	public AltaPoliza1(JFrame ventana, Object[] tema) {
		
		inicializarTema((Color) tema[0], (Color) tema[1], (Color)tema[2], (Color) tema[3], (Font) tema[4], (Font) tema[5]);
		setLayout(new MigLayout());
		
		//add(ldatosCliente,"cell 0 0, wrap, alignx center, span 12 1");
		
		btnBuscarCliente.setEnabled(true);
		add(btnBuscarCliente, "height 20px, width 150px");
		
		add(lnumeroCliente, "gapleft 20,  alignx right");
		tnumeroCliente.disable();
		add(tnumeroCliente);

		add(ltipoDocumento, "alignx right");
		ttipoDocumento.disable();
		add(ttipoDocumento);

		add(ldocumento, "alignx right");
		tdocumento.disable();
		add(tdocumento, "WRAP");
		
		btnAltaCliente.setEnabled(true);
		add(btnAltaCliente, "height 10px, width 150px");
		
		add(lapellido, "alignx right");
		tapellido.disable();
		add(tapellido);
		
		add(lnombres, "gap left 10, alignx right");
		tnombres.disable();
		add(tnombres, "wrap");
		
		add(lcalle, "cell 1 3, alignx right");
		tcalle.disable();
		add(tcalle);
		
		add(lnumeroDom, "gapleft 10, alignx right");
		tnumeroDom.disable();
		add(tnumeroDom);
	
		add(ldepartamento, "gapleft 10, alignx right");
		tdepartamento.disable();
		add(tdepartamento, "wrap");
		
		add(new JLabel("_______________________________________________________________________________________________________________________"), "wrap, span 20 1, alignx center");
		
		//add(ldatosPoliza, "gaptop 5, cell 0 5, wrap, alignx center, span 10 1");
		
		add(lprovincia, "gaptop 10, cell 0 6, alignx left, span 10 1");
		cmbProvincia.setModel(new DefaultComboBoxModel(new String[] {"SANTIAGO DEL ESTERO", "Santa FE"}));
		add(cmbProvincia, "cell 0 6, alignx left, span 7 1");
				
		add(lciudad, "gapleft 20, cell 0 6, alignx left, span 10 1");
		cmbCiudad.setModel(new DefaultComboBoxModel(new String[] {"Santiago del Estero"}));
		add(cmbCiudad,"cell 0 6, alignx left, span 4 1, wrap");
	
		add(lmarca,"gaptop 10, cell 0 7, alignx left, span 10 1");
		cmbMarca.setModel(new DefaultComboBoxModel(new String[] {"Seleccionar marca"}));
		add(cmbMarca, "cell 0 7, alignx left, span 6 1");
		
		add(lmodelo, "gapleft 20, cell 0 7, alignx left, span 10 1");
		cmbModelo.setModel(new DefaultComboBoxModel(new String[] {"Seleccionar modelo"}));
		cmbModelo.setEnabled(false);
		add(cmbModelo, "cell 0 7, alignx left, span 10 1");
		
		add(lanio, "gapleft 20, cell 0 7, alignx left, span 10 1");
		cmbAnio.setModel(new DefaultComboBoxModel(new String[] {"Seleccionar a\u00f1o"}));
		cmbAnio.setEnabled(false);
		add(cmbAnio, "cell 0 7, alignx left, span 10 1, wrap");
		
		add(lmotor, "gaptop 10, cell 0 8, alignx left, span 6 1");
		add(tmotor, "cell 0 8, alignx left, span 10 1");
		
		add(lchasis, "gapleft 30, cell 0 8, alignx left, span 6 1");
		add(tchasis, "cell 0 8, alignx left, span 10 1");
		
		add(lpatente, "gapleft 30, cell 0 8, alignx left, span 6 1");
		add(tpatente, "cell 0 8, alignx left, span 10 1, WRAP");

		add(lsumaAseg, "gaptop 10, cell 0 9, alignx left, span 3 1");
		add(tsumaAsegurada, "cell 0 9, alignx left, span 3 1");
		add(lmoneda, "cell 0 9, alignx left, span 3 1, wrap");
		
		add(lkm, "gaptop 10, cell 0 10, alignx left, span 3 1");
		cmbKm.setModel(new DefaultComboBoxModel(new String[] {"Seleccionar rango"}));
		add(cmbKm, "cell 0 10, alignx left, span 3 1, wrap");
		
		add(lsiniestros, "gaptop 10, cell 0 11, alignx left, span 10 1");
		cmbSiniestros.setModel(new DefaultComboBoxModel(new String[] {"AGRANDAR PARA ESPACIO DE 10"}));
		add(cmbSiniestros, "cell 0 11, alignx left, span 10 1, wrap");
		
		add(lgarage, "gaptop 10, cell 0 12, alignx left, span 12 1");
		garage.add(rgarageSi);
		garage.add(rgarageNo);
		add(rgarageSi, "cell 0 12, alignx center, span 20 1");
		rgarageNo.setSelected(true);
		add(rgarageNo, "cell 0 12, alignx center, span 20 1");
		
		add(lalarma, "gapleft 50, cell 0 12, alignx center, span 12 1");
		alarma.add(ralarmaSi);
		alarma.add(ralarmaNo);
		add(ralarmaSi, "cell 0 12, alignx center, span 20 1");
		ralarmaNo.setSelected(true);
		add(ralarmaNo, "cell 0 12, alignx center, span 20 1");
		
		add(lrastreo, "cell 0 13, alignx left, span 12 1");
		rastreo.add(rrastreoSi);
		rastreo.add(rrastreoNo);
		add(rrastreoSi, "cell 0 13, alignx center, span 20 1");
		rrastreoNo.setSelected(true);
		add(rrastreoNo, "cell 0 13, alignx center, span 20 1");
		
		add(ltuercas, "gapleft 50, cell 0 13, alignx center, span 12 1");
		tuercas.add(rtuercasSi);
		tuercas.add(rtuercasNo);
		add(rtuercasSi, "cell 0 13, alignx left, span 20 1");
		rtuercasNo.setSelected(true);
		add(rtuercasNo, "cell 0 13, alignx left, span 20 1");

		add(lcantidadHijos, "gaptop 10, cell 0 14, align left, span 2 1, wrap");
		
		add(tablaHijosScroll, "cell 0 15, span 4 8");
	
		btnAgregarHijo.setEnabled(true);
		add(btnAgregarHijo, "gaptop 5,  cell 2 16, height 20px, width 150px, span 2 1, alignx right");

		btnQuitarHijo.setEnabled(false);
		add(btnQuitarHijo, "cell 2 19, height 20px, width 150px, span 2 1, alignx right");

		
		btnConfirmarDatos.setEnabled(false);
		add(btnConfirmarDatos, "cell 5 16, span 3 1, alignx right, height 20px, width 150px");
		
		btnCancelar.setEnabled(true);
		add(btnCancelar, "cell 5 19, alignx right, span 3 1, height 20px, width 150px");
		
		add(ldatosObligatorios, "dock south");
		
		ventana.setBounds(0,0,1024,600);
		ventana.setLocationRelativeTo(null);
		ventana.setTitle("Dar de alta póliza: INGRESAR DATOS");
		ventana.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		ventana.setVisible(true);
		
	}

	/**
	 * @param colorBoton
	 * @param colorFoncolorFondoPantallado
	 * @param colorFondoTexto
	 * @param letra
	 * @param letraTitulo
	 */
	public void inicializarTema(Color colorBoton, Color colorFondoPantalla, Color colorFondoTexto, Color borde, Font letra, Font letraTitulo) {
		setBounds(0,0,1024,600);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setFont(letra);
		setBackground(colorFondoPantalla);
		
		
		ldatosCliente.setFont(letraTitulo);
		ldatosPoliza.setFont(letraTitulo);
		
		//subrayado
		Font subrayado = ldatosCliente.getFont();
		Map<TextAttribute, Object> attributes = new HashMap<>(subrayado.getAttributes());
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		ldatosCliente.setFont(subrayado.deriveFont(attributes));
		ldatosPoliza.setFont(subrayado.deriveFont(attributes));
		
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
		ldatosObligatorios.setFont(letra);
			
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
}


