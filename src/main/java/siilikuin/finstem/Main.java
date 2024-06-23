package siilikuin.finstem;

import picocli.CommandLine;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.regex.Pattern;

@SuppressWarnings({"unused"})
@CommandLine.Command(name = "wordmeat", version = "wordmeat 0.1", mixinStandardHelpOptions = true)
public class Main implements Runnable {

    private static final Pattern IETF_SUBTAG_PATTERN = Pattern.compile("^[a-z]{2,3}$");
    private static final String OPENAI_API_KEY = System.getenv("OPENAI_API_KEY");

    @Option(names = "--target-language", description = "The target language IETF code", required = true, defaultValue = "fi")
    public String targetLanguage;

    @Parameters(arity = "1", paramLabel = "<word>", defaultValue = "sana", description = "The word to generate sentences for")
    public String word;

    public static void main(String[] args) {
        int exitCode = new CommandLine(new Main()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public void run() {
        if (!IETF_SUBTAG_PATTERN.matcher(targetLanguage).matches()) {
            System.err.println("Please give a proper IETF subtag of 2-3 lowercase letters. See\n\n  https://en.wikipedia.org/wiki/IETF_language_tag#List_of_common_primary_language_subtags\n\nfor some examples.");
            return;
        }

        Language language = new Language(targetLanguage);

        if ("Unknown".equals(language.nativeName()) && "Unknown".equals(language.englishName())) {
            System.err.println("Unknown language code: " + targetLanguage);
            System.err.println("Did you spell the 2-3 letter code wrong. See\n\n  https://en.wikipedia.org/wiki/IETF_language_tag#List_of_common_primary_language_subtags\n\nfor some examples.");
            return;
        } else {
            System.out.println("Target Language: " + language.englishName() + " (" + language.subtag() + ")");
            System.out.println("Word: " + word);
        }

        if (OPENAI_API_KEY == null || OPENAI_API_KEY.isEmpty()) {
            System.err.println("The environment variable OPENAI_API_KEY is not set.");
            System.err.println("Please set the OPENAI_API_KEY environment variable to your OpenAI API key.\n");
            System.err.println("Examples:\n");
            System.err.println("  export OPENAI_API_KEY=your_openai_api_key_here              # Unix-based");
            System.err.println("  setx OPENAI_API_KEY \"your_openai_api_key_here\"              # Windows");
            return;
        }

        try {
            OpenAIClient openAIClient = new OpenAIClient(OPENAI_API_KEY);
            String[] sentences = openAIClient.getExampleSentences(language.englishName(), word);
            for (String sentence : sentences) {
                System.out.println(sentence);
            }
        } catch (Exception e) {
            System.err.println("Error fetching example sentences: " + e.getMessage());
        }
    }
}
