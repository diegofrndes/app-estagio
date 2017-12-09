package br.com.taldi.aneel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
}
