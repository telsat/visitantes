package to;

import java.util.Date;

public class RegistroTO {

	private int Id;
	private int cedulapersona;
	private Date fechaingreso;
	private Date fechasalida;	
	private String equipos;
	private String personavisitada;
	private String asunto;
	private String visitadaausente;
	
	
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
	public String getEquipos() {
		return equipos;
	}
	public void setEquipos(String equipos) {
		this.equipos = equipos;
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