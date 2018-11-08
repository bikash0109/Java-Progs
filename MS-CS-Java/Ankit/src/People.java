import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class People implements Runnable {

	static Object o = new Object();
	int id;
	Random r = new Random();
	int source = 0;
	int destination = 0;

	public People(int source, int destination) {
		this.source = source;
		this.destination = destination;
	}

	@Override
	public void run() {
		
		synchronized(o)
		{
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	}
}
