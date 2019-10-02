package isi.dds.tp.app;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import isi.dds.tp.enums.EnumSiniestros;
import isi.dds.tp.modelo.*;
import net.miginfocom.swing.MigLayout;


@SuppressWarnings("serial")
public class WinBuilAltaPoliza1 extends JPanel {
	
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
	
	

	private JButton btnAgregarHijo;
	private JButton btnQuitarHijo;
	private JButton btnConfirmarDatos;
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
	private JLabel lcantidadHijos = new JLabel("Cantidad de hijos entre 18 y 30 a\u00f1os:");
	private JLabel ldatosObligatorios = new JLabel("(*) datos obligatios)");
	//Agregar label para si y no en los jradiobuton
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
	private JList<HijoDeclarado> tablaHijos;
	private final JSeparator separator = new JSeparator();
	
	
	@SuppressWarnings("deprecation")
	public WinBuilAltaPoliza1(JFrame f) {

		setFont(new Font("Open Sans", Font.PLAIN, 13));
		//setBackground(new Color(210, 180, 140));
		setBackground(Color.GRAY);
		setBounds(0,0,1024,600);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setLayout(new MigLayout("", "[20.00px][189.00px][-46.00][34.00][pref!][][118.00][][][]", "[27px][][][][pref!][][]"));	
		
		btnBuscarCliente.setFont(new Font("Open Sans", Font.PLAIN, 13));
		btnBuscarCliente.setEnabled(true);
		btnBuscarCliente.setBackground(Color.LIGHT_GRAY);
		add(btnBuscarCliente, "cell 1 0,alignx left,aligny top");
		
		lnumeroCliente.setFont(new Font("Open Sans", Font.PLAIN, 13));
		add(lnumeroCliente, "flowx,cell 4 0,alignx left");
		tnumeroCliente.disable();
		add(tnumeroCliente, "cell 5 0,alignx left");
		
		
		ttipoDocumento.disable();
		add(ttipoDocumento, "flowx,cell 7 0,alignx left");
		ltipoDocumento.setFont(new Font("Open Sans", Font.PLAIN, 13));
		add(ltipoDocumento, "cell 6 0,alignx right");

		
		ldocumento.setFont(new Font("Open Sans", Font.PLAIN, 13));
		add(ldocumento, "cell 8 0,alignx right");
		tdocumento.disable();
		add(tdocumento, "cell 9 0,alignx left");
		
		
		lapellido.setFont(new Font("Open Sans", Font.PLAIN, 13));
		add(lapellido, "flowx,cell 4 1,alignx right");
		tapellido.disable();
		add(tapellido, "cell 5 1,alignx left");
		
		
		lnombres.setFont(new Font("Open Sans", Font.PLAIN, 13));
		add(lnombres, "cell 6 1,alignx right");
		tnombres.disable();
		add(tnombres, "cell 7 1,alignx left");
		
		btnAltaCliente.setFont(new Font("Open Sans", Font.PLAIN, 13));
		btnAltaCliente.setEnabled(true);
		btnAltaCliente.setBackground(Color.LIGHT_GRAY);
		add(btnAltaCliente, "cell 1 2,alignx left,aligny top");
				
		lcalle.setFont(new Font("Open Sans", Font.PLAIN, 13));
		add(lcalle, "flowx,cell 4 2,alignx right");
		tcalle.disable();
		add(tcalle, "cell 5 2,alignx center");
		
		lnumeroDom.setFont(new Font("Open Sans", Font.PLAIN, 13));
		add(lnumeroDom, "cell 6 2,alignx right");
		tnumeroDom.disable();
		add(tnumeroDom, "flowx,cell 7 2,alignx left");
	
		ldepartamento.setFont(new Font("Open Sans", Font.PLAIN, 13));
		add(ldepartamento, "cell 8 2,alignx left");
		tdepartamento.disable();
		add(tdepartamento, "cell 9 2,alignx left");
		/*TODO SETERAR COMBOBOXES*/
		
		add(separator, "cell 1 4 7 2");
		
		
		
		//-------------------------------------------
		
		
		lprovincia.setFont(new Font("Open Sans", Font.PLAIN, 13));
		add(lprovincia, "flowx,cell 1 6");
		
		cprovincia.setModel(new DefaultComboBoxModel(new String[] {"Santiago del Estero"}));
		cprovincia.setBounds(40, 120, 341, 34);
		cprovincia.setBackground(Color.LIGHT_GRAY);
		cprovincia.setFont(new Font("Open Sans", Font.PLAIN, 13));
		add(cprovincia, "cell 1 6");
		
		lciudad.setFont(new Font("Open Sans", Font.PLAIN, 13));
		add(lciudad, "cell 4 6");
		
		cciudad.setBounds(40, 120, 341, 34);
		cciudad.setBackground(Color.LIGHT_GRAY);
		cciudad.setFont(new Font("Open Sans", Font.PLAIN, 13));
		add(cciudad, "cell 5 6");
	
		
		
		/*
		
		
		cmarca.setBackground(Color.LIGHT_GRAY);
		cmarca.setFont(new Font("Open Sans", Font.PLAIN, 13));
		add(cmarca);
		
		cmodelo.setBackground(Color.LIGHT_GRAY);
		cmodelo.setFont(new Font("Open Sans", Font.PLAIN, 13));
		add(cmodelo);
		
		canio.setBackground(Color.LIGHT_GRAY);
		canio.setFont(new Font("Open Sans", Font.PLAIN, 13));
		add(canio);
		
		lmarca.setFont(new Font("Open Sans", Font.PLAIN, 13));
		
		
		add(lmarca);
		
		lmodelo.setFont(new Font("Open Sans", Font.PLAIN, 13));
		add(lmodelo);
		
		lanio.setFont(new Font("Open Sans", Font.PLAIN, 13));
		add(lanio);
		
		lmotor.setFont(new Font("Open Sans", Font.PLAIN, 13));
		add(lmotor);
		
		lnombres.setFont(new Font("Open Sans", Font.PLAIN, 13));
		add(lchasis);
		
		lpatente.setFont(new Font("Open Sans", Font.PLAIN, 13));
		add(lpatente);
		
		lsumaAseg.setFont(new Font("Open Sans", Font.PLAIN, 13));
		add(lsumaAseg);
		
		lmoneda.setFont(new Font("Open Sans", Font.PLAIN, 13));
		add(lmoneda);
		
		lkm.setFont(new Font("Open Sans", Font.PLAIN, 13));
		add(lkm);
		
		lsiniestros.setFont(new Font("Open Sans", Font.PLAIN, 13));
		add(lsiniestros);
		
		lcantidadHijos.setFont(new Font("Open Sans", Font.PLAIN, 13));
		add(lcantidadHijos);
				
		ldatosObligatorios.setFont(new Font("Open Sans", Font.PLAIN, 9));
		add(ldatosObligatorios);
		/*
				
		ckm.setBackground(Color.LIGHT_GRAY);
		ckm.setFont(new Font("Open Sans", Font.PLAIN, 13));
		add(ckm);
		
		csiniestros.setBackground(Color.LIGHT_GRAY);
		csiniestros.setFont(new Font("Open Sans", Font.PLAIN, 13));
		add(csiniestros);
		
		
		add(tmotor);
		
		add(tchasis);
		
		add(tpatente);
		
		add(tsumaAsegurada);
		
		
		garage.add(rgarageSi);
		garage.add(rgarageNo);
		rgarageSi.setBackground(Color.WHITE);
		add(rgarageSi);
		rgarageNo.setBackground(Color.WHITE);
		rgarageNo.setSelected(true);
		add(rgarageNo);
		
		alarma.add(ralarmaSi);
		alarma.add(ralarmaNo);
		ralarmaSi.setBackground(Color.WHITE);
		add(ralarmaSi);
		ralarmaNo.setBackground(Color.WHITE);
		ralarmaNo.setSelected(true);
		add(ralarmaNo);
		
		rastreo.add(rrastreoSi);
		rastreo.add(rrastreoNo);
		rrastreoSi.setBackground(Color.WHITE);
		add(rrastreoSi);
		rrastreoNo.setSelected(true);
		rrastreoNo.setBackground(Color.WHITE);
		add(rrastreoNo);
		
		tuercas.add(rtuercasSi);
		tuercas.add(rtuercasNo);
		rtuercasSi.setBackground(Color.WHITE);
		add(rtuercasSi);
		rtuercasNo.setSelected(true);
		rtuercasNo.setBackground(Color.WHITE);
		add(rtuercasNo);
		
		tablaHijos;
		add()
		
		btnAgregarHijo = new JButton("Agregar datos hijo");
		btnAgregarHijo.setFont(new Font("Open Sans", Font.PLAIN, 13));
		btnAgregarHijo.setEnabled(true);
		btnAgregarHijo.setBackground(Color.LIGHT_GRAY);
		add(btnAgregarHijo);
		
		btnQuitarHijo = new JButton("Quitar datos hijo");
		btnQuitarHijo.setFont(new Font("Open Sans", Font.PLAIN, 13));
		btnQuitarHijo.setEnabled(false);
		btnQuitarHijo.setBackground(Color.LIGHT_GRAY);
		add(btnQuitarHijo);
		
		
		
		
		
		
		btnConfirmarDatos = new JButton("Confirmar datos");
		btnConfirmarDatos.setFont(new Font("Open Sans", Font.PLAIN, 13));
		btnConfirmarDatos.setEnabled(false);
		btnConfirmarDatos.setBackground(Color.LIGHT_GRAY);
		
		btnCancelar.setFont(new Font("Open Sans", Font.PLAIN, 13));
		btnCancelar.setEnabled(true);
		btnCancelar.setBackground(Color.LIGHT_GRAY);
		
		*/
						
	}

}
