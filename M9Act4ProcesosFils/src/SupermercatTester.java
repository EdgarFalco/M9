import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SupermercatTester {

	private static final int fils = 2;

    public static void main(String[] args) {

        ArrayList<Client>clients = new ArrayList<Client>();
        for (int i = 0; i < 2; i++) {
			clients.add(new Client("Client "+ i, new int[]{ 2, 2, 1, 5, 2 }));
		}
        
        
        
        long init = System.currentTimeMillis();  // Inici
        
        ExecutorService executor = Executors.newFixedThreadPool(fils);
        for (Client cliente: clients) {
            Runnable cajera = new CaixerRunnable(cliente, init);
            executor.execute(cajera);
        }
        executor.shutdown();	// Tanca Executor
        while (!executor.isTerminated()) {
        	// Espera que acavin d'executarse tots els processos 
        	// per passar les següents instruccions
        }
        
        long fin = System.currentTimeMillis();	
        System.out.println("Tiempo total de procesamiento: "+(fin-init)/1000+" Segundos");
    }
}


