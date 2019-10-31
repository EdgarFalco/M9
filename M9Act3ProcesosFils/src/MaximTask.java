
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ForkJoinPool;

public class MaximTask extends RecursiveTask<Short> {
//private static final int LLINDAR=10000000;
	private static final int LLINDAR = 999999999;
	private short[] arr;
	private int inici, fi;
	//Contara les vegades que s'executa el compute
	private static int contador=0;
	
	//Constructor
	public MaximTask(short[] arr, int inici, int fi) {
		this.arr = arr;
		this.inici = inici;
		this.fi = fi;
	}
	//Metode, agafa el valor maxim del array
	private short getMaxSeq() {
		short max = arr[inici];
		for (int i = inici + 1; i < fi; i++) {
			if (arr[i] > max) {
				max = arr[i];
			}
		}
		return max;
	}
	//Metode, crea dos tasques una començara pel principi de la llista i l'altre pel mig de la llista
	//i aplica un fork per treballarles amb fils per despres unirles amb join, i retornar la tasca mes alta de les dos.
	private short getMaxReq() {
		MaximTask task1;
		MaximTask task2;
		int mig = (inici + fi) / 2 + 1;
		task1 = new MaximTask(arr, inici, mig);
		task1.fork();
		task2 = new MaximTask(arr, mig, fi);
		task2.fork();
		return (short) Math.max(task1.join(), task2.join());
	}

	@Override
	//Modifica el metode compute, retornara el maxim de la llista o el maxim de les tasques del metode getMaxReq
	protected Short compute() {
		System.out.println("Comptador" + (contador++) + " Inici: " + inici + " Fi: " + fi);
		
		if(fi - inici <= LLINDAR){
			
			return getMaxSeq();
		}else{
			
			return getMaxReq();
		}
	}

	public static void main(String[] args) {
		//Crea un array
		short[] data = createArray(100000000);
		
		// Mira el número de processadors
		System.out.println("Inici càlcul");
		ForkJoinPool pool = new ForkJoinPool();
		
		int inici=1;
		int fi= data.length;
		
		//Crea una tasca
		MaximTask tasca = new MaximTask(data, inici, fi);
		//Guarda el temps
		long time = System.currentTimeMillis();
		// crida la tasca i espera que es completin
		int result1 = (int)pool.invoke(tasca);
		//Guarda la tasca que ha sigut treballada amb fils
		int result= tasca.join();
		// Mostra el màxim
		System.out.println("Temps utilitzat:" + (System.currentTimeMillis() - time));
		System.out.println ("Màxim es " + result);
	}
	
	//Metode, per crear un array on especifiques la mida, genera numeros del 0 al 999
	private static short[] createArray(int size) {
		short[] ret = new short[size];
		for (int i = 0; i < size; i++) {
			ret[i] = (short) (1000 * Math.random());
			if (i == ((short) (size * 0.9))) {
				ret[i] = 105;
			}
		}
		return ret;
	}
	
}
