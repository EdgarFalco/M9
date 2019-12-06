import java.awt.TexturePaint;
import java.io.*;
import java.util.Scanner;

import org.apache.commons.net.ftp.*;

public class ClientFTP2 {

	public static void main (String[] args) {

		//Servidor FTP
		FTPClient client = new FTPClient();
		
		String ServerFTP = "ftp.urv.cat";
		//String ServerFTP = "ftp.urv.es";
		System.out.println("Ens connectem al servidor: " + ServerFTP);
		//Usuari FTP
		String usuari = "anonymous";
		String contrasenya = "guest";
		
		Boolean continuar = true;
		
		BasicFTP basicFtp = new BasicFTP(client, ServerFTP, usuari, contrasenya);
		
		
		try {

			
			boolean login = basicFtp.getUsuarioConectado();

			if (login){

				System.out.println("Login correcte... ");
			}
			else {

				System.out.println("Login incorrecte... ");
				client.disconnect();
				System.exit(1);

			}
			//Mentres no sortim demanara que seleccionis una cosa per fer
			while(continuar){
				Scanner sc = new Scanner(System.in);
				System.out.println("Introdueix la opcio que vulguis realitzar:\n"
						+ "1. Crear Fitxer\n"+
						"2. Eliminar Fitxer\n"+
						"3. Pujar Fitxer\n"+
						"4. Renombrar Fitxer\n"+
						"5. Eliminar Fitxer\n"+
						"6. Sortir");
				
				int linea = sc.nextInt();
				String textDireccio ="";
				String textFitxer ="";
				String nomFitxer = "";
				String nomFitxerNou ="";
				
				sc.nextLine();
				
				switch (linea) {
					case 1:
						//Crea fitxer al ftp
						System.out.println("1.Crear Fitxer");
						System.out.println("Introdueix una direccio on crear el fitxer:");
						textDireccio = sc.nextLine();
						basicFtp.crearFicheroFTP(textDireccio);
						break;
					case 2:
						System.out.println("2.Eliminar Fitxer");
						System.out.println("Introdueix la direccio d'on vols eliminar el fitxer");
						textDireccio = sc.nextLine();
						System.out.println("Introdueix el fitxer que vols eliminar");
						textFitxer = sc.nextLine();
						//Eliminar fitxer del ftp
						basicFtp.eliminarFicheroFTP(textDireccio, textFitxer);
						break;
					case 3:
						System.out.println("3.Pujar Fitxer");
						System.out.println("Introdueix la direccio d'on vols pujar el fitxer:");
						textDireccio = sc.nextLine();
						System.out.println("Introdueix la direccio del fitxer que vols pujar");
						textFitxer=sc.nextLine();
						System.out.println("Introdueix el nom del fitxer que vols pujar");
						nomFitxer=sc.nextLine();
						//Puja fitxer al ftp
						basicFtp.subirFicheroFTP(textDireccio, textFitxer, nomFitxer);
						break;
					case 4:
						System.out.println("4.Renombrar Fitxer");
						System.out.println("Introdueix una direccio d'on vols canviar el nom:");
						textDireccio = sc.next();
						System.out.println("Introdueix el nom del fitxer que vols canviar");
						nomFitxer = sc.nextLine();
						System.out.println("Intordueix el nou nom del fitxer");
						nomFitxerNou = sc.nextLine();
						//Canvia nom fitxer del ftp
						basicFtp.renombrarFicheroFTP(textDireccio, nomFitxer, nomFitxerNou);
						break;
					case 5:
						System.out.println("5.Eliminar Fitxer");
						System.out.println("Introdueix una direccio on borrar el fitxer:");
						textDireccio = sc.nextLine();
						System.out.println("Introdueix el nom del fitxer");
						nomFitxer = sc.nextLine();
						//Eliminar fitxer del ftp
						basicFtp.eliminarFicheroFTP(textDireccio, nomFitxer);
						break;
					case 6:
						//sortirm
						System.out.println("Has sortit del programa FTP");
						continuar = false;
						break;
					default:
						System.out.println("No has introduit un nuemero correcte");
						break;
				}
			}
						
			System.out.println("Directori actual: "+client.printWorkingDirectory());
			FTPFile[] files = client.listFiles();
			System.out.println("Fitxers al directori actual: " + files.length);

			//Array par a visualitzar el tipus de fitxer
			String tipus[] = {"Fitxer", "Directori", "Enllaç simbolic"};

			for (int i=0; i<files.length; i++) {

				System.out.println("\t"+files[i].getName()+"=>"+tipus[files[i].getType()]);

			}

			boolean logout = client.logout();

			if (logout)

				System.out.println("Logout del servidor FTP... ");

			else

				System.out.println("Error en fer un logout... ");

			client.disconnect();
			System.out.println("Desconnectat... ");

		} catch (IOException ioe) {

			ioe.printStackTrace();

		}
	}
}
