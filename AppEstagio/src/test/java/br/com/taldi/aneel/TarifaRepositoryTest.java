package br.com.taldi.aneel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
public class TarifaRepositoryTest {
	
	@Autowired
	private TarifaRepository tarifaRepository;

	@Test
	public void findTarifaByClassificacao() {
		String testDate = "31-03-2017";
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date data = new Date();
		try {
			data = formatter.parse(testDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(data.toString()); 
		Tarifa tarifa = tarifaRepository.findByClassificacaoIdAndFimVigencia((long) 5, data);
		System.out.println(tarifa.getValor());
		assert(!tarifa.equals(null));
	}	
	
}
