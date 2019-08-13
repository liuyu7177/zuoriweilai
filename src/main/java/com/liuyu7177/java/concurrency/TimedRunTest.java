package com.liuyu7177.java.concurrency;

import java.util.concurrent.*;

/**
 * 计时运行
 * Created by liuyu7177 On 2019/6/28
 */
public class TimedRunTest {
    private static final ExecutorService taskExec = Executors.newSingleThreadExecutor();

    public static void timeRun(Runnable r, long timeout, TimeUnit unit) throws InterruptedException {
        Future<?> task = taskExec.submit(r);
        try {
            task.get(timeout, unit);
        } catch (TimeoutException e) {
            task.cancel(true);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            task.cancel(true);
        }
    }
}
