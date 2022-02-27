import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * jstack分析
 * CPU过高demo
 * @author liangxifeng
 * @date 2021-08-09
 */
public class JstackCPUHeightCase {
    private static ExecutorService executorService = Executors.newFixedThreadPool(5);
    public static Object lock = new Object();

    static class Task implements Runnable{
        public void run() {
            synchronized (lock){
                long sum = 0L;
                while (true){
                    sum += 1;
                }
            }
        }
    }

    public static void main(String[] args) {
        Task task1 = new Task();
        Task task2 = new Task();
        executorService.execute(task1);
        executorService.execute(task2);
    }
}
