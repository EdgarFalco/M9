import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;

public class Exemple1URL {
	
	public static void main (String[] args) {
		URL url;
		//Arguments
		String argumentDireccio = args[0];
		int argumentPort= Integer.parseInt(args[1]);
		
		try {
			
			System.out.println("Constructor per a protocol + URL + port + directori: ");
			url = new URL("https", argumentDireccio, argumentPort,"/");
			//Crida el metode per mostra la informacio de la url especificada
			visualitzarInformacio (url);
			//Crida el metode per llegir el codi de la url especificada
			llegirCodi(url);
			
		} catch (MalformedURLException e) { System.out.println(e); }
		
	}
		
	private static void visualitzarInformacio(URL url) {
		//Retorna url completa
		System.out.println("\tURL complerta: " + url.toString());
		//Retorna el protocol que utilitza
		System.out.println("\tgetProtocol: " + url.getProtocol());
		//Retorna la direccio del host
		System.out.println("\tgetHost: " + url.getHost());
		//Retorna el numero de port
		System.out.println("\tgetPort: " + url.getPort());
		//Retorna la ruta
		System.out.println("\tgetFile: " + url.getFile());
		//Retorna la informacio del usuari
		System.out.println("\tgetUserInfo: " + url.getUserInfo());
		//Retorna la ruta 
		System.out.println("\tgetPath: " + url.getPath());
		//Retorna la autoritat de una url especifica
		System.out.println("\tgetAuthority: " + url.getAuthority());
		//Retorna la consulta
		System.out.println("\tgetQuery: " + url.getQuery());
		System.out.println("=====================================================");
	}
	
	private static void llegirCodi(URL url){
				
		BufferedReader in;
		
		try {
			
			InputStream inputStream = url.openStream();
			in = new BufferedReader(new InputStreamReader(inputStream));
			
			String inputLine;
			//Imprimeix per consola el contigut de la url especificada
			while ((inputLine = in.readLine()) != null)
				System.out.println(inputLine);
			in.close();
			
		} catch (IOException e) {e.printStackTrace(); }
	}

}
