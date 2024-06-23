package siilikuin.finstem;

import picocli.CommandLine;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@SuppressWarnings({"unused"})
@CommandLine.Command(name = "wordmeat", version = "wordmeat 0.1", mixinStandardHelpOptions = true)
public class Main implements Runnable {

    @Parameters(arity="1", paramLabel = "<word>", defaultValue = "sana", description = "The word to generate sentences for")
    private String word;

    @Option(names = "--target-language", description = "The target language code", required = true, defaultValue = "fi")
    private String targetLanguage;

    public static void main(String[] args) {
        int exitCode = new CommandLine(new Main()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public void run() {
        System.out.println("Hello and welcome!");
        System.out.println("Target Language: " + targetLanguage);
        System.out.println("Word: " + word);
    }
}
