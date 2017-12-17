package br.com.taldi.uconsumidora;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace=Replace.NONE)	
public class UnidadeConsumidoraRepositoryTest {
	
	@Autowired
	private UnidadeConsumidoraRepository unidadeConsumidoraRepository;
	@Autowired
	private UnidadeConsumidoraService unidadeConsumidoraService;
	
	@Test
	public void findUnidadesConsumidorasComUsinas() {
		List<UnidadeConsumidora> ucs = unidadeConsumidoraService.getByUsuarioId(3);
		System.out.println(ucs.size());
		assert(!ucs.isEmpty());
	}
}
