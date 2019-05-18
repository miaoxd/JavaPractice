import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
/**
 * @since 2014
 *
 */
public class TestLinkedBlockingQueue {

    /**
     * @param args
     */
    public static void main(String[] args) {
        final int thnum = 10;
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<String>();
        final ScheduledExecutorService executors = Executors.newScheduledThreadPool(thnum);
        for (int i=0; i<thnum; i++){
            
            MyThread th = new MyThread("thread-"+i+"-");
            th.setQueue(queue);
            executors.schedule(th, 100, TimeUnit.MILLISECONDS);
        }
        
        while (true){
            String elem;
            try {
                elem = queue.take();
                System.out.println(elem);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
        }
    }

}
