package br.com.taldi.aneel;

import java.util.Date;

import org.springframework.data.repository.CrudRepository;

public interface TarifaRepository extends CrudRepository<Tarifa, Long>{
	public Tarifa findByClassificacaoId(Long classificacaoId);
	public Tarifa findByClassificacaoIdAndFimVigencia(Long classificacaoId, Date fimVigencia);
}
