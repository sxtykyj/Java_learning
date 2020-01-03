package java_concurrency.ThreadPool_WS;

import java.util.concurrent.Executor;

/**
 * @Author: yk
 * @Date: 2019/12/27 17:30
 */
public class WithThreadExecutor implements Executor {
    /**
     * 应使用Executor代替Thread（eg. new Thread(runnable).start()） -> 可使执行策略更灵活
     *
     * 执行策略：
     *     1. 任务在什么（what）线程中执行？
     *     2. 任务以什么（what）顺序执行（FIFO,LIFO,优先级）？
     *     3. 可以有多少个（how many）任务并发执行？
     *     4. 可以有多少个（how many）任务进入等待执行队列？
     *     5. 如果系统过载，需要放弃一个任务，应该挑选哪一个（which）任务？另外，如何（how）通知应用程序知道这一切？
     *     6. 在一个任务执行前和结束后，应该做什么（what）处理？
     *
     * Executor：异步地执行任务
     *   1. 生命周期：
     *       运行（running）
     *          * ExecutorService创建后的初始状态
     *       关闭（shutting down）
     *          * shutdown方法    ：启动平缓的关闭过程 -> 停止接受新任务，同时等待已经提交任务完成
     *            shutdownNow方法 ：启动强制的关闭过程 -> 尝试取消所有运行中和队列中尚未开始的任务
     *          * 关闭后提交到ExecutorService的任务会被拒绝执行处理器（rejected execution handler）处理
     *       终止（terminated）
     *          * 所有任务全部完成后会到达终止状态
     */
    @Override
    public void execute(Runnable r) {
        r.run();
    }
}
