import java.net.*;
import java.io.*;

public class ServidorTCP4 {

	public static void main (String[] args) throws Exception {
		
		int numeroClients = Integer.parseInt(args[0]);
		int numPort = 60000;
		ServerSocket servidor = new ServerSocket(numPort);
		String cadena = "";
		int contador = 0;
		boolean si = true;
		
		
			System.out.println("Esperant connexió... ");
			
		while(si) {
			if(contador >= numeroClients) {
				si = false;
				System.out.println("Ja he atés 3 clients, no puc atendre mes clients");
				break;
			}
			Socket clientConnectat = servidor.accept();
			
			PrintWriter fclient = new PrintWriter(clientConnectat.getOutputStream(), true);
			fclient.println("Benvingut client " + contador);
			
			System.out.println("Client connectat... ");
			contador++;
			
			//FLUX DE SORTIDA AL CLIENT
			PrintWriter fsortida = new PrintWriter(clientConnectat.getOutputStream(), true);
			
			
			
			
			//FLUX D'ENTRADA DEL CLIENT
			BufferedReader fentrada = new BufferedReader(new InputStreamReader(clientConnectat.getInputStream()));
						
			while ((cadena = fentrada.readLine()) != null) {
	
				fsortida.println(cadena);
				System.out.println("Rebent: "+ cadena);
				if (cadena.equals("*")) 
				break;
	
			}
		

			//TANCAR STREAMS I SOCKETS
			System.out.println("Tancant connexió... ");
			fentrada.close();
			fsortida.close();
			clientConnectat.close();
		}
			servidor.close();
		

	}

}

