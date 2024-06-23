package siilikuin.finstem;

import java.util.Map;

public record Language(String subtag, String nativeName, String englishName) {

    private static final Map<String, String[]> LANGUAGE_MAP = Map.ofEntries(
            Map.entry("af", new String[]{"Afrikaans", "Afrikaans"}),
            Map.entry("am", new String[]{"አማርኛ", "Amharic"}),
            Map.entry("ar", new String[]{"العربية", "Arabic"}),
            Map.entry("arn", new String[]{"Mapudungun", "Mapudungun"}),
            Map.entry("ary", new String[]{"الدارجة المغربية", "Moroccan Arabic"}),
            Map.entry("as", new String[]{"অসমীয়া", "Assamese"}),
            Map.entry("az", new String[]{"Azərbaycan", "Azerbaijani"}),
            Map.entry("ba", new String[]{"Башҡорт", "Bashkir"}),
            Map.entry("be", new String[]{"беларуская", "Belarusian"}),
            Map.entry("bg", new String[]{"български", "Bulgarian"}),
            Map.entry("bn", new String[]{"বাংলা", "Bengali"}),
            Map.entry("bo", new String[]{"བོད་ཡིག", "Tibetan"}),
            Map.entry("br", new String[]{"brezhoneg", "Breton"}),
            Map.entry("bs", new String[]{"bosanski/босански", "Bosnian"}),
            Map.entry("ca", new String[]{"català", "Catalan"}),
            Map.entry("co", new String[]{"Corsu", "Corsican"}),
            Map.entry("cs", new String[]{"čeština", "Czech"}),
            Map.entry("cy", new String[]{"Cymraeg", "Welsh"}),
            Map.entry("da", new String[]{"dansk", "Danish"}),
            Map.entry("de", new String[]{"Deutsch", "German"}),
            Map.entry("dsb", new String[]{"dolnoserbšćina", "Lower Sorbian"}),
            Map.entry("dv", new String[]{"ދިވެހިބަސް", "Divehi"}),
            Map.entry("el", new String[]{"Ελληνικά", "Greek"}),
            Map.entry("en", new String[]{"English", "English"}),
            Map.entry("es", new String[]{"español", "Spanish"}),
            Map.entry("et", new String[]{"eesti", "Estonian"}),
            Map.entry("eu", new String[]{"euskara", "Basque"}),
            Map.entry("fa", new String[]{"فارسى", "Persian"}),
            Map.entry("fi", new String[]{"suomi", "Finnish"}),
            Map.entry("fil", new String[]{"Filipino", "Filipino"}),
            Map.entry("fo", new String[]{"føroyskt", "Faroese"}),
            Map.entry("fr", new String[]{"français", "French"}),
            Map.entry("fy", new String[]{"Frysk", "Frisian"}),
            Map.entry("ga", new String[]{"Gaeilge", "Irish"}),
            Map.entry("gd", new String[]{"Gàidhlig", "Scottish Gaelic"}),
            Map.entry("gl", new String[]{"galego", "Galician"}),
            Map.entry("gsw", new String[]{"Schweizerdeutsch", "Swiss German"}),
            Map.entry("gu", new String[]{"ગુજરાતી", "Gujarati"}),
            Map.entry("ha", new String[]{"Hausa", "Hausa"}),
            Map.entry("he", new String[]{"עברית", "Hebrew"}),
            Map.entry("hi", new String[]{"हिंदी", "Hindi"}),
            Map.entry("hr", new String[]{"hrvatski", "Croatian"}),
            Map.entry("hsb", new String[]{"hornjoserbšćina", "Upper Sorbian"}),
            Map.entry("hu", new String[]{"magyar", "Hungarian"}),
            Map.entry("hy", new String[]{"Հայերեն", "Armenian"}),
            Map.entry("id", new String[]{"Bahasa Indonesia", "Indonesian"}),
            Map.entry("ig", new String[]{"Igbo", "Igbo"}),
            Map.entry("ii", new String[]{"ꆈꌠꁱꂷ", "Yi"}),
            Map.entry("is", new String[]{"íslenska", "Icelandic"}),
            Map.entry("it", new String[]{"italiano", "Italian"}),
            Map.entry("iu", new String[]{"Inuktitut /ᐃᓄᒃᑎᑐᑦ (ᑲᓇᑕ)", "Inuktitut"}),
            Map.entry("ja", new String[]{"日本語", "Japanese"}),
            Map.entry("ka", new String[]{"ქართული", "Georgian"}),
            Map.entry("kk", new String[]{"Қазақша", "Kazakh"}),
            Map.entry("kl", new String[]{"kalaallisut", "Greenlandic"}),
            Map.entry("km", new String[]{"ខ្មែរ", "Khmer"}),
            Map.entry("kn", new String[]{"ಕನ್ನಡ", "Kannada"}),
            Map.entry("ko", new String[]{"한국어", "Korean"}),
            Map.entry("kok", new String[]{"कोंकणी", "Konkani"}),
            Map.entry("kb", new String[]{"کوردی/Kurdî", "Kurdi"}),
            Map.entry("ky", new String[]{"Кыргыз", "Kyrgyz"}),
            Map.entry("lb", new String[]{"Lëtzebuergesch", "Luxembourgish"}),
            Map.entry("lo", new String[]{"ລາວ", "Lao"}),
            Map.entry("lt", new String[]{"lietuvių", "Lithuanian"}),
            Map.entry("lv", new String[]{"latviešu", "Latvian"}),
            Map.entry("mi", new String[]{"Reo Māori", "Maori"}),
            Map.entry("mk", new String[]{"македонски јазик", "Macedonian"}),
            Map.entry("ml", new String[]{"മലയാളം", "Malayalam"}),
            Map.entry("mn", new String[]{"Монгол хэл/ᠮᠤᠨᠭᠭᠤᠯ ᠬᠡᠯᠡ", "Mongolian"}),
            Map.entry("moh", new String[]{"Kanien'kéha", "Mohawk"}),
            Map.entry("mr", new String[]{"मराठी", "Marathi"}),
            Map.entry("ms", new String[]{"Bahasa Malaysia", "Malay"}),
            Map.entry("mt", new String[]{"Malti", "Maltese"}),
            Map.entry("my", new String[]{"မြန်မာဘာသာ", "Burmese"}),
            Map.entry("nb", new String[]{"norsk (bokmål)", "Norwegian (Bokmål)"}),
            Map.entry("ne", new String[]{"नेपाली (नेपाल)", "Nepali"}),
            Map.entry("nl", new String[]{"Nederlands", "Dutch"}),
            Map.entry("nn", new String[]{"norsk (nynorsk)", "Norwegian (Nynorsk)"}),
            Map.entry("no", new String[]{"norsk", "Norwegian"}),
            Map.entry("oc", new String[]{"occitan", "Occitan"}),
            Map.entry("or", new String[]{"ଓଡ଼ିଆ", "Odia"}),
            Map.entry("pap", new String[]{"Papiamentu", "Papiamento"}),
            Map.entry("pa", new String[]{"ਪੰਜਾਬੀ / پنجابی", "Punjabi"}),
            Map.entry("pl", new String[]{"polski", "Polish"}),
            Map.entry("prs", new String[]{"درى", "Dari"}),
            Map.entry("ps", new String[]{"پښتو", "Pashto"}),
            Map.entry("pt", new String[]{"português", "Portuguese"}),
            Map.entry("quc", new String[]{"K'iche", "K'iche"}),
            Map.entry("qu", new String[]{"runasimi", "Quechua"}),
            Map.entry("rm", new String[]{"Rumantsch", "Romansh"}),
            Map.entry("ro", new String[]{"română", "Romanian"}),
            Map.entry("ru", new String[]{"русский", "Russian"}),
            Map.entry("rw", new String[]{"Kinyarwanda", "Kinyarwanda"}),
            Map.entry("sa", new String[]{"संस्कृत", "Sanskrit"}),
            Map.entry("sah", new String[]{"саха", "Yakut"}),
            Map.entry("se", new String[]{"davvisámegiella", "Sami (Northern)"}),
            Map.entry("si", new String[]{"සිංහල", "Sinhala"}),
            Map.entry("sk", new String[]{"slovenčina", "Slovak"}),
            Map.entry("sl", new String[]{"slovenski", "Slovenian"}),
            Map.entry("sma", new String[]{"åarjelsaemiengiele", "Sami (Southern)"}),
            Map.entry("smj", new String[]{"julevusámegiella", "Sami (Lule)"}),
            Map.entry("smn", new String[]{"sämikielâ", "Sami (Inari)"}),
            Map.entry("sms", new String[]{"sääʹmǩiõll", "Sami (Skolt)"}),
            Map.entry("sq", new String[]{"shqip", "Albanian"}),
            Map.entry("sr", new String[]{"srpski/српски", "Serbian"}),
            Map.entry("st", new String[]{"Sesotho sa Leboa", "Sesotho"}),
            Map.entry("sv", new String[]{"svenska", "Swedish"}),
            Map.entry("sw", new String[]{"Kiswahili", "Kiswahili"}),
            Map.entry("syc", new String[]{"ܣܘܪܝܝܐ", "Syriac"}),
            Map.entry("ta", new String[]{"தமிழ்", "Tamil"}),
            Map.entry("te", new String[]{"తెలుగు", "Telugu"}),
            Map.entry("tg", new String[]{"Тоҷикӣ", "Tajik"}),
            Map.entry("th", new String[]{"ไทย", "Thai"}),
            Map.entry("tk", new String[]{"türkmençe", "Turkmen"}),
            Map.entry("tn", new String[]{"Setswana", "Tswana"}),
            Map.entry("tr", new String[]{"Türkçe", "Turkish"}),
            Map.entry("tt", new String[]{"Татарча", "Tatar"}),
            Map.entry("tzm", new String[]{"Tamazight", "Tamazight"}),
            Map.entry("ug", new String[]{"ئۇيغۇرچە", "Uyghur"}),
            Map.entry("uk", new String[]{"українська", "Ukrainian"}),
            Map.entry("ur", new String[]{"اُردو", "Urdu"}),
            Map.entry("uz", new String[]{"Uzbek/Ўзбек", "Uzbek"}),
            Map.entry("vi", new String[]{"Tiếng Việt", "Vietnamese"}),
            Map.entry("wo", new String[]{"Wolof", "Wolof"}),
            Map.entry("xh", new String[]{"isiXhosa", "Xhosa"}),
            Map.entry("yo", new String[]{"Yoruba", "Yoruba"}),
            Map.entry("zh", new String[]{"中文", "Chinese"}),
            Map.entry("zu", new String[]{"isiZulu", "Zulu"})
    );

    public Language(String subtag) {
        this(subtag, LANGUAGE_MAP.getOrDefault(subtag, new String[]{"Unknown", "Unknown"})[0],
                LANGUAGE_MAP.getOrDefault(subtag, new String[]{"Unknown", "Unknown"})[1]);
    }
}
