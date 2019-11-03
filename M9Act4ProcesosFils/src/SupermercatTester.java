import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SupermercatTester {
	//Especifica els fils
	private static final int fils = 2;

    public static void main(String[] args) {
    	
    	ExecutorService executor = Executors.newFixedThreadPool(fils);
    	 	    	
    	//Creem "x" clients
        ArrayList<Client>clients = new ArrayList<Client>();
        for (int i = 0; i < 500; i++) {
			clients.add(new Client("Client "+ i, new int[]{ 2, 2, 1, 5, 2 }));
			
		}
                
        long init = System.currentTimeMillis();  // Inici
        
        
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
        System.out.println("Temps total: "+(fin-init)/1000+" Segons");
    }
}


