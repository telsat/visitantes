package controladores;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import conexionSQLServer.EntityManagerUtil;
import conexionSQLServer.Persona;
import to.PersonaTO;

public class ControladorPersona {
	private EntityManager em = EntityManagerUtil.getEntityManager();

	Persona persona = new Persona();
		
		public void guardarPersona(PersonaTO personaTO){
			
			int cedula = personaTO.getCedula();
			String nombre = personaTO.getNombre();
			String apellido = personaTO.getApellido();
			String sexo = personaTO.getSexo();
			Date fecha = personaTO.getFechanacimiento();
			String tipoSangre = personaTO.getTipoSangre();
			String foto = personaTO.getFoto();
			String huella = personaTO.getHuella();
			
			try {
				Persona pers = em.find(Persona.class, cedula);
				if(pers!=null){
					String fotovieja = pers.getFoto();
					DisplayImg img = new DisplayImg();
					img.Display(fotovieja);
				}else{
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
		
		public void mostrarPersona(){
			try {			
				Query query = em.createNativeQuery("SELECT *" + " FROM [ensayo].[dbo].[persona] WHERE cedula = 18430 ",Persona.class);
				
				@SuppressWarnings("unchecked")
				List<Persona> persona =  (List<Persona>)query.getResultList();
				
				
				
				
				for(Iterator<Persona> it = persona.iterator();it.hasNext();){
					Persona ingre = (Persona)it.next();
					
					System.out.println("nombre: " + ingre.getNombre());
					System.out.println("apellido: " + ingre.getApellido());
					System.out.println("sexo: " + ingre.getSexo());
					System.out.println("fecha " + ingre.getFechanacimiento());		
					
					
				}
				
				
				
			
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
				
			}
			
			
		}
}

		
