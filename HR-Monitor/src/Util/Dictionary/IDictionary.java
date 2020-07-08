package Util;

public interface IDictionary {
    public String getEnglish(String german);
    public String getGerman(String english);
//    private static Map<String, String> dictionary = new TreeMap<String, String>(); //<English, German>
//
//    public static String getGerman(String english) {
//        init();
//        return dictionary.get(english);
//    }
//
//    public static String getEnglish(String german) {
//        init();
//        return dictionary.entrySet()
//                .stream()
//                .filter(entry -> Objects.equals(entry.getValue(), german))
//                .map(Map.Entry::getKey).findAny().get();
//    }
//
//
//    private static void init() {
//        if (dictionary.size() == 0) {
//            // add all values
//            dictionary.put("title", "Anrede");
//            dictionary.put("welcome", "Willkommen");
//            dictionary.put("firstName", "Vorname");
//            dictionary.put("lastName", "Nachname");
//            dictionary.put("mr", "Herr");
//            dictionary.put("mrs", "Frau");
//        }
//    }

}
