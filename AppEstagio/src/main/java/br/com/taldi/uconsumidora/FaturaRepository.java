package br.com.taldi.uconsumidora;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface FaturaRepository extends CrudRepository<Fatura, Long> {
	
	@Query("SELECT f FROM Fatura f "
			+ "INNER JOIN f.unidadeConsumidora uc "
			+ "WHERE uc.usuario.id = :idUsuario GROUP BY f.mesAno ORDER BY f.mesAno DESC")
	public List<Fatura> findByUsuarioId(@Param("idUsuario") Long idUsuario);
	
	public List<Fatura> findByUnidadeConsumidoraId(@Param("id") Long id);

	public Fatura findByUnidadeConsumidoraIdAndMesAno(@Param("id") Long id, @Param("mesAno") Date mesAno);

	@Query("SELECT SUM(fc.valor) FROM FaturaConsumo fc WHERE fc.primaryKey.fatura.id = :id")
	public BigDecimal getValorConsumoByFaturaId(@Param("id") Long id);

	@Query("SELECT SUM(fo.valor) FROM FaturaOutro fo WHERE fo.primaryKey.fatura.id = :id")
	public BigDecimal getValorOutroByFaturaId(@Param("id") Long id);
	
}
