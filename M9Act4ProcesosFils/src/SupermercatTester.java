import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SupermercatTester {

	private static final int fils = 2;

    public static void main(String[] args) {

        ArrayList<Client>clients = new ArrayList<Client>();
        clients.add(new Client("Client 1", new int[] { 2, 2, 1, 5, 2 })); 
        clients.add(new Client("Client 2", new int[] { 1, 1, 5, 1, 1 })); 
        clients.add(new Client("Client 3", new int[] { 5, 3, 1, 5, 2 })); 
        clients.add(new Client("Client 4", new int[] { 2, 4, 3, 2, 5 })); 
        clients.add(new Client("Client 5", new int[] { 1, 3, 2, 2, 3 })); 
        clients.add(new Client("Client 6", new int[] { 4, 2, 1, 3, 1 })); 
        clients.add(new Client("Client 7", new int[] { 3, 3, 2, 4, 7 })); 
        clients.add(new Client("Client 8", new int[] { 6, 1, 3, 1, 3 })); 
        
        
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


