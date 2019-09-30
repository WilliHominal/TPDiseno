package isi.dds.tp.modelo;

import java.util.ArrayList;
import java.util.List;
import isi.dds.tp.enums.EnumTipoCobertura;

import javax.persistence.*;

import org.hibernate.annotations.IndexColumn;


@SuppressWarnings("deprecation")
@Entity
@Table
public class TipoCobertura {
	

	@OneToMany(cascade= CascadeType.ALL)
	@JoinColumn(name="tipoCobertura")
	@IndexColumn(name="idx")
	private List<RiesgoTipoCobertura> riesgo;
	
	@Id
	@Enumerated(EnumType.STRING)
	private EnumTipoCobertura tipoCobertura;
	
	public TipoCobertura() {
		
	}
	
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
