package oppgave2;

import java.util.ArrayList;
import java.util.Random;

public class Kokk extends Thread {
	
	private Rutsjebane rb;
	private Kunde k;
	private ArrayList<String> bestillinger;
	
	public Kokk(Rutsjebane rb, Kunde k, ArrayList<String> bestillinger) {
		super();
		this.rb = rb;
		this.k = k;
		this.bestillinger = bestillinger;
	}
	
	@Override
	public void run() {
		int antall = 0;
		Burger b = null;
		Random r = new Random();
		
		synchronized(rb) {
			while(true) {
				while(bestillinger.isEmpty()) {
					synchronized(bestillinger) {
						try {
							bestillinger.wait();
						} catch (InterruptedException e) {}
					}
				}
				
				long tall = (long)((r.nextInt(4) + 2) * 1000);
				try {
					Thread.sleep(tall);
				} catch (InterruptedException e) {}
				
				
				b = new Burger(antall);
				
				while(rb.erFull()) {
					try {
						rb.wait();
					} catch (InterruptedException e) {}
				}
				
				rb.leggTil(b);
				rb.skrivUt();
				rb.notifyAll();
				
				antall++;
			}
		}
	}

}