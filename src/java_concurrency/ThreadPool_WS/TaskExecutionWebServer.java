package java_concurrency.ThreadPool_WS;

import org.omg.CORBA.Request;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;


/**
 * @Author: yk
 * @Date: 2019/12/27 17:24
 */
public class TaskExecutionWebServer {
    private static final int NTHREADS = 100;
    private static final int NTHREADS_Max = 200;
    private static final ThreadPoolExecutor exec = new ThreadPoolExecutor(NTHREADS,NTHREADS_Max,0L,TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>());
    // private static final ExecutorService exec = Executors.newFixedThreadPool(NTHREADS);

    public void start() throws IOException {
        ServerSocket socket = new ServerSocket(80);
        while (!exec.isShutdown()) {
            try {
                final Socket conn = socket.accept();
                exec.execute(new Runnable() {
                    @Override
                    public void run() {
                        handleRequest(conn);
                    }
                });
            } catch (RejectedExecutionException e) {
                if (!exec.isShutdown()) {
                    System.out.println("task submission rejected" + e);
                }

            }

        }
    }

    public void stop(){
        exec.shutdown();
    }

    void handleRequest(Socket connection){
        Request req = readRequest(connection);
        if (isShutdownRequest(req)){
            stop();
        }
        else {
            dispatchRequest(req);
        }
    }

    Request readRequest(Socket socket){
        // do something
        return null;
    }

    boolean isShutdownRequest(Request req){
        // do something
        return false;
    }

    void dispatchRequest(Request req){
        // do something
    }

//    public static void main(String[] args) throws IOException {
//        ServerSocket socket = new ServerSocket(80);
//        while (true) {
//            final Socket connection = socket.accept();
//            Runnable task = new Runnable() {
//                @Override
//                public void run() {
//                    handleRequest(connection);
//                }
//            };
//            exec.execute(task);
//        }
//    }
}
