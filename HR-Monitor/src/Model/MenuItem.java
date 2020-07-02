package Model;

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
        for (MenuItem menuItem : MenuItem.values()) {
            if (menuItem.menuItem.equalsIgnoreCase(text)) {
                return menuItem;
            }
        }
        return null;
    }
}
