package isi.dds.tp.gestor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import isi.dds.tp.dao.DAOPago;
import isi.dds.tp.enums.EnumEstadoCuota;
import isi.dds.tp.modelo.Cuota;
import isi.dds.tp.modelo.Pago;
import isi.dds.tp.modelo.Poliza;

public class GestorPago {
	
	private static GestorPago instanciaGestor = null;
	 
    private GestorPago() { }

    public static GestorPago get() {
        if (instanciaGestor == null){
        	instanciaGestor = new GestorPago();
        }    
        return instanciaGestor;
    }
    
    public void registrarPago (List<Cuota> cuotasApagar, Float monto) {
		Pago pago = new Pago();
    	pago.setFechaPago(LocalDate.now());
    	pago.setHora(LocalTime.now());
    	pago.setOperador("Operador n");
    	pago.setImporte(monto);
    	pago.setPremio(cuotasApagar.get(0).getPoliza().getValorPremio());
    	pago.setCuotas(new ArrayList<Cuota>());
		for(Cuota cuota:cuotasApagar) {
			GestorPoliza.get().pagarCuota(cuota, pago);
			pago.getCuotas().add(cuota);
		}			
		DAOPago.getDAO().addPago(pago);
    }
 
  public Pago ultimoPago(Poliza poliza) {
    	Pago pCuota=null;
    	
    	List<Cuota> cuotas = new ArrayList<Cuota>();
    	cuotas = poliza.getCuotas();
    	int i = 0;
    	LocalDate fecha = LocalDate.parse("1800-01-01");
    	while (i < cuotas.size()) {
    		
    		if (cuotas.get(i).getEstado() == EnumEstadoCuota.PAGO) {
    			
    			if (i == 0) {
    				pCuota = cuotas.get(i).getPago();
    				fecha =	cuotas.get(i).getPago().getFechaPago();
    			}
    			
    			else if (LocalDate.parse(fecha.toString()).isBefore(LocalDate.parse(cuotas.get(i).getPago().getFechaPago().toString()))){
	    					fecha =	cuotas.get(i).getPago().getFechaPago();
	    					pCuota = cuotas.get(i).getPago();
	    			}
    			
    		}
    		i++;
    	}
    	return pCuota;
  }
}
	

	