package br.com.taldi;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import br.com.taldi.aneel.Consumo;
import br.com.taldi.aneel.Outro;
import br.com.taldi.armazenamento.ArmazenamentoProperties;
import br.com.taldi.armazenamento.ArmazenamentoService;
import br.com.taldi.uconsumidora.Fatura;
import br.com.taldi.uconsumidora.FaturaConsumo;
import br.com.taldi.uconsumidora.FaturaOutro;
import br.com.taldi.uconsumidora.FaturaRepository;
import br.com.taldi.uconsumidora.UnidadeConsumidora;

@SpringBootApplication
@EnableConfigurationProperties(ArmazenamentoProperties.class)
public class AppEstagio {

	public static void main(String[] args) {
		SpringApplication.run(AppEstagio.class, args);
	}

	@Bean
	CommandLineRunner init(ArmazenamentoService armazenamentoService, FaturaRepository repository) {
		return (args) -> {
			Fatura outubro = new Fatura();
			Fatura novembro = new Fatura();
			UnidadeConsumidora sedeAmana = new UnidadeConsumidora();
			sedeAmana.setId(10);
			BigDecimal quantidadeAtivoOutubro = new BigDecimal(2754.75);
			BigDecimal valorAtivoOutubro = new BigDecimal(1064.07);
			BigDecimal quantidadeReativoOutubro = new BigDecimal(196.80);
			BigDecimal valorReativoOutubro = new BigDecimal(57.88);
			BigDecimal quantidadeAtivoNovembro = new BigDecimal(3012.24);
			BigDecimal valorAtivoNovembro = new BigDecimal(1297.58);
			BigDecimal quantidadeReativoNovembro = new BigDecimal(816.93);
			BigDecimal valorReativoNovembro = new BigDecimal(234.84);
			Consumo consumoAtivoOutubro = new Consumo();
			consumoAtivoOutubro.setId(1);
			Consumo consumoReativoOutubro = new Consumo();
			consumoReativoOutubro.setId(2);
			Consumo consumoAtivoNovembro = new Consumo();
			consumoAtivoNovembro.setId(1);
			Consumo consumoReativoNovembro = new Consumo();
			consumoReativoNovembro.setId(2);
			
			Outro bandeiraAmarelaOutubro = new Outro();
			bandeiraAmarelaOutubro.setId(1);
			Outro bandeiraVermelhaOutubro = new Outro();
			bandeiraVermelhaOutubro.setId(2);
			Outro cobrancaOutubro = new Outro();
			cobrancaOutubro.setId(3);
			Outro cobrancaNovembro = new Outro();
			cobrancaNovembro.setId(3);
			
			FaturaOutro faturaBandeiraAmarelaOutubro = new FaturaOutro();
			faturaBandeiraAmarelaOutubro.setFatura(outubro);
			faturaBandeiraAmarelaOutubro.setOutro(bandeiraAmarelaOutubro);
			faturaBandeiraAmarelaOutubro.setValor(new BigDecimal(49.58));
			FaturaOutro faturaBandeiraVermelhaOutubro = new FaturaOutro();
			faturaBandeiraVermelhaOutubro.setFatura(outubro);
			faturaBandeiraVermelhaOutubro.setOutro(bandeiraVermelhaOutubro);
			faturaBandeiraVermelhaOutubro.setValor(new BigDecimal(36.42));
			FaturaOutro faturaCobrancaOutubro = new FaturaOutro();
			faturaCobrancaOutubro.setFatura(outubro);
			faturaCobrancaOutubro.setOutro(cobrancaOutubro);
			faturaCobrancaOutubro.setValor(new BigDecimal(82.08));
			FaturaOutro faturaCobrancaNovembro = new FaturaOutro();
			faturaCobrancaNovembro.setFatura(novembro);
			faturaCobrancaNovembro.setOutro(cobrancaNovembro);
			faturaCobrancaNovembro.setValor(new BigDecimal(87.72));
			
			List<FaturaOutro> faturaOutrosOutubro = new ArrayList<>();
			List<FaturaOutro> faturaOutrosNovembro = new ArrayList<>();
			faturaOutrosOutubro.add(faturaBandeiraAmarelaOutubro);
			faturaOutrosOutubro.add(faturaBandeiraVermelhaOutubro);
			faturaOutrosOutubro.add(faturaCobrancaOutubro);
			faturaOutrosNovembro.add(faturaCobrancaNovembro);
			
			FaturaConsumo faturaConsumoAtivoOutubro = new FaturaConsumo();
			FaturaConsumo faturaConsumoReativoOutubro = new FaturaConsumo();
			FaturaConsumo faturaConsumoAtivoNovembro = new FaturaConsumo();
			FaturaConsumo faturaConsumoReativoNovembro = new FaturaConsumo();
			
			faturaConsumoAtivoOutubro.setFatura(outubro);
			faturaConsumoReativoOutubro.setFatura(outubro);
			faturaConsumoAtivoNovembro.setFatura(novembro);
			faturaConsumoReativoNovembro.setFatura(novembro);
			
		
			faturaConsumoAtivoOutubro.setConsumo(consumoAtivoOutubro);
			faturaConsumoAtivoOutubro.setQuantidade(quantidadeAtivoOutubro);
			faturaConsumoAtivoOutubro.setValor(valorAtivoOutubro);
			faturaConsumoReativoOutubro.setConsumo(consumoReativoOutubro);
			faturaConsumoReativoOutubro.setQuantidade(quantidadeReativoOutubro);
			faturaConsumoReativoOutubro.setValor(valorReativoOutubro);
			faturaConsumoAtivoNovembro.setConsumo(consumoAtivoNovembro);
			faturaConsumoAtivoNovembro.setQuantidade(quantidadeAtivoNovembro);
			faturaConsumoAtivoNovembro.setValor(valorAtivoNovembro);
			faturaConsumoReativoNovembro.setConsumo(consumoReativoNovembro);
			faturaConsumoReativoNovembro.setQuantidade(quantidadeReativoNovembro);
			faturaConsumoReativoNovembro.setValor(valorReativoNovembro);
			
			List<FaturaConsumo> faturaConsumosOutubro = new ArrayList<>();
			List<FaturaConsumo> faturaConsumosNovembro = new ArrayList<>();
			faturaConsumosOutubro.add(faturaConsumoAtivoOutubro);
			faturaConsumosOutubro.add(faturaConsumoReativoOutubro);
			faturaConsumosNovembro.add(faturaConsumoAtivoNovembro);
			faturaConsumosNovembro.add(faturaConsumoReativoNovembro);

			outubro.setConsumos(faturaConsumosOutubro);
			outubro.setOutros(faturaOutrosOutubro);
			outubro.setUnidadeConsumidora(sedeAmana);
			novembro.setConsumos(faturaConsumosNovembro);
			novembro.setOutros(faturaOutrosNovembro);
			novembro.setUnidadeConsumidora(sedeAmana);
			
			Date mesOutubro = new GregorianCalendar(2017, Calendar.OCTOBER, 00).getTime();
			Date mesNovembro = new GregorianCalendar(2017, Calendar.NOVEMBER, 00).getTime();
			Date vencimentoOutubro = new GregorianCalendar(2017, Calendar.NOVEMBER, 1).getTime();
			Date vencimentoNovembro = new GregorianCalendar(2017, Calendar.DECEMBER, 04).getTime();
			outubro.setDataVencimento(vencimentoOutubro);
			outubro.setMesAno(mesOutubro);
			novembro.setMesAno(mesNovembro);
			novembro.setDataVencimento(vencimentoNovembro);
			//repository.save(outubro);
			//repository.save(novembro);
			//armazenamentoService.deleteAll();
			//armazenamentoService.init();
		};
	}
}