package br.com.taldi.usina;

import java.math.BigDecimal;

import br.com.taldi.uconsumidora.UnidadeConsumidora;

public class DemonstrativoSolarDTO {
	private UnidadeConsumidora unidadeConsumidora;
	private BigDecimal energiaInjetada;
	
	public DemonstrativoSolarDTO(UnidadeConsumidora unidadeConsumidora) {
		this.setUnidadeConsumidora(unidadeConsumidora);
	}
	
	public DemonstrativoSolarDTO() {
	}

	public UnidadeConsumidora getUnidadeConsumidora() {
		return unidadeConsumidora;
	}

	public void setUnidadeConsumidora(UnidadeConsumidora unidadeConsumidora) {
		this.unidadeConsumidora = unidadeConsumidora;
	}
}
