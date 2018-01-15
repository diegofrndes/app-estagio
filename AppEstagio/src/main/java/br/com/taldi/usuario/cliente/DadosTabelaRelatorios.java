package br.com.taldi.usuario.cliente;

import java.math.BigDecimal;
import java.util.Date;

public class DadosTabelaRelatorios {
	private Date mesAno;
	private BigDecimal faturado;
	private BigDecimal economia;
	
	public Date getMesAno() {
		return mesAno;
	}
	public void setMesAno(Date mesAno) {
		this.mesAno = mesAno;
	}
	public BigDecimal getFaturado() {
		return faturado;
	}
	public void setFaturado(BigDecimal faturado) {
		this.faturado = faturado;
	}
	public BigDecimal getEconomia() {
		return economia;
	}
	public void setEconomia(BigDecimal economia) {
		this.economia = economia;
	}
	
}
