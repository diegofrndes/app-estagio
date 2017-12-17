package br.com.taldi.reports;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
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
import br.com.taldi.uconsumidora.UnidadeConsumidoraService;
import br.com.taldi.usuario.Usuario;
import br.com.taldi.usuario.UsuarioService;
import net.sf.jasperreports.engine.JasperReport;

@Controller
@RequestMapping("**/solar")
public class RelatorioSolarController {
	
	private Usuario usuario;
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private UnidadeConsumidoraService unidadeConsumidoraService;
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
        Map<String, Object> customSubReportUrls = new HashMap<String, Object>();
    	customSubReportUrls.put("JasperGeracaoDiariaReportLocation", "classpath:jasperreports/reports/geracao_diaria.jrxml");
    	//Properties subReportsUrls = new Properties();
    	//subReportsUrls.putAll(customSubReportUrls);
    	//view.setSubReportUrls(subReportsUrls);
    	//view.setJdbcDataSource(jdbcTemplate.getDataSource());
        Map<String, Object> params = new HashMap<>();
        usuario = usuarioService.getUsuario(id);
        System.out.println(mes.toString());
        params.put("IdUsuario", id);
        params.put("Mes", mes);
        params.put("LogoPath", Paths.get(armazenamentoProperties.getLocation()).toString() + "/usuarios/" + Long.toString(usuario.getId()) + "/logo/logo.png");
        params.put("Nome", usuario.getPessoa().getNome());
        if(usuario.getPessoa() instanceof PessoaFisica)
            params.put("Documento", "CPF " + ((PessoaFisica) usuario.getPessoa()).getCpf());
        else if(usuario.getPessoa() instanceof PessoaJuridica)
            params.put("Documento", "CNPJ " + ((PessoaJuridica) usuario.getPessoa()).getCnpj());
        params.put("Classificacao", "B2 RURAL AGROINDUSTRIAL TRIFÁSICO");
        List<UnidadeConsumidora> ucs = unidadeConsumidoraService.getByUsuarioId(usuario.getId());
        List<RelatorioSolarBean> datasource = new ArrayList<RelatorioSolarBean>();
		for(int i = 0; i < 5; i++) {
			RelatorioSolarBean bean = new RelatorioSolarBean();
			bean.setDenominacao("SEDE AMANA");
			datasource.add(bean);
		}
        params.put("datasource", datasource);
        params.put("JasperGeracaoDiariaReportLocation", "classpath:jasperreports/reports/geracao_diaria.jasper");
        params.put("JasperConsumoUconsumidorasReportLocation", "classpath:jasperreports/reports/consumo_uconsumidoras.jasper");
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
	
	@RequestMapping(value = "/relatorio/{id}/{date}", method = RequestMethod.GET)
	public String getRelatorio(HttpServletRequest request, @PathVariable("id") long id, @PathVariable("date") String date) {
		request.setAttribute("usuarios", usuarioService.getAllUsuarios());
		return "relatorio";
	}

}
