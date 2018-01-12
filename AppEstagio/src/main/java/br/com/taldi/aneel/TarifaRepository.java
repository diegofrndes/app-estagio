package br.com.taldi.aneel;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface TarifaRepository extends CrudRepository<Tarifa, Long>{
	
	public Tarifa findByClassificacaoId(Long classificacaoId);
	public Tarifa findByClassificacaoIdAndFimVigencia(Long classificacaoId, Date fimVigencia);

	@Query("SELECT t FROM Tarifa t "
			+ "INNER JOIN t.classificacao c "
			+ "WHERE c.id = :classificacaoId AND t.fimVigencia = :fimVigencia AND :consumo BETWEEN ifnull(t.consumoMin,0) AND ifnull(t.consumoMax,99999999)")
	public Tarifa findByClassificacaoIdAndFimVigenciaAndBetweenConsumoMinAndMax(@Param("classificacaoId") Long classificacaoId,@Param("fimVigencia") Date fimVigencia,@Param("consumo") BigDecimal consumo);	

}
