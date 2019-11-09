public class Cliente {

	private String cliente;
	private int[] arrayArticulos;

	public Cliente() {	
	}

	public Cliente(String cliente, int[] arrayArticulos) {
		this.cliente = cliente;
		this.arrayArticulos = arrayArticulos;
	}

	public String getCliente() {
		return cliente;
	}

	public int[] getArrayArticulos() {
		return arrayArticulos;
	}
}