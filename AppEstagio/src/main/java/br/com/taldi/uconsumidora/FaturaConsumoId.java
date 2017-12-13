package br.com.taldi.uconsumidora;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import br.com.taldi.aneel.Consumo;

@SuppressWarnings("serial")
@Embeddable
public class FaturaConsumoId implements Serializable {
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Consumo consumo;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Fatura fatura;

	public Consumo getConsumo() {
		return consumo;
	}

	public void setConsumo(Consumo consumo) {
		this.consumo = consumo;
	}

	public Fatura getFatura() {
		return fatura;
	}

	public void setFatura(Fatura fatura) {
		this.fatura = fatura;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FaturaConsumoId other = (FaturaConsumoId) obj;
		if (consumo == null) {
			if (other.consumo != null)
				return false;
		} else if (!(consumo.getId() == other.consumo.getId()))
			return false;
		if (fatura == null) {
			if (other.fatura != null)
				return false;
		} else if (!(fatura.getId() == other.fatura.getId()))
			return false;
		return true;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((consumo == null) ? 0 : consumo.hashCode());
		result = prime * result + ((fatura == null) ? 0 : fatura.hashCode());
		return result;
	}
	
}
