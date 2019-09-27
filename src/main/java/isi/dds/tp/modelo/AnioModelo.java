package isi.dds.tp.modelo;

public class AnioModelo {
	
	private Modelo modelo;
	
	private Integer anio;

	public AnioModelo(Modelo modelo, Integer anio) {
		this.modelo = modelo;
		modelo.getAnios().add(this);
		this.anio = anio;		
	}
	
	public Modelo getModelo() {
		return modelo;
	}
	public Integer getAnio() {
		return anio;
	}
	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}
	public void setAnio(Integer anio) {
		this.anio = anio;
	}
}
