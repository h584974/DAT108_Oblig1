package oppgave3;

import java.util.concurrent.BlockingQueue;
import java.util.Random;

public class Kokk extends Thread {
	
	private BlockingQueue<Burger> queue;
	private BurgerID burgerID;
	
	public Kokk(String navn, BlockingQueue<Burger> queue, BurgerID burgerID) {
		super(navn);
		this.queue = queue;
		this.burgerID = burgerID;
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
			} catch (InterruptedException e) {}
				
				
			b = new Burger(burgerID.getId());
			burgerID.setId(burgerID.getId() + 1);
				
			try {
				queue.put(b);
				// System.out.println("### " + getName() + " vil legge en hamburger p� rutsjebanen, men den er full. Venter! ###");
			}
			catch (InterruptedException e) {}
				
			System.out.println(getName() + " legger p� en hamburger   (" + b.getNr() + ")   =>   " + queue.toString());
		}
	}

}