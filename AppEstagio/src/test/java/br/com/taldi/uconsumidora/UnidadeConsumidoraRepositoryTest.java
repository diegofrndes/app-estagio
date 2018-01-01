package br.com.taldi.uconsumidora;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace=Replace.NONE)	
public class UnidadeConsumidoraRepositoryTest {
		
	@Test
	public void findUnidadesConsumidorasComUsinas() {
		assert(true);
	}
}
