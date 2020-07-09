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
            ///////////////////////
            dictionary.put("Create Admin Account", "Erstellung Administratorkonto");
            dictionary.put("Username", "Nutzername");
            dictionary.put("Password", "Passwort");
            dictionary.put("First name", "Vorname");
            dictionary.put("Last name", "Nachname");
            dictionary.put("Confirm Password", "Passwort Bestätigung");
            dictionary.put("Password Hint", "Passwort Hinweis");
            ///////////////////////
            dictionary.put("Create Account Error", "Kontofehler Erstellung");
            dictionary.put(
                    "A password must be at least 4 characters",
                    "Ein Passwort muss mindestens 4 Zeichen lang sein");
            dictionary.put("Create new Account!", "Das Konto wurde Erstellt!");
            dictionary.put("Your Account has been created!", "Das Konto wurde Erstellt!");
            dictionary.put(
                    "Your password hint must not contain your password & not empty!",
                    "Der Passworthinweis darf das Passwort nicht enthalten und darf nicht leer sein!");
            dictionary.put("Incongruent passwords!", "Inkongruente Passwörter");
            dictionary.put(
                    "Your Email must contain @ and .!", "Die E-Mail muss @ und . enthalten!");
            dictionary.put(
                    "Username already taken or forbidden characters used! A username must contain only alphanumeric characters.",
                    "Benutzername ist bereits vergeben oder verbotene Zeichen verwendet! Benutzername darf nur alphanumerische Buchstaben enthalten.");
            dictionary.put(
                    "Create Account was not possible due to:",
                    "Kontoerstellung ist nicht möglich:");
            ///////////////////////
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
