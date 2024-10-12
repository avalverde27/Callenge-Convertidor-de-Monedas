import com.google.gson.Gson;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

public class ConsultaMoneda {

    public Map<String, Double> obtenerTasasDeCambio() {
        String apiUrl = "https://v6.exchangerate-api.com/v6/1d16000362c8bb0b8d3f1d7b/latest/USD";
        Map<String, Double> tasas = new HashMap<>();

        try {
            URI direccion = URI.create(apiUrl);
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(direccion)
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                throw new RuntimeException("Error en la conexión: " + response.statusCode());
            }

            Gson gson = new Gson();
            ApiResponse apiResponse = gson.fromJson(response.body(), ApiResponse.class);
            tasas.putAll(apiResponse.conversion_rates);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return tasas;
    }

}

class ApiResponse {
    public Map<String, Double> conversion_rates;
}
