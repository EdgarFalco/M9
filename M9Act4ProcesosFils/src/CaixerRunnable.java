public class CaixerRunnable implements Runnable {

	private Client client;
	private long initialTime;
	
	//CONSTRUCTOR
	public CaixerRunnable(Client client, long initialTime) {
		this.client = client;
		this.initialTime = initialTime;
	}


	@Override
	public void run() {
		System.out.println("El caixer " + Thread.currentThread().getName() +
			"Processa la compra del client " + this.client.getNom() +
			" EN EL TEMPS: " + (System.currentTimeMillis() - this.initialTime) / 1000 + "seg");

		for (int i = 0; i < this.client.getCarroCompra().length; i++) { 			
                    // Es processa en x segons 
                    this.esperarXsegundos(client.getCarroCompra()[i]);
               	    System.out.println("Proces del producte " + (i + 1) + " del " + this.client.getNom()+ 
                    "->Temps: " + (System.currentTimeMillis() - this.initialTime) / 1000 + "seg");
		}

		System.out.println("El caixer " + Thread.currentThread().getName() + " FI DEL PROCES " 
				+ this.client.getNom() + " EN EL TEMPS: "
				+ (System.currentTimeMillis() - this.initialTime) / 1000 + "seg");

	}

	private void esperarXsegundos(int segundos) {
		try {
			Thread.sleep(segundos * 3000);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}
	
	//GETTERS AND SETTERS
	public Client getCliente() {
		return client;
	}

	public void setCliente(Client cliente) {
		this.client = cliente;
	}

	public long getInitialTime() {
		return initialTime;
	}

	public void setInitialTime(long initialTime) {
		this.initialTime = initialTime;
	}

	
}     