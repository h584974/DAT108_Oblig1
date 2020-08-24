package oppgave2;

import java.util.ArrayList;
import java.util.Random;

public class Servitor extends Thread {
	
	private ArrayList<String> bestillinger;
	private Rutsjebane rb;
	private Object servitorLock;
	private Object kokkLock;
	
	public Servitor(String navn, Rutsjebane rb, ArrayList<String> bestillinger, Object kokkLock, Object servitorLock) {
		super(navn);
		this.rb = rb;
		this.bestillinger = bestillinger;
		this.kokkLock = kokkLock;
		this.servitorLock = servitorLock;
	}
	
	@Override
	public void run() {
		Random r = new Random();
		System.out.println(getName() + " har startet å jobbe");
			
		while(true) {
			long tall = (long)((r.nextInt(4) + 2) * 1000);
			try {
				Thread.sleep(tall);
			} catch (InterruptedException e) {}
			
			synchronized(bestillinger) {
				bestillinger.add("1");
				bestillinger.notifyAll();
				System.out.println("Bestilling sendt");
			}
			
			while(rb.erTom()) {
				try {
					synchronized(servitorLock) {
						servitorLock.wait();
					}
				} catch (InterruptedException e) {}
			}
			
			System.out.println(rb.taUt().getNr());
			synchronized(kokkLock) {
				kokkLock.notifyAll();
			}
		}
	}

}