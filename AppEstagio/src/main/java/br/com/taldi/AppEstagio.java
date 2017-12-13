package br.com.taldi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import br.com.taldi.armazenamento.ArmazenamentoProperties;
import br.com.taldi.armazenamento.ArmazenamentoService;
import br.com.taldi.uconsumidora.FaturaRepository;

@SpringBootApplication
@EnableConfigurationProperties(ArmazenamentoProperties.class)
public class AppEstagio {

	public static void main(String[] args) {
		SpringApplication.run(AppEstagio.class, args);
	}

	@Bean
	CommandLineRunner init(ArmazenamentoService armazenamentoService, FaturaRepository repository) {
		return (args) -> {
			/*Fatura novembro = new Fatura();
			UnidadeConsumidora uc = new UnidadeConsumidora();
			uc.setId(1);
			BigDecimal quantidade = new BigDecimal(103);
			BigDecimal valor = new BigDecimal(51);
			Consumo consumoAtivo = new Consumo();
			consumoAtivo.setId(1);
			List<FaturaConsumo> faturaConsumos = new ArrayList<>();
			FaturaConsumo faturaConsumo = new FaturaConsumo();
			faturaConsumo.setFatura(novembro);
			faturaConsumo.setConsumo(consumoAtivo);
			faturaConsumo.setQuantidade(quantidade);
			faturaConsumo.setValor(valor);
			faturaConsumos.add(faturaConsumo);
			novembro.setFaturaConsumos(faturaConsumos);
			novembro.setUnidadeConsumidora(uc);
			Date mes = new GregorianCalendar(2017, Calendar.NOVEMBER, 00).getTime();
			Date inicio = new GregorianCalendar(2017, Calendar.OCTOBER, 15).getTime();
			Date fim = new GregorianCalendar(2017, Calendar.NOVEMBER, 20).getTime();
			novembro.setCicloMes(mes);
			novembro.setCicloInicio(inicio);
			novembro.setCicloFim(fim);
			repository.save(novembro);*/
						//armazenamentoService.deleteAll();
			//armazenamentoService.init();
		};
	}
}