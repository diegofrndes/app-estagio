package br.com.taldi.usuario;

import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
	public Usuario findByLogin(String login);
}
