import java.net.*;
import java.util.concurrent.TimeoutException;
import java.io.*;

public class ClientUDP3 {

	public static void main (String[] args) throws Exception {

		//FLUX PER A ENTRADA ESTÀNDARD
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		//Socket client
		DatagramSocket clientSocket = new DatagramSocket();
		byte[] enviats = new byte[1024];
		byte[] rebuts = new byte[1024];
		boolean si = true;

		//DADES DEL SERVIDOR al qual s'envia el missatge
		InetAddress IPServidor = InetAddress.getLocalHost();
		int port = 9800;
		
		//Mentre sigui true, demanara enviar un missatge al servidor.
		while(si){
			//INTRODUIR DADES PEL TECLAT
			System.out.print("Introdueix missatge: ");
			String cadena = in.readLine();
			enviats = cadena.getBytes();
	
			//ENVIANT DATAGRAMA AL SERVIDOR
			System.out.println("Enviant "+enviats.length + "bytes al servidor.");
			DatagramPacket enviament = new DatagramPacket(enviats, enviats.length, IPServidor, port);
			clientSocket.send(enviament);
	
			//REBENT DATAGRAMA DEL SERVIDOR
			DatagramPacket rebut = new DatagramPacket(rebuts, rebuts.length);
			System.out.println("Esperant datagrama...");
			//Es bloqueja al passar un temps (podem especificar el temps) en aquest cas 5 segons.
			clientSocket.setSoTimeout(5000);
			try{
				
				clientSocket.receive(rebut);
				String majuscula = new String(rebut.getData());
				//ACONSEGUINT INFORMACIÓ DEL DATAGRAMA
				InetAddress IPOrigen = rebut.getAddress();
				int portOrigen = rebut.getPort();
				System.out.println("\tProcedent de: " + IPOrigen+":" + portOrigen);
				System.out.println("\tDades: " + majuscula.trim());
			}
			catch (SocketTimeoutException e){
				//Si es bloqueja mostra un "stop" i posa el valor boolea en false per que no demani mes el missatge.
				si = false;
				System.out.println("stop");
			}
		}
			//Tanca el socket
			clientSocket.close();
		
	}

}
