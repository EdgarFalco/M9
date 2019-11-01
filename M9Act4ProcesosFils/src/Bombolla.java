
import java.io.IOException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
 
public class Bombolla extends RecursiveTask<Short>
{
	private static final int LIMITAR = 10000;
	short[] array;
	private int inici, fi;
	
	public Bombolla(short[] data, int inici, int fi) {
		this.array = data;
		this.inici = inici;
		this.fi = fi;
	}
  //Metode, per crear un array on especifiques la mida
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
  	
	private Short getArrays() {
		Bombolla bombolla1;
		Bombolla bombolla2;
		
		int mig = (inici + fi) / 2 + 1;
		
		bombolla1 = new Bombolla(array, inici, mig);
		bombolla1.fork();
		
		bombolla2 = new Bombolla(array, mig, fi);
		bombolla2.fork();
		
		return (short) Math.max(bombolla1.join(),bombolla2.join());
	}
	
	@Override
	protected Short compute() {
	
	if(fi - inici <= LIMITAR){
				
				return getArrays();
			}else{
				
				return null;
			}
		

	}
        
    //Metode, Bombolla
    private static short[] burbuja(short arreglo[])
    {
        for(int i = 0; i < arreglo.length - 1; i++)
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
            //Mostra l'array quan l'algoritme esta ordenant
            System.out.println("ordenant...");
            for (int k = 0; k < arreglo.length; k++) {
             	System.out.print(arreglo[k]+" ");
            }
            System.out.println("\n");
            
        }
        return arreglo;
    }
    
    public static void main(String arg[]) throws IOException
    {
    	    	    	
    	short[] data = createArray(10000);
    	
    	// Mira el número de processadors
		System.out.println("Inici càlcul");
		ForkJoinPool pool = new ForkJoinPool();
		
		int inici=1;
		int fi= data.length;
		
		Bombolla tasca1 = new Bombolla(data, inici, fi);
		
		// crida la tasca i espera que es completin
		int result1 = (int)pool.invoke(tasca1);
		//Guarda la tasca que ha sigut treballada amb fils
		int result= tasca1.join();
		
    	//Mostra el resultat final
    	System.out.println("Final: ");
    	
    	for(int i = 0;i < data.length; i++)
        {
    		System.out.print(data[i]+" ");
        }
    	
    	
		System.out.println("\nCompletat");
		System.out.println ("Màxim es " + result);
    }
    
         
}

