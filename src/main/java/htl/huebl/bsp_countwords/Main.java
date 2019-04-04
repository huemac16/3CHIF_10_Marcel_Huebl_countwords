package htl.huebl.bsp_countwords;

public class Main {

    public static void main(String[] args) {

        MyQueue<Book> queue = new MyQueue<>(3);

        FileProducer producer1 = new FileProducer(queue);
        FileConsumer consumer1 = new FileConsumer(queue);
        FileConsumer consumer2 = new FileConsumer(queue);
        FileConsumer consumer3 = new FileConsumer(queue);

        producer1.setName("pro1");
        producer1.start();

        consumer1.setName("con1");
        consumer1.start();

        consumer2.setName("con2");
        consumer2.start();

        consumer3.setName("con3");
        consumer3.start();
    

    }

}
