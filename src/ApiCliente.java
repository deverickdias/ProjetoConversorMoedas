import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ApiCliente {
    private String apiKey;

    // Construtor para definir a chave da API
    public ApiCliente(String apiKey) {
        this.apiKey = apiKey;
    }

    // Método para obter a taxa de câmbio
    public double obterTaxaDeCambio(String moedaOrigem, String moedaDestino) throws IOException, InterruptedException {
        // URL da API para obter a taxa de câmbio
        String url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/pair/" + moedaOrigem + "/" + moedaDestino;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Parsing do JSON retornado
        JsonObject jsonResponse = JsonParser.parseString(response.body()).getAsJsonObject();

        // Retorna a taxa de câmbio
        return jsonResponse.get("conversion_rate").getAsDouble();
    }
}
