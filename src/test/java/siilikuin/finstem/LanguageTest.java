package siilikuin.finstem;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LanguageTest {

    @Test
    public void testEnglish() {
        Language language = new Language("en");
        assertEquals("en", language.subtag());
        assertEquals("English", language.nativeName());
        assertEquals("English", language.englishName());
    }

    @Test
    public void testSpanish() {
        Language language = new Language("es");
        assertEquals("es", language.subtag());
        assertEquals("español", language.nativeName());
        assertEquals("Spanish", language.englishName());
    }

    @Test
    public void testChinese() {
        Language language = new Language("zh");
        assertEquals("zh", language.subtag());
        assertEquals("中文", language.nativeName());
        assertEquals("Chinese", language.englishName());
    }

    @Test
    public void testArabic() {
        Language language = new Language("ar");
        assertEquals("ar", language.subtag());
        assertEquals("العربية", language.nativeName());
        assertEquals("Arabic", language.englishName());
    }

    @Test
    public void testRussian() {
        Language language = new Language("ru");
        assertEquals("ru", language.subtag());
        assertEquals("русский", language.nativeName());
        assertEquals("Russian", language.englishName());
    }

    @Test
    public void testHindi() {
        Language language = new Language("hi");
        assertEquals("hi", language.subtag());
        assertEquals("हिंदी", language.nativeName());
        assertEquals("Hindi", language.englishName());
    }

    @Test
    public void testGerman() {
        Language language = new Language("de");
        assertEquals("de", language.subtag());
        assertEquals("Deutsch", language.nativeName());
        assertEquals("German", language.englishName());
    }

    @Test
    public void testUnknown() {
        Language language = new Language("unknown");
        assertEquals("unknown", language.subtag());
        assertEquals("Unknown", language.nativeName());
        assertEquals("Unknown", language.englishName());
    }
}
