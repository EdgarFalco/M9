
import java.util.concurrent.ForkJoinPool; 
import java.util.concurrent.RecursiveTask;

public class CalculaNombres extends RecursiveTask<Double>{
	//ATRIBUTS
	double numero1;
	double numero2;
	
	//CONSTRUCTOR
	public CalculaNombres(double numero1, double numero2){
		this.numero1 = numero1;
		this.numero2 = numero2;
	}    
	//Calclul
	public static double calcul(double numero3, double numero4) {
		if (numero4 > 0) {
			return numero3 * calcul(numero3, numero4 - 1);
		}
		else if (numero4 == 0) {
			return 1.0;
		}
		else {
			return 1.0 / numero3 * calcul(1.0 / numero3, -numero4 - 1); 
		}
	}
	@Override
	protected Double compute() {
		


	}
	public static void main(String[] args){
		
	}
}