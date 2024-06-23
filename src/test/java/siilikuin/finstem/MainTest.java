package siilikuin.finstem;

import org.junit.jupiter.api.Test;
import picocli.CommandLine;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void testDefaultWordAndLanguage() {
        Main main = new Main();
        new CommandLine(main).parseArgs();
        assertEquals("sana", main.word);
        assertEquals("fi", main.targetLanguage);
    }

    @Test
    void testCustomWordAndLanguage() {
        Main main = new Main();
        new CommandLine(main).parseArgs("--target-language", "en", "testword");
        assertEquals("testword", main.word);
        assertEquals("en", main.targetLanguage);
    }

    @Test
    void testRunMethodWithValidLanguage() {
        Main main = new Main();
        new CommandLine(main).parseArgs("--target-language", "en", "testword");

        // Capture output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        main.run();

        System.setOut(originalOut);

        String output = outContent.toString();
        assertTrue(output.contains("Target Language: English (en)"));
        assertTrue(output.contains("Word: testword"));
    }

    @Test
    void testRunMethodWithInvalidLanguage() {
        Main main = new Main();
        new CommandLine(main).parseArgs("--target-language", "invalid", "testword");

        // Capture error output
        ByteArrayOutputStream errContent = new ByteArrayOutputStream();
        PrintStream originalErr = System.err;
        System.setErr(new PrintStream(errContent));

        // Run the main method and capture the output
        main.run();

        System.setErr(originalErr);

        String errorOutput = errContent.toString();
        assertTrue(errorOutput.contains("Please give a proper IETF subtag of 2-3 lowercase letters."));
    }

    @Test
    void testRunMethodWithUnknownLanguageCode() {
        Main main = new Main();
        new CommandLine(main).parseArgs("--target-language", "zz", "testword");

        // Capture error output
        ByteArrayOutputStream errContent = new ByteArrayOutputStream();
        PrintStream originalErr = System.err;
        System.setErr(new PrintStream(errContent));

        // Run the main method and capture the output
        main.run();

        System.setErr(originalErr);

        String errorOutput = errContent.toString();
        assertTrue(errorOutput.contains("Unknown language code: zz"));
    }

    @Test
    void testRunMethodWithMissingApiKey() {
        // Ensure the API key is not set
        String originalApiKey = System.getenv("OPENAI_API_KEY");
        if (originalApiKey != null) {
            System.clearProperty("OPENAI_API_KEY");
        }

        Main main = new Main();
        new CommandLine(main).parseArgs("--target-language", "en", "testword");

        // Capture error output
        ByteArrayOutputStream errContent = new ByteArrayOutputStream();
        PrintStream originalErr = System.err;
        System.setErr(new PrintStream(errContent));

        // Run the main method and capture the output
        main.run();

        System.setErr(originalErr);

        String errorOutput = errContent.toString();
        assertTrue(errorOutput.contains("The environment variable OPENAI_API_KEY is not set."));
        assertTrue(errorOutput.contains("Please set the OPENAI_API_KEY environment variable to your OpenAI API key."));

        // Restore the original API key if it was set
        if (originalApiKey != null) {
            System.setProperty("OPENAI_API_KEY", originalApiKey);
        }
    }
}