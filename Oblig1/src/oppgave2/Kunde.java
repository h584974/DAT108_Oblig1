package oppgave2;

import java.util.ArrayList;
import java.util.Random;

public class Kunde extends Thread {
	
	private ArrayList<String> bestillinger;
	
	public Kunde(ArrayList<String> bestillinger) {
		this.bestillinger = bestillinger;
	}
	
	@Override
	public void run() {
		Random r = new Random();
		while(true) {
			long tall = (long)((r.nextInt(4) + 2) * 1000);
			try {
				Thread.sleep(tall);
			} catch (InterruptedException e) {}
			
			synchronized(bestillinger) {
				bestillinger.add("");
				bestillinger.notifyAll();
			}
		}
	}

}