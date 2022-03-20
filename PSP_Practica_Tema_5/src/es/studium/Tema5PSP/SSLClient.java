package es.studium.Tema5PSP;
import java.io.PrintStream;
import java.util.Scanner;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
public class SSLClient
{
	public static void main (String [] argv) throws Exception
	{
		// Almacén de confianza del cliente
		System.setProperty("javax.net.ssl.trustStore", "ClientTrustStore.jks");
		System.setProperty("javax.net.ssl.trustStorePassword", "Studium2020;");
		
		try
		{
			SSLSocketFactory sslFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
			SSLSocket cliSocket = (SSLSocket) sslFactory.createSocket("localhost", 4043);
			Scanner teclado = new Scanner(System.in);
			PrintStream writer = new PrintStream(cliSocket.getOutputStream());
			System.out.println("Pulsa la tecla ENTER sin haber escrito nada para finalizar:");
			String texto = teclado.nextLine();
			
			while(!texto.equals(""))
			{
				writer.println(texto);
				writer.flush();
				texto = teclado.nextLine();
			}
			writer.println("<< FIN >>");
			writer.flush();
			cliSocket.close ();
			teclado.close();
		}
		catch (Exception ex)
		{
			System.out.println ( "Error en las comunicaciones:" + ex);
		}
	}
}