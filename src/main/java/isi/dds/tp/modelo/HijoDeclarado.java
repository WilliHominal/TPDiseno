package isi.dds.tp.modelo;

import java.sql.Date;
import isi.dds.tp.enums.EnumEstadoCivil;

public class HijoDeclarado {

	private Integer numeroHijo;
	private Date fechaNacimiento; 
	private String sexo; 
	private EnumEstadoCivil estadoCivil;
	
	public Integer getNumeroHijo() {
		return numeroHijo;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public String getSexo() {
		return sexo;
	}
	public EnumEstadoCivil getEstadoCivil() {
		return estadoCivil;
	}
	public void setNumeroHijo(Integer numeroHijo) {
		this.numeroHijo = numeroHijo;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public void setEstadoCivil(EnumEstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
}
