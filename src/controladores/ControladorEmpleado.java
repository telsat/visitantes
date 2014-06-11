package controladores;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import conexionSQLServer.EntityManagerUtil;
import entity.Empleado;


/*esta clase contiene los metodos para hacer queries en la tabla empleado*/

public class ControladorEmpleado {
	private EntityManager em = EntityManagerUtil.getEntityManager();
	
	
	/*este metodo lista los empleados buscando por nombre o apellido, recibe un string 
	  con el nmbre O el apellido del empleado y lo busca, como pueden ser varios empleados
	  con el mismo nombre o apellido enotnces llena una matriz de string con los datos requeridos
	  y la retorna al metodo que la llama*/
	
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
