package entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



/*esta clase es un mapeo entre una clase java que contiene
los mismos campos o atrbutos que la tabla empleado en la base de datos lo cual permite
hacer la persistencia con JPA*/

@Entity
@Table(name="empleado")
public class Empleado {
	@Id
	private int Cedula;
	private String nombre;
	private String apellido;
	private String cargo;
	private String extension;
	private String area;
	public int getCedula() {
		return Cedula;
	}
	public void setCedula(int cedula) {
		Cedula = cedula;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	
	

}
