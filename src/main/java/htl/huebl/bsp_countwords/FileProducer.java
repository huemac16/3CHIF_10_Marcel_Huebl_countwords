package htl.huebl.bsp_countwords;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

public class FileProducer extends Thread {

    private final MyQueue<Book> books;
    private File[] fileList = new File(".\\files").listFiles();

    public FileProducer(MyQueue<Book> books) {
        this.books = books;
    }

    @Override
    public void run() {

        while (true) {
            String text = "";
            File f = null;
            JFileChooser fc = new JFileChooser(".\\files");

            if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                f = fc.getSelectedFile();
                try (BufferedReader br = new BufferedReader(new FileReader(f))) {
                    String line = "";
                    while ((line = br.readLine()) != null) {
                        text += br.readLine();
                    }
                } catch (Exception e) {
                }
            } else if (fc.showOpenDialog(null) == JFileChooser.CANCEL_OPTION) {
                break;
            }

            synchronized (books) {
                try {
                    books.put(new Book(f.getName(), text));
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

        }
    }

}
