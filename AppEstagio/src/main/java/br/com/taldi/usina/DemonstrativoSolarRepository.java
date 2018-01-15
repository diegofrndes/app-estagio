package br.com.taldi.usina;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface DemonstrativoSolarRepository extends CrudRepository<DemonstrativoSolar, Long> {
	
	@Query("SELECT d FROM DemonstrativoSolar d "
			+ "INNER JOIN d.fatura f "
			+ "INNER JOIN f.unidadeConsumidora uc "
			+ "WHERE uc.usuario.id = :idUsuario GROUP BY d.cicloMes ORDER BY d.cicloMes DESC")
	public List<DemonstrativoSolar> findDemonstrativoSolarByUsuarioId(@Param("idUsuario") Long idUsuario);
	
	@Query("SELECT new br.com.taldi.usina.DemonstrativoSolarDTO(uc, d) FROM DemonstrativoSolar d "
			+ "INNER JOIN d.fatura f "
			+ "INNER JOIN f.unidadeConsumidora uc "
			+ "WHERE f.mesAno = :mesAno AND uc.usuario.id = :idUsuario")
	public List<DemonstrativoSolarDTO> findDemonstrativoSolarDTOSByUsuarioAndMesAno(@Param("idUsuario") Long idUsuario,@Param("mesAno") Date mesAno);
	
	@Query("SELECT SUM(d.credito) FROM DemonstrativoSolar d "
			+ "INNER JOIN d.fatura f "
			+ "INNER JOIN f.unidadeConsumidora uc "
			+ "WHERE f.mesAno <= :mesAno AND uc.usuario.id = :idUsuario")
	public BigDecimal getSumCreditoByUsuario(@Param("idUsuario") Long idUsuario, @Param("mesAno") Date mesAno);
	
}
