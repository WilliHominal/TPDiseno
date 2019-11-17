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
    
    public void registrarPago (Pago pago, Float monto) {
    	pago.setFechaPago(LocalDate.now());
    	pago.setHora(LocalTime.now());
    	pago.setOperador("Operador n");
    	pago.setImporte(monto);
    	DAOPago.getDAO().addPago(pago);    	
    }

	public Pago realizarPago(List<Cuota> cuotasApagar) {
		Pago pago = new Pago();
		for(Cuota cuota:cuotasApagar) {
    		cuota.setEstado(EnumEstadoCuota.PAGO);
    	}
		//TODO CU12 implementar logica en gestor poliza diria, que calcule los valores atrasados y los bonificados y setearlos en cuota (junto con estadopagocuota, bonificacion etc) y despues setearlos en la view
		//CApaz haya que hacerlo directamente en el controller1, y habria que modificar el diagrama de secuencias que actualiza las cuotas ahi, no se bien
		pago.setCuotas(cuotasApagar);
		return pago;
	}
	
	/*
	public Cuota ultimoPago(Poliza poliza) {
    	
    	List<Cuota> cuotas = new ArrayList<Cuota>();
    	List<Cuota> cuotasPago = new ArrayList <Cuota>();
    	cuotas = poliza.getCuotas();
    	
    	
    	
    	for (Cuota cuota : cuotas)
			if (cuota.getEstado() == EnumEstadoCuota.PAGO)
				cuotasPago.add(cuota);
  	
    //	int auxiliar=0;
    	if(cuotasPago.size()>0) {
    		Cuota ultimoPago = cuotasPago.get(0);
    		String fecha= ultimoPago.getPago().getFechaPago().toString();
	    	for(int i=1; i<cuotasPago.size(); i++) {
	    		if(fecha.compareTo(cuotasPago.get(i).getPago().getFechaPago().toString()) < 0) {
	    			fecha = cuotasPago.get(i).getPago().getFechaPago().toString();
	    			ultimoPago = cuotasPago.get(i);
	    	//		auxiliar = i;
	    		}
	    	}
	    	return ultimoPago;
    	}
    	else
    		return null; 
    	
	}
*/
}
	

	