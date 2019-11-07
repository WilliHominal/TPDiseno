package isi.dds.tp.gestor;

import java.time.LocalDate;
import java.util.ArrayList;

import isi.dds.tp.modelo.BitacoraParametrosPoliza;
import isi.dds.tp.modelo.ParametrosPoliza;
import isi.dds.tp.modelo.RiesgoCiudad;
import isi.dds.tp.modelo.RiesgoModelo;
import isi.dds.tp.modelo.RiesgoTipoCobertura;

public class GestorBitacora {

	private static GestorBitacora instanciaGestor = null;
	 
    private GestorBitacora() { }
    
    public static GestorBitacora get() {
        if (instanciaGestor == null){
        	instanciaGestor = new GestorBitacora();
        }    
        return instanciaGestor;
    }
    
    public BitacoraParametrosPoliza newBitacoraParametros() {
    	BitacoraParametrosPoliza b = new BitacoraParametrosPoliza();
    	b.setFechaModificacion(LocalDate.now());
    	return b;
    }
    
    public void addRiesgoModelo(BitacoraParametrosPoliza bitacora, RiesgoModelo r) {
    	if(bitacora.getRiesgosModelo() == null) {
    		bitacora.setRiesgosModelo(new ArrayList<RiesgoModelo>());
    	}
    	bitacora.getRiesgosModelo().add(r);
    	r.setBitacoraParametros(bitacora);
    }
    
    public void addRiesgoCiudad(BitacoraParametrosPoliza bitacora, RiesgoCiudad r) {
    	if(bitacora.getRiesgosCiudad() == null) {
    		bitacora.setRiesgosCiudad(new ArrayList<RiesgoCiudad>());
    	}
    	bitacora.getRiesgosCiudad().add(r);
    	r.setBitacoraParametros(bitacora);
    }
    
    public void addRiesgoTipoCobertura(BitacoraParametrosPoliza bitacora, RiesgoTipoCobertura r) {
    	if(bitacora.getRiesgosTipoCobertura() == null) {
    		bitacora.setRiesgosTipoCobertura(new ArrayList<RiesgoTipoCobertura>());
    	}
    	bitacora.getRiesgosTipoCobertura().add(r);
    	r.setBitacoraParametros(bitacora);
    }
    
    public void addParametrosPoliza(BitacoraParametrosPoliza bitacora, ParametrosPoliza p) {
    	bitacora.setParametrosPoliza(p);
    	p.setBitacoraParametros(bitacora);
    }
}
