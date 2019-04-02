package htl.huebl.bsp_countwords;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileProducer extends Thread {

    private final MyQueue<Book> books;

    public FileProducer(MyQueue<Book> books) {
        this.books = books;
    }

    @Override
    public void run() {

        while (true) {
            File[] fileList = new File("\\files").listFiles();
            String[] texts = new String[fileList.length];

            for (int i = 0; i < fileList.length; i++) {
                String text = "";

                try (BufferedReader br = new BufferedReader(new FileReader(fileList[i]))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        text += line;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                texts[i] = text;
            }

        }
    }

}
