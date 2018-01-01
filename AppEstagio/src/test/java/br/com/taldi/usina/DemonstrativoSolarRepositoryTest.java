package br.com.taldi.usina;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class DemonstrativoSolarRepositoryTest {
	
	@Autowired
	private DemonstrativoSolarRepository demonstrativoSolarRepository;
	
	@Test
	public void findUnidadesConsumidorasComUsinas() {
		String testDate = "31-10-2017";
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date();
		try {
			date = formatter.parse(testDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(date.toString()); 
		List<DemonstrativoSolarDTO> ucs = demonstrativoSolarRepository.findUCBeneficiadaSolarByUsuarioAndMesAno(new Long(3), date);
		//System.out.println(ucs.get(0).getDenominacao());
		assert(!ucs.isEmpty());
	}
}