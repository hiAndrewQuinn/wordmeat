package siilikuin.finstem;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

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
    void testGetExampleSentences() throws IOException, SQLException {
        String jsonResponse = "{\n" +
                "  \"choices\": [\n" +
                "    {\"message\": {\"content\": \"fi: Minulla on iso sanakirja kotona\\nen: I have a large dictionary at home\\n\\nfi: Tarvitsen englanti-suomi sanakirjaa\\nen: I need an English-Finnish dictionary\\n\\nfi: Ostin uuden elektronisen sanakirjan\\nen: I bought a new electronic dictionary\\n\\nfi: Voisitko lainata minulle ranska-suomi sanakirjaa\\nen: Could you lend me a French-Finnish dictionary\\n\\nfi: Sanakirja auttaa minua löytämään oikeat sanat\\nen: The dictionary helps me find the right words\\n\\nfi: Opi uusia sanoja sanakirjan avulla\\nen: Learn new words with the help of a dictionary\\n\\nfi: En löydä sanaa sanakirjasta\\nen: I can't find the word in the dictionary\\n\\nfi: Sanakirja on hyödyllinen työkalu kielten opiskelussa\\nen: The dictionary is a useful tool for studying languages\\n\\nfi: Käännä tämä lause sanakirjasta\\nen: Translate this sentence from the dictionary\\n\\nfi: Olen menossa kirjastoon etsimään saksan sanakirjaa\\nen: I'm going to the library to look for a German dictionary\"}}\n" +
                "  ]\n" +
                "}";
        mockWebServer.enqueue(new MockResponse().setBody(jsonResponse).addHeader("Content-Type", "application/json"));

        String[] expectedSentences = {
                "fi: Minulla on iso sanakirja kotona\nen: I have a large dictionary at home",
                "fi: Tarvitsen englanti-suomi sanakirjaa\nen: I need an English-Finnish dictionary",
                "fi: Ostin uuden elektronisen sanakirjan\nen: I bought a new electronic dictionary",
                "fi: Voisitko lainata minulle ranska-suomi sanakirjaa\nen: Could you lend me a French-Finnish dictionary",
                "fi: Sanakirja auttaa minua löytämään oikeat sanat\nen: The dictionary helps me find the right words",
                "fi: Opi uusia sanoja sanakirjan avulla\nen: Learn new words with the help of a dictionary",
                "fi: En löydä sanaa sanakirjasta\nen: I can't find the word in the dictionary",
                "fi: Sanakirja on hyödyllinen työkalu kielten opiskelussa\nen: The dictionary is a useful tool for studying languages",
                "fi: Käännä tämä lause sanakirjasta\nen: Translate this sentence from the dictionary",
                "fi: Olen menossa kirjastoon etsimään saksan sanakirjaa\nen: I'm going to the library to look for a German dictionary"
        };

        Language finnish = new Language("fi");
        String[] actualSentences = openAIClient.getExampleSentences(finnish, "sanakirja");

        assertArrayEquals(expectedSentences, actualSentences);
    }

    @Test
    void testGetExampleSentencesWithInvalidResponse() throws IOException, SQLException {
        mockWebServer.enqueue(new MockResponse().setResponseCode(500));

        Language english = new Language("en", "English", "English");
        assertThrows(IOException.class, () -> openAIClient.getExampleSentences(english, "testword"));
    }
}
