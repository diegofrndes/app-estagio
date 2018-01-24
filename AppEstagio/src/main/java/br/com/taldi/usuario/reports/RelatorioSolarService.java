package br.com.taldi.usuario.reports;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.taldi.usina.DemonstrativoSolarRepository;
import br.com.taldi.usina.IrradiacaoSolar;
import br.com.taldi.usina.IrradiacaoSolarRepository;
import br.com.taldi.usina.UsinaSolar;
import br.com.taldi.usina.UsinaSolarRepository;

@Service
public class RelatorioSolarService {

	@Autowired
	private UsinaSolarRepository usinaSolarRepository;
	@Autowired
	private DemonstrativoSolarRepository demonstrativoSolarRepository;
	@Autowired
	private IrradiacaoSolarRepository irradiacaoSolarRepository;
	
	public BigDecimal getNumeroDiasCiclo(Date cicloInicio, Date cicloFim) {
		return new BigDecimal(Days.daysBetween(new LocalDate(cicloInicio.getTime()), new LocalDate(cicloFim.getTime())).getDays()).add(new BigDecimal("1"));
	}
	
	public BigDecimal getPrognosticoByUsuarioId(long idUsuario, Date cicloMes) {
		BigDecimal prognostico = new BigDecimal(0);
		List<UsinaSolar> usinas = usinaSolarRepository.findUsinaSolarByUsuarioId(idUsuario);
		
		for(int i = 0; i < usinas.size(); i++) {
			BigDecimal irradiacao = new BigDecimal(5.6);
			IrradiacaoSolar ir = irradiacaoSolarRepository.findIrradiacaoSolarByMes(cicloMes, usinas.get(i).getUnidadeConsumidora().getEndereco().getCidade().getId());
			if(ir != null)
				irradiacao = ir.getValor();
			//System.out.println(irradiacao);
			prognostico = prognostico.add(usinas.get(i).getPerformance().multiply(usinas.get(i).getPotencia()).multiply(irradiacao));
		}
		//usinas.forEach(usina->prognostico.add(usina.getEnergiaAno()));
		return prognostico;
		
	}
	
	public BigDecimal getCreditoAcumuladoByUsuarioId(long idUsuario, Date mesAno) {
		return demonstrativoSolarRepository.getSumCreditoByUsuario(idUsuario, mesAno); 
	}
	
}
