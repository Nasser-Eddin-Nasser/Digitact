package Util.Dictionary;

import java.util.Map;
import java.util.TreeMap;

public class MenuDictionary implements IDictionary {
    private Map<String, String> dictionary = new TreeMap<String, String>(); // <English, German>

    public MenuDictionary() {
        init();
    }

    @Override
    public void init() {
        if (dictionary.size() == 0) {
            /////////////////////// Menu
            dictionary.put("Logout", "Abmelden");
            dictionary.put("Hello", "Hallo");
            dictionary.put("Applicants", "Bewerber");
            dictionary.put("Create Account", "Konto Erstellen");
        }
    }

    public Map<String, String> getDictionary() {
        return dictionary;
    }
}
