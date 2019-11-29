package isi.dds.tp.modelo;


import java.time.LocalDate;
import isi.dds.tp.enums.EnumEstadoCuota;
import isi.dds.tp.enums.EnumPagoCuota;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table
public class Cuota {
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "numero_poliza", foreignKey=@ForeignKey(name = "fk_numero_poliza"), nullable = false)
	private Poliza poliza;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="numero_recibo", foreignKey=@ForeignKey(name = "fk_numero_recibo"))
	private Pago pago;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_cuota")
	@SequenceGenerator(name="id_cuota", sequenceName = "id_cuota_seq", initialValue = 100, allocationSize = 1)
	@Column(nullable = false, name = "id_cuota")
	private Integer idCuota;
	
	@Column(nullable = false)
	private Float monto;
	
	@Column(nullable = false, name = "ultimo_dia_pago")
	private LocalDate ultimoDiaPago;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private EnumEstadoCuota estado;
	
	@Column(name = "bonificacion_pago_adelantado")
	private Float bonificacionPagoAdelantado;
	
	@Column(name = "recargo_por_mora")
	private Float recargoPorMora;
	
	@Column(name = "estado_pago_cuota")
	@Enumerated(EnumType.STRING)
	private EnumPagoCuota estadoPagoCuota;

	public Cuota() { }
	
	public Poliza getPoliza() {
		return poliza;
	}
	
	public Float getMonto() {
		return monto;
	}
	
	public LocalDate getUltimoDiaPago() {
		return ultimoDiaPago;
	}
	
	public EnumEstadoCuota getEstado() {
		return estado;
	}
	
	public void setPoliza(Poliza poliza) {
		this.poliza = poliza;
	}
	
	public void setMonto(Float monto) {
		this.monto = monto;
	}
	
	public void setUltimoDiaPago(LocalDate ultimoDiaPago) {
		this.ultimoDiaPago = ultimoDiaPago;
	}
	
	public void setEstado(EnumEstadoCuota estado) {
		this.estado = estado;
	}
	
	public Pago getPago() {
		return pago;
	}
	
	public void setPago(Pago pago) {
		this.pago = pago;
	}
	
	public Integer getIdCuota() {
		return idCuota;
	}
	
	public void setIdCuota(Integer idCuota) {
		this.idCuota = idCuota;
	}
	
	public Float getBonificacionPagoAdelantado() {
		return bonificacionPagoAdelantado;
	}
	
	public void setBonificacionPagoAdelantado(Float bonificacionPagoAdelantado) {
		this.bonificacionPagoAdelantado = bonificacionPagoAdelantado;
	}
	
	public Float getRecargoPorMora() {
		return recargoPorMora;
	}
	
	public void setRecargoPorMora(Float recargoPorMora) {
		this.recargoPorMora = recargoPorMora;
	}
	
	public EnumPagoCuota getEstadoPagoCuota() {
		return estadoPagoCuota;
	}
	
	public void setEstadoPagoCuota(EnumPagoCuota estadoPagoCuota) {
		this.estadoPagoCuota = estadoPagoCuota;
	}
}
