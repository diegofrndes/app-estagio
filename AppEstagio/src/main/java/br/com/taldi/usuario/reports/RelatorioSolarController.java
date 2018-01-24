package br.com.taldi.usuario.reports;

import java.nio.file.Paths;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsPdfView;

import br.com.taldi.armazenamento.ArmazenamentoProperties;
import br.com.taldi.pessoa.fisica.PessoaFisica;
import br.com.taldi.pessoa.juridica.PessoaJuridica;
import br.com.taldi.usina.DemonstrativoSolarDTO;
import br.com.taldi.usina.DemonstrativoSolarService;
import br.com.taldi.usuario.UsuarioPrincipal;

@Controller
@RequestMapping("/usuario/protected/**/solar")
public class RelatorioSolarController {

	private Logger logger = LogManager.getLogger(RelatorioSolarController.class);

	@Autowired
	private DemonstrativoSolarService demonstrativoSolarService;
	@Autowired
	private ArmazenamentoProperties armazenamentoProperties;
	@Autowired
	private RelatorioSolarService relatorioSolarService;

	@Autowired
	private ApplicationContext appContext;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@RequestMapping(path = "/pdf/{arquivo}", method = RequestMethod.GET)
	public ModelAndView report(@PathVariable("arquivo") String arquivo,
			@RequestParam(value = "mes", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") Date mes,
			@AuthenticationPrincipal UserDetails userDetails) {
		JasperReportsPdfView view = new JasperReportsPdfView();
		Map<String, Object> params = new HashMap<>();
		try {
			UsuarioPrincipal usuarioPrincipal = (UsuarioPrincipal) userDetails;		
			SimpleDateFormat ft = new SimpleDateFormat ("MMMM/yyyy");
			logger.info(usuarioPrincipal.getUsername() + " gerando relatório de " + ft.format(mes) + ".");			
			view.setUrl("classpath:jasperreports/reports/solar/solar.jasper");
			view.setApplicationContext(appContext);
			params.put("IdUsuario", usuarioPrincipal.getUsuario().getId());
			params.put("Mes", mes);			
			params.put("LogoPath", Paths.get(armazenamentoProperties.getLocation()).toString() + "/usuarios/"
					+ Long.toString(usuarioPrincipal.getUsuario().getId()) + "/logo/logo.png");
			List<DemonstrativoSolarDTO> datasource = new ArrayList<DemonstrativoSolarDTO>();
			datasource = demonstrativoSolarService.getDemonstrativoSolarDTOSByUsuarioAndMesAno(usuarioPrincipal.getUsuario().getId(), mes);
			if (datasource != null) {
				params.put("Nome", datasource.get(0).getUnidadeConsumidora().getUsuario().getPessoa().getNome());
				if (datasource.get(0).getUnidadeConsumidora().getUsuario().getPessoa() instanceof PessoaFisica)
					params.put("Documento",
							"CPF " + ((PessoaFisica) datasource.get(0).getUnidadeConsumidora().getUsuario().getPessoa())
									.getCpf());
				else if (datasource.get(0).getUnidadeConsumidora().getUsuario().getPessoa() instanceof PessoaJuridica)
					params.put("Documento", "CNPJ "
							+ ((PessoaJuridica) datasource.get(0).getUnidadeConsumidora().getUsuario().getPessoa())
									.getCnpj());
				params.put("Classificacao", datasource.get(0).getUnidadeConsumidora().getClassificacao().toString()
						+ " " + datasource.get(0).getUnidadeConsumidora().getLigacao().getNome());
				params.put("LogradouroBairro", datasource.get(0).getUnidadeConsumidora().getEndereco().getLogradouro()
						+ ", " + datasource.get(0).getUnidadeConsumidora().getEndereco().getBairro());
				params.put("CepCidade", "CEP - " + datasource.get(0).getUnidadeConsumidora().getEndereco().getCep()
						+ " - " + datasource.get(0).getUnidadeConsumidora().getEndereco().getCidade().getNome() + " - "
						+ datasource.get(0).getUnidadeConsumidora().getEndereco().getCidade().getEstado().getUf());
				params.put("Email", datasource.get(0).getUnidadeConsumidora().getUsuario().getEmail());
				params.put("CicloInicio", datasource.get(0).getDemonstrativoSolar().getCicloInicio());
				params.put("CicloFim", datasource.get(0).getDemonstrativoSolar().getCicloFim());
				
				params.put("GeracaoContratadaDia", relatorioSolarService.getPrognosticoByUsuarioId(usuarioPrincipal.getUsuario().getId(), datasource.get(0).getDemonstrativoSolar().getCicloInicio()));
				params.put("Prognostico", relatorioSolarService.getPrognosticoByUsuarioId(usuarioPrincipal.getUsuario().getId(), datasource.get(0).getDemonstrativoSolar().getCicloInicio()).multiply(relatorioSolarService.getNumeroDiasCiclo(
						datasource.get(0).getDemonstrativoSolar().getCicloInicio(),
						datasource.get(0).getDemonstrativoSolar().getCicloFim())));
	
				params.put("CreditoAcumulado", relatorioSolarService.getCreditoAcumuladoByUsuarioId(usuarioPrincipal.getUsuario().getId(), mes));
			}
			params.put("datasource", datasource);
			params.put("JasperGeracaoDiariaReportLocation",
					"classpath:jasperreports/reports/solar/geracao_solar_mensal.jasper");

			// params.put("DominioGrafico", dominioGrafico);
			// if(usuario.getPessoa() instanceof PessoaFisica)
			// params.put("Documento", "CPF " + ((PessoaFisica)
			// usuario.getPessoa()).getCpf());
			// else if(usuario.getPessoa() instanceof PessoaJuridica)
			// params.put("Documento", "CNPJ " + ((PessoaJuridica)
			// usuario.getPessoa()).getCnpj());
			params.put("REPORT_CONNECTION", jdbcTemplate.getDataSource().getConnection());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("Erro ao tentar gerar relatório mensal.", e.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("Erro ao tentar gerar relatório mensal.", e.getMessage());
		}
		
		// params.put("GeracaoDiariaDataSource", jdbcTemplate.getDataSource());
		// params.put("JasperCustomSubReportDatasource", jdbcTemplate.getDataSource());
		return new ModelAndView(view, params);
	}

}
