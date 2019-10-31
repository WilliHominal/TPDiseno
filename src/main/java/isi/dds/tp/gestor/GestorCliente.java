package isi.dds.tp.gestor;

import isi.dds.tp.modelo.Cliente;
import isi.dds.tp.modelo.Poliza;
import java.util.ArrayList;
import java.util.List;
import isi.dds.tp.dao.DAOCliente;
import isi.dds.tp.enums.EnumTipoDocumento;

public class GestorCliente {
	
	private static GestorCliente instanciaGestor = null;
	 
    private GestorCliente() { }

    public static GestorCliente get() {
        if (instanciaGestor == null){
        	instanciaGestor = new GestorCliente();
        }    
        return instanciaGestor;
    }

    public void AltaCliente(Cliente c) {
    	DAOCliente.getDAO().addCliente(c);
    }
    
    public void actualizarCliente(Cliente cliente, Poliza poliza) {
    	if(cliente.getPolizas() == null) {
			cliente.setPolizas(new ArrayList<Poliza>());
		}
    	cliente.getPolizas().add(poliza);
    	
    	actualizarCondicion(cliente);    	
    }
    
    public void actualizarCondicion(Cliente cliente) {
    	//TODO implementar actualizarCondicion
    			//veo si es cliente Plata o Activo
    			Boolean esPlata = true;

    			
    		/*	if (!poliza.getCliente().getNumerosSiniestrosUltimoAnios().equals(EnumSiniestros.NINGUNO))
    				esPlata = false;

    			for (Cuota cuota : poliza.getCuotas()) {
    				if (cuota.getEstado() == EnumEstadoCuota.IMPAGO)
    					esPlata = false;
    			}
*/
    			if (calcularTiempoActivo(cliente) < 365*2)
    				esPlata = false;

    			
    			//if (esPlata)
    				//diria que si modificas el cliente de la poliza, se actualiza el cliente de la base de datos al persistir la poliza
    				//actualizarCondicion(cliente, EnumCondicion.PLATA);
    			//else
    				//actualizarCondicion(cliente, EnumCondicion.ACTIVO);
    }
    
    public Integer calcularTiempoActivo(Cliente c) {
    	//TODO implementar calcularTiempoActivo(Cliente c)
    	return 0;
    }

    public Cliente getCliente(Long numeroCliente) {
    	Cliente cliente = DAOCliente.getDAO().getCliente(numeroCliente);
    	return cliente;
    }
    
    public List<Poliza> getPoliza(Long numeroCliente) {
    	return null;
    }

	public List<Cliente> buscarClientes(Long numeroCliente, String apellido, String nombre, EnumTipoDocumento tipoDocumento, String numeroDocumento) {
    	String condicionesConsulta = "";
    	
    	if(numeroCliente != null) {
    		condicionesConsulta += " and numero_cliente="+numeroCliente;
    	}
    	
    	if(!apellido.isEmpty()) {
    		condicionesConsulta += " and to_ascii(apellido, 'latin1') ilike  to_ascii('"+apellido+"%', 'latin1') ";
    	}
    	
    	if(!nombre.isEmpty()) {
   			condicionesConsulta += " and to_ascii(nombre, 'LATIN1') ilike TO_ASCII('"+nombre+"%', 'latin1') ";
    	}
    	
    	if(tipoDocumento != null) {
    		condicionesConsulta += " and tipo_documento = '"+tipoDocumento+"' ";
    	}
    	
    	if(!numeroDocumento.isEmpty()) {
    		condicionesConsulta += " and documento = '"+numeroDocumento+"' ";
    	}

    	condicionesConsulta += " order by numero_cliente asc";
	
    	return DAOCliente.getDAO().getClientes(condicionesConsulta);
    }
	
	public void cargarClientes() {
		DAOCliente.getDAO().cargarClientes();
	}
}
