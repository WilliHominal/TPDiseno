package isi.dds.tp.modelo;

import java.util.*;

public class Modelo {

	private Marca marca;
	private List<AnioModelo> anios;
	private List<RiesgoModelo> riesgos;
	
	private Integer idModelo;
	private String nombre;
	
	public Modelo(Marca marca, Integer idModelo, String nombre) {
		this.marca = marca;
		/*AÑADIR CON ADD A MARCA, CREANDO UN METODO*/
		this.anios = new ArrayList<AnioModelo>();
		this.riesgos = new ArrayList<RiesgoModelo>();
		this.idModelo = idModelo;
		this.nombre = nombre;		
	}
	
	public Marca getMarca() {
		return marca;
	}
	public List<AnioModelo> getAnios() {
		return anios;
	}
	public List<RiesgoModelo> getRiesgos() {
		return riesgos;
	}
	public Integer getIdModelo() {
		return idModelo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setMarca(Marca marca) {
		this.marca = marca;
	}
	public void setAnios(List<AnioModelo> anios) {
		this.anios = anios;
	}
	public void setRiesgos(List<RiesgoModelo> riesgos) {
		this.riesgos = riesgos;
	}
	public void setIdModelo(Integer idModelo) {
		this.idModelo = idModelo;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}	
}	
