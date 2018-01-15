package br.com.taldi.usuario;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	private Logger logger = LogManager.getLogger(UsuarioController.class);

	@GetMapping("/protected/home")
	public String protegido(HttpServletRequest request) {
		return request.isUserInRole("ROLE_ADMIN") ? "redirect:admin/home":"redirect:cliente/home";
	}
	
	@GetMapping("/login")
	public String login(HttpServletRequest request, @RequestParam(value="error", required=false) String error) {
		if(error != null)
	    	logger.error("Tentativa de login malsucedida.");
	    logger.info("anonymoususer acessou a página de login.");
		return "app-login";
	}
	
	@GetMapping("/error")
	public String error(HttpServletRequest request, @AuthenticationPrincipal UserDetails userDetails) {
		return "error";
	}
	
}
