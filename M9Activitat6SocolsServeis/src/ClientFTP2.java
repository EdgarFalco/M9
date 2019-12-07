import java.io.IOException;
import java.util.Scanner;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPSClient;

public class ClientFTP2 {

	public static void main (String[] args) {

		//Servidor FTP
		FTPClient client = new FTPClient();
		//Connectem al servidor FTP, ftpupload.net
		String ServerFTP = "ftpupload.net";
		System.out.println("Ens connectem al servidor: " + ServerFTP);

		//Usuari i contraseña, per accedir al servidor FTP
		String usuari = "epiz_24891451";
		String contrasenya = "TdOLXdEFeEv";
		//Variable per controlar la sortida del while
		boolean continuar = true;

		BasicFTP basicFtp = new BasicFTP(client, ServerFTP, usuari, contrasenya);
		
		//Varibales per controlar el que introdueix l'usuari
		Scanner sc = new Scanner(System.in);
		String textDireccio = "";
		String textFitxer = "";
		String nomFitxer = "";
		String nomFitxerNou = "";

		try {

			boolean login = basicFtp.getUsuarioConectado();

			if (login){

				System.out.println("Login correcte... ");

				// Mentres no sortim demanara que seleccionis una cosa per fer
				while (continuar){
					System.out.println("Introdueix la opcio que vulguis realitzar:\n"
							+ "1. Crear Directori\n"+
							"2. Eliminar Fitxer\n"+
							"3. Pujar Fitxer\n"+
							"4. Renombrar Fitxer\n"+
							"5. Eliminar Fitxer\n"+
							"6. Sortir");

					int linea = sc.nextInt();

					sc.nextLine();

					switch (linea) {
					case 1:
						//Demanara a l'usuari introduir dades
						System.out.println("1.Crear Fitxer");
						System.out.println("Introdueix una direccio on crear el fitxer:");
						textDireccio = sc.nextLine();
						//Crea fitxer al ftp
						basicFtp.crearFicheroFTP(textDireccio);
						break;
					case 2:
						//Demanara a l'usuari introduir dades
						System.out.println("2.Eliminar Fitxer");
						System.out.println("Introdueix la direccio d'on vols eliminar el fitxer");
						textDireccio = sc.nextLine();
						System.out.println("Introdueix el fitxer que vols eliminar");
						textFitxer = sc.nextLine();
						//Eliminar fitxer del ftp
						basicFtp.eliminarFicheroFTP(textDireccio, textFitxer);
						break;
					case 3:
						//Demanara a l'usuari introduir dades
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
						//Demanara a l'usuari introduir dades
						System.out.println("4.Renombrar Fitxer");
						System.out.println("Introdueix una direccio d'on vols canviar el nom:");
						textDireccio = sc.nextLine();
						System.out.println("Introdueix el nom del fitxer que vols canviar");
						nomFitxer = sc.nextLine();
						System.out.println("Intordueix el nou nom del fitxer");
						nomFitxerNou = sc.nextLine();
						//Canvia nom fitxer del ftp
						basicFtp.renombrarFicheroFTP(textDireccio, nomFitxer, nomFitxerNou);
						break;
					case 5:
						//Demanara a l'usuari introduir dades
						System.out.println("5.Eliminar Fitxer");
						System.out.println("Introdueix una direccio on borrar el fitxer:");
						textDireccio = sc.nextLine();
						System.out.println("Introdueix el nom del fitxer");
						nomFitxer = sc.nextLine();
						//Eliminar fitxer del ftp
						basicFtp.eliminarFicheroFTP(textDireccio, nomFitxer);
						break;
					case 6:
						// sortim
						System.out.println("Has sortit del programa FTP");
						continuar = false;
						break;
					default:
						System.out.println("No has introduit un nombre correcte.");
						break;
					}
				}
			} else {
				//Si el client no conecta amb el servidor mostrara "Login incorrecte" i ens desconectarem
				System.out.println("Login incorrecte... ");
				client.disconnect();
				System.exit(1);
			}

			//Mostra el numero de fitxers en el directori actual
			System.out.println("Directori actual: " + basicFtp.getCliente().printWorkingDirectory());
			FTPFile[] files = basicFtp.getCliente().listFiles();
			System.out.println("Fitxers al directori actual: " + files.length);

			//Array per a visualitzar el tipus de fitxer
			String tipus[] = {"Fitxer", "Directori", "Enllaç simbolic"};

			for (int i = 0; i < files.length; i++) {

				System.out.println("\t" + files[i].getName() + "=>" + tipus[files[i].getType()]);
			}
			//Sortim del servidor
			boolean logout = basicFtp.getCliente().logout();

			if (logout) {

				System.out.println("Logout del servidor FTP... ");

			} else {

				System.out.println("Error en fer un logout... ");
			}
			//Tanquem la connexio amb el servidor
			basicFtp.getCliente().disconnect();
			System.out.println("Desconnectat... ");

			sc.close();

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
