/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.dicisas;


import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;
import java.io.IOException;

public class ApiCurrencyService implements CurrencyService {

    private static final String API_KEY = "40392c18b8e1693f645f23e8"; // Reemplaza con tu API key
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/";

    private OkHttpClient client;

    public ApiCurrencyService() {
        client = new OkHttpClient();
    }

    @Override
    public double convert(String fromCurrencyCode, String toCurrencyCode, double amount) throws IOException {
        String url = API_URL + fromCurrencyCode;

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            String responseBody = response.body().string();
            JSONObject jsonResponse = new JSONObject(responseBody);
            double exchangeRate = jsonResponse.getJSONObject("conversion_rates").getDouble(toCurrencyCode);

            return amount * exchangeRate;
        }
    }
}
