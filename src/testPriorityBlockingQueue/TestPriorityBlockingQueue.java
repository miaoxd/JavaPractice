package testPriorityBlockingQueue;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * if a.compareTo(b) == -1, then a will be inserted ahead of b. A will be poll first 
 * @author exinmia
 * @since 2014
 *
 */
public class TestPriorityBlockingQueue {
    PriorityBlockingQueue<String> queue = new PriorityBlockingQueue<String>();

    /**
     * @param args
     * @throws InterruptedException 
     */
    public static void main(String[] args) throws InterruptedException {
        System.out.println("TestPriorityBlockingQueue");
        TestPriorityBlockingQueue test = new TestPriorityBlockingQueue();
        test.putElementInAlphaOrder();
        test.printQueue();
        test.putElementInInvertedAlphaOrder();
        test.printQueue();
        test.putElementInShuffleAlphaOrder();
        test.printQueue();
        
        
        String a = "a";
        String b = "b";
        System.out.println(a.compareTo(b));
        System.out.println(b.compareTo(a));

    }
    
    public void putElementInAlphaOrder(){
        System.out.println("\nputElementInAlphaOrder");
        queue.put("a");
        queue.put("b");
        queue.put("c");
    }
    
    public void putElementInInvertedAlphaOrder(){
        System.out.println("\nputElementInInvertedAlphaOrder");
        queue.put("f");
        queue.put("d");
        queue.put("c");
    }
    
    public void putElementInShuffleAlphaOrder(){
        System.out.println("\nputElementInShuffleAlphaOrder");
        queue.put("x");
        queue.put("z");
        queue.put("y");
    }
    
    public void printQueue() throws InterruptedException {
        System.out.println("The queue contains:");
        while (!queue.isEmpty()){
            //same to use take() and poll() here
            final String s = queue.take();//queue.poll();
            System.out.println(s);
        }
    }
    
}
