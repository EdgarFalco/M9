
public class Client {
	private String nom;
	private int[] carroCompra;
	
	public Client(String nom, int[] carroCompra) {
		this.nom = nom;
		this.carroCompra = carroCompra;
	}

	public String getNombre() {
		return nom;
	}

	public void setNombre(String nombre) {
		this.nom = nom;
	}

	public int[] getCarroCompra() {
		return carroCompra;
	}

	public void setCarroCompra(int[] carroCompra) {
		this.carroCompra = carroCompra;
	}
	
}