package Util.Dictionary;

import java.util.Map;
import java.util.TreeMap;

public class ACDictionary implements IDictionary {
    private Map<String, String> dictionary = new TreeMap<String, String>(); // <English, German>

    public ACDictionary() {
        init();
    }

    private void init() {
        if (dictionary.size() == 0) {
            // add all values
            dictionary.put("TITLE-Login", "Anmeldung"); // login
            dictionary.put("Login", "Anmelden");
            dictionary.put("USERNAME:", "NUTZERNAME:"); // Username Nutzername
            dictionary.put("PASSWORD:", "PASSWORT:"); // Password  Passwort
            dictionary.put("Forgot password?", "Passwort vergessen?"); // Passwort vergessen
            dictionary.put("Your personal password hint:", "Dein persönlicher Passworthinweis:");
            dictionary.put("Password Hint", "Passwort Hinweis");
            dictionary.put("Login Error", "Anmeldungsfehler");
            dictionary.put("Login was not possible due to:", "Anmeldung ist nicht möglich:");
            dictionary.put("UserName or Password WRONG!", "Benutzername oder Passwort Inkorrekt!");
            dictionary.put("Connection Error", "Verbindungsfehler");
            dictionary.put(
                    "Please check your connection with BES then start the Application again!",
                    "Bitte überprüfst Du die Verbindung mit BES und Start den HR-Monitor!");
        }
    }

    public Map<String, String> getDictionary() {
        return dictionary;
    }
}
