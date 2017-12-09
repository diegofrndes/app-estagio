package br.com.taldi.usuario;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/")
	public String home(HttpServletRequest request) {
		request.setAttribute("usuarios", usuarioService.getAllUsuarios());
		return "index";
	}	
	
}
