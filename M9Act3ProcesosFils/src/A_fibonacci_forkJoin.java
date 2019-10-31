import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;


public class A_fibonacci_forkJoin extends RecursiveTask<Long> {
	long numero;
	public A_fibonacci_forkJoin(long numero){
		this.numero=numero;
	} 
	
	@Override
	protected Long compute() {
		
		double calcul = java.lang.Math.cos(54879854);
		if(numero <= 1) return numero;
		A_fibonacci_forkJoin fib1 = new A_fibonacci_forkJoin(numero-1);
		//fib1.fork();
		A_fibonacci_forkJoin fib2 = new A_fibonacci_forkJoin(numero-2);
		fib2.fork();
		return fib1.compute()+ fib2.join();
	}
	
	public static void main(String[] args){
		long inicio = System.currentTimeMillis();
		int NumeroFibonacci=35;
		
		ForkJoinPool pool = new ForkJoinPool();
		System.out.println("Calculat: " + pool.invoke(new A_fibonacci_forkJoin(NumeroFibonacci)));
		
		long fin = System.currentTimeMillis();
		double temps = (double) ((fin - inicio)/1000);
		
		System.out.println("Tarda: " + temps +" segons a calcular Fibonacci de " + NumeroFibonacci );
	}
}
