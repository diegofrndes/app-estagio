package br.com.taldi.uconsumidora;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Transient;

import br.com.taldi.aneel.Consumo;

import java.math.BigDecimal;

@Entity
@AssociationOverrides({ @AssociationOverride(name = "primaryKey.fatura", joinColumns = @JoinColumn(name = "fatura_id")),
		@AssociationOverride(name = "primaryKey.consumo", joinColumns = @JoinColumn(name = "consumo_id")) })
public class FaturaConsumo {
	// Id composta
	@EmbeddedId
	private FaturaConsumoId primaryKey = new FaturaConsumoId();
	// Colunas adicionais join table
	@Column(nullable = false)
	private BigDecimal quantidade;
	@Column(nullable = false)
	private BigDecimal valor;

	public FaturaConsumoId getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(FaturaConsumoId primaryKey) {
		this.primaryKey = primaryKey;
	}

	@Transient
	public Fatura getFatura() {
		return getPrimaryKey().getFatura();
	}

	public void setFatura(Fatura fatura) {
		getPrimaryKey().setFatura(fatura);
	}

	@Transient
	public Consumo getConsumo() {
		return getPrimaryKey().getConsumo();
	}

	public void setConsumo(Consumo consumo) {
		getPrimaryKey().setConsumo(consumo);
	}

	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

}
