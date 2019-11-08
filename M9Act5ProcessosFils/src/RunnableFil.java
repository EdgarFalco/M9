import java.util.Set;



public class RunnableFil implements Runnable {

	String strImprimir;
	public RunnableFil(String strP) {
	strImprimir=strP;
	}

	public void run(){
		for(int x=0;x<5;x++){
		System.out.println(strImprimir+ " " + x);
		}
	
	}

	public static void main(String[] args) {

		//Creem dos objecte de la classe RunnableFil
		RunnableFil objRunnable1 = new RunnableFil("Fil 1");
		RunnableFil objRunnable2 = new RunnableFil("Fil 2");
		RunnableFil objRunnable3 = new RunnableFil("Fil 3");
		RunnableFil objRunnable4 = new RunnableFil("Fil 4");
		RunnableFil objRunnable5 = new RunnableFil("Fil 5");
		RunnableFil objRunnable6 = new RunnableFil("Fil 6");
		RunnableFil objRunnable7 = new RunnableFil("Fil 7");
		RunnableFil objRunnable8 = new RunnableFil("Fil 8");
		RunnableFil objRunnable9 = new RunnableFil("Fil 9");
		RunnableFil objRunnable10 = new RunnableFil("Fil 10");
		
		
		//Creem dos Fils i li passem per paràmetres els objecte de la classe RunnableFil
		Thread un = new Thread(objRunnable1);
		Thread dos = new Thread(objRunnable2);
		Thread tres = new Thread(objRunnable2);
		Thread cuatre = new Thread(objRunnable2);
		Thread cinc= new Thread(objRunnable2);
		Thread sis = new Thread(objRunnable2);
		Thread siete = new Thread(objRunnable2);
		Thread vuit= new Thread(objRunnable2);
		Thread nou = new Thread(objRunnable2);
		Thread deu = new Thread(objRunnable2);
		
		
		// Hem creat dos fils primer i segon, però no s’han executat.
		// Per poder−lo executar s’ha de cridar al mètode start()
		un.run();
		dos.run();
		tres.run();
		cuatre.run();
		cinc.run();
		sis.run();
		siete.run();
		vuit.run();
		nou.run();
		deu.run();

		System.out.println("Final Fil Principal");

	}
}
