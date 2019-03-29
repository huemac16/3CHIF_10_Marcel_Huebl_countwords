package htl.huebl.bsp_countwords;

import java.util.LinkedList;

public class MyQueue<T> {

    private final LinkedList<T> data = new LinkedList<>();
    private final int maxSize;

    public MyQueue(int maxSize) {
        this.maxSize = maxSize;
    }

}
