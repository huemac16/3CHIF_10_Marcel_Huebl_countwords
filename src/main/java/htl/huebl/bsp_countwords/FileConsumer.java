package htl.huebl.bsp_countwords;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileConsumer extends Thread {

    private MyQueue<Book> queue;

    public FileConsumer(MyQueue<Book> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            Book b = null;
            synchronized (queue) {
                try {
                    b = queue.get();
                    queue.notifyAll();
                } catch (EmptyException ex) {
                    try {
                        queue.wait();
                    } catch (InterruptedException ex1) {
                        Logger.getLogger(FileConsumer.class.getName()).log(Level.SEVERE, null, ex1);
                    }
                }

            }

            //go on
            String path = "\\outFiles";

            File f = new File(path, b.getInputfilename() + "-output");

            f.getParentFile().mkdirs();
            try {
                f.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(FileConsumer.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            fillFile(f, b);

        }

    }

    private void fillFile(File f, Book b) {
        HashMap<String, Integer> map = b.countWords();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(f))) {
            bw.write("" + map.toString());
            

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
