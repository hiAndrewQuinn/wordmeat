package siilikuin.finstem;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import java.io.IOException;

public class OpenAIClient {
    private final String apiKey;
    private String apiUrl;
    private static final String DEFAULT_API_URL = "https://api.openai.com/v1/chat/completions";

    private final OkHttpClient httpClient;
    private final ObjectMapper objectMapper;

    public OpenAIClient(String apiKey) {
        if (apiKey == null || apiKey.isEmpty()) {
            throw new IllegalStateException("OpenAI API key is not set.");
        }
        this.apiKey = apiKey;
        this.apiUrl = DEFAULT_API_URL;
        this.httpClient = new OkHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public String[] getExampleSentences(String language, String word) throws IOException {
        String prompt = String.format("Give me 10 example sentences in %s using the word '%s' and provide their English translations.", language, word);

        // Create the request payload
        String requestBodyJson = objectMapper.writeValueAsString(new OpenAIRequest(new OpenAIMessage("user", prompt)));

        RequestBody body = RequestBody.create(
                requestBodyJson,
                MediaType.get("application/json")
        );

        Request request = new Request.Builder()
                .url(apiUrl)
                .post(body)
                .addHeader("Authorization", "Bearer " + apiKey)
                .addHeader("Content-Type", "application/json")
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            JsonNode responseJson = objectMapper.readTree(response.body().string());
            return parseSentences(responseJson);
        }
    }

    private String[] parseSentences(JsonNode responseJson) {
        JsonNode choices = responseJson.get("choices");
        String[] sentences = new String[choices.size()];
        for (int i = 0; i < choices.size(); i++) {
            sentences[i] = choices.get(i).get("message").get("content").asText().trim();
        }
        return sentences;
    }

    private static class OpenAIRequest {
        public final String model = "gpt-3.5-turbo";
        public final OpenAIMessage[] messages;
        public final double temperature = 0.7;

        public OpenAIRequest(OpenAIMessage message) {
            this.messages = new OpenAIMessage[]{message};
        }
    }

    private static class OpenAIMessage {
        public final String role;
        public final String content;

        public OpenAIMessage(String role, String content) {
            this.role = role;
            this.content = content;
        }
    }
}
