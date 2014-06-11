package conexionSQLServer;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



/*esta clase es un mapeo entre una clase java que contiene
  los mismos campos o atrbutos que la tabla persona en la base de datos lo cual permite
  hacer la persistencia con JPA*/
@Entity
@Table(name = "persona")
public class Persona {
	
	@Id
	private int cedula;
	private String nombre;
	private String apellido;
	private String sexo;
	@Column(name="tiposangre")
	private String tipoSangre;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fechanacimiento")
	private Date fechanacimiento;
	private String foto;
	private String huella;
	
	
	

	
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
	public String getTipoSangre() {
		return tipoSangre;
	}
	public void setTipoSangre(String tipoSangre) {
		this.tipoSangre = tipoSangre;
	}
	
	public Date getFechanacimiento() {
		return fechanacimiento;
	}
	public void setFechanacimiento(Date fechanacimiento) {
		this.fechanacimiento = fechanacimiento;
	}
	
	
	public int getCedula() {
		return  cedula;
	}
	public void setCedula(int cedula) {
		this.cedula = cedula;
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

	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}	

}
