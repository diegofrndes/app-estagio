package br.com.taldi.usina;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface UsinaSolarRepository extends CrudRepository<UsinaSolar, Long>{
	@Query("SELECT us FROM UsinaSolar us "
			+ "INNER JOIN us.unidadeConsumidora uc "
			+ "INNER JOIN uc.usuario u "
			+ "WHERE u.id = :idUsuario")
	public List<UsinaSolar> findUsinaSolarByUsuarioId(@Param("idUsuario") Long idUsuario);

}
