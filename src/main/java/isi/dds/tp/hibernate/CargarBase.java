package isi.dds.tp.hibernate;

import isi.dds.tp.gestor.GestorCliente;
import isi.dds.tp.gestor.GestorDomicilio;
import isi.dds.tp.gestor.GestorParametrosPoliza;
import isi.dds.tp.gestor.GestorParametrosVehiculo;
import isi.dds.tp.gestor.GestorSubsistemaSiniestros;
import isi.dds.tp.gestor.GestorTipoCobertura;

public class CargarBase {
	
	public static void load() {
		HibernateUtil.shutdown();
		HibernateUtil.getSessionFactoryCreate();	
		GestorParametrosPoliza.get().cargarParametrosPoliza();
		GestorDomicilio.get().cargarUbicaciones();
		GestorSubsistemaSiniestros.get().cargarSiniestros();
		GestorTipoCobertura.get().cargarTiposCoberturas();
		GestorParametrosVehiculo.get().cargarParametrosVehiculos();
		GestorCliente.get().cargarClientes();
		//GestorPoliza.get().cargarPolizas();
    }
}
