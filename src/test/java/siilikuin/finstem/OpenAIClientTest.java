package siilikuin.finstem;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OpenAIClientTest {

    private MockWebServer mockWebServer;
    private OpenAIClient openAIClient;
    private static final String MOCK_API_KEY = "mock-api-key";

    @BeforeEach
    void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
        openAIClient = new OpenAIClient(MOCK_API_KEY);
        openAIClient.setApiUrl(mockWebServer.url("/v1/engines/text-davinci-003/completions").toString());
    }

    @AfterEach
    void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    void testGetExampleSentences() throws IOException {
        String jsonResponse = "{\n" +
                "  \"choices\": [\n" +
                "    {\"text\": \"Example sentence 1.\"},\n" +
                "    {\"text\": \"Example sentence 2.\"},\n" +
                "    {\"text\": \"Example sentence 3.\"},\n" +
                "    {\"text\": \"Example sentence 4.\"},\n" +
                "    {\"text\": \"Example sentence 5.\"},\n" +
                "    {\"text\": \"Example sentence 6.\"},\n" +
                "    {\"text\": \"Example sentence 7.\"},\n" +
                "    {\"text\": \"Example sentence 8.\"},\n" +
                "    {\"text\": \"Example sentence 9.\"},\n" +
                "    {\"text\": \"Example sentence 10.\"}\n" +
                "  ]\n" +
                "}";
        mockWebServer.enqueue(new MockResponse().setBody(jsonResponse).addHeader("Content-Type", "application/json"));

        String[] expectedSentences = {
                "Example sentence 1.",
                "Example sentence 2.",
                "Example sentence 3.",
                "Example sentence 4.",
                "Example sentence 5.",
                "Example sentence 6.",
                "Example sentence 7.",
                "Example sentence 8.",
                "Example sentence 9.",
                "Example sentence 10."
        };

        String[] actualSentences = openAIClient.getExampleSentences("English", "testword");

        assertArrayEquals(expectedSentences, actualSentences);
    }

    @Test
    void testGetExampleSentencesWithInvalidResponse() {
        mockWebServer.enqueue(new MockResponse().setResponseCode(500));

        assertThrows(IOException.class, () -> openAIClient.getExampleSentences("English", "testword"));
    }
}
