package isi.dds.tp.modelo;

import java.util.List;

public class Marca {
	
	private List<Modelo> modelos;
	
	private Integer idMarca;
	private String nombre;
	
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
