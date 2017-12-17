package br.com.taldi.uconsumidora;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface UnidadeConsumidoraRepository extends CrudRepository<UnidadeConsumidora, Long>{
	public List<UnidadeConsumidora> findByUsuarioId(Long usuarioId);
	
}
