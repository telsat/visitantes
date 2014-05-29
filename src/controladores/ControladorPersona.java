package controladores;

import huella.GuardarHuella;

import java.awt.Image;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.github.sarxos.webcam.Webcam;

import conexionSQLServer.EntityManagerUtil;
import conexionSQLServer.Persona;
import to.PersonaTO;
import webcam.Fotos;

public class ControladorPersona {
	private EntityManager em = EntityManagerUtil.getEntityManager();

	Persona persona = new Persona();
	Fotos fotos = new Fotos();
	GuardarHuella h = new GuardarHuella();
		public void guardarPersona(PersonaTO personaTO, Webcam webcam,Image huellaImg){
			
			int cedula = personaTO.getCedula();
			
			
			String nombre = personaTO.getNombre();
			String apellido = personaTO.getApellido();
			String sexo = personaTO.getSexo();
			Date fecha = personaTO.getFechanacimiento();
			String tipoSangre = personaTO.getTipoSangre();
			
			
			
			
			try {
				Persona pers = em.find(Persona.class, cedula);
				if(pers!=null){
					String fotovieja = pers.getFoto();
					DisplayImg img = new DisplayImg();
					img.Display(fotovieja);
				}else{
					
					String foto = fotos.tomarfoto(webcam);
					String huella = "";
					try {
						huella = h.guardarguella(huellaImg, cedula);
					} catch (IOException e1) {				
						e1.printStackTrace();
					}
					em.getTransaction().begin();
					
					persona.setCedula(cedula);			
					persona.setNombre(nombre);
					persona.setApellido(apellido);	
					persona.setFechanacimiento(fecha);
					persona.setSexo(sexo);
					persona.setTipoSangre(tipoSangre);
					persona.setFoto(foto);
					persona.setHuella(huella);
					em.persist(persona);
					em.getTransaction().commit();					
					
				}
				
				
					
					
				
				
				
				
			} catch (Exception e) {
				
				System.out.println("error " + e);
				
			}
			
			
		}
		
		@SuppressWarnings("unchecked")
		public Object[][] mostrarPersona(String nombre){
			
			Query query = em.createNativeQuery("SELECT * FROM [ensayo].[dbo].[persona] WHERE (nombre like '%"+nombre+"%' or apellido like'%"+nombre+"%')",Persona.class);
			List<Persona> persona =  query.getResultList();		
			Object [][] datos = new String[persona.size()][8];			
			int size = persona.size();
			for(int x = 0;x<size;x++){
					datos[x][0] = persona.get(x).getNombre();
					datos[x][1] = persona.get(x).getApellido();
					datos[x][2] = persona.get(x).getCedula();
					
							
			}
				
				return datos;		
			
		}
		
		
		public void consulatrpersona(int cedula){
			Persona p = new Persona();
			p = em.find(Persona.class, cedula);
			if (p != null){
				String foto = p.getFoto();
				DisplayImg img = new DisplayImg();
				try {
					img.Display(foto);
				} catch (IOException e) {					
					e.printStackTrace();
				}
			}
		}
}

		
