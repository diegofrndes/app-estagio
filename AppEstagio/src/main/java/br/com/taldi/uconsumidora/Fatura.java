package br.com.taldi.uconsumidora;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
//@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "mesAno", "id_unidade_consumidora" }) })
public class Fatura {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(unique = true, nullable = false)
	@Temporal(TemporalType.DATE)
	private Date mesAno;

	@Temporal(TemporalType.DATE)
	private Date dataVencimento;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_unidade_consumidora", referencedColumnName = "id")
	private UnidadeConsumidora unidadeConsumidora;

	@OneToMany(mappedBy = "primaryKey.fatura", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<FaturaConsumo> consumos = new ArrayList<FaturaConsumo>();

	@OneToMany(mappedBy = "primaryKey.fatura", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<FaturaOutro> outros = new ArrayList<FaturaOutro>();

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

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public List<FaturaConsumo> getConsumos() {
		return consumos;
	}

	public void setConsumos(List<FaturaConsumo> consumos) {
		this.consumos = consumos;
	}

	public List<FaturaOutro> getOutros() {
		return outros;
	}

	public void setOutros(List<FaturaOutro> outros) {
		this.outros = outros;
	}

	public Date getMesAno() {
		return mesAno;
	}

	public void setMesAno(Date mesAno) {
		this.mesAno = mesAno;
	}

}
