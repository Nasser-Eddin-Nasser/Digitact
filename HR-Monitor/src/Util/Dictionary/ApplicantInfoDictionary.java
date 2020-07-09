package Util.Dictionary;


import java.util.Map;
import java.util.TreeMap;

public class ApplicantInfoDictionary implements IDictionary {
    private Map<String, String> dictionary = new TreeMap<String, String>(); // <English, German>

    public ApplicantInfoDictionary() {
        init();
    }

    private void init() {
        if (dictionary.size() == 0) {
            // add all values
            dictionary.put("Basic Information", "Grundlegende Informationen");
            dictionary.put("Additional Information", "Zusatzinformationen");
            dictionary.put("Work Experience", "Berufserfahrung");
            dictionary.put("Educational Information", "Bildungsinformation");
            dictionary.put("Key Competencies", "Schlüsselkompetenzen");
            dictionary.put("Fields of Interest", "Interessengebiete");

            dictionary.put("Title", "Titel");
            dictionary.put("Company", "Firma");
            dictionary.put("Employment Type", "Art der Beschäftigung");
            dictionary.put("Start Date", "Startdatum");
            dictionary.put("End Date", "Enddatum");
            dictionary.put("Description", "Beschreibung");

            dictionary.put("University", "Universität");
            dictionary.put("Subject", "Studienfach");
            dictionary.put("Degree", "Studienabschlusses");
            dictionary.put("Grade", "Note");
            dictionary.put("Graduation Year", "Abschlussjahr");

            dictionary.put("Programming Languages", "Programmiersprachen");
            dictionary.put("Bussiness Skills", "Geschäftliche Fähigkeiten");
            dictionary.put("Databases", "Datenbanken");
            dictionary.put("Professional Software", "Professionelle Software");
            dictionary.put("Spoken Langauges", "Gesprochene Sprachen");

            dictionary.put("Position", "Position");
            dictionary.put("Industry", "Industrie");

            dictionary.put("First Name", "Vorname");
            dictionary.put("Second Name", "Nachname");
            dictionary.put("Phone Number", "Telefonnummer");

            dictionary.put("Information", "Informationen");
            dictionary.put("Documents", "Dokumente");
            dictionary.put("Applicant Ratings", "Bewerber-Bewertungen");
        }
    }

    public Map<String, String> getDictionary() {
        return dictionary;
    }
}
