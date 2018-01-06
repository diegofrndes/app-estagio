package br.com.taldi.usina;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace=Replace.NONE)	
public class UsinaSolarRepositoryTest {
	
	@Autowired
	private UsinaSolarRepository usinaSolarRepository;
	
	@Test
	public void findUsinaSolarByUsuarioId() {
		List<UsinaSolar> us = usinaSolarRepository.findUsinaSolarByUsuarioId((long) 3);
		assert(!us.isEmpty());
	}
}
