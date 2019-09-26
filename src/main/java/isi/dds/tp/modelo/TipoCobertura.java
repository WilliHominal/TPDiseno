package isi.dds.tp.modelo;

import java.util.List;
import isi.dds.tp.enums.EnumTipoCobertura;

public class TipoCobertura {
	
	private List<RiesgoTipoCobertura> riesgo;
	
	private EnumTipoCobertura tipoCobertura;

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
