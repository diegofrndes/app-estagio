package br.com.taldi.uconsumidora;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface FaturaRepository extends CrudRepository<Fatura, Long> {
	public List<Fatura> findByUnidadeConsumidoraId(@Param("id") Long id);
	@Query("SELECT SUM(fc.valor) FROM FaturaConsumo fc WHERE fc.primaryKey.fatura.id = :id")
	public BigDecimal findSumValorByFaturaId(@Param("id") Long id);	
}
