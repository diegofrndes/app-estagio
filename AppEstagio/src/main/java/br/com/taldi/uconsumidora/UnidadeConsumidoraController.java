package br.com.taldi.uconsumidora;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.taldi.usuario.UsuarioService;

@Controller
@RequestMapping("/uconsumidora")
public class UnidadeConsumidoraController {

	@Autowired
	private UnidadeConsumidoraService unidadeConsumidoraService;
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private FaturaService faturaService;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String getRelatorio(HttpServletRequest request, @PathVariable("id") long id) {
		request.setAttribute("uconsumidoras", unidadeConsumidoraService.getByUsuarioId(id));
		request.setAttribute("proprietario", usuarioService.getUsuario(id).getPessoa().getNome());
		return "uconsumidora";
	}
	
	@GetMapping(value = "/faturas/{id}")
	public String mostrarFaturasUnidadeConsumidora(@PathVariable("id") long id, Model model) {
		model.addAttribute("nome", unidadeConsumidoraService.getProprietarioUnidadeConsumidora(id));
		model.addAttribute("faturas", faturaService.getByUnidadeConsumidoraId(id));
		return "faturas";
	}
	
}
