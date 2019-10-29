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
	
	public static void temps() {
		//Mostra el resultat
		Calendar calendario = new GregorianCalendar();
		System.out.println("Hora execució tasca: "
				+ calendario.get(Calendar.HOUR_OF_DAY) + ":"
				+ calendario.get(Calendar.MINUTE) + ":"
				+ calendario.get(Calendar.SECOND));
		System.out.println("Tasca en execució");
		System.out.println("Execució acabada");
	}
	

	public static void main(String[] args) {
		System.out.println(calculaFibonacci(30));
		temps();
				
	}
		

		

	

	
	
}
