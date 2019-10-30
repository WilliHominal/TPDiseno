package isi.dds.tp.hibernate;

import isi.dds.tp.gestor.GestorCliente;
import isi.dds.tp.gestor.GestorDomicilio;
import isi.dds.tp.gestor.GestorParametrosVehiculo;
import isi.dds.tp.gestor.GestorSubsistemaSiniestros;
import isi.dds.tp.gestor.GestorTipoCobertura;

public class CargarBase {
	
	public static void load() {
		HibernateUtil.shutdown();
		HibernateUtil.getSessionFactoryCreate();	
		GestorDomicilio.get().cargarUbicaciones();
		GestorCliente.get().cargarClientes();
		GestorSubsistemaSiniestros.get().cargarSiniestros();
		GestorTipoCobertura.get().cargarTiposCoberturas();
		GestorParametrosVehiculo.get().cargarParametrosVehiculos();
	//	GestorParametrosPoliza.get().cargarParametrosPoliza();
	//	GestorPoliza.get().cargarPolizas();
    }
}
