
package htl.huebl.bsp_countwords;


public class Main {

    public static void main(String[] args) {
        MyQueue<Book> queue = new MyQueue<>(3);
        
        FileProducer producer1 = new FileProducer(queue);
        FileConsumer consumer1 = new FileConsumer(queue);
        FileConsumer consumer2 = new FileConsumer(queue);
        
        new Thread(producer1, "Producer 1").start();

        consumer1.setName("Consumer 1");
        consumer1.start();

        consumer2.setName("Consumer 2");
        consumer2.start();

    }

}
