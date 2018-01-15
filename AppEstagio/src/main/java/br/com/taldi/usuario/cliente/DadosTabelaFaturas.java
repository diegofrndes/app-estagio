package br.com.taldi.usuario.cliente;

import java.math.BigDecimal;

import br.com.taldi.uconsumidora.Fatura;

public class DadosTabelaFaturas {
	private Fatura fatura;
	private BigDecimal valor;
	
	public Fatura getFatura() {
		return fatura;
	}
	public void setFatura(Fatura fatura) {
		this.fatura = fatura;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
}
