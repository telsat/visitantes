package to;

import java.util.Date;


/*esta clase es un transfer object que se utiliza para 
enviar datos desde la interfaz hacia las entidades 
y hacer la persistencia de los datos con la tabla persona*/
public class PersonaTO {
	private int cedula;
	private String nombre;
	private String apellido;
	private Date fechanacimiento;
	private String sexo;
	private String tipoSangre;
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

	public Date getFechanacimiento() {
		return fechanacimiento;
	}
	public void setFechanacimiento(Date fechanacimiento) {
		this.fechanacimiento = fechanacimiento;
	}
	public String getTipoSangre() {
		return tipoSangre;
	}
	public void setTipoSangre(String tipoSangre) {
		this.tipoSangre = tipoSangre;
	}
	public int getCedula() {
		return cedula;
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
