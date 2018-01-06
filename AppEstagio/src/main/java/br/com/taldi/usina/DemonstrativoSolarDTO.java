package br.com.taldi.usina;

import java.math.BigDecimal;

import br.com.taldi.uconsumidora.UnidadeConsumidora;

public class DemonstrativoSolarDTO {
	private UnidadeConsumidora unidadeConsumidora;
	private DemonstrativoSolar demonstrativoSolar;
	private BigDecimal geracaoUnidadeConsumidoraKWH;
	private BigDecimal consumoInstantaneoUnidadeConsumidoraKWH;
	private BigDecimal economiaUnidadeConsumidoraRS;
	private BigDecimal consumoUnidadeConsumidoraRS;
	private BigDecimal valorFaturaUnidadeConsumidora;
	
	public DemonstrativoSolarDTO(UnidadeConsumidora unidadeConsumidora, DemonstrativoSolar demonstrativoSolar) {
		this.setUnidadeConsumidora(unidadeConsumidora);
		this.setDemonstrativoSolar(demonstrativoSolar);
		consumoInstantaneoUnidadeConsumidoraKWH = new BigDecimal(0.00f);
	}
	
	public DemonstrativoSolarDTO() {
	}

	public UnidadeConsumidora getUnidadeConsumidora() {
		return unidadeConsumidora;
	}

	public void setUnidadeConsumidora(UnidadeConsumidora unidadeConsumidora) {
		this.unidadeConsumidora = unidadeConsumidora;
	}

	public DemonstrativoSolar getDemonstrativoSolar() {
		return demonstrativoSolar;
	}

	public void setDemonstrativoSolar(DemonstrativoSolar demonstrativoSolar) {
		this.demonstrativoSolar = demonstrativoSolar;
	}

	public BigDecimal getGeracaoUnidadeConsumidoraKWH() {
		return geracaoUnidadeConsumidoraKWH;
	}

	public void setGeracaoUnidadeConsumidoraKWH(BigDecimal geracaoUnidadeConsumidoraKWH) {
		this.geracaoUnidadeConsumidoraKWH = geracaoUnidadeConsumidoraKWH;
	}

	public BigDecimal getConsumoInstantaneoUnidadeConsumidoraKWH() {
		return consumoInstantaneoUnidadeConsumidoraKWH;
	}

	public void setConsumoInstantaneoUnidadeConsumidoraKWH(BigDecimal consumoInstantaneoUnidadeConsumidoraKWH) {
		this.consumoInstantaneoUnidadeConsumidoraKWH = consumoInstantaneoUnidadeConsumidoraKWH;
	}

	public BigDecimal getEconomiaUnidadeConsumidoraRS() {
		return economiaUnidadeConsumidoraRS;
	}

	public void setEconomiaUnidadeConsumidoraRS(BigDecimal economiaUnidadeConsumidoraRS) {
		this.economiaUnidadeConsumidoraRS = economiaUnidadeConsumidoraRS;
	}

	public BigDecimal getConsumoUnidadeConsumidoraRS() {
		return consumoUnidadeConsumidoraRS;
	}

	public void setConsumoUnidadeConsumidoraRS(BigDecimal consumoUnidadeConsumidoraRS) {
		this.consumoUnidadeConsumidoraRS = consumoUnidadeConsumidoraRS;
	}

	public BigDecimal getValorFaturaUnidadeConsumidora() {
		return valorFaturaUnidadeConsumidora;
	}

	public void setValorFaturaUnidadeConsumidora(BigDecimal valorFaturaUnidadeConsumidora) {
		this.valorFaturaUnidadeConsumidora = valorFaturaUnidadeConsumidora;
	}
	
}
