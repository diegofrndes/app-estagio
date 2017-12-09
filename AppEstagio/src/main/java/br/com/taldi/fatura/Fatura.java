package br.com.taldi.fatura;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Fatura {
	@Id
	private long id;
	@Temporal(TemporalType.DATE)
	private Date ciclo;
	@Temporal(TemporalType.DATE)
	private Date inicio;
	@Temporal(TemporalType.DATE)
	private Date fim;
	
}
