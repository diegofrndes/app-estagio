package br.com.taldi.usuario.cliente;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("**/cliente")
public class ClienteController {
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(HttpServletRequest request, @AuthenticationPrincipal UserDetails userDetails) {
		//UsuarioPrincipal usuarioPrincipal = (UsuarioPrincipal) userDetails;		
		//request.setAttribute("uconsumidoras", unidadeConsumidoraService.getByUsuarioId(usuarioPrincipal.getUsuario().getId()));
		//request.setAttribute("proprietario", usuarioPrincipal.getUsuario().getPessoa().getNome());
		return "cliente";
	}
}
