package htl.huebl.bsp_countwords;

import java.util.HashMap;

public class Book {

    private String inputfilename;
    private String text;

    public Book(String inputfilename, String text) {
        this.inputfilename = inputfilename;
        this.text = text;
    }

    public String getInputfilename() {
        return inputfilename;
    }

    public HashMap<String, Integer> countWords() {
        HashMap<String, Integer> map = new HashMap<>();

        text = text.replaceAll(",", ";");
        text = text.replaceAll("\\s+", ";");

        String[] words = text.split(";");

        for (int i = 0; i < words.length; i++) {
            if (counter(words, words[i]) >= 2) {
                map.put(words[i], counter(words, words[i]));
            }

        }

        return map;
    }

    public int counter(String[] words, String word) {
        int counter = 0;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word)) {
                counter++;
            }
        }

        return counter;
    }

}
