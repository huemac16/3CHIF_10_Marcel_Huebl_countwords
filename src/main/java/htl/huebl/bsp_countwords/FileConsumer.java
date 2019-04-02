package htl.huebl.bsp_countwords;

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
            
            

        }

    }

}
