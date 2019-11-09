import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Supermercado implements Runnable {

	private Cliente cliente;

	public Supermercado(Cliente cliente) {

		this.cliente = cliente;
	}

	public Cliente getCliente() {
		return cliente;
	}
	
	@Override
	public void run() {

		System.out.print("Creando el " + cliente.getCliente() + " con " + cliente.getArrayArticulos().length + " art�culos (");
		
		// Imprime los segundos de cada art�culo del cliente
		for (int i = 0; i < cliente.getArrayArticulos().length; i++) {

			if ((i + 1) != cliente.getArrayArticulos().length) {
				System.out.print(cliente.getArrayArticulos()[i] + ", ");
			} else {
				System.out.print(cliente.getArrayArticulos()[i] + ")");
			}
		}
		System.out.println();

		System.out.println(cliente.getCliente() + " pasa por caja...");

		for (int i = 0; i < cliente.getArrayArticulos().length; i++) {

			// Para iniciar cada art�culo cada determinado tiempo,donde pasamos el n�mero que haya almacenado 
			// en el array de Art�culos del cliente
			try {
				Thread.sleep(cliente.getArrayArticulos()[i] * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			// Cuando estemos en la �ltima posici�n del array de los art�culos, imprimimos que se ha finalizado
			if (i == cliente.getArrayArticulos().length - 1) {

				System.out.println(cliente.getCliente() + " art�culo " + (i + 1) + "/" + cliente.getArrayArticulos().length + " (" + cliente.getArrayArticulos()[i] + " segundos)" + "...FINALIZADO");
			} else {

				System.out.println(cliente.getCliente() + " art�culo " + (i + 1) + "/" + cliente.getArrayArticulos().length + " (" + cliente.getArrayArticulos()[i] + " segundos)");
			}	
		}
	}

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		int cantidadCajas = 10;
		List<Cliente> clientes = new ArrayList<Cliente>();

		for (int i = 0; i < 50; i++) {  

			// Array para almacenar la cantidad de art�culos que tendr� un cliente
			int[] numArticulos = new int[(int) (Math.random() * 30 - 1) + 1];

			// Rellenamos cada art�culo con un n�mero aleatorio de 2 a 8 que ser�an los segundos
			for (int j = 0; j < numArticulos.length; j++) {
				numArticulos[j] = (int) (Math.random() * 8 - 1) + 1;
			}

			// A�adimos el cliente creado con el nombre y el array de n�mero de art�culos
			// donde tendr� el n�mero aleatorio para saber cuando se tiene que ejecutar el siguiente art�culo
			clientes.add(new Cliente("Cliente " + (i + 1) , numArticulos));
		}  

		// Pasamos el n�mero de hilos que queremos ejecutar
		ExecutorService executor = Executors.newFixedThreadPool(cantidadCajas);

		// Por cada cliente que pasamos, esperamos 3 segundos y ejecutamos los art�culos
		for (Cliente cliente: clientes) {

			Thread.sleep(3000);
			Runnable caja = new Supermercado(cliente);
			executor.execute(caja);
		}
		executor.shutdown();
	}
}