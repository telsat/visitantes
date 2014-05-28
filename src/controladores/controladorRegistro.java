package controladores;


import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import to.RegistroTO;
import conexionSQLServer.EntityManagerUtil;
import conexionSQLServer.Persona;
import entity.Empleado;
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
		String nombre = registroTO.getNombre();
		
		try {
			em.getTransaction().begin();
			registro.setCedulapersona(cedula);
			registro.setFechaingreso(fechaingreso);
			registro.setFechasalida(fechasalida);
			registro.setPersonavisitada(personavisitada);
			registro.setId(id);
			registro.setAsunto(asunto);
			registro.setNombre(nombre);
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
	
	
	@SuppressWarnings("unchecked")
	public Object[][] ConsultarRegistro(Date idate, Date fdate,String nombre){	
		
		String q = "SELECT TOP 100 * FROM [ensayo].[dbo].[registro] WHERE (fechaingreso BETWEEN  ? AND ?) or nombre like '%"+nombre+"%'";
		
		Query query = em.createNativeQuery(q,Registro.class);
		query.setParameter(1, idate);
		query.setParameter(2, fdate);
		List<Registro> listaR = query.getResultList();
		Object[][] datos = new Object[listaR.size()][8];
		
		int size = listaR.size();
		for(int x = 0;x<size;x++){
			
			datos[x][0] = listaR.get(x).getNombre();
			datos[x][1] = listaR.get(x).getCedulapersona();
			datos[x][2] = listaR.get(x).getPersonavisitada();
			datos[x][3] = listaR.get(x).getFechaingreso();
			datos[x][4] = listaR.get(x).getFechasalida();
			//datos[x][5] = listaR.get(x).getCedulapersona();		
					
		}
		
		
		
		
		return datos;
	}

}
