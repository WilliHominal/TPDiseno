package isi.dds.tp.modelo;

import java.util.ArrayList;
import java.util.List;
import isi.dds.tp.enums.EnumTipoCobertura;

public class TipoCobertura {
	private List<RiesgoTipoCobertura> riesgo;
	
	private EnumTipoCobertura tipoCobertura;
	
	/**
	 * @param tipoCobertura
	 * @param riesgo
	 */
	public TipoCobertura(EnumTipoCobertura tipoCobertura, Float riesgo) {
		this.riesgo =  new  ArrayList<RiesgoTipoCobertura>();
		this.riesgo.add(new RiesgoTipoCobertura(this, riesgo));
		this.tipoCobertura = tipoCobertura;
	}	

	public List<RiesgoTipoCobertura> getRiesgo() {
		return riesgo;
	}

	public EnumTipoCobertura getTipoCobertura() {
		return tipoCobertura;
	}

	public void setRiesgo(List<RiesgoTipoCobertura> riesgo) {
		this.riesgo = riesgo;
	}

	public void setTipoCobertura(EnumTipoCobertura tipoCobertura) {
		this.tipoCobertura = tipoCobertura;
	}
}
