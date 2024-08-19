import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class ServidorLancadorDados {

	private ServerSocket sckServidor;
	private Random random;

	public ServidorLancadorDados() throws IOException {
		this.sckServidor = new ServerSocket(4002);
		random = new Random();

		for (;;) { //listener
			Socket sckEcho;
			InputStream canalEntrada;
			OutputStream canalSaida;
			BufferedReader entrada;
			PrintWriter saida;

			sckEcho = this.sckServidor.accept();
			canalEntrada = sckEcho.getInputStream();
			canalSaida = sckEcho.getOutputStream();
			entrada = new BufferedReader(new InputStreamReader(canalEntrada));
			saida = new PrintWriter(canalSaida, true);

			while (true) {
				String linhaPedido = entrada.readLine();

				if (linhaPedido == null || linhaPedido.length() == 0)
					break;

				if (linhaPedido.equalsIgnoreCase("GIRAR")) {
					int resultadoDado = random.nextInt(6);
					saida.println("Resultado do dado: " + resultadoDado);
				} else {
					saida.println("Digite 'GIRAR' para lançar o dado.");
				}

			}
			sckEcho.close();
		}
	}
}
