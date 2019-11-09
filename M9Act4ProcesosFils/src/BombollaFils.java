public class BombollaFils implements Runnable {

	private int[] array;
	private int inicio;
	private int finalArray;
	//Constructor
	public BombollaFils (int[]array, int inicio, int finalArray){
		
		this.array = array;
		this.inicio = inicio;
		this.finalArray = finalArray;
	}
	
	//Ordena de mes gran a mes petit
	public void bombollaOrdena(int[] array, int inicio, int finalArray) {
		
		for(int i = inicio; i < finalArray; i++)
        {        	         	
            for(int j = inicio; j < finalArray; j++)
            {
                if (array[j] < array[j + 1])
                {
                    int tmp = array[ j + 1];
                    array[j + 1] = array[j];
                    array[j] = tmp;
                }
            }

       }
    }
	
	public static void main(String[] args) {
		//Creem array
		int[] arrayNum = generarNumeros(12);
		
		//Controlem les posicions de l'array
		int primerCuarto = 0;
		int finalPrimerCuarto = (arrayNum.length / 4) - 1;

		int segundoCuarto = finalPrimerCuarto + 1;
		int finalSegundoCuarto = (arrayNum.length / 2) - 1;

		int inicioTercerCuarto = finalSegundoCuarto + 1;
		int finalTercerCuarto = finalPrimerCuarto + finalSegundoCuarto +1;
		
		int inicioUltimo = finalTercerCuarto + 1;
		int finalFinal = arrayNum.length - 1;
		
		//Mostra la llista creada
		System.out.println("Creacio llista: ");
		for (int i = 0; i < arrayNum.length; i++) {
			System.out.print(arrayNum[i] + " ");
		}
		System.out.println("\n");
		
		Runnable hilo1 = new BombollaFils(arrayNum, primerCuarto, finalPrimerCuarto);
		Thread thread1 = new Thread(hilo1);
		
		Runnable hilo2 = new BombollaFils(arrayNum, segundoCuarto, finalSegundoCuarto);
		Thread thread2 = new Thread(hilo2);
		
		Runnable hilo3 = new BombollaFils(arrayNum, inicioTercerCuarto, finalSegundoCuarto);
		Thread thread3 = new Thread(hilo3);
		
		Runnable hilo4 = new BombollaFils(arrayNum, inicioUltimo, finalFinal);
		Thread thread4 = new Thread(hilo4);
		//Inicia fils
		new Thread(thread1).start();
		
		new Thread(thread2).start();
		
		new Thread(thread3).start();
		
		new Thread(thread4).start();
				
		//Mostra el resultat de les 4 llistes ordenades
		System.out.println("Part 1 Llista ordenada: ");
		for (int i = 0; i <= 2; i++) {
			System.out.print(arrayNum[i] + " ");
		}
		
		System.out.println("\n");
		
		System.out.println("Part 2 Llista ordenada: ");
		for (int i = 3; i <= 5; i++) {
			System.out.print(arrayNum[i] + " ");
		}
		
		System.out.println("\n");
		
		System.out.println("Part 3 Llista ordenada: ");
		for (int i = 6; i <= 8; i++) {
			System.out.print(arrayNum[i] + " ");
		}
		
		System.out.println("\n");
		
		System.out.println("Part 4 Llista ordenada: ");
		for (int i = 9; i <= 11; i++) {
			System.out.print(arrayNum[i] + " ");
		}
	}

// Retorna un array amb numeros aleatoris de 0 al 999
	private static int[] generarNumeros(int size) {

		int[] array = new int[size];

		for (int i = 0; i < size; i++) {

			array[i] = (int) (Math.random() * (1000 - 1) + 1);
		}
		return array;
	}
	
	//Medore run, executa el fil
	@Override
	public void run() {
		bombollaOrdena(this.array, this.inicio, this.finalArray);
		
//		System.out.println("Ordenat: ");
//		for (int i = inicio; i <= finalArray; i++) {
//			
//			System.out.print(array[i]+" ");
//			
//		}
//		System.out.println();
//		
	}
}

