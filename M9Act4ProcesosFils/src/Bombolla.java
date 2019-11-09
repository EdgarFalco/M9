
import java.io.*;
 
public class Bombolla
{
    public static void main(String arg[]) throws IOException
    {
    	//Crea un array de mida 10 o la que especifiquem
    	int[] data = createArray(10);
    	//Ordena la llista i mostra el proces
    	burbuja(data);
    	//Mostra el resultat final de la ordenacio
    	System.out.println("Ordenacio Completada: ");
    	for(int i = 0;i < data.length; i++)
        {
            System.out.print(data[i] + " ");
        }
    }
    
    //METODES
    
    //Metode, per crear un array on especifiques la mida
  	private static int[] createArray(int size) {
  		int[] ret = new int[size];
  		for (int i = 0; i < size; i++) {
  			ret[i] = (int) (1000 * Math.random());
  			if (i == ((int) (size * 0.9))) {
  				
  			}
  		}
  		return ret;
  	}
      
  	
    //Metode, Bombolla Ordena de mes gran a m�s petit una llista
    private static void burbuja(int arreglo[])
    {
        for(int i = 0; i < arreglo.length - 1; i++)
        {        	         	
            for(int j = 0; j < arreglo.length - 1; j++)
            {
                if (arreglo[j] < arreglo[j + 1])
                {
                    int tmp = arreglo[ j + 1];
                    arreglo[j + 1] = arreglo[j];
                    arreglo[j] = tmp;
                }
            }
            System.out.println("ordenant...");	
            for (int k = 0; k < arreglo.length; k++) {
           	 System.out.print(arreglo[k]+" ");
            }
            System.out.println("\n");
        }
    }
}

