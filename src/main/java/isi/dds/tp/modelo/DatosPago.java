package isi.dds.tp.modelo;

import isi.dds.tp.enums.EnumPagoCuota;
import javax.persistence.*;

@Entity
@Table(name = "datos_pago")
public class DatosPago {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_datos_pago")
	@SequenceGenerator(name="id_datos_pago", sequenceName = "id_datos_pago_seq", initialValue = 100, allocationSize = 1)
	@Column(nullable = false)
	private Integer id;
	
    @OneToOne
    @PrimaryKeyJoinColumn
	private Cuota cuota;
    
	@ManyToOne
	@JoinColumn(name="numero_recibo")
	private Pago pago;

	@Column(nullable = false, name = "bonificacion_pago_adelantado")
	private Float bonificacionPagoAdelantado;
	
	@Column(nullable = false, name = "recargo_por_mora")
	private Float recargoPorMora;
	
	@Column(nullable = false, name = "estado_pago_cuota")
	@Enumerated(EnumType.STRING)
	private EnumPagoCuota estadoPagoCuota;
	
	public DatosPago() {
		
	}
	
	public DatosPago(Cuota cuota, Pago pago, Float bonificacionPagoAdelantado, Float recargoPorMora, EnumPagoCuota estadoPagoCuota) {
		this.cuota = cuota;
		this.pago = pago;
		this.bonificacionPagoAdelantado = bonificacionPagoAdelantado;
		this.recargoPorMora = recargoPorMora;
		this.estadoPagoCuota = estadoPagoCuota;
	}
	
	public Cuota getCuota() {
		return cuota;
	}
	public Pago getPago() {
		return pago;
	}
	public Float getBonificacionPagoAdelantado() {
		return bonificacionPagoAdelantado;
	}
	public Float getRecargoPorMora() {
		return recargoPorMora;
	}
	public EnumPagoCuota getEstadoPagoCuota() {
		return estadoPagoCuota;
	}
	public void setCuota(Cuota cuota) {
		this.cuota = cuota;
	}
	public void setPago(Pago pago) {
		this.pago = pago;
	}
	public void setBonificacionPagoAdelantado(Float bonificacionPagoAdelantado) {
		this.bonificacionPagoAdelantado = bonificacionPagoAdelantado;
	}
	public void setRecargoPorMora(Float recargoPorMora) {
		this.recargoPorMora = recargoPorMora;
	}
	public void setEstadoPagoCuota(EnumPagoCuota estadoPagoCuota) {
		this.estadoPagoCuota = estadoPagoCuota;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}	
}
