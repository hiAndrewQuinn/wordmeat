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

    public String[] getExampleSentences(Language language, String word) throws IOException {
        String prompt = String.format(
                "Give me 10 examples, from %s to English, of the word '%s' in context.\n" +
                        "Sentences should begin with their IETF code (%s: %s, English: en), and be separated by newlines.\n" +
                        "Example:\n\n" +
                        "%s: x\n" +
                        "en: y\n\n" +
                        "%s: a\n" +
                        "en: b\n\n" +
                        "%s: c\n" +
                        "en: d\n", language.englishName(), word, language.englishName(), language.subtag(), language.subtag(), language.subtag(), language.subtag());

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
            System.out.println(responseJson);

            return parseSentences(responseJson);
        }
    }

    /**
     * Parses sentences from the response JSON.
     * Expected JSON structure:
     * {
     *   "id": "chatcmpl-9dIOjUTpIjnVUAMhmQyw3QqUmtRfG",
     *   "object": "chat.completion",
     *   "created": 1719152541,
     *   "model": "gpt-3.5-turbo-0125",
     *   "choices": [
     *     {
     *       "index": 0,
     *       "message": {
     *         "role": "assistant",
     *         "content": "fi: Minulla on iso sanakirja kotona\nen: I have a large dictionary at home\n\nfi: Tarvitsen englanti-suomi sanakirjaa\nen: I need an English-Finnish dictionary\n\nfi: Ostin uuden elektronisen sanakirjan\nen: I bought a new electronic dictionary\n\nfi: Voisitko lainata minulle ranska-suomi sanakirjaa\nen: Could you lend me a French-Finnish dictionary\n\nfi: Sanakirja auttaa minua löytämään oikeat sanat\nen: The dictionary helps me find the right words\n\nfi: Opi uusia sanoja sanakirjan avulla\nen: Learn new words with the help of a dictionary\n\nfi: En löydä sanaa sanakirjasta\nen: I can't find the word in the dictionary\n\nfi: Sanakirja on hyödyllinen työkalu kielten opiskelussa\nen: The dictionary is a useful tool for studying languages\n\nfi: Käännä tämä lause sanakirjasta\nen: Translate this sentence from the dictionary\n\nfi: Olen menossa kirjastoon etsimään saksan sanakirjaa\nen: I'm going to the library to look for a German dictionary"
     *       },
     *       "logprobs": null,
     *       "finish_reason": "stop"
     *     }
     *   ],
     *   "usage": {
     *     "prompt_tokens": 30,
     *     "completion_tokens": 297,
     *     "total_tokens": 327
     *   },
     *   "system_fingerprint": null
     * }
     *
     * @param responseJson the JSON response from the API
     * @return an array of sentences parsed from the JSON response
     */
    private String[] parseSentences(JsonNode responseJson) {
        JsonNode choices = responseJson.get("choices");
        if (choices.size() == 0) {
            return new String[0];
        }
        String content = choices.get(0).get("message").get("content").asText().trim();
        return content.split("\n\n");
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
