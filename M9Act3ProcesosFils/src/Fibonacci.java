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
	
	public static void tempsInici() {
		//Mostra el resultat
		Calendar calendario = new GregorianCalendar();
		System.out.println("Hora inici tasca: "
				+ calendario.get(Calendar.HOUR_OF_DAY) + ":"
				+ calendario.get(Calendar.MINUTE) + ":"
				+ calendario.get(Calendar.SECOND));
		
		
	}
	
	public static void tempsFi() {
		//Mostra el resultat
		Calendar calendario = new GregorianCalendar();
		System.out.println("Hora fi tasca: "
				+ calendario.get(Calendar.HOUR_OF_DAY) + ":"
				+ calendario.get(Calendar.MINUTE) + ":"
				+ calendario.get(Calendar.SECOND));
			
	}
	

	public static void main(String[] args) {
		tempsInici();
		System.out.println(calculaFibonacci(35));
		tempsFi();
		
				
	}
		

		

	

	
	
}
