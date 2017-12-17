package br.com.taldi.uconsumidora;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import br.com.taldi.aneel.Outro;

@SuppressWarnings("serial")
@Embeddable
public class FaturaOutroId implements Serializable {
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Outro outro;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Fatura fatura;

	public Outro getOutro() {
		return outro;
	}

	public void setOutro(Outro outro) {
		this.outro = outro;
	}

	public Fatura getFatura() {
		return fatura;
	}

	public void setFatura(Fatura fatura) {
		this.fatura = fatura;
	}
	
}
