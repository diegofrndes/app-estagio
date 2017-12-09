package br.com.taldi.pessoa.juridica;

import javax.persistence.Column;
import javax.persistence.Entity;

import br.com.taldi.pessoa.Pessoa;

@Entity
public class PessoaJuridica extends Pessoa {
	@Column(unique=true, length = 14)
	private String cnpj;

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
}
