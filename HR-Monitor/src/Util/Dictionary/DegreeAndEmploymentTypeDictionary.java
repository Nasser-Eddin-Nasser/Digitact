package Util.Dictionary;

import java.util.Map;
import java.util.TreeMap;

public class DegreeAndEmploymentTypeDictionary implements IDictionary {
    private Map<String, String> dictionary = new TreeMap<String, String>(); // <English, German>

    public DegreeAndEmploymentTypeDictionary() {
        init();
    }

    @Override
    public void init() {
        if (dictionary.size() == 0) {
        /////////// EmploymentType
        dictionary.put("FullTime", "Vollzeit");
        dictionary.put("PartTime", "Teilzeit");
        dictionary.put("SelfEmployed", "Selbstständig");
        dictionary.put("Freelance", "Freiberuflich");
        dictionary.put("Contract", "Vertrag");
        dictionary.put("Internship", "Praktikum");
        dictionary.put("Apprenticeship", "Ausbildung");
        dictionary.put("Other", "Andere");
        dictionary.put("present", "Zurzeit");
        ////////////////// Degree
        dictionary.put("Master", "Master");
        dictionary.put("Bachelor", "Bachelor");
        dictionary.put("PhD", "PhD");
        dictionary.put("School", "Schule");
    }}

    @Override
    public Map<String, String> getDictionary() {
        return dictionary;
    }
}
