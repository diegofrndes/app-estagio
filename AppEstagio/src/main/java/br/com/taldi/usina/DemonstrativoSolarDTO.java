package br.com.taldi.usina;

import java.math.BigDecimal;

import br.com.taldi.uconsumidora.UnidadeConsumidora;

public class DemonstrativoSolarDTO {
	private UnidadeConsumidora unidadeConsumidora;
	private DemonstrativoSolar demonstrativoSolar;
	private BigDecimal geracaoUnidadeConsumidora;
	private BigDecimal valorFaturaUnidadeConsumidora;
	
	public DemonstrativoSolarDTO(UnidadeConsumidora unidadeConsumidora, DemonstrativoSolar demonstrativoSolar) {
		this.setUnidadeConsumidora(unidadeConsumidora);
		this.setDemonstrativoSolar(demonstrativoSolar);
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

	public BigDecimal getGeracaoUnidadeConsumidora() {
		return geracaoUnidadeConsumidora;
	}

	public void setGeracaoUnidadeConsumidora(BigDecimal geracaoUnidadeConsumidora) {
		this.geracaoUnidadeConsumidora = geracaoUnidadeConsumidora;
	}

	public BigDecimal getValorFaturaUnidadeConsumidora() {
		return valorFaturaUnidadeConsumidora;
	}

	public void setValorFaturaUnidadeConsumidora(BigDecimal valorFaturaUnidadeConsumidora) {
		this.valorFaturaUnidadeConsumidora = valorFaturaUnidadeConsumidora;
	}

}
