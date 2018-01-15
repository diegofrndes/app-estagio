package br.com.taldi.usuario.cliente;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.taldi.aneel.Tarifa;
import br.com.taldi.aneel.TarifaRepository;
import br.com.taldi.uconsumidora.Fatura;
import br.com.taldi.uconsumidora.FaturaRepository;
import br.com.taldi.uconsumidora.UnidadeConsumidora;
import br.com.taldi.uconsumidora.UnidadeConsumidoraRepository;
import br.com.taldi.usina.DemonstrativoSolar;
import br.com.taldi.usina.DemonstrativoSolarDTO;
import br.com.taldi.usina.DemonstrativoSolarRepository;
import br.com.taldi.usina.GeracaoSolarRepository;

@Service
public class ClienteService {
	
	@Autowired
	public DemonstrativoSolarRepository demonstrativoSolarRepository;
	@Autowired
	public GeracaoSolarRepository geracaoSolarRepository;
	@Autowired
	public FaturaRepository faturaRepository;
	@Autowired
	public TarifaRepository tarifaRepository;
	@Autowired
	public UnidadeConsumidoraRepository unidadeConsumidoraRepository;
	
	
	public List<DadosTabelaRelatorios> getDadosTabelaRelatorios(long idUsuario){
		List<DadosTabelaRelatorios> dadosTabelaRelatorios = new ArrayList<DadosTabelaRelatorios>();
		
		List<DemonstrativoSolar> demonstrativosSolar = demonstrativoSolarRepository.findDemonstrativoSolarByUsuarioId(idUsuario);
		for(int index = 0; index < demonstrativosSolar.size(); index++) {
			DadosTabelaRelatorios data = new DadosTabelaRelatorios();
			data.setEconomia(new BigDecimal(0));
			data.setFaturado(new BigDecimal(0));
			
			data.setMesAno(demonstrativosSolar.get(index).getCicloMes());
			
			List<DemonstrativoSolarDTO> demonstrativoSolarDTO = demonstrativoSolarRepository.findDemonstrativoSolarDTOSByUsuarioAndMesAno(idUsuario,
					data.getMesAno());
			
			for(int i = 0; i < demonstrativoSolarDTO.size(); i++) {
				DemonstrativoSolarDTO d = demonstrativoSolarDTO.get(i);
				d.setGeracaoUnidadeConsumidoraKWH(geracaoSolarRepository.getGeracaoByIdUnidadeConsumidoraAndCiclo(d.getUnidadeConsumidora().getId(),
						d.getDemonstrativoSolar().getCicloInicio(), d.getDemonstrativoSolar().getCicloFim()));
				if(d.getGeracaoUnidadeConsumidoraKWH() == null)
					d.setGeracaoUnidadeConsumidoraKWH(new BigDecimal(0));
				Fatura fatura = faturaRepository.findByUnidadeConsumidoraIdAndMesAno(d.getUnidadeConsumidora().getId(), data.getMesAno());
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
				data.setEconomia(data.getEconomia().add(d.getEconomiaUnidadeConsumidoraRS()));
				data.setFaturado(data.getFaturado().add(d.getConsumoUnidadeConsumidoraRS().subtract(d.getEconomiaUnidadeConsumidoraRS())));				
			}
			dadosTabelaRelatorios.add(data);			
		}
		
		return dadosTabelaRelatorios;
	}
	
	public List<DadosTabelaFaturas> getDadosTabelaFaturas(long idUsuario){
		List<DadosTabelaFaturas> dadosTabelaFaturas = new ArrayList<DadosTabelaFaturas>();
		
		List<Fatura> faturas = faturaRepository.findByUsuarioId(idUsuario);
		for(int index = 0; index < faturas.size(); index++) {
			DadosTabelaFaturas data = new DadosTabelaFaturas();
			data.setFatura(faturas.get(index));
			data.setValor(faturaRepository.getValorConsumoByFaturaId(faturas.get(index).getId()).add(faturaRepository.getValorOutroByFaturaId(faturas.get(index).getId())));
			dadosTabelaFaturas.add(data);
		}
			
		return dadosTabelaFaturas;
	}

	public List<DadosTabelaUnidadesConsumidoras> getDadosTabelaUnidadesConsumidoras(long idUsuario){
		List<DadosTabelaUnidadesConsumidoras> dadosTabelaUnidadesConsumidoras = new ArrayList<DadosTabelaUnidadesConsumidoras>();
		List<UnidadeConsumidora> unidadesConsumidoras = unidadeConsumidoraRepository.findByUsuarioId(idUsuario);
		for(int index = 0; index < unidadesConsumidoras.size(); index++) {
			DadosTabelaUnidadesConsumidoras data = new DadosTabelaUnidadesConsumidoras();
			data.setUnidadeConsumidora(unidadesConsumidoras.get(index));
			dadosTabelaUnidadesConsumidoras.add(data);
		}
			
		return dadosTabelaUnidadesConsumidoras;
	}
	
}
