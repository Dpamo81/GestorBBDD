import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class Start {
	
	public static void crearTabla(BBDD db){
		String tabla="";
		
		Scanner entradaDeDatos = new Scanner(System.in);
		boolean estado = false;
		System.out.println("Introduzca el nombre de la tabla.");
		tabla= entradaDeDatos.next();
		
		estado =db.T(tabla);
		if (!estado) {
			System.out.println("Tabla Creada.");
		}else {
			System.err.println("Error al Crear la tabla.");
		}
	}
	public static void eliminarTabla(BBDD db) {
		String tabla="";
		
		Scanner entradaDeDatos = new Scanner(System.in);
		boolean estado = false;
		System.out.println("Introduzca la tabla a borrar.");
		tabla = entradaDeDatos.next();
		
		estado = db.H(tabla);
		if (!estado) {
			System.out.println("Tabla eliminada correctamente.");
		}else {
			System.err.println("Error al eliminar la tabla.");
		}
	}
	public static void insertarMontana(BBDD db) {
		Scanner entradaDeDatos = new Scanner(System.in);
		Montana m = new Montana();
		boolean estado = false;
		String tabla="";
		
		System.out.println("Introduzca la tabla para insertar campo.");
		tabla= entradaDeDatos.next();
		System.out.println("Introduzca el nombre de la montaña");
		m.setNombre(entradaDeDatos.next());
		System.out.println("Introduzca la altura de la montaña");
		m.setAltura(entradaDeDatos.nextInt());
		System.out.println("Introduzca el año de la primera ascensión");
		m.setPrimeraAscension(entradaDeDatos.nextInt());
		System.out.println("Introduzca la región");
		m.setRegion(entradaDeDatos.next());
		System.out.println("Introduzca el País de la montaña");
		m.setPais(entradaDeDatos.next());
		
		estado=db.C(m, tabla);
		
		if (!estado) {
			System.out.println("Insertado correctamente");
		}else {
			System.err.println("Error al insertar los datos");
		}
	}

	public static void leerTabla(BBDD db) {
		int i =0;
		String tabla = "";
		Scanner entradaDeDatos = new Scanner(System.in);
		
		System.out.println("Inserte el nombre de la tabla a leer: ");
		tabla = entradaDeDatos.next();
		
		ArrayList<Montana> montana = new ArrayList<Montana>();
		montana = db.R("SELECT * FROM " + tabla);
		
		for(i=0; i<montana.size();i++) {
			System.out.println(montana.get(i).getId()+" / " + montana.get(i).getNombre()+" / " + montana.get(i).getAltura()+" / " +montana.get(i).getPrimeraAscension()+
					" / " + montana.get(i).getRegion()+" / " + montana.get(i).getPais());
			System.out.println("");
		}
	}
	public static void leerFila(BBDD db) {
		Scanner entradaDeDatos = new Scanner(System.in);
		ArrayList<Montana> montana = new ArrayList<Montana>();
		boolean estado = false;
		int id= 0;
		int i= 0;
		String tabla = "";
		
		leerTabla(db);
		System.out.println("*****");
		System.out.println("Introduce el id de la Montaña a leer.");
		id = entradaDeDatos.nextInt();
		
		montana = db.R("SELECT * FROM montana WHERE id= " + id);
		
		for(i=0; i<montana.size();i++) {
			 while(montana.get(i).getId()== id) {
				System.out.println(montana.get(i).getId()+" / " + montana.get(i).getNombre()+" / " + montana.get(i).getAltura()+" / " +montana.get(i).getPrimeraAscension()+
						" / " + montana.get(i).getRegion()+" / " + montana.get(i).getPais());
				System.out.println(""); 
				break;
			 }
		}
	}
	public static void modificarMontana(BBDD db) {
		Scanner entradaDeDatos = new Scanner(System.in);
		Montana m = new Montana();
		boolean estado = false;
		int opcion =0;
		
		leerTabla(db);
		System.out.println("***********************");
		System.out.println("");
		System.out.println("Introduzca el id de la montaña que quiere editar");
		m.setId(entradaDeDatos.nextInt());
		System.out.println("¿Que quiere hacer?");
		System.out.println("   1.- Modificar montaña.");
		System.out.println("   2.- Modificar 1 campo.");
		
		opcion = entradaDeDatos.nextInt();
		
		if(opcion ==1) {
			
			System.out.println("Introduzca el Nombre de la montaña que quiere editar");
			m.setNombre(entradaDeDatos.next());
			System.out.println("Introduzca la altura de la a que quiere editar");
			m.setAltura(entradaDeDatos.nextInt());
			System.out.println("Introduzca el año de la primera ascensión de la montaña que quiere editar");
			m.setPrimeraAscension(entradaDeDatos.nextInt());
			System.out.println("Introduzca la región de la montaña que quiere editar");
			m.setRegion(entradaDeDatos.next());
			System.out.println("Introduzca el país de la montaña que quiere editar");
			m.setPais(entradaDeDatos.next());
			
			estado= db.U(m, "montana");
			if(!estado) {
				System.out.println("Montaña actualizada correctamente");
			}else {
				System.err.println("Error al actualizar la montaña");
			}	
		}else if (opcion==2 ){
			System.out.println("Elija que campo quiere modificar.");
			
			int id =m.getId();
			int cont=0;
			int i =0;
			
			ArrayList<Montana> mon = new ArrayList<Montana>();
			
			mon = db.R("SELECT * FROM montana");
			
			for (i=0; i<mon.size(); i++) {
				
				if (id == mon.get(i).getId()) {
					cont = 1;
					System.out.println("1- Nombre: " + mon.get(i).getNombre());
					m.setNombre(mon.get(i).getNombre());
					System.out.println("2- Altura: " + mon.get(i).getAltura());
					m.setAltura(mon.get(i).getAltura());
					System.out.println("3- Primera Ascension: " + mon.get(i).getPrimeraAscension());
					m.setPrimeraAscension(mon.get(i).getPrimeraAscension());
					System.out.println("4- Región: " + mon.get(i).getRegion());
					m.setRegion(mon.get(i).getRegion());
					System.out.println("5- País: " + mon.get(i).getPais());
					m.setPais(mon.get(i).getPais());
					System.out.println("opcion: ");
					opcion =entradaDeDatos.nextInt();
					
				}
			}
			if (cont ==1) {
				if (opcion == 1) {
					System.out.println("Escriba el nuevo nombre.");
					entradaDeDatos.nextLine();
					m.setNombre(entradaDeDatos.nextLine());
				}else if (opcion == 2) {
					System.out.println("Escriba la nueva altura.");
					m.setAltura(entradaDeDatos.nextInt());
				}else if (opcion == 3) {
					System.out.println("Escriba la primera ascension.");
					m.setPrimeraAscension(entradaDeDatos.nextInt());
				}else if (opcion == 4) {
					System.out.println("Escriba la nueva región.");
					entradaDeDatos.nextLine();
					m.setRegion(entradaDeDatos.nextLine());
				}else if (opcion == 5) {
					System.out.println("Escriba el nuevo País.");
					entradaDeDatos.nextLine();
					m.setPais(entradaDeDatos.nextLine());
				}else {
					System.out.println("Debe introducir uno de los cambios.");
				}
				
				estado= db.U(m, "montana");
				if(!estado) {
					System.out.println("Montaña actualizada correctamente");
				}else {
					System.err.println("Error al actualizar la montaña");
				}	

					
			}else {
				System.err.println("Elija uno de los campos mostrados.");
			}
			
		}
	}
	
	
	public static void borrarMontana(BBDD db) {
		Scanner entradaDeDatos = new Scanner(System.in);
		Montana m = new Montana();
		boolean estado= false;
		int id=0;
		
		leerTabla(db);
		System.out.println("*****");
		System.out.println("Introduce el id de la montaña que quiere eliminar: ");
		id = entradaDeDatos.nextInt();
		
		estado=db.D("DELETE FROM montana WHERE id= " + id);
		
		if (!estado) {
			System.out.println("Fila borrada correctamente.");
		}else {
			System.err.println("Error al borrar la fila.");
		}	
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner entradaDeDatos = new Scanner(System.in);
		
		BBDD db = new BBDD("localhost","uf2_ej2_accesoadatos","root","");
		
		String nombre="";
		int altura=0;
		int pAscension=0;
		String region="";
		String pais ="";
		int opcion=0;
		int correcto=0;
		
		do {
			System.out.println("********************************");
			System.out.println("******   Acceso a datos   ******");
			System.out.println("********************************");
			
			System.out.println("");
			System.out.println("Selecione una opcion del menu.");
			System.out.println("");
			System.out.println("     1)Crear tabla.");
			System.out.println("     2)Añadir fila.");
			System.out.println("     3)Leer tabla.");
			System.out.println("     4)Leer fila.");
			System.out.println("     5)Modificar fila.");
			System.out.println("     6)Eliminar fila.");
			System.out.println("     7)Eliminar tabla.");
			System.out.println("");
			System.out.println("     0)Salir.");
			System.out.println("");
			System.out.print("Opcion:  ");
			opcion= entradaDeDatos.nextInt();
			System.out.println("");
			
			if (opcion==1) {	
				System.out.println("*****");
				crearTabla(db);
			}else if (opcion == 2) {
				System.out.println("*****");
				insertarMontana(db);			
			}else if (opcion == 3) {
				System.out.println("*****");
				leerTabla(db);
			}else if (opcion == 4){
				System.out.println("*****");
				leerFila(db);
			}else if (opcion == 5){		
				System.out.println("*****");
				modificarMontana(db);			
			}else if (opcion == 6) {			
				System.out.println("*****");
				borrarMontana(db);
			}else if (opcion == 7) {
				System.out.println("*****");
				eliminarTabla(db);
			}
		}while (opcion!=0);
			System.out.println("Salió del programa");
	}

}

