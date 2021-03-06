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
	
	public String getProprietarioUnidadeConsumidora(long id) {
		UnidadeConsumidora uc = unidadeConsumidoraRepository.findOne(id);
		return uc.getUsuario().getPessoa().getNome();
	}
	
	public List<UnidadeConsumidora> getByUsuarioId(long usuarioId){
		List<UnidadeConsumidora> ucs = new ArrayList<>();
		unidadeConsumidoraRepository.findByUsuarioId(usuarioId).forEach(ucs::add);
		return ucs;
	}

}
