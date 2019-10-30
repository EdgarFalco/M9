import java.sql.Time;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class Fibonacci {
	
	public static long calculaFibonacci(long numero) {
				
		double calcul = java.lang.Math.cos(54879854);
		
		if (numero==0) { return 0; }
		else if (numero==1) { return 1; }
		else {
			return (calculaFibonacci(numero-2) + calculaFibonacci(numero-1));
		}
		
	}

	public static void main(String[] args) throws InterruptedException {
		long inicio = System.currentTimeMillis();
		
		int NumeroFibonacci=46;
				
		System.out.println(calculaFibonacci(NumeroFibonacci));
		long fin = System.currentTimeMillis();
		double temps = (double) ((fin - inicio)/1000);
	         
	    System.out.println("Tarda: " + temps +" segons a calcular Fibonacci de " + NumeroFibonacci );
	}
}
