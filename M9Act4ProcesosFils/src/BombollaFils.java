import java.io.IOException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
 
public class BombollaFils extends RecursiveTask<Short>{
	
	private static final int LLINDAR = 1;
	private short[] arr;
	private int inici, fi;
	
	//Constructor
		public BombollaFils(short[] arr, int inici, int fi) {
			this.arr = arr;
			this.inici = inici;
			this.fi = fi;
	}
		
    public static void main(String arg[]) throws IOException
    {
		// Crea un array
		short[] data = createArray(10);

		// Mira el n�mero de processadors
		System.out.println("Inici c�lcul");
		ForkJoinPool pool = new ForkJoinPool();

		int inici = 0;
		int fi = data.length;

		// Crea una tasca
		MaximTask tasca = new MaximTask(data, inici, fi);
		// Guarda el temps
		long time = System.currentTimeMillis();
		// crida la tasca i espera que es completin
		pool.invoke(tasca);
		// Guarda la tasca que ha sigut treballada amb fils
		tasca.join();

		System.out.println("Ordenant...");
		for (int k = 0; k < data.length; k++) {
			System.out.println(data[k] + " ");
		}
		System.out.println("Ordenacio completada");
    }
    
    //METODES
    
    //Metode, per crear un array on especifiques la mida
  	private static int[] createArray(int size) {
  		int[] ret = new int[size];
  		for (int i = 0; i < size; i++) {
  			ret[i] = (int) (1000 * Math.random());
  			if (i == ((int) (size * 0.9))) {
  				ret[i] = 105;
  			}
  		}
  		return ret;
  	}
      	 	
    //Metode, Bombolla Ordena de mes gran a m�s petit una llista
   	private static void burbuja(short arreglo[], int inici, int fi)
    {
          for(int i = inici; i < fi - 1; i++)
          {        	         	
              for(int j = 0; j < arreglo.length - 1; j++)
              {
                  if (arreglo[j] < arreglo[j + 1])
                  {
                      short tmp = arreglo[j+1];
                      arreglo[j+1] = arreglo[j];
                      arreglo[j] = tmp;
                  }
              }
              
          }
          
    }
 
  //Metode, crea dos tasques una comen�ara pel principi de la llista i l'altre pel mig de la llista
  	//i aplica un fork per treballarles amb fils per despres unirles amb join.
  	private void getMaxReq() {
  		MaximTask task1;
  		MaximTask task2;
  		int mig = (inici + fi) / 2 + 1;
  		task1 = new MaximTask(arr, 1, mig);
  		task1.fork();
  		task2 = new MaximTask(arr, 2, fi);
  		task2.fork();
  		task1.join(); 
  		task2.join();
  	}
  
  	//Metode RecursiveTask
	@Override
	protected Short compute() {
		if(inici >= LLINDAR){
			if (inici == 1) {
				burbuja(arr, 0, fi);
				} else {
					burbuja(arr, fi/2 + 1, fi);
				}
				
				burbuja(arr, inici, fi);
			}else{			
				getMaxReq();
			}
			return null;
	}
	


}
