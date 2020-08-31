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
		System.out.println(getName() + " har startet � jobbe");
			
		while(true) {
			
			long tall = (long)((r.nextInt(5) + 2) * 1000);
			try {
				Thread.sleep(tall);
			} 
			catch (InterruptedException e) {}
			
			try {
				b = queue.take();
				// System.out.println("### " + getName() + " vil ta en hamburger, men rutsjebanen er tom. Venter! ###");
			}
			catch (InterruptedException e) {}
			
			System.out.println(getName() + " tar av en hamburger   (" + b.getNr() + ")   =>   " + queue.toString());
			
		}
	}

}