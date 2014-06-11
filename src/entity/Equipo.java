package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;




/*esta clase es un mapeo entre una clase java que contiene
los mismos campos o atrbutos que la tabla equipo en la base de datos lo cual permite
hacer la persistencia con JPA*/
@Entity
@Table(name="equipo")
public class Equipo {
	@Id	
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
	private int id;
	private String serial;
	@Column(name="idregistro")
	private int idRegistro;
	private String foto;
	private String nombre;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSerial() {
		return serial;
	}
	public void setSerial(String serial) {
		this.serial = serial;
	}
	public int getIdRegistro() {
		return idRegistro;
	}
	public void setIdRegistro(int idRegistro) {
		this.idRegistro = idRegistro;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	

}
