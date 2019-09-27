package isi.dds.tp.modelo;

import java.util.*;

public class Marca {
	
	private List<Modelo> modelos;
	
	private Integer idMarca;
	private String nombre;
	
	/**
	 * @param idMarca
	 * @param nombre
	 */
	public Marca(Integer idMarca, String nombre) {
		this.modelos = new ArrayList<Modelo>();
		this.idMarca = idMarca;
		this.nombre = nombre;
	}
	
	public List<Modelo> getModelos() {
		return modelos;
	}
	public Integer getIdMarca() {
		return idMarca;
	}
	public String getNombre() {
		return nombre;
	}
	public void setModelos(List<Modelo> modelos) {
		this.modelos = modelos;
	}
	public void setIdMarca(Integer idMarca) {
		this.idMarca = idMarca;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
