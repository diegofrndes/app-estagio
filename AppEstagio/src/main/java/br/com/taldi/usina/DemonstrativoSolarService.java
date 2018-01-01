package br.com.taldi.usina;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	
	public List<DemonstrativoSolarDTO> getUCBeneficiadaSolarByUsuarioAndMesAno(long idUsuario, Date mesAno) {
		List<DemonstrativoSolarDTO> demonstrativoSolarDTO = new ArrayList<>();
		demonstrativoSolarDTO = demonstrativoSolarRepository.findUCBeneficiadaSolarByUsuarioAndMesAno(idUsuario,
				mesAno);
		for(int i = 0; i < demonstrativoSolarDTO.size(); i++) {
			DemonstrativoSolarDTO d = demonstrativoSolarDTO.get(i);
			d.setGeracaoUnidadeConsumidora(geracaoSolarRepository.getGeracaoByIdUnidadeConsumidoraAndCiclo(d.getUnidadeConsumidora().getId(),
					d.getDemonstrativoSolar().getCicloInicio(), d.getDemonstrativoSolar().getCicloFim()));
			Fatura fatura = faturaRepository.findByUnidadeConsumidoraIdAndMesAno(d.getUnidadeConsumidora().getId(), mesAno);
			d.setValorFaturaUnidadeConsumidora(faturaRepository.getValorConsumoByFaturaId(fatura.getId()).add(faturaRepository.getValorOutroByFaturaId(fatura.getId())));
		}		
		while (demonstrativoSolarDTO.size() < 5) {
			DemonstrativoSolarDTO vazio = new DemonstrativoSolarDTO();
			UnidadeConsumidora ucVazia = new UnidadeConsumidora();
			//DemonstrativoSolar demonstrativoVazio = new DemonstrativoSolar();
			//demonstrativoVazio.setEnergiaRegistrada(new BigDecimal("0.00"));
			//demonstrativoVazio.setEnergiaInjetada(new BigDecimal("0.00"));
			//demonstrativoVazio.setEnergiaCompensada(new BigDecimal("0.00"));
			//demonstrativoVazio.setEnergiaFaturada(new BigDecimal("0.00"));
			ucVazia.setDenominacao("-");
			vazio.setUnidadeConsumidora(ucVazia);
			//vazio.setDemonstrativoSolar(demonstrativoVazio);
			//vazio.setGeracaoUnidadeConsumidora(new BigDecimal(0.00));
			demonstrativoSolarDTO.add(vazio);
		}

		return demonstrativoSolarDTO;
	}

}
