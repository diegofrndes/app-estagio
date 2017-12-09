package br.com.taldi.pessoa.fisica;

import javax.persistence.Column;
import javax.persistence.Entity;

import br.com.taldi.pessoa.Pessoa;

@Entity
public class PessoaFisica extends Pessoa {
	@Column(unique=true, length = 11)
	private String cpf;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
}
