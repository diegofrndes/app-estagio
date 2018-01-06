package br.com.taldi.aneel;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = {
		@UniqueConstraint(columnNames = { "grupo", "subgrupo", "classe", "subclasse", "modalidade" }) })
public class Classificacao {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String grupo;
	private String subgrupo;
	private String classe;
	private String subclasse;
	private String modalidade;
	@OneToMany(mappedBy="classificacao", fetch = FetchType.LAZY)
	private List<Tarifa> tarifas;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public String getSubgrupo() {
		return subgrupo;
	}

	public void setSubgrupo(String subgrupo) {
		this.subgrupo = subgrupo;
	}

	public String getClasse() {
		return classe;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}

	public String getSubclasse() {
		return subclasse;
	}

	public void setSubclasse(String subclasse) {
		this.subclasse = subclasse;
	}

	public String getModalidade() {
		return modalidade;
	}

	public void setModalidade(String modalidade) {
		this.modalidade = modalidade;
	}
	
	public String toString() {
		String classificacao = "";
		if(subgrupo != null)
			classificacao = subgrupo + " ";
		if(classe != null)
			classificacao = classificacao + classe + " ";
		if(subclasse != null)
			classificacao = classificacao + subclasse;
		if(classificacao.charAt(classificacao.length()-1) == ' ')
			classificacao = classificacao.substring(0, classificacao.length()-1);
			
		return classificacao;
	}
}
