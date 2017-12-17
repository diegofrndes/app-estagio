package br.com.taldi.usina;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class DemonstrativoSolarService {
	
	@Autowired
	private DemonstrativoSolarRepository demonstrativoSolarRepository;
	
	public List<DemonstrativoSolarDTO> getUCBeneficiadaSolarByUsuarioAndMesAno(long idUsuario, Date mesAno){
		return demonstrativoSolarRepository.findUCBeneficiadaSolarByUsuarioAndMesAno(idUsuario, mesAno);
	}

}
