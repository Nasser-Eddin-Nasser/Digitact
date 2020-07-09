package Util.Dictionary;

import java.util.Map;
import java.util.TreeMap;

public class ApplicantDictionary implements IDictionary {
    private Map<String, String> dictionary = new TreeMap<String, String>(); // <English, German>

    public ApplicantDictionary() {
        init();
    }


    @Override
    public Map<String, String> getDictionary() {
        return dictionary;
    }

    @Override
    public void init() {
        dictionary.put("languages", "Sprachen");
        dictionary.put("businessSkills", "Unternehmerische Fähigkeiten");
        dictionary.put("professionalSoftware", "Software-Kenntnisse");
        dictionary.put("database", "Datenbank-Kenntnisse");
        dictionary.put("programmingLanguagesAndFrameworks", "Programmiersprachen und Frameworks"); 
    }

}
