package to;

import java.util.Date;

public class RegistroTO {

	private int Id;
	private int cedulapersona;
	private Date fechaingreso;
	private Date fechasalida;	
	private String foto;
	private String huella;
	private String equipos;
	private String personavisitada;
	
	
	
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
