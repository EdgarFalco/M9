import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class HttpURLConnectionTest {

	public static void main(String[] args) throws Exception {
		//Llegirem el que introdueixi Usuari
		Scanner sc = new Scanner(System.in);

		boolean si = false;

		//Mentres no sortim seguira demanan a l'usuari introdueixi una opcio
		while (si == false) {
			System.out.println("Elegeix utilitzar GET o POST: \n 1.GET \n 2.POST");
	
			int seleccio = sc.nextInt();
			
			while (seleccio != 1 && seleccio != 2) {
				System.out.println("No has introduit correctament, torna a provar");
				System.out.println("Elegeix utilitzar GET o POST: \n 1.GET \n 2.POST");
				seleccio = sc.nextInt();
			}
			sc.nextLine();
			
			//Establim les variables buides.
			String url = "";
			String userAgent = "";
			String llengua = "";
			String parametersUrl = "";

			//OPCIO 1 GET
			if (seleccio == 1) {

				System.out.println("GET");
				System.out.println("Introdueix UserAgent, exemples: Mozilla/5.0, HTTP/1.0 :");
				userAgent = sc.nextLine();		

				System.out.println("Introdueix una URL: ");
				//Agafem la dada de que introdueix l'usuari
				url = sc.nextLine();		
				//Utilitzem el metode get i utilitzem els parametres que passa l'usuari
				sendGet(userAgent, url);

			} else {
			//OPCIO 2 POST
				System.out.println("POST");
				System.out.println("Escribe User-Agent, exemples: Mozilla/5.0, HTTP/1.0 :");
				userAgent = sc.nextLine();	

				System.out.println("Introdueix una URL: ");
				url = sc.nextLine();

				System.out.println("Escriu format del llenguatge: ");
				llengua = sc.nextLine();	

				System.out.println("Escriu URL Parameters: ");
				parametersUrl = sc.nextLine();

				// Utilitzem el metode post i utilitzem els parametres que passarà l'usuari
				sendPost(userAgent, url, llengua, parametersUrl);
			}

			System.out.println("\nEscriu: seguir | sortir");
			String seguir = sc.nextLine();
			//Si l'usuari no introdueix correctament seguir o sortir, tornara a demanar
			while (!seguir.equalsIgnoreCase("seguir") && !seguir.equalsIgnoreCase("sortir")) {
				
				System.out.println("No has introduit correctament, torna a provar");
				System.out.println("Escriu: seguir | sortir");
				seguir = sc.nextLine();
			}

			// Si l'usuari elegeix "Sortir" Sortim del programa.
			if (seguir.equalsIgnoreCase("sortir")) {
				si = true;
				System.out.println("Programa tancat");
			}
		}
		//Tanquem scanner
		sc.close();
	}

	// HTTP GET request
	//Especificarem dos parametres que seran els que l'usuari introdueixi quan seleccioni GET
	private static void sendGet(String userAgent, String url) throws Exception {

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("User-Agent", userAgent);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		System.out.println(response.toString());
	}

	// HTTP POST request
	//Especificarem nou parametres que seran els que l'usuari introdueixi quan seleccioni POST
	private static void sendPost(String userAgent, String url, String llengua, String ParametersUrl) throws Exception {

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		//add request header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", userAgent);
		con.setRequestProperty("Accept-Language", llengua);

		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(ParametersUrl);
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + ParametersUrl);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		System.out.println(response.toString());
	}
}

