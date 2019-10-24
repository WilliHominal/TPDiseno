package isi.dds.tp.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import isi.dds.tp.enums.EnumSiniestros;
import isi.dds.tp.enums.EnumTipoDocumento;

@Entity
@Table
public class Siniestro {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_siniestros")
	@SequenceGenerator(name="id_siniestros", sequenceName = "id_siniestros_seq", initialValue = 100, allocationSize = 1)
	@Column(nullable = false)
	private Integer id;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private EnumSiniestros siniestros; 
	
	@Column(nullable = false)
	private Integer anio;
	
	@Column(nullable = false, name = "tipo_documento")
	@Enumerated(EnumType.STRING)
	private EnumTipoDocumento tipoDocumento; 
	
	@Column(nullable = false)
	private String documento;
	
	public Siniestro() { }
	
	public Siniestro(EnumSiniestros siniestros, Integer anio, EnumTipoDocumento tipoDocumento, String documento) {
		this.siniestros = siniestros;
		this.anio = anio;
		this.tipoDocumento = tipoDocumento;
		this.documento = documento;
	}
	
	public EnumTipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(EnumTipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAnio() {
		return anio;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String dni) {
		this.documento = dni;
	}

	public EnumSiniestros getSiniestros() {
		return siniestros;
	}

	public void setSiniestros(EnumSiniestros siniestros) {
		this.siniestros = siniestros;
	}
}
