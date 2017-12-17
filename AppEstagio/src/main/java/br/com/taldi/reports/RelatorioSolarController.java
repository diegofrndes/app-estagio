package br.com.taldi.reports;

import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.jdbc.core.JdbcTemplate;
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
import br.com.taldi.uconsumidora.UnidadeConsumidora;
import br.com.taldi.usina.DemonstrativoSolarDTO;
import br.com.taldi.usina.DemonstrativoSolarService;

@Controller
@RequestMapping("**/solar")
public class RelatorioSolarController {
	
	@Autowired
	DemonstrativoSolarService demonstrativoSolarService;
	@Autowired
	ArmazenamentoProperties armazenamentoProperties;
	
	
	@Autowired
    private ApplicationContext appContext;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@RequestMapping(path = "/pdf/{id}", method = RequestMethod.GET)
    public ModelAndView report(@PathVariable("id") long id, @RequestParam(value="mes", required=true) @DateTimeFormat(pattern="yyyy-MM-dd") Date mes) {
    	JasperReportsPdfView view = new JasperReportsPdfView();
    	view.setUrl("classpath:jasperreports/reports/solar.jrxml");
    	view.setApplicationContext(appContext);
        
    	Map<String, Object> params = new HashMap<>();
    	params.put("IdUsuario", id);
        params.put("Mes", mes);
        params.put("LogoPath", Paths.get(armazenamentoProperties.getLocation()).toString() + "/usuarios/" + Long.toString(id) + "/logo/logo.png");
        List<DemonstrativoSolarDTO> datasource = new ArrayList<DemonstrativoSolarDTO>();
    	datasource = demonstrativoSolarService.getUCBeneficiadaSolarByUsuarioAndMesAno(id, mes);
        if(datasource != null) {
        	while(datasource.size() < 5) {
    			DemonstrativoSolarDTO vazio = new DemonstrativoSolarDTO();
    			UnidadeConsumidora ucVazia = new UnidadeConsumidora();
    			ucVazia.setDenominacao("-");
    			vazio.setUnidadeConsumidora(ucVazia);
    			datasource.add(vazio);
    		}
    		params.put("Nome", datasource.get(0).getUnidadeConsumidora().getUsuario().getPessoa().getNome());
    		if(datasource.get(0).getUnidadeConsumidora().getUsuario().getPessoa() instanceof PessoaFisica)
                params.put("Documento", "CPF " + ((PessoaFisica) datasource.get(0).getUnidadeConsumidora().getUsuario().getPessoa()).getCpf());
            else if(datasource.get(0).getUnidadeConsumidora().getUsuario().getPessoa() instanceof PessoaJuridica)
                params.put("Documento", "CNPJ " + ((PessoaJuridica) datasource.get(0).getUnidadeConsumidora().getUsuario().getPessoa()).getCnpj());
    		params.put("Classificacao", datasource.get(0).getUnidadeConsumidora().getClassificacao().toString() +
    				" " + datasource.get(0).getUnidadeConsumidora().getLigacao().getNome());
        }
        params.put("datasource", datasource);
        params.put("JasperGeracaoDiariaReportLocation", "classpath:jasperreports/reports/geracao_solar_mensal.jasper");
        params.put("JasperConsumoUconsumidorasReportLocation", "classpath:jasperreports/reports/consumo_uconsumidoras.jasper");
        
    	//if(usuario.getPessoa() instanceof PessoaFisica)
        //    params.put("Documento", "CPF " + ((PessoaFisica) usuario.getPessoa()).getCpf());
        //else if(usuario.getPessoa() instanceof PessoaJuridica)
        //    params.put("Documento", "CNPJ " + ((PessoaJuridica) usuario.getPessoa()).getCnpj());
        try {
			params.put("REPORT_CONNECTION", jdbcTemplate.getDataSource().getConnection());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //params.put("GeracaoDiariaDataSource", jdbcTemplate.getDataSource());
        //params.put("JasperCustomSubReportDatasource", jdbcTemplate.getDataSource());
        return new ModelAndView(view, params);
    }
	
}
