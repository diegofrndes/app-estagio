package br.com.taldi.usuario.cliente;

import java.util.List;

import br.com.taldi.usina.GeracaoSolar;
import br.com.taldi.usina.UsinaSolar;
import java.math.BigDecimal;

public class DadosUsina {
	private UsinaSolar usinaSolar;
	private List<GeracaoSolar> geracaoSolar;
	private BigDecimal prognostico;
	private BigDecimal prognosticoAnterior;
	private BigDecimal mediaTarifa;
	
	public UsinaSolar getUsinaSolar() {
		return usinaSolar;
	}
	
	public void setUsinaSolar(UsinaSolar usinaSolar) {
		this.usinaSolar = usinaSolar;
	}
	
	public List<GeracaoSolar> getGeracaoSolar() {
		return geracaoSolar;
	}
	
	public void setGeracaoSolar(List<GeracaoSolar> geracaoSolar) {
		this.geracaoSolar = geracaoSolar;
	}

	public BigDecimal getPrognostico() {
		return prognostico;
	}

	public void setPrognostico(BigDecimal prognostico) {
		this.prognostico = prognostico;
	}
	public BigDecimal getPrognosticoAnterior() {
		return prognosticoAnterior;
	}
	public void setPrognosticoAnterior(BigDecimal prognosticoAnterior) {
		this.prognosticoAnterior = prognosticoAnterior;
	}

	public BigDecimal getMediaTarifa() {
		return mediaTarifa;
	}

	public void setMediaTarifa(BigDecimal mediaTarifa) {
		this.mediaTarifa = mediaTarifa;
	} 
}
