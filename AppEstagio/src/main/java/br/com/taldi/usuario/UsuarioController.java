package br.com.taldi.usuario;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	@GetMapping("/login")
	public String login(HttpServletRequest request, @AuthenticationPrincipal UserDetails userDetails) {
		return "taldi-login";
	}	
	
	@GetMapping("/error")
	public String error(HttpServletRequest request, @AuthenticationPrincipal UserDetails userDetails) {
		return "error";
	}	
	
}
