package br.com.taldi.usina;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.taldi.usuario.Usuario;

@Entity
public class GeracaoSolar {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private float kwh;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public float getKwh() {
		return kwh;
	}

	public void setKwh(float kwh) {
		this.kwh = kwh;
	}

}
