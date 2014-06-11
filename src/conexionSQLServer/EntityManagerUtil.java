package conexionSQLServer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


/*esta clase contiene el EntityManager y el nombre del persistence UNIT que 
 * identifica este proyecto para la conexcion con la base de datos*/
public class EntityManagerUtil {
	private static final EntityManagerFactory emf;
	static {
		try {
			emf = Persistence.createEntityManagerFactory("conexionSQLServer");
			
		} catch (Throwable e) {
			System.err.println("Initial sessionFactory creation failed"+  e);
			throw new ExceptionInInitializerError(e);
		}
	}
	
	public static EntityManager getEntityManager(){
		return emf.createEntityManager();
	}	

}
