package br.com.taldi.uconsumidora;

import java.math.BigDecimal;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Transient;

import br.com.taldi.aneel.Outro;

@Entity
@AssociationOverrides({ @AssociationOverride(name = "primaryKey.fatura", joinColumns = @JoinColumn(name = "fatura_id")),
		@AssociationOverride(name = "primaryKey.outro", joinColumns = @JoinColumn(name = "outro_id")) })
public class FaturaOutro {

	// Id composta
	@EmbeddedId
	private FaturaOutroId primaryKey = new FaturaOutroId();
	// Colunas adicionais join table
	@Column(nullable = false)
	private BigDecimal valor;

	public FaturaOutroId getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(FaturaOutroId primaryKey) {
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
	public Outro getOutro() {
		return getPrimaryKey().getOutro();
	}

	public void setOutro(Outro outro) {
		getPrimaryKey().setOutro(outro);
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

}
