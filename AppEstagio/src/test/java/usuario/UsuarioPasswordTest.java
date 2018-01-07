package usuario;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootConfiguration
@SpringBootTest
public class UsuarioPasswordTest {
		
	@Test
	public void findUnidadesConsumidorasComUsinas() {
		System.out.println(new BCryptPasswordEncoder().encode("taldi2008"));
		assert(true);
	}
}


