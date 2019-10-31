
import java.io.*;
 
public class Bombolla
{
    public static void main(String arg[]) throws IOException
    {
    	int[] data = createArray(100);
    	System.out.println(burbuja(data));
        
    }
    
  //Metode, per crear un array on especifiques la mida, genera numeros del 0 al 999
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
    
        
    //Metode, Bombolla
    private static void burbuja(int arreglo[])
    {
        for(int i = 0; i < arreglo.length - 1; i++)
        {
            for(int j = 0; j < arreglo.length - 1; j++)
            {
                if (arreglo[j] < arreglo[j + 1])
                {
                    int tmp = arreglo[j+1];
                    arreglo[j+1] = arreglo[j];
                    arreglo[j] = tmp;
                }
            }
        }
        for(int i = 0;i < arreglo.length; i++)
        {
            System.out.print(arreglo[i]+"\n");
        }
    }
}
