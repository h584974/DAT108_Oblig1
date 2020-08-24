package oppgave2;

import java.util.Random;

public class Kokk extends Thread {
	
	private Rutsjebane rb;
	private Kunde k;
	
	public Kokk(Rutsjebane rb, Kunde k) {
		super();
		this.rb = rb;
		this.k = k;
	}
	
	@Override
	public void run() {
		int antall = 0;
		Burger b = null;
		Random r = new Random();
		
		synchronized(rb) {
			while(true) {
				try {
					k.wait();
				} catch (InterruptedException e1) {}
				
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
				rb.notifyAll();
				
				antall++;
			}
		}
	}

}