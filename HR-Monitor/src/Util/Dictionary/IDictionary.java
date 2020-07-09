package Util.Dictionary;

import static Main.App.LANG;

import Model.Language;
import java.util.Map;
import java.util.Objects;

public interface IDictionary {

    Map<String, String> getDictionary();

    public static String getTranslation(IDictionary dictionary, String english) {
        if (LANG.equals(Language.English)) {
            if (english.contains("TITLE-")) // GUI title
            return english.replace("TITLE-", "");
            return english;
        }

        return getGerman(dictionary, english);
    }

    private static String getGerman(IDictionary dictionary, String english) {
        return dictionary.getDictionary().get(english);
    }

    private static String getEnglish(IDictionary dictionary, String german) {
        return dictionary
                .getDictionary()
                .entrySet()
                .stream()
                .filter(entry -> Objects.equals(entry.getValue(), german))
                .map(Map.Entry::getKey)
                .findAny()
                .get();
    }
}
