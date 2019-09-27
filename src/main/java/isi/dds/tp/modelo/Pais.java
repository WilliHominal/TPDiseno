package isi.dds.tp.modelo;

import java.util.ArrayList;
import java.util.List;

public class Pais {
	
	private List<Provincia> provincias;
	
	private Integer idPais;
	private String nombre;
	
	/**
	 * @param idPais
	 * @param nombre
	 */
	public Pais(Integer idPais, String nombre) {
		this.provincias = new ArrayList<Provincia>();
		this.idPais = idPais;
		this.nombre = nombre;
	}
	
	public List<Provincia> getProvincias() {
		return provincias;
	}
	public Integer getIdPais() {
		return idPais;
	}
	public String getNombre() {
		return nombre;
	}
	public void setProvincias(List<Provincia> provincias) {
		this.provincias = provincias;
	}
	public void setIdPais(Integer idPais) {
		this.idPais = idPais;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
