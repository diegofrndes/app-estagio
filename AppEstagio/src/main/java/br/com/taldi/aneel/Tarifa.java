package br.com.taldi.aneel;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Tarifa {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private BigDecimal valor;
	@ManyToOne
	@JoinColumn(name = "id_classificacao", referencedColumnName = "id")
	private Classificacao classificacao;
	@Temporal(TemporalType.DATE)
	private Date inicioVigencia;
	@Temporal(TemporalType.DATE)
	private Date fimVigencia;
	private BigDecimal consumoMin;
	private BigDecimal consumoMax;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	@Column(precision = 9, scale = 8)
	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Classificacao getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(Classificacao classificacao) {
		this.classificacao = classificacao;
	}

	public Date getFimVigencia() {
		return fimVigencia;
	}

	public void setFimVigencia(Date fimVigencia) {
		this.fimVigencia = fimVigencia;
	}

	public Date getInicioVigencia() {
		return inicioVigencia;
	}

	public void setInicioVigencia(Date inicioVigencia) {
		this.inicioVigencia = inicioVigencia;
	}

	public BigDecimal getConsumoMin() {
		return consumoMin;
	}

	public void setConsumoMin(BigDecimal consumoMin) {
		this.consumoMin = consumoMin;
	}

	public BigDecimal getConsumoMax() {
		return consumoMax;
	}

	public void setConsumoMax(BigDecimal consumoMax) {
		this.consumoMax = consumoMax;
	}

}
