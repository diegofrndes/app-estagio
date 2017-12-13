package br.com.taldi.uconsumidora;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import br.com.taldi.aneel.Classificacao;
import br.com.taldi.aneel.Ligacao;
import br.com.taldi.pessoa.Pessoa;
import br.com.taldi.usuario.Usuario;

@Entity
public class UnidadeConsumidora {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String denominacao;
	private String contrato;
	
	@ManyToOne
	@JoinColumn(name = "id_classificacao", referencedColumnName = "id")
	private Classificacao classificacao;
	
	@ManyToOne
	@JoinColumn(name = "id_ligacao", referencedColumnName = "id")
	private Ligacao ligacao;
	
	@ManyToOne
	@JoinColumn(name = "id_pessoa", referencedColumnName = "id")
	private Pessoa pessoa;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario", referencedColumnName = "id")
	private Usuario usuario;
	
	@OneToMany(mappedBy = "unidadeConsumidora", fetch = FetchType.LAZY)
	private List<Fatura> faturas;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDenominacao() {
		return denominacao;
	}

	public void setDenominacao(String denominacao) {
		this.denominacao = denominacao;
	}

	public String getContrato() {
		return contrato;
	}

	public void setContrato(String contrato) {
		this.contrato = contrato;
	}

	public Classificacao getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(Classificacao classificacao) {
		this.classificacao = classificacao;
	}

}
