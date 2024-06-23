package siilikuin.finstem;

import org.junit.jupiter.api.Test;
import picocli.CommandLine;

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
    void testRunMethod() {
        Main main = new Main();
        new CommandLine(main).parseArgs("--target-language", "en", "testword");
        main.run();
        assertEquals("en", main.targetLanguage);
        assertEquals("testword", main.word);
    }
}
