import java.util.concurrent.LinkedBlockingQueue;

/**
 * @since 2014
 *
 */
public class MyThread implements Runnable {
    private LinkedBlockingQueue<String> queue;
    final static int n = 100;
    private String name;
    
    public MyThread(final String name){
        this.name = name;
    }
    
    public LinkedBlockingQueue<String> getQueue() {
        return queue;
    }

    public void setQueue(LinkedBlockingQueue<String> queue) {
        this.queue = queue;
    }

    /* (non-Javadoc)
     * @see java.lang.Runnable#run()
     */
    public void run() {
        putElem();
        
    }
    
    private void putElem(){
        for (int i=0; i<n; i++){
            try {
                queue.put(name + " " +Integer.toString(i));
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
}
