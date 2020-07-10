package Util.Dictionary;

import java.util.Map;
import java.util.TreeMap;

public class PositionsAndIndustriesDictionary implements IDictionary {
    private Map<String, String> dictionary = new TreeMap<String, String>(); // <English, German>

    public PositionsAndIndustriesDictionary() {
        init();
    }

    @Override
    public void init() {
        if (dictionary.size() == 0) {
            ///////////// Positions
            dictionary.put("Business Consultant", "Consultant/Business Consultant");
            dictionary.put(
                    "IT Consultant - Informationsmanagement",
                    "IT-Consultant - Informationsmanagement");
            dictionary.put("IT Consultant - Java JEE", "IT-Consultant - Java/JEE");
            dictionary.put(
                    "IT Consultant - Data Science",
                    "IT-Consultant - Data Science / Artificial Intelligence");
            dictionary.put("Consultant - SAP", "Consultant SAP");
            dictionary.put("Internship/Working Student", "Praktikant/Werkstudent");
            dictionary.put("Others", "Nutzername");
            ///////////// Positions
            dictionary.put("Automotive", "Automobil");
            dictionary.put("Finance", "Finanzen");
            dictionary.put("Commerce", "Handel");
            dictionary.put("Pharma Healthcare", "Pharma/Gesundheitswesen");
            dictionary.put("Public Sector", "Öffentlicher Sektor");
        }
    }

    @Override
    public Map<String, String> getDictionary() {
        return dictionary;
    }
}
