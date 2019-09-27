package isi.dds.tp.modelo;

import java.time.LocalDate;
import isi.dds.tp.enums.EnumEstadoCivil;

public class HijoDeclarado {

	private Integer numeroHijo;
	private LocalDate fechaNacimiento; 
	private String sexo; 
	private EnumEstadoCivil estadoCivil;
	
	public Integer getNumeroHijo() {
		return numeroHijo;
	}
	public LocalDate getFechaNacimiento() {
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
	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public void setEstadoCivil(EnumEstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
}
