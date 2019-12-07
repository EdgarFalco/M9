import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

public class BasicFTP {

	private FTPClient cliente;
	private String servidor;
	private String usuario;
	private String contrasena;
	private boolean usuarioConectado;

	public BasicFTP(FTPClient cliente, String servidor, String usuario, String contrasena)  {
		this.cliente = new FTPClient();
		this.servidor = servidor;
		this.usuario = usuario;
		this.contrasena = contrasena;

		try {
			this.cliente.connect(servidor);
			this.usuarioConectado = this.cliente.login(usuario, contrasena);
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public void crearFicheroFTP(String direccion) {

		try {

			System.out.println("Ens connectem al servidor: " + servidor);

			//Crea el directori
			//			String adreca = "\\prova\\docs\\nouDirectori";

			if (cliente.makeDirectory(direccion)) {
				System.out.println("Directori creat... ");
				cliente.changeWorkingDirectory(direccion);
			} else {
				System.out.println("No s'ha pogut crear el directori... ");
			}

			System.out.println("L'operació ha acabat... ");

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public void eliminarFicheroFTP(String direccion, String nomFichero) {

		try {

			System.out.println("Ens connectem al servidor: " + servidor);

			//Elimina el fitxer
			//			String adreca = "\\prova\\docs\\holaNou.txt";
			if (cliente.deleteFile(direccion + nomFichero)) {
				System.out.println("Fitxer eliminat... ");
			} else {
				System.out.println("No s'ha pogut eliminar el fitxer... ");
			}

			System.out.println("L'operació ha acabat... ");

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public void subirFicheroFTP(String direccion, String direccionSubir, String nomFichero) {

		try {
			System.out.println("Ens connectem al servidor: " + servidor);
			boolean login = getUsuarioConectado();
			//			String adreca = "\\lib";

			if (login) {

				// Le pasamos la direccion donde queremos subir el archivo
				cliente.changeWorkingDirectory(direccion);
				cliente.setFileType(FTP.BINARY_FILE_TYPE);

				//Stream d'entrada amb el fitxer que es vol pujar
				//				BufferedInputStream in = new BufferedInputStream(new FileInputStream("/home/user/text1.txt"));
				BufferedInputStream in = new BufferedInputStream(new FileInputStream(direccionSubir + nomFichero));
				cliente.storeFile("text1.txt", in);

				in.close();
				System.out.println("L'operació ha acabat... ");
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public void renombrarFicheroFTP(String direccion, String nomFichero, String nomFicheroNuevo) {

		try {
			System.out.println("Ens connectem al servidor: " + servidor);

			//Descarregar el fitxer
			//			String adreca = "\\prova\\docs\\";
			cliente.changeWorkingDirectory(direccion);

			if (cliente.rename(nomFichero, nomFicheroNuevo)) {

				System.out.println("Fitxer reanomenat... ");

			} else {

				System.out.println("No s'ha pogut reanomenat el fitxer... ");
			}

			System.out.println("L'operació ha acabat... ");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public FTPClient getCliente() {
		return cliente;
	}

	public String getServidor() {
		return servidor;
	}

	public String getUsuario() {
		return usuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public boolean getUsuarioConectado() {
		return usuarioConectado;
	}

}