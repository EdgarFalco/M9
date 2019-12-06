import java.net.*;
import java.io.*;

public class ServidorTCP3 {

	public static void main (String[] args) throws Exception {
		//Port per on escoltar� el servidor
		int numPort = 60000;
		ServerSocket servidor = new ServerSocket(numPort);
		String cadena = "";
		//Controlara el numero de clients
		int contador = 0;
		//Controlara la sortida del bucle per poder deixar d'escoltar.
		boolean si = true;		
		
		System.out.println("Esperant connexi�... ");
			
		while(si) {
			//Controlara el clients que li entrin, si son m�s de tres el servidor deixara d'escoltar.
			if(contador < 3) {
				si = false;
				System.out.println("Ja he at�s 3 clients, no puc atendre mes clients");
				break;
			}
			
			//Accepta la connexio amb el client
			Socket clientConnectat = servidor.accept();
			//Missatge al Client "numero de client"
			PrintWriter fclient = new PrintWriter(clientConnectat.getOutputStream(), true);
			fclient.println("Benvingut client " + contador);
						
			System.out.println("Client connectat... ");
			//Incrementem el numero de client
			contador++;
			
			//FLUX DE SORTIDA AL CLIENT
			PrintWriter fsortida = new PrintWriter(clientConnectat.getOutputStream(), true);
			
			//FLUX D'ENTRADA DEL CLIENT
			BufferedReader fentrada = new BufferedReader(new InputStreamReader(clientConnectat.getInputStream()));
			//Mentres el client no introdueixi * el servidor seguira escoltant-lo			
			while ((cadena = fentrada.readLine()) != null) {
	
				fsortida.println(cadena);
				System.out.println("Rebent: "+ cadena);
				if (cadena.equals("*")) 
				break;
	
			}
		
			//TANCAR STREAMS I SOCKETS
			System.out.println("Tancant connexi�... ");
			fentrada.close();
			fsortida.close();
			clientConnectat.close();
		}
			servidor.close();
		

	}

}

