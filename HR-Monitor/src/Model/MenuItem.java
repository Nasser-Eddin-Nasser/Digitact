package Model;

import static Main.App.LANG;

import Util.Dictionary.IDictionary;
import Util.Dictionary.MenuDictionary;

public enum MenuItem {
    Applicants("Applicants"),
    CreateAccount("Create Account"),
    Logout("Logout");
    private String menuItem;

    MenuItem(String menuItem) {
        this.menuItem = menuItem;
    }

    public String getMenuItem() {
        return menuItem;
    }

    public static MenuItem fromString(String text) {
        if (LANG.equals(Language.German)) {
            text = IDictionary.getEnglish(new MenuDictionary(), text);
        }
        for (MenuItem menuItem : MenuItem.values()) {
            if (menuItem.menuItem.equalsIgnoreCase(text)) {
                return menuItem;
            }
        }
        return Applicants; // by Default
    }
}
