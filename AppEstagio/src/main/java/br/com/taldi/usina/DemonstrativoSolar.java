package br.com.taldi.usina;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import br.com.taldi.uconsumidora.Fatura;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "cicloMes", "id_fatura" }) })
public class DemonstrativoSolar {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private BigDecimal energiaRegistrada;
	private BigDecimal energiaInjetada;
	private BigDecimal energiaCompensada;
	private BigDecimal energiaFaturada;
	private BigDecimal credito;
	
	@Temporal(TemporalType.DATE)
	private Date cicloMes;
	@Temporal(TemporalType.DATE)
	private Date cicloInicio;
	@Temporal(TemporalType.DATE)
	private Date cicloFim;
	@OneToOne
	@JoinColumn(name = "id_fatura")
	private Fatura fatura;

	public BigDecimal getEnergiaRegistrada() {
		return energiaRegistrada;
	}

	public void setEnergiaRegistrada(BigDecimal energiaRegistrada) {
		this.energiaRegistrada = energiaRegistrada;
	}

	public BigDecimal getEnergiaInjetada() {
		return energiaInjetada;
	}

	public void setEnergiaInjetada(BigDecimal energiaInjetada) {
		this.energiaInjetada = energiaInjetada;
	}

	public BigDecimal getEnergiaCompensada() {
		return energiaCompensada;
	}

	public void setEnergiaCompensada(BigDecimal energiaCompensada) {
		this.energiaCompensada = energiaCompensada;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getCicloMes() {
		return cicloMes;
	}

	public void setCicloMes(Date cicloMes) {
		this.cicloMes = cicloMes;
	}

	public Date getCicloInicio() {
		return cicloInicio;
	}

	public void setCicloInicio(Date cicloInicio) {
		this.cicloInicio = cicloInicio;
	}

	public Date getCicloFim() {
		return cicloFim;
	}

	public void setCicloFim(Date cicloFim) {
		this.cicloFim = cicloFim;
	}

	public Fatura getFatura() {
		return fatura;
	}

	public void setFatura(Fatura fatura) {
		this.fatura = fatura;
	}

	public BigDecimal getCredito() {
		return credito;
	}

	public void setCredito(BigDecimal credito) {
		this.credito = credito;
	}

	public BigDecimal getEnergiaFaturada() {
		return energiaFaturada;
	}

	public void setEnergiaFaturada(BigDecimal energiaFaturada) {
		this.energiaFaturada = energiaFaturada;
	}

}
