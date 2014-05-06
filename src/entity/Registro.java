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
	private String equipos;
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaingreso;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechasalida;
	
	private String foto;
	private String huella;
	
	
	
	
	public String getPersonavisitada() {
		return personavisitada;
	}
	public void setPersonavisitada(String personavisitada) {
		this.personavisitada = personavisitada;
	}
	public String getEquipos() {
		return equipos;
	}
	public void setEquipos(String equipos) {
		this.equipos = equipos;
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
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public String getHuella() {
		return huella;
	}
	public void setHuella(String huella) {
		this.huella = huella;
	}
	
	
	

}
