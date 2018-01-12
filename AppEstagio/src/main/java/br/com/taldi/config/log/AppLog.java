package br.com.taldi.config.log;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class AppLog {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String level;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataEvento;
	@Column(columnDefinition = "TEXT")
	private String mensagem;
	
	public String getLevel() {
		return level;
	}
	
	public void setLevel(String level) {
		this.level = level;
	}
	
	
		
}
