package entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="fechas")

public class Fechas {
	
	@Id
	private String id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechai;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaf;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getFechai() {
		return fechai;
	}

	public void setFechai(Date fechai) {
		this.fechai = fechai;
	}

	public Date getFechaf() {
		return fechaf;
	}

	public void setFechaf(Date fechaf) {
		this.fechaf = fechaf;
	}
	
	

}
