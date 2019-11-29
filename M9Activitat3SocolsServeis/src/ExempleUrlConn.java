import java.net.*;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExempleUrlConn {
	
	@SuppressWarnings("rawtypes")
	public static void main (String[] args) {
		//Arguments
		String direccio = args[0].toString();
		int capsalera = Integer.parseInt(args[1]);
		String text = args[2].toString();
		
		try {
			String cadena;
			//Instanciem l'objecte url i guardem la direccio
			URL url = new URL(direccio);
			//Obrim la connexio amb la url
			URLConnection connexio = url.openConnection();
			
			System.out.println("===============================================================");
			System.out.println("ADREÇA, DARA I CONTINGUT");
			//Retorna la direccio de la url
			System.out.println("Adreça [getURL]: " + connexio.getURL());
			//Instanciem l'objecte data i guardem la data de la ultima modificacio.
			Date data = new Date(connexio.getLastModified());
			//Mostrem la data de la ultima modificacio
			System.out.println("Data última modificació [getLastModified()]: " + data);
			//Mostra el tiups de contingut en aquest cas UTF8
			System.out.println("Tipus de Contingut [getContentType()]" + connexio.getContentType());
			
			System.out.println("===============================================================");
			System.out.println("TOTS ELS CAMPS DE CAPÇALERA AMB getHeaderFields(): ");
			
			//Fem servir una estructura Map per a recuperar capçaleres
			Map campsCapçalera = connexio.getHeaderFields();
			Iterator it = campsCapçalera.entrySet().iterator();
			
			//Mostra el valor de les capçaleres
			while (it.hasNext()) {
				Map.Entry map = (Map.Entry) it.next();
				System.out.println(map.getKey() + ":" + map.getValue());
			}
			
			//Mostra el numero de valors de la capçalera especificat amb l'argument
			System.out.println("\n ARGUMENT AMB EL NUMERO ESPECIFICAT");
			System.out.println("===============================================================");
			for (int i = 0; i < capsalera; i++) {
				System.out.println("getHeaderField(" + i + ")=> " + connexio.getHeaderField(i));
			}
			
			
			
			//Mostra només dos capçaleras la 1 i la 4
			System.out.println("===============================================================");
			System.out.println("Camps 1 i 4 de Capçalera");
			System.out.println("getHeaderField(1)=> " + connexio.getHeaderField(1));
			System.out.println("getHeaderField(4)=> " + connexio.getHeaderField(4));
			System.out.println("===============================================================");
			
			//Retorna el contingut de la url
			System.out.println("Contingut de [url.getFile()]: " + url.getFile());
			BufferedReader pagina = new BufferedReader(new InputStreamReader(url.openStream()));
			//Mostra el contingut "especificat per parametre"
			while ((cadena = pagina.readLine()) != null) {
				if(cadena.contains(text)){	
					System.out.println(cadena);
				}
			}
			
		}
		catch (MalformedURLException e) { e.printStackTrace();}
		catch (IOException e) {e.printStackTrace();}
	}
	

	}

