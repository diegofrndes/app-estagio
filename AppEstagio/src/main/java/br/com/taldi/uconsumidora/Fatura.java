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
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "cicloMes", "id_unidade_consumidora" }) })
public class Fatura {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(unique = true, nullable = false)
	@Temporal(TemporalType.DATE)
	private Date cicloMes;
	
	@Temporal(TemporalType.DATE)
	private Date cicloInicio;
	
	@Temporal(TemporalType.DATE)
	private Date cicloFim;
	
	@Temporal(TemporalType.DATE)
	private Date dataVencimento;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_unidade_consumidora", referencedColumnName = "id")
	private UnidadeConsumidora unidadeConsumidora;
	
	@OneToMany(mappedBy = "primaryKey.fatura", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<FaturaConsumo> faturaConsumos = new ArrayList<FaturaConsumo>();

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

	public Date getCicloMes() {
		return cicloMes;
	}

	public void setCicloMes(Date cicloMes) {
		this.cicloMes = cicloMes;
	}

	public Date getCicloInicio() {
		return cicloInicio;
	}

	public void setCicloInicio(Date cicloInicio) {
		this.cicloInicio = cicloInicio;
	}

	public Date getCicloFim() {
		return cicloFim;
	}

	public void setCicloFim(Date cicloFim) {
		this.cicloFim = cicloFim;
	}

	public List<FaturaConsumo> getFaturaConsumos() {
		return faturaConsumos;
	}

	public void setFaturaConsumos(List<FaturaConsumo> faturaConsumos) {
		this.faturaConsumos = faturaConsumos;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

}
