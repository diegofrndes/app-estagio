package br.com.taldi.uconsumidora;

import java.math.BigDecimal;
import java.util.HashMap;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class FaturaService {

	@Autowired
	FaturaRepository faturaRepository;

	public HashMap<Fatura, BigDecimal> getByUnidadeConsumidoraId(long unidadeConsumidoraId) {		
		HashMap<Fatura, BigDecimal> faturas = new HashMap<Fatura, BigDecimal>();
		faturaRepository.findByUnidadeConsumidoraId(unidadeConsumidoraId).forEach(fatura->faturas.put(fatura, faturaRepository.findSumValorByFaturaId(fatura.getId())));
		return faturas;
	}

}
