public class Cliente {

	private String cliente;
	private int[] arrayArticulos;

	public Cliente() {	
	}

	public Cliente(String nombre, int[] arrayArticulos) {
		this.cliente = nombre;
		this.arrayArticulos = arrayArticulos;
	}

	public String getNombre() {
		return cliente;
	}

	public void setNombre(String nombre) {
		this.cliente = nombre;
	}

	public int[] getArrayArticulos() {
		return arrayArticulos;
	}

	public void setArrayArticulos(int[] arrayArticulos) {
		this.arrayArticulos = arrayArticulos;
	}	
}
