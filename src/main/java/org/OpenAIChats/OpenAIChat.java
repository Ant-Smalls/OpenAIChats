package org.OpenAIChats;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class OpenAIChat {
    private final static String MODEL_ENDPOINT = "https://api.openai.com/v1/models";
    private final static String APIKEY = System.getenv("OPENAI_API_KEY");

    // Records to map to the JSON structure
    public record Model(String id, Long created) {
    }

    public record ModelList(List<Model> data) {
    }

    // Gson parser to convert JSON to Java objects and back
    private final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create();

    public ModelList getModels() throws IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();
        // Create an HTTP Request, whose default access method is GET
        var request = HttpRequest.newBuilder()
                // Parse the URL from the given string
                .uri(URI.create(MODEL_ENDPOINT))
                // Set the Authorization header to the API key
                .header("Authorization", "Bearer " + APIKEY)
                .header("Accept", "application/json")
                .build();
        // Send the request and get the response (blocking query)
        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new RuntimeException("HTTP error: " + response.statusCode());
        }
        // Extract the body from the response and parse it into a ModelList object
        return gson.fromJson(response.body(), ModelList.class);
    }
}
