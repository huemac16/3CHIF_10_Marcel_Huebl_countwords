/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package htl.huebl.bsp_countwords;

import java.util.HashMap;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Marcel
 */
public class BookTest {

    public BookTest() {
    }

    /**
     * Test of countWords method, of class Book.
     */
    @Test
    public void testCountWords() {
        String text = "Das ist ein Beispiel hier. Ein Beispiel ist das. Hoffentlich"
                + " wird dieser Test funktionieren";

        HashMap<String, Integer> expectedMap = new HashMap<>();

        expectedMap.put("ist", 2);
        expectedMap.put("Beispiel", 2);

        Book b = new Book("text.txt", text);

        HashMap<String, Integer> map = b.countWords();

        assertEquals(expectedMap.toString(), map.toString(), "should be equals");
    }

    /**
     * Test of counter method, of class Book.
     */
    @Test
    public void testCounter() {
        Book b = new Book("test.txt", "test 123 124,  !");

        String[] words = new String[]{"word1", "word2", "word1", "word3", "word4", "word3",
            "word3", "word5", "word5"};

        int erg1 = b.counter(words, "word3");
        int erg2 = b.counter(words, "word5");

        assertEquals(3, erg1, "Solution must be 3");
        assertEquals(2, erg2, "Solution must be 2");

    }

}
