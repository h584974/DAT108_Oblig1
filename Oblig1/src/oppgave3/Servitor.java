package oppgave3;

import java.util.concurrent.BlockingQueue;
import java.util.Random;

public class Servitor extends Thread {
	
	private BlockingQueue<Burger> queue;
	
	public Servitor(String navn, BlockingQueue<Burger> queue) {
		super(navn);
		this.queue = queue;
	}
	
	@Override
	public void run() {
		Burger b = null;
		Random r = new Random();
		System.out.println(getName() + " har startet å jobbe");
			
		while(true) {
			
			long tall = (long)((r.nextInt(5) + 2) * 1000);
			try {
				Thread.sleep(tall);
			} 
			catch (InterruptedException e) {}
			
			try {
				b = queue.take();
				String s = queue.toString();
				System.out.println(getName() + " tar av en hamburger   (" + b.getNr() + ")   =>   " + s);
			}
			catch (InterruptedException e) {}	
			
		}
	}

}