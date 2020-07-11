package Util.Dictionary;

import java.util.Map;
import java.util.TreeMap;

public class BasicInfoDictionary implements IDictionary {
    private Map<String, String> dictionary = new TreeMap<String, String>(); // <English, German>

    public BasicInfoDictionary() {
        init();
    }

    @Override
    public void init() {
        if (dictionary.size() == 0) {
            // add all values
            dictionary.put("title", "Anrede");
            dictionary.put("welcome", "Willkommen");
            dictionary.put("First Name", "Vorname");
            dictionary.put("Last Name", "Nachname");
            dictionary.put("mr", "Herr");
            dictionary.put("mrs", "Frau");
        }
    }

    public Map<String, String> getDictionary() {
        return dictionary;
    }
}
