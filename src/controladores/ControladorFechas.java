package controladores;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import conexionSQLServer.EntityManagerUtil;
import entity.Fechas;
import entity.Registro;

public class ControladorFechas {
	private EntityManager em = EntityManagerUtil.getEntityManager();
	
	public void mostrarFechas(Date fechai, Date fechaf){
		String q = "SELECT TOP 100 * FROM [ensayo].[dbo].[fechas] WHERE (fechai BETWEEN  ? AND ?) ";
		System.out.println("antes del query");
		Query query = em.createNativeQuery(q,Fechas.class);
		query.setParameter(1, fechai);
		query.setParameter(2, fechaf);
		List<Fechas> listaR = query.getResultList();
		
		System.out.println("funciono el query");
		
		
		
	}

}
