// Custom ThreadFactory class, returns a new thread, with runnable object as parameter

import java.util.concurrent.ThreadFactory;

public class MyThreadFactory implements ThreadFactory {

    public Thread newThread(Runnable r){
        return new Thread(r);
    }
}
