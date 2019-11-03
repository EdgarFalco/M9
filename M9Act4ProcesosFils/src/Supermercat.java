import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Supermercat {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		//Llançara x fils
		final ScheduledExecutorService schExService = Executors.newScheduledThreadPool(5);

		final Runnable ob = new Supermercat().new Client();
		
		//Esperara 3 segons a crear un altre client
		schExService.scheduleWithFixedDelay(ob, 1, 3, TimeUnit.SECONDS);
		
		schExService.awaitTermination(35, TimeUnit.SECONDS);
				
		//Tancara els fils oberts
		schExService.shutdownNow();
	}
	
	

	class Caja implements Runnable {

		@Override
		public void run() {
			
			
		}
		
	}

	
	class Client implements Runnable {
		
		private int numeroSegons;
		private int[] numeroArticles;
		private int contador = 0;

		public void mostraClient(int contador) {

			int numeroArticlesAleatori = (int) (Math.random() * (30 - 1) + 1);
			this.numeroArticles = new int[numeroArticlesAleatori];

			
			System.out.print("Client: " + contador + " amb " + numeroArticles.length + " articles ");

			for (int i = 0; i < numeroArticles.length; i++) {
				numeroSegons = (int) (Math.random() * (8 - 2) + 2);
				numeroArticles[i] = numeroSegons;
			}

			
			for (int i = 0; i < numeroArticles.length; i++) {

				if (i + 1 != numeroArticles.length) {
					System.out.print(numeroArticles[i] + ", ");
				} else {
					System.out.print(numeroArticles[i]);
				}
			}
			System.out.println();
		}

		public void pasaPerCaixa(int contador) throws InterruptedException {

			for (int i = 0; i < numeroArticles.length; i++) {

				Thread.sleep(numeroArticles[i]);
				System.out.println("Client " + contador + " passa per caixa...");
				System.out.println("Client " + contador  + " article " + (i + 1) + "/" + numeroArticles.length + " (" + numeroArticles[i] + "segundos)");
			}
		}

		@Override
		public void run() {
			contador++;
			mostraClient(contador);
		}
		
		}
	}
