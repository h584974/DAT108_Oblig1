package oppgave2;

import java.util.Random;

public class Kunde extends Thread {
	
	@Override
	public void run() {
		Random r = new Random();
		while(true) {
			long tall = (long)((r.nextInt(4) + 2) * 1000);
			try {
				Thread.sleep(tall);
			} catch (InterruptedException e) {}
			
			notifyAll();
		}
	}

}
