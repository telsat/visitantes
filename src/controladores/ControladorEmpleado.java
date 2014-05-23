package controladores;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import conexionSQLServer.EntityManagerUtil;
import entity.Empleado;



public class ControladorEmpleado {
	private EntityManager em = EntityManagerUtil.getEntityManager();
	
	@SuppressWarnings("unchecked")
	public String[][] empleados(String nombre){
		Query query = em.createNativeQuery("SELECT TOP 1000 * FROM [ensayo].[dbo].[empleado]  where (nombre like '%"+nombre+"%' or apellido like'%"+nombre+"%')",Empleado.class);
		
		List<Empleado> lista = query.getResultList();
		
		int size = lista.size();
		String[][] datos = new String[lista.size()][6];
		for(int x = 0;x<size;x++){
			datos[x][0] = lista.get(x).getNombre();
			datos[x][1] = lista.get(x).getApellido();
			datos[x][2] = lista.get(x).getCargo();
			datos[x][3] = lista.get(x).getExtension();
			datos[x][4] = lista.get(x).getArea();			
		}
		
		return datos;

}
	
}
