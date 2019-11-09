public class BombollaFils implements Runnable {

	private int[] array;
	private int inicio;
	private int finalArray;
	
	public BombollaFils (int[]array, int inicio, int finalArray){
		
		this.array = array;
		this.inicio = inicio;
		this.finalArray = finalArray;
	}
		
	public void bombollaOrdena(int[] array, int inicio, int finalArray) {
		//int contador = 0;
		//contador ++;
		//System.out.println("Llista"+contador);
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
//            System.out.println("ordenant...");	
//            for (int k = inicio; k <= finalArray; k++) {
//           	 System.out.print(array[k]+" ");
            }
            //System.out.println("\n");
    }
    
	
	//}

	public static void main(String[] args) {

		int[] arrayNum = generarNumeros(12);
		int primerCuarto = 0;
		int finalPrimerCuarto = (arrayNum.length / 4) - 1;

		int segundoCuarto = finalPrimerCuarto + 1;
		int finalSegundoCuarto = (arrayNum.length / 2) - 1;

		int inicioTercerCuarto = finalSegundoCuarto + 1;
		int finalTercerCuarto = arrayNum.length - 1;
		
		System.out.println("Creacio llista: ");
		for (int i = 0; i < arrayNum.length; i++) {
			System.out.print(arrayNum[i] + " ");
		}
		System.out.println("\n");
		
		Runnable hilo1 = new BombollaFils(arrayNum, primerCuarto, finalPrimerCuarto);
		Thread thread1 = new Thread(hilo1);
		
		Runnable hilo2 = new BombollaFils(arrayNum, segundoCuarto, finalSegundoCuarto);
		Thread thread2 = new Thread(hilo2);
		
		Runnable hilo3 = new BombollaFils(arrayNum, 6, 8);
		Thread thread3 = new Thread(hilo3);
		
		Runnable hilo4 = new BombollaFils(arrayNum, 9, 11);
		Thread thread4 = new Thread(hilo4);
		
		new Thread(thread1).start();
		
		new Thread(thread2).start();
		
		new Thread(thread3).start();
		
		new Thread(thread4).start();
				

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

// Retorna un array con números aleatorios del 1 al 100 excluyéndolo
	private static int[] generarNumeros(int size) {

		int[] array = new int[size];

		for (int i = 0; i < size; i++) {

			array[i] = (int) (Math.random() * (1000 - 1) + 1);
		}
		return array;
	}

//// Ordena un array que pasamos por parámetros de menor a mayor e imprime cada cambio que hace
//	private static void burbuja(int arreglo[], int inici, int fi) {
//
//		for (int i = inici; i < fi - 1; i++) {
//
//			for (int j = 0; j < arreglo.length - 1; j++) {
//
//				if (arreglo[j] < arreglo[j + 1]) {
//					int tmp = arreglo[j + 1];
//					arreglo[j + 1] = arreglo[j];
//					arreglo[j] = tmp;
//				}
//			}
//		}
//	}

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

