import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Supermercado implements Runnable {

	private Cliente cliente;
	private long tiempoInicial;

	public Supermercado(Cliente cliente, long tiempoInicial) {

		this.cliente = cliente;
		this.tiempoInicial = tiempoInicial;
	}

	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public long getTiempoInicial() {
		return tiempoInicial;
	}
	public void setTiempoInicial(long tiempoInicial) {
		this.tiempoInicial = tiempoInicial;
	}

	@Override
	public void run() {

		System.out.println("Creando el " + cliente.getNombre() + " con " + cliente.getArrayArticulos().length + " articulos " + Arrays.toString(cliente.getArrayArticulos()));
		System.out.println(cliente.getNombre() + " pasa por caja...");

		//for (int i = 0; i < cliente.getArrayArticulos().length; i++) {

			// Para iniciar cada articulo cada determinado tiempo, utilizamos la funcion "iniciarArticulos"
			// Donde pasamos el numero que haya almacenado en el array de Articulos
			try {
				Thread.sleep(cliente.getArrayArticulos()[i] * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}



	public static void main(String[] args) throws InterruptedException {

		int cajas = 10;
		List<Cliente> clientes = new ArrayList<Cliente>();

		for (int i = 0; i < 10; i++) {  

			
		}  

		long init = System.currentTimeMillis();

		
		ExecutorService executor = Executors.newFixedThreadPool(cajas);

		
		for (Cliente cliente: clientes) {

			
			Thread.sleep(3000);
			Runnable caja = new Supermercado(cliente, init);
			executor.execute(caja);
		}
		executor.shutdown();
	}
}


