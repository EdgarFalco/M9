
import java.net.*;

public class TestInetAdress {
	
	public static void main (String[] args) {
		
		InetAddress dir = null;
		System.out.println("=====================================================");
		System.out.println("SORTIDA PER A LOCALHOST");
		
		try {
			//LOCALHOST
			//Guardem l'adre�a d'on obtindrem la informacio
			dir = InetAddress.getByName(args[0].toLowerCase());
			provaTots(dir);
			
			//URL www.google.com
			System.out.println("=====================================================");
			System.out.println("SORTIDA PER A URL");
			//Guardem l'adre�a d'on obtindrem la informacio
			dir = InetAddress.getByName(args[1].toString());
			provaTots(dir);
			
			//Array tipus InetAddress amb totes les adreces IP de google.com
			System.out.println("\tAdreces IP per a: "+dir.getHostName());
			InetAddress[] adreces = InetAddress.getAllByName(dir.getHostName());
			//Mostra les adreces
			for (int i=0; i<adreces.length; i++) 
				System.out.println("\t\t"+adreces[i].toString());
			System.out.println("=====================================================");
			
		} catch (UnknownHostException e1) {e1.printStackTrace();}
		
	}
	
	private static void provaTots(InetAddress dir) {
		
		InetAddress dir2;
		
		System.out.println("\tM�tode getByName(): " + dir);
		
		try {
			dir2 = InetAddress.getLocalHost();
			//Retorna l'adre�a de l'amfitri� local. Aix� s'aconsegueix recuperant el nom de l'amfitri� del sistema i, a continuaci�, resoldrem aquest nom en un InetAddress.
			System.out.println("\tM�tode getLocalHost(): " + dir2);
		} catch (UnknownHostException e) {e.printStackTrace();}
		
		//FEM SERVIR METODES DE LA CLASSE
		//Retorna el nom del host
		System.out.println("\tM�tode getHostName(): "+dir.getHostName());
		//Retorna la adre�a ip del host
		System.out.println("\tM�tode getHostAddress(): "+dir.getHostAddress());
		//Retorna el host i la adre�a
		System.out.println("\tM�tode toString(): " + dir.toString());
		//Retorna el nom de domini per la ip especificada
		System.out.println("\tM�tode getCanonicalHostName(): " + dir.getCanonicalHostName());
		
	}

}