package br.com.taldi.usina;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface GeracaoSolarRepository extends CrudRepository<GeracaoSolar, Long>{
	@Query("SELECT SUM(g.quantidade) FROM GeracaoSolar g "
			+ "INNER JOIN g.usinaSolar us "
			+ "INNER JOIN us.unidadeConsumidora uc "
			+ "WHERE uc.id = :idUnidadeConsumidora AND g.data >= :cicloInicio AND g.data <= :cicloFim")
	public BigDecimal getGeracaoByIdUnidadeConsumidoraAndCiclo(@Param("idUnidadeConsumidora") long idUnidadeConsumidora, @Param("cicloInicio") Date cicloInicio, @Param("cicloFim") Date cicloFim);

	public List<GeracaoSolar> findTop30ByUsinaSolarIdOrderByDataDesc(Long usinaSolarId);

}
