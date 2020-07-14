package Util.Dictionary;

import static Main.App.LANG;

import Model.Language;
import java.util.Map;
import java.util.Objects;

public interface IDictionary {
    void init();

    Map<String, String> getDictionary();

    static String getTranslation(IDictionary dictionary, String english) {
        if (LANG.equals(Language.English)) {
            if (english.contains("TITLE-")) // GUI title
            return english.replace("TITLE-", "");
            return english;
        }

        return getGerman(dictionary, english);
    }

    static String getGerman(IDictionary dictionary, String english) {
        String res = dictionary.getDictionary().get(english);
        return res != null ? res : english;
    }

    static String getEnglish(IDictionary dictionary, String german) {
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
