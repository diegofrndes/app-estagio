package br.com.taldi.usuario;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UsuarioService {
	@Autowired
	private UsuarioRepository usuarioRepository;

	public Usuario getUsuario(Long id) {
		return usuarioRepository.findOne(id);
	}

	public List<Usuario> getAllUsuarios() {
		List<Usuario> usuarios = new ArrayList<>();
		usuarioRepository.findAll().forEach(usuarios::add);
		return usuarios;
	}

}
