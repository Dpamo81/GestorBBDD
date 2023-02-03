
public class Montana {
//	Creamos los atributos de la clase.
	protected int id;
	protected String nombre;
	protected int altura;
	protected int primeraAscension;
	protected String region;
	protected String pais;

//Getters and setters.
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getAltura() {
		return altura;
	}
	public void setAltura(int altura) {
		this.altura = altura;
	}
	public int getPrimeraAscension() {
		return primeraAscension;
	}
	public void setPrimeraAscension(int primeraAscension) {
		this.primeraAscension = primeraAscension;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String Pais) {
		this.pais = pais;
	}
//	Metodos.
	public String print() {
		String cadena = this.getNombre() + " " + this.getAltura() + " " + this.getPrimeraAscension() + " " + this.getRegion() + " " + this.getPais();
		return cadena;
	}
	
}
