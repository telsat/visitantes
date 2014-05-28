package entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="registro")
@SequenceGenerator(name="seq", initialValue=1, allocationSize=1)
public class Registro {
	@Id	
	@Column(name="iden")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
	private int Id;
	private int cedulapersona;
	private String personavisitada;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaingreso;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechasalida;	
	private String asunto;
	private String visitadaausente;
	private String nombre;
	
	
	
	
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getAsunto() {
		return asunto;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	public String getVisitadaausente() {
		return visitadaausente;
	}
	public void setVisitadaausente(String visitadaausente) {
		this.visitadaausente = visitadaausente;
	}
	public String getPersonavisitada() {
		return personavisitada;
	}
	public void setPersonavisitada(String personavisitada) {
		this.personavisitada = personavisitada;
	}

	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public int getCedulapersona() {
		return cedulapersona;
	}
	public void setCedulapersona(int cedulapersona) {
		this.cedulapersona = cedulapersona;
	}
	public Date getFechaingreso() {
		return fechaingreso;
	}
	public void setFechaingreso(Date fechaingreso) {
		this.fechaingreso = fechaingreso;
	}
	public Date getFechasalida() {
		return fechasalida;
	}
	public void setFechasalida(Date fechasalida) {
		this.fechasalida = fechasalida;
	}
	
	

}
