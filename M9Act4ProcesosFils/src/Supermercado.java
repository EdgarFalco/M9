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

		for (int i = 0; i < cliente.getArrayArticulos().length; i++) {

			// Para iniciar cada articulo cada determinado tiempo, utilizamos la funcion "iniciarArticulos"
			// Donde pasamos el numero que haya almacenado en el array de Articulos
			try {
				Thread.sleep(cliente.getArrayArticulos()[i] * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			// Cuando estemos en la ultima posicion del array de los articulos, imprimimos que se ha finalizado
			if (i == cliente.getArrayArticulos().length - 1) {

				System.out.println(cliente.getNombre() + " articulo " + (i + 1) + "/" + cliente.getArrayArticulos().length +
						" (" + cliente.getArrayArticulos()[i] + " segundos)" + "...FINALIZADO");
			} else {

				System.out.println(cliente.getNombre() + " articulo " + (i + 1) + "/" + cliente.getArrayArticulos().length +
						" (" + cliente.getArrayArticulos()[i] + " segundos)");
			}	
		}
	}

	public static void main(String[] args) throws InterruptedException {

		int cajas = 10;
		List<Cliente> clientes = new ArrayList<Cliente>();

		for (int i = 0; i < 10; i++) {  

			// Array para almacenar la cantidad de articulos que tendra un cliente
			int[] numArticulos = new int[(int)(Math.random() * 30 - 1) + 1];

			// Rellenamos cada articulo con un numero aleatorio de 2 a 8 que seraan los segundos
			for (int j = 0; j < numArticulos.length; j++) {
				numArticulos[j] = (int)(Math.random() * 8 - 1) + 1;
			}
			// Añadimos el cliente creado con el nombre y el array de numero de articulos
			// donde tendra el numero aleatorio para saber cuando se tiene que ejecutar el siguiente articulo
			clientes.add(new Cliente("Cliente "+ (i + 1) , numArticulos));
		}  

		long init = System.currentTimeMillis();

		// Pasamos el numero de hilos que queremos ejecutar
		ExecutorService executor = Executors.newFixedThreadPool(cajas);

		// Por cada cliente que pasamos, esperamos 3 segundos y ejecutamos los articulos
		for (Cliente cliente: clientes) {

			// Para crear un cliente esperamos 3 segundos
			Thread.sleep(3000);
			Runnable caja = new Supermercado(cliente, init);
			executor.execute(caja);
		}
		executor.shutdown();
	}
}


