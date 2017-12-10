package br.com.taldi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import br.com.taldi.armazenamento.ArmazenamentoProperties;
import br.com.taldi.armazenamento.ArmazenamentoService;

@SpringBootApplication
@EnableConfigurationProperties(ArmazenamentoProperties.class)
public class AppEstagio {

	public static void main(String[] args) {
		SpringApplication.run(AppEstagio.class, args);
	}

	@Bean
	CommandLineRunner init(ArmazenamentoService armazenamentoService) {
		return (args) -> {
			//armazenamentoService.deleteAll();
			armazenamentoService.init();
		};
	}
}