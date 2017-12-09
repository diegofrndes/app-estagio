package br.com.taldi.uconsumidora;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.taldi.usuario.Usuario;

@Entity
public class UnidadeConsumidora {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String denominacao;
	@ManyToOne
	@JoinColumn(name = "id_usuario", referencedColumnName="id")
	private Usuario usuario;
	
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

}
