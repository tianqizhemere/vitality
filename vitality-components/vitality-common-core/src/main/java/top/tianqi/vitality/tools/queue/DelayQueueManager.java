package top.tianqi.vitality.tools.queue;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 延时队列管理类
 *
 * @Author wkh
 * @Date 2020/8/4 13:45
 */
@Component(value = "delayQueueManger")
public class DelayQueueManager {

    private static final Logger logger = LoggerFactory.getLogger(DelayQueueManager.class);

    /** 默认线程数量*/
    private final static int DEFAULT_THREAD_NUM = 5;
    /** 固定大小线程池*/
    private ExecutorService executor;
    /** 守护线程*/
    private Thread daemonThread;
    /** 延时队列*/
    private DelayQueue<DelayTask<?>> delayQueue;

    public DelayQueueManager() {
        executor = Executors.newFixedThreadPool(DEFAULT_THREAD_NUM);
        delayQueue = new DelayQueue<>();
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        daemonThread = new Thread(execute());
        daemonThread.setName("DelayQueueMonitor");
        daemonThread.start();
    }

    /**
     * 执行任务
     *
     * @return
     */
    private Runnable execute() {
        Runnable run = () -> {
            while (true) {
//                Map<Thread, StackTraceElement[]> map = Thread.getAllStackTraces();
//                System.out.println("当前存活线程数量:" + map.size());
//                int taskNum = delayQueue.size();
//                System.out.println("当前延时任务数量:" + taskNum);
                try {
                    // 从延时队列中获取任务
                    DelayTask<?> delayTask = delayQueue.take();
                    Runnable task = delayTask.getTask();
                    if (null == task) {
                        continue;
                    }
                    // 提交到线程池执行task
                    executor.execute(task);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        return run;
    }

    /**
     * 添加任务
     *
     * @param task 任务类
     */
    public boolean put(DelayTask<?> task) {
        logger.info("添加任务{}", task);
        return delayQueue.add(task);
    }

    /**
     * 是否包含
     *
     * @param task 任务类
     * @return
     */
    public boolean contains(DelayTask<?> task) {
        return delayQueue.contains(task);
    }

    /**
     * 删除任务
     *
     * @param task
     * @return
     */
    public boolean removeTask(DelayTask<?> task) {
        logger.info("清除任务{}", task);
        return delayQueue.remove(task);
    }

    /**
     * 重新加入计时队列
     *
     * @param task 任务类
     * @return
     */
    public boolean reAddQueue(DelayTask<?> task) {
        removeTask(task);
        return put(task);
    }

    /**
     * 队列元素个数
     * @return
     */
    public int getSize() {
        return delayQueue.size();
    }
}
