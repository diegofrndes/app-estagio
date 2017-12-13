package br.com.taldi.usina;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsPdfView;

import br.com.taldi.armazenamento.ArmazenamentoProperties;
import br.com.taldi.pessoa.fisica.PessoaFisica;
import br.com.taldi.pessoa.juridica.PessoaJuridica;
import br.com.taldi.uconsumidora.UnidadeConsumidora;
import br.com.taldi.uconsumidora.UnidadeConsumidoraService;
import br.com.taldi.usuario.Usuario;
import br.com.taldi.usuario.UsuarioService;

@Controller
@RequestMapping("/solar")
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

	@RequestMapping(path = "/pdf/{id}", method = RequestMethod.GET)
    public ModelAndView report(@PathVariable("id") long id) {
    	JasperReportsPdfView view = new JasperReportsPdfView();
    	view.setUrl("classpath:jasperreports/reports/solar.jrxml");
        view.setApplicationContext(appContext);
        Map<String, Object> params = new HashMap<>();
        usuario = usuarioService.getUsuario(id);
        params.put("LogoPath", Paths.get(armazenamentoProperties.getLocation()).toString() + "/usuarios/" + Long.toString(usuario.getId()) + "/logo/logo.png");
        params.put("Nome", usuario.getPessoa().getNome());
        if(usuario.getPessoa() instanceof PessoaFisica)
            params.put("Documento", "CPF " + ((PessoaFisica) usuario.getPessoa()).getCpf());
        else if(usuario.getPessoa() instanceof PessoaJuridica)
            params.put("Documento", "CNPJ " + ((PessoaJuridica) usuario.getPessoa()).getCnpj());
        params.put("Classificacao", "B2 RESIDENCIAL TRIFÁSICO");
        List<UnidadeConsumidora> ucs = unidadeConsumidoraService.getByUsuarioId(usuario.getId());
        params.put("datasource", ucs);
        
        return new ModelAndView(view, params);
    }
	
	@RequestMapping(value = "/relatorio/{id}/{date}", method = RequestMethod.GET)
	public String getRelatorio(HttpServletRequest request, @PathVariable("id") long id, @PathVariable("date") String date) {
		request.setAttribute("usuarios", usuarioService.getAllUsuarios());
		return "relatorio";
	}

}
