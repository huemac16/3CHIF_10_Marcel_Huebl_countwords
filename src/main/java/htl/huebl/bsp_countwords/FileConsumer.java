package htl.huebl.bsp_countwords;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
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
                    System.out.println(Thread.currentThread().getName() + " grabbed book");
                } catch (EmptyException ex) {
                    try {
                        queue.wait();
                        System.out.println(Thread.currentThread().getName() + " waits");
                        continue;
                    } catch (InterruptedException ex1) {
                        Logger.getLogger(FileConsumer.class.getName()).log(Level.SEVERE, null, ex1);
                    }
                }

            }

            //go on
            String path = ".\\outFiles";

            File f = new File(path, b.getInputfilename() + "-output.txt");

            f.getParentFile().mkdirs();
            try {
                f.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(FileConsumer.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                writeFile(f, b);
            } catch (Exception ex) {
                Logger.getLogger(FileConsumer.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    private void writeFile(File f, Book b) throws Exception {
        HashMap<String, Integer> map = b.countWords();
        Iterator iterator = map.keySet().iterator();
        PrintWriter writer = new PrintWriter(f, "UTF-8");

        while (iterator.hasNext()) {
            String key = iterator.next().toString();
            String value = map.get(key).toString();
            writer.println(key + ": " + value);
            //System.out.println(key + ": " + value);

        }

        writer.close();

    }

}
