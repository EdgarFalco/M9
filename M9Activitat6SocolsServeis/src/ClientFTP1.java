import java.io.IOException;
import java.net.SocketException;
import org.apache.commons.net.ftp.*;


public class ClientFTP1 {
	
	public static void main (String[] args) throws SocketException, IOException {
		
		FTPClient client = new FTPClient();
		
		//Servidor FTP
		String ServerFTP = "ftp.urv.es";
		System.out.println("Ens connectem al servidor: " + ServerFTP);
		client.connect(ServerFTP);
		
		
		//Resposta del servidor FTP
		System.out.println(client.getReplyString());
		
		//Codi de resposta
		int resposta = client.getReplyCode();
		
		//Comprovació del codi de resposta
		if (!FTPReply.isPositiveCompletion(resposta)) {
			client.disconnect();
			System.out.println("Connexió rebutjada: "+resposta);
			System.exit(0);
			
		}
		
		//Desconnexió del servidor FTP
		client.disconnect();
		System.out.println("Connexió finalitzada");
	}

}