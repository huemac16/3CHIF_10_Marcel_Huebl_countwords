package htl.huebl.bsp_countwords;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileProducer extends Thread {

    private final MyQueue<Book> books;
    private File[] fileList = new File(".\\files").listFiles();
    private int counter = 0;

    public FileProducer(MyQueue<Book> books) {
        this.books = books;
    }

    @Override
    public void run() {

        while (true) {
            String text = "";
            if (counter >= fileList.length) {
                System.out.println("producer finished");
                break;
            }

            try (BufferedReader br = new BufferedReader(new FileReader(fileList[counter]))) {
                String line = "";
                while ((line = br.readLine()) != null) {
                    text += br.readLine();
                }
            } catch (Exception e) {
            }

            synchronized (books) {
                try {
                    books.put(new Book(fileList[counter].getName(), text));
                    books.notifyAll();
                    System.out.println(Thread.currentThread().getName() + " added to queue");
                } catch (FullException ex) {
                    try {
                        books.wait();
                        System.out.println(Thread.currentThread().getName() + " waits");
                    } catch (InterruptedException ex1) {
                        Logger.getLogger(FileProducer.class.getName()).log(Level.SEVERE, null, ex1);
                    }
                }

            }
            counter++;

        }
    }

}
