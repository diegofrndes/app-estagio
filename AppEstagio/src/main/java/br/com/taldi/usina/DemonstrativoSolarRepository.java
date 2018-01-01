package br.com.taldi.usina;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface DemonstrativoSolarRepository extends CrudRepository<DemonstrativoSolar, Long> {

	@Query("SELECT new br.com.taldi.usina.DemonstrativoSolarDTO(uc, d) FROM DemonstrativoSolar d "
			+ "INNER JOIN d.fatura f "
			+ "INNER JOIN f.unidadeConsumidora uc "
			+ "WHERE f.mesAno = :mesAno AND uc.usuario.id = :idUsuario")
	public List<DemonstrativoSolarDTO> findUCBeneficiadaSolarByUsuarioAndMesAno(@Param("idUsuario") Long idUsuario,@Param("mesAno") Date mesAno);
}
