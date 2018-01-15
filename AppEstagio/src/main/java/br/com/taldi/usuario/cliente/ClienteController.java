package br.com.taldi.usuario.cliente;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import br.com.taldi.usuario.UsuarioPrincipal;

@Controller
@RequestMapping("**/cliente")
public class ClienteController {
	private Logger logger = LogManager.getLogger(ClienteController.class);

	@Autowired
	private ClienteService clienteService;
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView home(@AuthenticationPrincipal UserDetails userDetails) {
		UsuarioPrincipal usuarioPrincipal = (UsuarioPrincipal) userDetails;		
		logger.info(usuarioPrincipal.getUsername() + " acessou painel de dados.");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("usuarioNome", usuarioPrincipal.getUsuario().getPessoa().getNome());
		modelAndView.setViewName("cliente");
		return modelAndView;
	}
	
	@RequestMapping(value = "/relatorios", method = RequestMethod.GET)
	public ModelAndView relatorios(@AuthenticationPrincipal UserDetails userDetails) {
		UsuarioPrincipal usuarioPrincipal = (UsuarioPrincipal) userDetails;		
		logger.info(usuarioPrincipal.getUsername() + " acessou relatórios.");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("usuarioNome", usuarioPrincipal.getUsuario().getPessoa().getNome());
		modelAndView.addObject("usuarioId", usuarioPrincipal.getUsuario().getId());
		modelAndView.addObject("usuarioLogin", usuarioPrincipal.getUsername());
		modelAndView.addObject("dadosTabelaRelatorios", clienteService.getDadosTabelaRelatorios(usuarioPrincipal.getUsuario().getId()));
		modelAndView.setViewName("relatorios");
		return modelAndView;
	}

	@RequestMapping(value = "/faturas", method = RequestMethod.GET)
	public ModelAndView faturas(@AuthenticationPrincipal UserDetails userDetails) {
		UsuarioPrincipal usuarioPrincipal = (UsuarioPrincipal) userDetails;		
		logger.info(usuarioPrincipal.getUsername() + " acessou faturas.");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("usuarioNome", usuarioPrincipal.getUsuario().getPessoa().getNome());
		modelAndView.addObject("usuarioId", usuarioPrincipal.getUsuario().getId());
		modelAndView.addObject("usuarioLogin", usuarioPrincipal.getUsername());
		modelAndView.addObject("dadosTabelaFaturas", clienteService.getDadosTabelaFaturas(usuarioPrincipal.getUsuario().getId()));
		modelAndView.setViewName("faturas");
		return modelAndView;
	}
	
	@RequestMapping(value = "/uconsumidoras", method = RequestMethod.GET)
	public ModelAndView uconsumidoras(@AuthenticationPrincipal UserDetails userDetails) {
		UsuarioPrincipal usuarioPrincipal = (UsuarioPrincipal) userDetails;		
		logger.info(usuarioPrincipal.getUsername() + " acessou unidades consumidoras.");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("usuarioNome", usuarioPrincipal.getUsuario().getPessoa().getNome());
		modelAndView.addObject("usuarioId", usuarioPrincipal.getUsuario().getId());
		modelAndView.addObject("usuarioLogin", usuarioPrincipal.getUsername());
		modelAndView.addObject("dadosTabelaUnidadesConsumidoras", clienteService.getDadosTabelaUnidadesConsumidoras(usuarioPrincipal.getUsuario().getId()));
		modelAndView.setViewName("uconsumidoras");
		return modelAndView;
	}

}
