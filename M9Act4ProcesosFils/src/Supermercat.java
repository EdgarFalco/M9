
public class Supermercat {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	
	
	
	class Caixer implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			
		}
	}
	class Client implements Runnable {
		
		private int numeroSegons;
		private int[] numeroArticles;
		private int contador = 0;
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			
		}
		public void mostraClient(int contador) {

			int numArtAleatorio = (int) (Math.random() * (30 - 1) + 1);
			this.numArticulos = new int[numArtAleatorio];

			
			System.out.print("Client: " + contador + " amb " + numArticles.length + " articles ");

			
			}

		
		
			System.out.println();
		}
	}

	
}
