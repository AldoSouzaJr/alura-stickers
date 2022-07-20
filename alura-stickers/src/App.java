import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {

	public static void main(String[] args) throws Exception {

		// fazer uma conexão HTTP e buscar os top 250 filmes
		
		//String url = "https://imdb-api.com/en/API/Top250Movies/k_9pgylnim";
		//ExtratorDeConteudo extrator = new ExtratorDeConteudoDoIMDB();

		String url = "https://api.mocki.io/v2/549a5d8b/NASA-APOD";
		ExtratorDeConteudo extrator = new ExtratorDeConteudoDaNasa(); 
		
		var http = new ClienteHttp();
		String json = http.buscaDados(url);
		
		// exibindo e manipulando os dados
		List<Conteudo> conteudos = extrator.extraiConteudo(json);
		
		
		var geradora = new GeradoraDeFigurinhas();
		
		for (int i = 0; i < 3; i++) {
		
		Conteudo conteudo = conteudos.get(i);
					
			InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
			String nomeArquivo = "saida/" + conteudo.getTitulo().replace(":", "-") + ".png";
			
			geradora.cria(inputStream, nomeArquivo);
		
			System.out.println(conteudo.getTitulo());
			System.out.println();
		}
	}
}
