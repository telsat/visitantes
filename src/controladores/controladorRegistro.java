package controladores;


import java.util.Date;
import javax.persistence.EntityManager;
import to.RegistroTO;
import conexionSQLServer.EntityManagerUtil;
import entity.Registro;


public class controladorRegistro {
	
	private EntityManager em = EntityManagerUtil.getEntityManager();
	Registro registro = new Registro();
	
	public void guardarRegistro(RegistroTO registroTO){
		int cedula = registroTO.getCedulapersona();
		Date fechaingreso = registroTO.getFechaingreso();
		Date fechasalida = registroTO.getFechasalida();
		String personavisitada = registroTO.getPersonavisitada();		
		int id = registroTO.getId();
		String asunto = registroTO.getAsunto();
		String ausente = registroTO.getVisitadaausente();
		
		try {
			em.getTransaction().begin();
			registro.setCedulapersona(cedula);
			registro.setFechaingreso(fechaingreso);
			registro.setFechasalida(fechasalida);
			registro.setPersonavisitada(personavisitada);
			registro.setId(id);
			registro.setAsunto(asunto);
			registro.setVisitadaausente(ausente);
			em.persist(registro);
			em.getTransaction().commit();
			
			
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
	}
	
	
	public void registrarSalida(RegistroTO registroTO){
		
		try {
			em.getTransaction().begin();
			
			int cedula = registroTO.getCedulapersona();
			
			Date fecha = registroTO.getFechasalida();
			
			Long identi = (Long) em.createNativeQuery("SELECT  MAX(iden)  FROM [ensayo].[dbo].[registro] where cedulapersona ="+cedula+";").getSingleResult(); 
			Integer identificador = (int)(long)identi;
			Registro reg = em.find(Registro.class,identificador );
			
			reg.setFechasalida(fecha);
			
			em.getTransaction().commit();
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			
		}
		
	}

}
