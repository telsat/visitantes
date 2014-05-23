package controladores;

import javax.persistence.EntityManager;

import to.EquipoTO;
import to.RegistroTO;
import conexionSQLServer.EntityManagerUtil;
import entity.Equipo;

public class ControladorEquipo {
	private EntityManager em = EntityManagerUtil.getEntityManager();
	Equipo equipo = new Equipo();
	public void RegistraEquipo(EquipoTO equipoTO,RegistroTO registroTO){
		
		String nombre = equipoTO.getNombre();
		String serial = equipoTO.getSerial();
		int cedula = registroTO.getCedulapersona();
		String foto = equipoTO.getFoto();
		
		try {			
			Long identi = (Long) em.createNativeQuery("SELECT  MAX(iden)  FROM [ensayo].[dbo].[registro] where cedulapersona ="+cedula+";").getSingleResult(); 
			Integer identificador = (int)(long)identi;		
			em.getTransaction().begin();			
			equipo.setFoto(foto);
			equipo.setIdRegistro(identificador);				
			equipo.setNombre(nombre);
			equipo.setSerial(serial);
			em.persist(equipo);
			em.getTransaction().commit();
		
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

}
