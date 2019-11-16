package isi.dds.tp.gestor;

import java.util.ArrayList;
import java.util.List;

import isi.dds.tp.dao.DAOPago;
import isi.dds.tp.dao.DAOPoliza;
import isi.dds.tp.enums.EnumEstadoCuota;
import isi.dds.tp.modelo.Cuota;

public class GestorPago {
	
	private static GestorPago instanciaGestor = null;
	 
    private GestorPago() { }

    public static GestorPago get() {
        if (instanciaGestor == null){
        	instanciaGestor = new GestorPago();
        }    
        return instanciaGestor;
    }
    
    public void registrarPago (long numeroPoliza, int cantidadCuotas) {
    	List<Cuota> cuotasPoliza = new ArrayList<Cuota>();
    	List<Cuota> cuotasImpagas = new ArrayList<Cuota>();
    	List<Cuota> cuotasActualizadas = new ArrayList<Cuota>();
    	
    	cuotasPoliza = DAOPoliza.getDAO().getCuotas(numeroPoliza);
    	
    	for (Cuota cuota : cuotasPoliza)
			if (cuota.getEstado() == EnumEstadoCuota.IMPAGO)
				cuotasImpagas.add(cuota);
			else
				cuotasActualizadas.add(cuota);
    	
    	for (int i=0; i<cantidadCuotas; i++) {
    		
    		DAOPago.getDAO().removeCuota(cuotasImpagas.get(i));
    		cuotasImpagas.get(i).setEstado(EnumEstadoCuota.PAGO);
    		DAOPago.getDAO().addCuota(cuotasImpagas.get(i));
    	}
    	cuotasActualizadas.addAll(cuotasImpagas);
    	cuotasPoliza.get(0).getPoliza().setCuotas(cuotasActualizadas);;

    	for (int i=0; i<6; i++)
    		System.out.println(cuotasActualizadas.get(i).getIdCuota() + cuotasActualizadas.get(i).getEstado().toString());
    	
    }
}
