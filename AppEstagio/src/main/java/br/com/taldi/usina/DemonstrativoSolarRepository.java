package br.com.taldi.usina;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.taldi.uconsumidora.Fatura;

public interface DemonstrativoSolarRepository extends CrudRepository<Fatura, Long> {

	@Query("SELECT new br.com.taldi.usina.DemonstrativoSolarDTO(uc) FROM DemonstrativoSolar d "
			+ "INNER JOIN d.fatura f "
			+ "INNER JOIN f.unidadeConsumidora uc "
			+ "WHERE f.mesAno = :mesAno AND uc.usuario.id = :idUsuario")
	public List<DemonstrativoSolarDTO> findUCBeneficiadaSolarByUsuarioAndMesAno(@Param("idUsuario") Long idUsuario,@Param("mesAno") Date mesAno);
}
