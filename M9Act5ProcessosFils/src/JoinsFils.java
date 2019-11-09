class TiradaDaus {
	private int tiradaDau;

	public TiradaDaus (int e) {
		tiradaDau=e;
	}

	public int getSumaTirada() {
		return tiradaDau;
	}

	public void setSumaTirada(int e) {
		tiradaDau += e;
	}
}


public class JoinsFils implements Runnable {

	private TiradaDaus xobj;

	public JoinsFils(TiradaDaus m) {
		xobj=m;
	}
	public void run(){
		try{
			Thread.sleep(1000);
			int resultatDau=(int) (Math.random()*6) + 1;
			xobj.setSumaTirada(resultatDau);
			System.out.println("Tirada fil " + Thread.currentThread().getName() + ": " + resultatDau);
		}catch (InterruptedException e){
		}
	}

	public static void main(String[] args) throws InterruptedException {
		TiradaDaus ans=new TiradaDaus(0);

		JoinsFils obj1 = new JoinsFils(ans);
		JoinsFils obj2 = new JoinsFils(ans);
		JoinsFils obj3 = new JoinsFils(ans);
		JoinsFils obj4 = new JoinsFils(ans);
		JoinsFils obj5 = new JoinsFils(ans);
		JoinsFils obj6 = new JoinsFils(ans);
		JoinsFils obj7 = new JoinsFils(ans);
		JoinsFils obj8 = new JoinsFils(ans);
		JoinsFils obj9 = new JoinsFils(ans);
		JoinsFils obj10 = new JoinsFils(ans);
		JoinsFils obj11 = new JoinsFils(ans);
		JoinsFils obj12 = new JoinsFils(ans);
		JoinsFils obj13 = new JoinsFils(ans);
		JoinsFils obj14 = new JoinsFils(ans);
		JoinsFils obj15 = new JoinsFils(ans);
		
		Thread fil_1 = new Thread(obj1);
		fil_1.setName("Dau1");
		Thread fil_2 = new Thread(obj2);
		fil_2.setName("Dau2");
		Thread fil_3 = new Thread(obj3);
		fil_3.setName("Dau3");
		Thread fil_4 = new Thread(obj4);
		fil_4.setName("Dau4");
		Thread fil_5 = new Thread(obj5);
		fil_5.setName("Dau5");
		Thread fil_6 = new Thread(obj6);
		fil_6.setName("Dau6");
		Thread fil_7 = new Thread(obj7);
		fil_7.setName("Dau7");
		Thread fil_8 = new Thread(obj8);
		fil_8.setName("Dau8");
		Thread fil_9 = new Thread(obj9);
		fil_9.setName("Dau9");
		Thread fil_10 = new Thread(obj10);
		fil_10.setName("Dau10");
		Thread fil_11 = new Thread(obj11);
		fil_11.setName("Dau11");
		Thread fil_12 = new Thread(obj12);
		fil_12.setName("Dau12");
		Thread fil_13 = new Thread(obj13);
		fil_13.setName("Dau13");
		Thread fil_14 = new Thread(obj14);
		fil_14.setName("Dau14");
		Thread fil_15 = new Thread(obj15);
		fil_15.setName("Dau15");
		
		fil_1.start();
		fil_2.start();
		fil_3.start();
		fil_4.start();
		fil_5.start();
		fil_6.start();
		fil_7.start();
		fil_8.start();
		fil_9.start();
		fil_10.start();
		fil_11.start();
		fil_12.start();
		fil_13.start();
		fil_14.start();
		fil_15.start();
		
		fil_1.join(); //Espera el fil_1 que el fil principal
		fil_2.join();
		fil_3.join();
		fil_4.join();
		fil_5.join();
		fil_6.join();
		fil_7.join();
		fil_8.join();
		fil_9.join();
		fil_10.join();
		fil_11.join();
		fil_12.join();
		fil_13.join();
		fil_14.join();
		fil_15.join();
		
		System.out.println("Total tirada: "+ ans.getSumaTirada());
		System.out.println("Final Fil Principal");

	}

}
