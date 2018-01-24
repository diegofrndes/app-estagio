package br.com.taldi.usina;

import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface IrradiacaoSolarRepository extends CrudRepository<IrradiacaoSolar, Long>{
	
	@Query("SELECT irrads FROM IrradiacaoSolar irrads WHERE MONTH(irrads.data) = MONTH(:cicloMes) AND irrads.cidade.id = :idCidade")
	public IrradiacaoSolar findIrradiacaoSolarByMes(@Param("cicloMes") Date cicloMes, @Param("idCidade") long idCidade);

}
