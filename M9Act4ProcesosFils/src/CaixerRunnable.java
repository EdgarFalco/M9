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
		System.out.println("La cajera " + Thread.currentThread().getName() +
			"COMIENZA A PROCESAR LA COMPRA DEL CLIENTE " + this.client.getNombre() +
			" EN EL TIEMPO: " + (System.currentTimeMillis() - this.initialTime) / 1000 + "seg");

		for (int i = 0; i < this.client.getCarroCompra().length; i++) { 			
                    // Es processa en x segons 
                    this.esperarXsegundos(client.getCarroCompra()[i]);
               	    System.out.println("Procesado el producto " + (i + 1) + " del " + this.client.getNombre()+ 
                    "->Tiempo: " + (System.currentTimeMillis() - this.initialTime) / 1000 + "seg");
		}

		System.out.println("La cajera " + Thread.currentThread().getName() + " HA TERMINADO DE PROCESAR " 
				+ this.client.getNombre() + " EN EL TIEMPO: "
				+ (System.currentTimeMillis() - this.initialTime) / 1000 + "seg");

	}

	private void esperarXsegundos(int segundos) {
		try {
			Thread.sleep(segundos * 1000);
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