import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class BBDD {
	private static final String driver = "com.mysql.jdbc.Driver";
//	Atributos
	private String servidor;
	private String nombreBD;
	private String usuario;
	private String password;
//	Creamos un constructor;
	public BBDD (String servidor, String nombreBD, String usuario, String password) {
		this.servidor = servidor;
		this.nombreBD = nombreBD;
		this.usuario = usuario;
		this.password = password;
//		Cargamos el driver para conectarnos en la bbdd.
		try {
			DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());
		} catch (SQLException e) {
			System.err.println("Error al cargar el driver.");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	metodo crear tabla.
	public boolean T(String tabla) {
		boolean result = false;
		String consulta="";
		Connection conexion = null;
		Statement s= null;
//		Creamos la conexion a la BBDD		
		try {
			conexion =(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/" + this.nombreBD, usuario, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("Error en la conexión");
			e.printStackTrace();
		}
//		Creamos el objeto de tipo Statement para crear la peticion consulta.
		try {
			s = (Statement)conexion.createStatement();
		} catch (SQLException e) {
			System.err.println("Error en el statement");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		Creamos la consulta.
		try {
			consulta ="CREATE TABLE "+ tabla + "(" + "id SERIAL PRIMARY KEY," + "nom TEXT," + "altura INT,"+"primeraAscension INT," +"region TEXT,"+ "pais TEXT)";
//		Introducimos la consulta dentro de la peticion.
			result = s.execute(consulta);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("Error en la consulta.");
			e.printStackTrace();
		}
//		Cerramos la peticion de consulta
		if(s!=null) {
			try {
				s.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
//		devolvemos el resultado.
		return result;
	}
//	Metodo para eliminar a tabla.
	public boolean H(String tabla) {
		boolean result = false;
		String consulta="";
		Connection conexion = null;
		Statement s= null;
//		Creamos la conexion.
		try {
			conexion = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/"+ this.nombreBD,usuario,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		abrimos la consulta.
		try {
			s = (Statement)conexion.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		generamos consulta.
		try {
			consulta = "DROP TABLE " + tabla;
			result = s.execute(consulta);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		Cerramos la solicitud.
		if(s!=null) {
			try {
				s.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
		return result;
	}
//	metodo para insertar.
	public boolean C(Montana m, String tabla) {
		String consulta="";
		Connection conexion = null;
		Statement s = null;
		boolean result = false;	

		try {
			conexion = (Connection)DriverManager.getConnection("jdbc:/mysql://localhost:3306/" + this.nombreBD, usuario, password);
			s= (Statement)conexion.createStatement();
			consulta = "INSERT INTO" + tabla + "(nombre,altura,priemraAscension,region,pais) VALUES('"+m.getNombre()+"','"+m.getAltura()+"','"+m.getPrimeraAscension()+"','"+m.getRegion()+"','"+m.getPais()+")";
			result = s.execute(consulta);
			conexion.close();
			s.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
// metodo para mostrar
	public ArrayList<Montana> R(String consulta){
		Montana m = null;
		ArrayList<Montana> aM = new ArrayList<Montana>();
		Connection conexion = null;
		Statement s = null;
		ResultSet rs = null;
		
		try {
			conexion = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/" + this.nombreBD, usuario, password);
			s= (Statement)conexion.createStatement();
			rs=s.executeQuery(consulta);
			while(rs.next()) {
				m= new Montana();
				m.setNombre(rs.getString("nombre"));
				m.setAltura(rs.getInt("altura"));
				m.setPrimeraAscension(rs.getInt("primeraAscencion"));
				m.setRegion(rs.getString("region"));
				m.setPais(rs.getString("pais"));				
				aM.add(m);
			}
			conexion.close();
			s.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return aM;
	}
//	metodo modificar montaña
	public boolean U(Montana m, String tabla) {
		Connection conex = null;
		Statement s= null;
		String consulta = "";
		boolean result = false;
		
		try {
			conex= (Connection)DriverManager.getConnection("jdbc:mysql://localhosts:3308/" + this.nombreBD,usuario,password);
			s =(Statement)conex.createStatement();
			consulta = "UPDATE montana SET nombre='"+m.getNombre()+"', altura="+m.getAltura()+", primeraAscension="+m.getPrimeraAscension()+", region='"+m.getRegion()+"','"+m.getPais()+"'WHERE id="+m.getId();
			result =s.execute(consulta);
		if (s!=null) {
			s.close();
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
//	metodo Borrar
	public boolean D(String consulta) {
		Connection conexion= null;
		Statement s= null;
		boolean result = false;
		try {
			conexion = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/"+ this.nombreBD, usuario, password);
			s= (Statement)conexion.createStatement();
			result = s.execute(consulta);
			conexion.close();
			s.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
