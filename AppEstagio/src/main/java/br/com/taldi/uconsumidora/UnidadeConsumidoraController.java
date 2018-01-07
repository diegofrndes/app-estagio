package br.com.taldi.uconsumidora;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.taldi.usuario.UsuarioPrincipal;

@Controller
@RequestMapping("**/uconsumidora")
public class UnidadeConsumidoraController {

	@Autowired
	private UnidadeConsumidoraService unidadeConsumidoraService;
	@Autowired
	private FaturaService faturaService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getRelatorio(HttpServletRequest request, @AuthenticationPrincipal UserDetails userDetails) {
		UsuarioPrincipal usuarioPrincipal = (UsuarioPrincipal) userDetails;		
		request.setAttribute("uconsumidoras", unidadeConsumidoraService.getByUsuarioId(usuarioPrincipal.getUsuario().getId()));
		request.setAttribute("proprietario", usuarioPrincipal.getUsuario().getPessoa().getNome());
		return "uconsumidora";
	}
	
	@GetMapping(value = "/faturas/{id}")
	public String mostrarFaturasUnidadeConsumidora(@PathVariable("id") long id, Model model) {
		model.addAttribute("nome", unidadeConsumidoraService.getProprietarioUnidadeConsumidora(id));
		model.addAttribute("faturas", faturaService.getByUnidadeConsumidoraId(id));
		return "faturas";
	}
	
}
