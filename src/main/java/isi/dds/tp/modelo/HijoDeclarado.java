package isi.dds.tp.modelo;

import java.time.LocalDate;
import isi.dds.tp.enums.EnumEstadoCivil;
import javax.persistence.*;

@Entity
@Table
public class HijoDeclarado {

	private Integer numeroHijo;
	private LocalDate fechaNacimiento; 
	private String sexo; 
	private EnumEstadoCivil estadoCivil;
	
	public HijoDeclarado () {
	
	}
	
	public HijoDeclarado(Integer numeroHijo, LocalDate fechaNacimiento, String sexo, EnumEstadoCivil estadoCivil) {
		this.numeroHijo = numeroHijo;
		this.fechaNacimiento = fechaNacimiento;
		this.sexo = sexo;
		this.estadoCivil = estadoCivil;
	}
	
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
