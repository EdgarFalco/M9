

	public class HeretaFil extends Thread {
	
		String strImprimir;
		public HeretaFil(String strP) {
		strImprimir=strP;
		}
	
	public void run(){
	for(int x=0;x<5;x++){
		System.out.println(strImprimir+ " " + x);
		}
	
	}

public static void main(String[] args) {

	Thread un = new HeretaFil("Fil 1");
	Thread dos = new HeretaFil("Fil 2");
	Thread tres = new HeretaFil("Fil 3");
	Thread quatre = new HeretaFil("Fil 4");
	Thread cinc = new HeretaFil("Fil 5");
	Thread sis = new HeretaFil("Fil 6");
	Thread set = new HeretaFil("Fil 7");
	Thread vuit = new HeretaFil("Fil 8");
	Thread nou = new HeretaFil("Fil 9");
	Thread deu = new HeretaFil("Fil 10");
	// Hem creat dos fils primer i segon, però no s’han executat.
	// Per poder−lo executar s’ha de cridar al mètode start()
	un.run();
	dos.run();
	tres.run();
	quatre.run();
	cinc.run();
	sis.run();
	set.run();
	vuit.run();
	nou.run();
	deu.run();
	

	System.out.println("Final Fil Principal");

	}
}
