package controladores;

import javax.persistence.EntityManager;

import to.EquipoTO;
import to.RegistroTO;
import conexionSQLServer.EntityManagerUtil;
import entity.Equipo;


/*esta clase contiene los metodos con los cuales se hacen queries como inserciones
 o consultas en la tabla equipo de la base de datos*/
public class ControladorEquipo {
	private EntityManager em = EntityManagerUtil.getEntityManager();
	Equipo equipo = new Equipo();
	
	
	/*este metodo recibe dos objetos transfer object con los datos 
	  del equipo a registrar y a quien pertenece el equipo y
	  el identificador del registro al cual pertenece el equipo*/
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
