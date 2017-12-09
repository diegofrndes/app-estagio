package br.com.taldi.uconsumidora;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UnidadeConsumidoraService {
	@Autowired
	private UnidadeConsumidoraRepository unidadeConsumidoraRepository;
	
	public List<UnidadeConsumidora> getByUsuarioId(long id){
		List<UnidadeConsumidora> ucs = new ArrayList<>();
		unidadeConsumidoraRepository.findByUsuarioId(id).forEach(ucs::add);
		return ucs;
	}

}
