package isi.dds.tp.modelo;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.IndexColumn;
import isi.dds.tp.enums.EnumCondicion;
import isi.dds.tp.enums.EnumCondicionIVA;
import isi.dds.tp.enums.EnumEstadoCivil;
import isi.dds.tp.enums.EnumSexo;
import isi.dds.tp.enums.EnumTipoDocumento;

@SuppressWarnings("deprecation")
@Entity
@Table
public class Cliente {
	
	@JoinColumn(name="id_ciudad")
    @OneToOne
	private Ciudad ciudad;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="numero_cliente")
	@IndexColumn(name="idx")
	private List<Poliza> polizas;
	
	@Id
	@Column(nullable = false, name = "numero_cliente")
	private Long numeroCliente;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private EnumCondicion condicion; 
	
	@Column(nullable = false)
	private String apellido;
	
	@Column(nullable = false)
	private String nombre;
	
	@Column(nullable = false, name = "tipo_documento")
	@Enumerated(EnumType.STRING)
	private EnumTipoDocumento tipoDocumento;
	
	@Column(nullable = false, unique = true, name = "documento")
	private String numeroDocumento;
	
	@Column(nullable = false, unique = true, name = "cuil")
	private Long numeroCuil;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private EnumSexo sexo;
	
	@Column(nullable = false, name = "nacimiento")
	private LocalDate fechaNacimiento;
	
	@Column(nullable = false)
	private String calle;
	
	@Column(nullable = false, name = "numero_calle")
	private Integer numeroCalle;
	
	@Column
	private Integer piso;
	
	@Column
	private String departamento;
	
	@Column(nullable = false, name = "codigo_postal")
	private Integer codigoPostal;
	
	@Column(nullable = false, name = "condicion_iva")
	@Enumerated(EnumType.STRING)
	private EnumCondicionIVA condicionIva;
	
	@Column(nullable = false, name = "correo_electronico")
	private String correoElectronico;
	
	@Column(nullable = false, name = "estado_civil")
	@Enumerated(EnumType.STRING)
	private EnumEstadoCivil estadoCivil; 
	
	@Column(nullable = false)
	private String profesion;
	
	@Column(nullable = false, name = "anio_registro")
	private Integer anioRegistro;
	
	public Cliente() { }
	
	public Ciudad getCiudad() {
		return ciudad;
	}
	public List<Poliza> getPolizas() {
		return polizas;
	}
	public Long getNumeroCliente() {
		return numeroCliente;
	}
	public EnumCondicion getCondicion() {
		return condicion;
	}
	public String getApellido() {
		return apellido;
	}
	public String getNombre() {
		return nombre;
	}
	public EnumTipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}
	public String getNumeroDocumento() {
		return numeroDocumento;
	}
	public Long getNumeroCuil() {
		return numeroCuil;
	}
	public EnumSexo getSexo() {
		return sexo;
	}
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}
	public String getCalle() {
		return calle;
	}
	public Integer getNumeroCalle() {
		return numeroCalle;
	}
	public Integer getPiso() {
		return piso;
	}
	public String getDepartamento() {
		return departamento;
	}
	public Integer getCodigoPostal() {
		return codigoPostal;
	}
	public EnumCondicionIVA getCondicionIva() {
		return condicionIva;
	}
	public String getCorreoElectronico() {
		return correoElectronico;
	}
	public EnumEstadoCivil getEstadoCivil() {
		return estadoCivil;
	}
	public String getProfesion() {
		return profesion;
	}
	public Integer getAnioRegistro() {
		return anioRegistro;
	}
	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}
	public void setPolizas(List<Poliza> polizas) {
		this.polizas = polizas;
	}
	public void setNumeroCliente(Long numeroCliente) {
		this.numeroCliente = numeroCliente;
	}
	public void setCondicion(EnumCondicion condicion) {
		this.condicion = condicion;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setTipoDocumento(EnumTipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	public void setNumeroCuil(Long numeroCuil) {
		this.numeroCuil = numeroCuil;
	}
	public void setSexo(EnumSexo sexo) {
		this.sexo = sexo;
	}
	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public void setNumeroCalle(Integer numeroCalle) {
		this.numeroCalle = numeroCalle;
	}
	public void setPiso(Integer piso) {
		this.piso = piso;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	public void setCodigoPostal(Integer codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	public void setCondicionIva(EnumCondicionIVA condicionIva) {
		this.condicionIva = condicionIva;
	}
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
	public void setEstadoCivil(EnumEstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	public void setProfesion(String profesion) {
		this.profesion = profesion;
	}
	public void setAnioRegistro(Integer anioRegistro) {
		this.anioRegistro = anioRegistro;
	}
}
