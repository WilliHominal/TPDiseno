package isi.dds.tp.gestor;

import isi.dds.tp.modelo.Cliente;
import isi.dds.tp.modelo.Poliza;

import java.util.Iterator;
import java.util.List;
import isi.dds.tp.dao.DAOCliente;
import isi.dds.tp.dao.DAOPoliza;
import isi.dds.tp.enums.EnumCondicion;
import isi.dds.tp.enums.EnumSiniestros;
import isi.dds.tp.enums.EnumTipoDocumento;
import isi.dds.tp.hibernate.HibernateUtil;

public class GestorCliente {
	private static GestorCliente instanciaGestor = null;
	
	private static GestorPoliza gestorPoliza = GestorPoliza.get();
	 
    private GestorCliente() { }

    public static GestorCliente get() {
        if (instanciaGestor == null){
        	instanciaGestor = new GestorCliente();
        }   
        return instanciaGestor;
    }
    
    public Cliente getCliente(Long numeroCliente) {
    	return DAOCliente.getDAO().getCliente(numeroCliente);
    }
    
    public void actualizarCliente(Cliente cliente, Poliza poliza) { 
    	if(gestorPoliza.getPolizas(cliente.getNumeroCliente()).size() <= 1) {
    		//la cantidad es uno, porque se actualiza al cliente luego de añadirle la póliza, con lo cuál ya tendría al menos una póliza
    		cliente.setCondicion(EnumCondicion.NORMAL);
    	}else {
    		Boolean polizasVigentes = gestorPoliza.vigenciaPolizas(cliente.getNumeroCliente());
    		if(!polizasVigentes) {
    			cliente.setCondicion(EnumCondicion.NORMAL);
    		}
    		else {
    			Boolean tieneSiniestros = !poliza.getNumerosSiniestrosUltimoAnios().equals(EnumSiniestros.NINGUNO);
    			Boolean cuotasImpagas = gestorPoliza.omisionPago(cliente.getNumeroCliente());
    			Boolean esClienteActivo = clienteActivo(cliente.getNumeroCliente());
    			if(tieneSiniestros || cuotasImpagas || !esClienteActivo) {
    				cliente.setCondicion(EnumCondicion.NORMAL);
    			}
    			else{
    				cliente.setCondicion(EnumCondicion.PLATA);
    			}
    		}
    	}
    	HibernateUtil.shutdown();
    	DAOCliente.getDAO().updateCliente(cliente);
    }

	public Boolean clienteActivo(Long numeroCliente) {
		List<Poliza> listaPolizasActivas = DAOPoliza.getDAO().getPolizasActivas(numeroCliente);
		String numeroRenovacion = "";
		Iterator<Poliza> iteratorPolizas = listaPolizasActivas.iterator();
		while(iteratorPolizas.hasNext()) {
			Poliza poliza = iteratorPolizas.next();
			numeroRenovacion = poliza.getNumeroPoliza().toString().substring(11, 13);
			//TODO CU01 rehacer bien
			if(Integer.parseInt(numeroRenovacion)>=4) {
				return true;
			}
		}
		return false;
	}

	public List<Cliente> buscarClientes(Long numeroCliente, String apellido, String nombre, EnumTipoDocumento tipoDocumento, String numeroDocumento) {
		String condicionesConsulta = "";
		Boolean primerConsulta = true;
    	if(numeroCliente != null) {
    		condicionesConsulta += "where numero_cliente="+numeroCliente;
    		primerConsulta = false;
    	}
    	
    	if(!apellido.isEmpty()) {
    		if(primerConsulta) {
    			primerConsulta = false;
    			condicionesConsulta += " where ";
      		}
    		else {
    			condicionesConsulta += " and ";
    		}
    		condicionesConsulta += " to_ascii(apellido, 'latin1') ilike  to_ascii('"+apellido+"%', 'latin1') ";
    	}
    	
    	if(!nombre.isEmpty()) {
    		if(primerConsulta) {
    			primerConsulta = false;
    			condicionesConsulta += " where ";
      		}
    		else {
    			condicionesConsulta += " and ";
    		}
   			condicionesConsulta += " to_ascii(nombre, 'LATIN1') ilike TO_ASCII('"+nombre+"%', 'latin1') ";
    	}
    	
    	if(tipoDocumento != null) {
    		if(primerConsulta) {
    			primerConsulta = false;
    			condicionesConsulta += " where ";
      		}
    		else {
    			condicionesConsulta += " and ";
    		}
    		condicionesConsulta += " tipo_documento = '"+tipoDocumento.name()+"' ";
    	}
    	
    	if(!numeroDocumento.isEmpty()) {
    		if(!primerConsulta) {
    			condicionesConsulta += " and ";
    			condicionesConsulta += " where ";
    		}
    		condicionesConsulta += " documento = '"+numeroDocumento+"' ";
    	}
    	condicionesConsulta += " order by numero_cliente asc";
    	return DAOCliente.getDAO().getClientes(condicionesConsulta);
    }
}
