package br.com.taldi.usina;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import br.com.taldi.aneel.Tarifa;
import br.com.taldi.aneel.TarifaRepository;
import br.com.taldi.uconsumidora.Fatura;
import br.com.taldi.uconsumidora.FaturaRepository;
import br.com.taldi.uconsumidora.UnidadeConsumidora;

@Transactional
@Service
public class DemonstrativoSolarService {

	@Autowired
	private DemonstrativoSolarRepository demonstrativoSolarRepository;
	@Autowired
	private GeracaoSolarRepository geracaoSolarRepository;
	@Autowired
	private FaturaRepository faturaRepository;
	@Autowired
	private TarifaRepository tarifaRepository;
	
	public List<DemonstrativoSolarDTO> getDemonstrativoSolarDTOSByUsuarioAndMesAno(long idUsuario, Date mesAno) {
		List<DemonstrativoSolarDTO> demonstrativoSolarDTO = new ArrayList<>();
		demonstrativoSolarDTO = demonstrativoSolarRepository.findDemonstrativoSolarDTOSByUsuarioAndMesAno(idUsuario,
				mesAno);
		for(int i = 0; i < demonstrativoSolarDTO.size(); i++) {
			DemonstrativoSolarDTO d = demonstrativoSolarDTO.get(i);
			d.setGeracaoUnidadeConsumidoraKWH(geracaoSolarRepository.getGeracaoByIdUnidadeConsumidoraAndCiclo(d.getUnidadeConsumidora().getId(),
					d.getDemonstrativoSolar().getCicloInicio(), d.getDemonstrativoSolar().getCicloFim()));
			if(d.getGeracaoUnidadeConsumidoraKWH() == null)
				d.setGeracaoUnidadeConsumidoraKWH(new BigDecimal(0));
			Fatura fatura = faturaRepository.findByUnidadeConsumidoraIdAndMesAno(d.getUnidadeConsumidora().getId(), mesAno);
			//System.out.println(fatura.getMesAno().toString());
			BigDecimal consumoInstantaneo = d.getGeracaoUnidadeConsumidoraKWH().subtract(d.getDemonstrativoSolar().getEnergiaInjetada());
			if(consumoInstantaneo.signum() >= 0)
				d.setConsumoInstantaneoUnidadeConsumidoraKWH(consumoInstantaneo.setScale(2, BigDecimal.ROUND_HALF_EVEN));
			d.setValorFaturaUnidadeConsumidora(faturaRepository.getValorConsumoByFaturaId(fatura.getId()).add(faturaRepository.getValorOutroByFaturaId(fatura.getId())));
			//Forma antiga de calcular a economia (sem considerar as diferentes tarifas de acordo com o consumo)
			//Tarifa tarifaFaturaUnidadeConsumidora = tarifaRepository.findByClassificacaoIdAndFimVigencia(d.getUnidadeConsumidora().getClassificacao().getId(), fatura.getMesAno());
			//d.setEconomiaUnidadeConsumidoraRS(tarifaFaturaUnidadeConsumidora.getValor().multiply(d.getDemonstrativoSolar().getEnergiaRegistrada().add(d.getConsumoInstantaneoUnidadeConsumidoraKWH()).subtract(d.getDemonstrativoSolar().getEnergiaFaturada())).setScale(2, BigDecimal.ROUND_HALF_EVEN));
			//Forma nova de calcular a economia (considerando as diferentes tarifas de acordo com o consumo)
			Tarifa tarifaFaturaUnidadeConsumidoraSemUsina = tarifaRepository.findByClassificacaoIdAndFimVigenciaAndBetweenConsumoMinAndMax(d.getUnidadeConsumidora().getClassificacao().getId(), fatura.getMesAno(), d.getConsumoInstantaneoUnidadeConsumidoraKWH().add(d.getDemonstrativoSolar().getEnergiaRegistrada()));
			//System.out.println(tarifaFaturaUnidadeConsumidoraSemUsina.getValor());
			d.setEconomiaUnidadeConsumidoraRS(tarifaFaturaUnidadeConsumidoraSemUsina.getValor().multiply(d.getDemonstrativoSolar().getEnergiaRegistrada().add(d.getConsumoInstantaneoUnidadeConsumidoraKWH()).subtract(d.getDemonstrativoSolar().getEnergiaFaturada())).setScale(2, BigDecimal.ROUND_HALF_EVEN));
			d.setConsumoUnidadeConsumidoraRS(d.getEconomiaUnidadeConsumidoraRS().add(d.getValorFaturaUnidadeConsumidora()).setScale(2, BigDecimal.ROUND_HALF_EVEN));
		}
		
		while (demonstrativoSolarDTO.size() < 5) {
			DemonstrativoSolarDTO vazio = new DemonstrativoSolarDTO();
			UnidadeConsumidora ucVazia = new UnidadeConsumidora();
			//Tarifa tarifaVazia = new Tarifa();
			//DemonstrativoSolar demonstrativoVazio = new DemonstrativoSolar();
			//demonstrativoVazio.setEnergiaRegistrada(new BigDecimal("0.00"));
			//demonstrativoVazio.setEnergiaInjetada(new BigDecimal("0.00"));
			//demonstrativoVazio.setEnergiaCompensada(new BigDecimal("0.00"));
			//demonstrativoVazio.setEnergiaFaturada(new BigDecimal("0.00"));
			ucVazia.setDenominacao("-");
			vazio.setUnidadeConsumidora(ucVazia);
			//vazio.setValorFaturaUnidadeConsumidora(valorFaturaUnidadeConsumidora);
			//vazio.setDemonstrativoSolar(demonstrativoVazio);
			//vazio.setGeracaoUnidadeConsumidora(new BigDecimal(0.00));
			demonstrativoSolarDTO.add(vazio);
		}

		return demonstrativoSolarDTO;
	}

}
