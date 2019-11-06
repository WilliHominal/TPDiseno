package isi.dds.tp.gestor;

import java.time.LocalDate;
import java.util.List;
import isi.dds.tp.dao.DAOTipoCobertura;
import isi.dds.tp.enums.EnumTipoCobertura;
import isi.dds.tp.modelo.TipoCobertura;
import isi.dds.tp.modelo.RiesgoTipoCobertura;

public class GestorTipoCobertura {
	
	private static GestorTipoCobertura instanciaGestor = null;
	 
    private GestorTipoCobertura() { }

    public static GestorTipoCobertura get() {
        if (instanciaGestor == null){
        	instanciaGestor = new GestorTipoCobertura();
        }    
        return instanciaGestor;
    }
    
    public void addRiesgoCobertura(RiesgoTipoCobertura r) {
    	DAOTipoCobertura.getDAO().addRiesgoCobertura(r);
    }
    
	public TipoCobertura getTipoCobertura(EnumTipoCobertura tipo){
		return DAOTipoCobertura.getDAO().getTipoCobertura(tipo);
    }
    
	@SuppressWarnings("null")
	public List<TipoCobertura> getTiposCobertura(Integer anioModelo){
		List<TipoCobertura> lista = null;
		if(anioModelo <= LocalDate.now().getYear() - 10) {
			lista.add(getTipoCobertura(EnumTipoCobertura.RESPONSABILIDAD_CIVIL));
		}
		else {
			lista = DAOTipoCobertura.getDAO().getTiposCobertura();
		}
		return lista;
    }
    
    public Float getUltimoRiesgoTipoCobertura(EnumTipoCobertura tipo) {
    	return DAOTipoCobertura.getDAO().getUltimoRiesgoTipoCobertura(tipo).getValorPorcentual(); 
    }
}
