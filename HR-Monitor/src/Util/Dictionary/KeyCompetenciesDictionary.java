package Util.Dictionary;

import java.util.Map;
import java.util.TreeMap;

public class KeyCompetenciesDictionary implements IDictionary {
    private Map<String, String> dictionary = new TreeMap<String, String>(); // <English, German>

    public KeyCompetenciesDictionary() {
        init();
    }

    @Override
    public Map<String, String> getDictionary() {
        return dictionary;
    }

    @Override
    public void init() {
        if (dictionary.size() == 0) {
            dictionary.put("languages", "Sprachen");
            dictionary.put("businessSkills", "Unternehmerische Fähigkeiten");
            dictionary.put("professionalSoftware", "Software-Kenntnisse");
            dictionary.put("database", "Datenbank-Kenntnisse");
            dictionary.put(
                    "programmingLanguagesAndFrameworks", "Programmiersprachen und Frameworks");
            ///////////////////////////////////////// languages
            dictionary.put("Arabic", "Arabisch");
            dictionary.put("Chinese", "Chinesisch");
            dictionary.put("Czech", "Tschechisch");
            dictionary.put("English", "Englisch");
            dictionary.put("Finnish", "Finnisch");
            dictionary.put("French", "Französisch");
            dictionary.put("German", "Deutsch");
            dictionary.put("Greek", "Griechisch");
            dictionary.put("Hungarian", "Ungarisch");
            dictionary.put("Italian", "Italienisch");
            dictionary.put("Japanese", "Japanisch");
            dictionary.put("Korean", "Koreanisch");
            dictionary.put("Norwegian", "Norwegisch");
            dictionary.put("Polish", "Polnisch");
            dictionary.put("Portuguese", "Portugiesisch");
            dictionary.put("Russian", "Russisch");
            dictionary.put("Spanish", "Spanisch");
            dictionary.put("Swedish", "Schwedisch");
            dictionary.put("Turkish", "Türkisch");
            //////////////////////////////////////// businessSkillsItems
            dictionary.put("Project Management", "Projektmanagement");
            dictionary.put("SCRUM", "SCRUM");
            dictionary.put("Accounting & Finance", "Rechnungswesen");
            dictionary.put("Controlling", "Controlling");
            dictionary.put("Production", "Produktion");
            dictionary.put("Human Resources", "Personalmanagement");
            dictionary.put("Marketing", "Marketing");
            dictionary.put("Sales", "Vertrieb");
            dictionary.put("Purchasing and Logistics", "Einkauf und Logistik");
            dictionary.put("Process Design", "Prozessdesign");
        }
    }
}
