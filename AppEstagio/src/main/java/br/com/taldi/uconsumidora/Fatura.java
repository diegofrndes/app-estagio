package br.com.taldi.uconsumidora;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Fatura {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(unique = true)
	@Temporal(TemporalType.DATE)
	private Date cicloMes;
	@Temporal(TemporalType.DATE)
	private Date cicloInicio;
	@Temporal(TemporalType.DATE)
	private Date cicloFim;
	@ManyToOne
	@JoinColumn(name = "id_unidade_consumidora", referencedColumnName = "id")
	private UnidadeConsumidora unidadeConsumidora;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public UnidadeConsumidora getUnidadeConsumidora() {
		return unidadeConsumidora;
	}

	public void setUnidadeConsumidora(UnidadeConsumidora unidadeConsumidora) {
		this.unidadeConsumidora = unidadeConsumidora;
	}

}
